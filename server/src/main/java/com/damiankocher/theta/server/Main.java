package com.damiankocher.theta.server;

import com.damiankocher.theta.server.audio.AudioManager;
import com.damiankocher.theta.server.content.Content;
import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

	private static final @NotNull Logger log = LoggerFactory.getLogger(Main.class);

	private static final int SERVER_PORT = 5000;

	public Main(final @NotNull Config config) throws IOException {
		final var script = parseScript(config.scriptPath());
		final var audioManager = new AudioManager(config.audioCacheDirectory(), config.textReplacementPath());

		final var content = Content.fromScript(script, audioManager, config.backgroundDirectory());

		log.info("starting server");
		Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(SERVER_PORT);

		app.get("bg/" + content.background().url(), ctx -> ctx.contentType("video/mp4").result(content.background().data()));

		for (String audioName : audioManager.getAudioNames()) {
			app.get("/" + audioName, ctx -> ctx.contentType("audio/mpeg").result(audioManager.getAudioSource(audioName).data()));
		}

		final var jsonSerializer = Content.createJsonSerializer();
		app.get("/content", ctx -> ctx.contentType("application/json").result(jsonSerializer.toJson(content)));
	}

	public static void main(String[] args) throws IOException {
		final var dataPath = Path.of("data");

		final var config = new Config.Builder()
				.setBackgroundDirectory(dataPath.resolve("backgrounds"))
				.setAudioCacheDirectory(dataPath.resolve("cache"))
				.setTextReplacementPath(dataPath.resolve("text-replacements.json"))
				.setScriptPath(dataPath.resolve("script.json"))
				.setPort(5000)
				.create();

		new Main(config);
	}

	private @NotNull Script parseScript(final @NotNull Path scriptPath) throws IOException {
		final var reader = Files.newBufferedReader(scriptPath);
		return new Gson().fromJson(reader, Script.class);
	}
}
