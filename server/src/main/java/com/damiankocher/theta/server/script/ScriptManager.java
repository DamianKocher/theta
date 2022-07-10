package com.damiankocher.theta.server.script;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ScriptManager {
	
	private static final @NotNull Logger log = LoggerFactory.getLogger(ScriptManager.class);

	private final @NotNull Set<String> invalidScripts = new HashSet<>();
	private final @NotNull Map<String, Script> validScripts = new HashMap<>();

	private final @NotNull Path scriptDirectory;

	public ScriptManager(final @NotNull Path scriptsDirectory) {
		this.scriptDirectory = scriptsDirectory;
	}

	public void loadScripts() throws IOException {
		invalidScripts.clear();
		validScripts.clear();

		try (final var stream = Files.walk(scriptDirectory)) {
			stream.filter(Files::isRegularFile).forEach(this::loadScript);
		}
	}

	private void loadScript(final @NotNull Path path) {
		final var scriptName = path.getFileName().toString();

		try (final var bufferedReader = Files.newBufferedReader(path)) {
			final var gson = new Gson();

			final var script = gson.fromJson(bufferedReader, Script.class);
			validScripts.put(scriptName, script);
		} catch (Exception e) {
			log.warn("Failed to load script from path: {}", path, e);
			invalidScripts.add(scriptName);
		}
	}

	public Set<String> invalidScriptNames() {
		return Collections.unmodifiableSet(invalidScripts);
	}

	public Set<String> validScriptNames() {
		return validScripts.keySet();
	}

	public Script getScript(String name) {
		return validScripts.get(name);
	}
}
