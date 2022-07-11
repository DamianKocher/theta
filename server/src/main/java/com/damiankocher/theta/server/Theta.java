package com.damiankocher.theta.server;

import com.damiankocher.theta.server.audio.AudioManager;
import com.damiankocher.theta.server.content.ContentManager;
import com.damiankocher.theta.server.script.ScriptManager;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;

public class Theta {

	private final @NotNull Config config;

	private final @NotNull ScriptManager scriptManager;
	private final @NotNull ContentManager contentManager;
	private final @NotNull AudioManager audioManager;
	private final @NotNull Server server;

	public Theta(final @NotNull Config config) throws IOException {
		this.config = config;

		this.scriptManager = new ScriptManager(this);
		this.audioManager = new AudioManager(this);
		this.contentManager = new ContentManager(this);

		this.server = new Server(this);
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

		Theta instance = new Theta(config);
		instance.server().start();
	}

	public Config config() {
		return config;
	}

	public ScriptManager scriptManager() {
		return scriptManager;
	}

	public ContentManager contentManager() {
		return contentManager;
	}

	public AudioManager audioManager() {
		return audioManager;
	}

	public Server server() {
		return server;
	}
}
