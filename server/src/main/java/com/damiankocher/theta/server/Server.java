package com.damiankocher.theta.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.util.Collections;
import java.util.Set;

public class Server {

    private static final @NotNull Logger log = LoggerFactory.getLogger(Server.class);

    private final @NotNull Theta theta;

    private final @NotNull Javalin app;

    private boolean running;

    public Server(final @NotNull Theta theta) {
        this.theta = theta;
        this.app = Javalin.create(JavalinConfig::enableCorsForAllOrigins);

        registerImageHandler();
        registerBackgroundHandler();
        registerAudioHandler();
        registerVideoHandler();

        app.get("/*", ctx -> ctx.result("hey :)"));
    }

    public void start() {
        if (running) throw new IllegalStateException("server is already running");
        running = true;

        log.info("starting server");

        final var port = theta.config().port();
        app.start(port);
    }

    public void stop() {
        if (!running) throw new IllegalStateException("server is not running");
        running = false;

        app.stop();
    }

    private void registerImageHandler() {
        app.get("/img/{id}", ctx -> {
            final var id = ctx.pathParam("id");

            ctx.contentType("image").result("");
        });
    }

    private void registerBackgroundHandler() {
        app.get("/bg/{id}", ctx -> {
            final var id = ctx.pathParam("id");

            final var path = theta.config().backgroundDirectory().resolve(id);
            final var bytes = Files.readAllBytes(path);

            ctx.contentType("video/mp4").result(bytes);
        });
    }

    private void registerAudioHandler() {
        app.get("/audio/{id}", ctx -> {
            final var id = ctx.pathParam("id");

            final var audioManager = theta.audioManager();
            final var audioSource = audioManager.getAudioSource(id);
            if (audioSource == null) {
                ctx.status(404).result(new byte[0]);
                return;
            }

            final var result = audioSource.data();

            ctx.contentType("audio/mpeg").result(result);
        });
    }

    private void registerVideoHandler() {
        app.get("/video/{id}", ctx -> {
            final var id = ctx.pathParam("id");

            final var contentManager = theta.videoManager();
            final var content = contentManager.getVideo(id);
            if (content == null) {
                ctx.status(404).result(new byte[0]);
                return;
            }

            final var gson = theta.gson();
            final var result = gson.toJson(content);

            ctx.contentType("application/json").result(result);
        });

        app.get("/videos", ctx -> {
            final var videoManager = theta.videoManager();
            final var videos = videoManager.videoIds();

            final var response = new JsonObject();

            response.add("validIds", createJsonArrayFromSet(videos));
            response.add("invalidIds", createJsonArrayFromSet(Collections.emptySet()));

            ctx.contentType("application/json").result(response.toString());
        });
    }

    private JsonArray createJsonArrayFromSet(final @NotNull Set<String> set) {
        final var jsonArray = new JsonArray();
        for (final var element : set) {
            jsonArray.add(element);
        }
        return jsonArray;
    }
}
