package com.damiankocher.theta.server;

import com.damiankocher.theta.server.audio.AudioManager;
import com.damiankocher.theta.server.content.ContentManager;
import com.damiankocher.theta.server.script.Script;
import com.damiankocher.theta.server.script.ScriptManager;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Theta {

	public Theta(final @NotNull Config config) throws IOException {
//		final var script = parseScript(config.scriptsDirectory());

		final var scriptManager = new ScriptManager(config.scriptsDirectory());
		final var audioManager = new AudioManager(config.audioCacheDirectory(), config.textReplacementPath());
		final var contentManager = new ContentManager();
		final var server = new Server(scriptManager, contentManager, audioManager);

//		final var content = Content.fromScript(script, audioManager, config.backgroundDirectory());

		server.start(config.port());
	}

	public static void main(String[] args) throws IOException {
		final var dataPath = Path.of("data");

		final var config = new Config.Builder()
				.setScriptsDirectory(dataPath.resolve("scripts"))
				.setBackgroundDirectory(dataPath.resolve("backgrounds"))
				.setAudioCacheDirectory(dataPath.resolve("audio_cache"))
				.setTextReplacementPath(dataPath.resolve("text-replacements.json"))
				.setPort(5000)
				.create();

		new Theta(config);
	}

	private @NotNull Script parseScript(final @NotNull Path scriptPath) throws IOException {
		final var reader = Files.newBufferedReader(scriptPath);
		return new Gson().fromJson(reader, Script.class);
	}
}
