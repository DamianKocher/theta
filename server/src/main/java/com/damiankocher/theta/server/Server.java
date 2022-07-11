package com.damiankocher.theta.server;

import com.damiankocher.theta.server.audio.AudioSource;
import com.damiankocher.theta.server.content.Section;
import com.damiankocher.theta.server.script.ScriptManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class Server {

	private static final @NotNull Logger log = LoggerFactory.getLogger(Server.class);

	private final @NotNull Theta theta;

	private final @NotNull Javalin app;
	private final @NotNull Gson gson;

	private boolean running;

	public Server(final @NotNull Theta theta) {
		this.theta = theta;

		this.gson = createGsonInstance();
		this.app = Javalin.create(JavalinConfig::enableCorsForAllOrigins);

		registerBackgroundHandler();
		registerAudioHandler();
		registerContentHandler();

		registerScriptHandler();

		app.get("/*", ctx -> ctx.result("hey :)"));
	}

	private static Gson createGsonInstance() {
		return new GsonBuilder()
				.registerTypeAdapter(Section.class, Section.createSerializer())
				.registerTypeAdapter(AudioSource.class, AudioSource.createSerializer())
				.create();
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

	private void registerBackgroundHandler() {
		app.get("/bg/{id}", ctx -> {
			final var id = ctx.pathParam("id");

			// TODO:
			ctx.contentType("video/mp4").result(new byte[0]);
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

	private void registerContentHandler() {
		app.get("/content/{id}", ctx -> {
			final var id = ctx.pathParam("id");

			final var contentManager = theta.contentManager();
			final var content = contentManager.getContent(id);
			if (content == null) {
				ctx.status(404).result(new byte[0]);
				return;
			}

			final var result = gson.toJson(content);

			ctx.contentType("application/json").result(result);
		});
	}

	private void registerScriptHandler() {
		app.get("scripts", ctx -> {
			ScriptManager scriptManager = theta.scriptManager();

			final JsonObject jsonResponse = new JsonObject();

			jsonResponse.add("invalid", createJsonArrayFromSet(scriptManager.invalidScriptNames()));
			jsonResponse.add("valid", createJsonArrayFromSet(scriptManager.validScriptNames()));

			ctx.contentType("application/json").result(jsonResponse.toString());
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
