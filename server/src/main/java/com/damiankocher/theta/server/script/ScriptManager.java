package com.damiankocher.theta.server.script;

import com.damiankocher.theta.server.Theta;
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

    private final @NotNull Theta theta;

    private final @NotNull Set<String> invalidScripts = new HashSet<>();
    private final @NotNull Map<String, ContentGenerator> validScripts = new HashMap<>();

    public ScriptManager(final @NotNull Theta theta) {
        this.theta = theta;
    }

    public void loadScripts() throws IOException {
        invalidScripts.clear();
        validScripts.clear();

        final var scriptsDirectory = theta.config().scriptsDirectory();

        try (final var stream = Files.walk(scriptsDirectory)) {
            stream.filter(Files::isRegularFile).forEach(this::loadScript);
        }
    }

    private void loadScript(final @NotNull Path path) {
        final var scriptName = path.getFileName().toString();

        try (final var bufferedReader = Files.newBufferedReader(path)) {
            final var gson = new Gson();

            final var script = gson.fromJson(bufferedReader, ContentGenerator.class);
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

    public ContentGenerator getScript(String name) {
        return validScripts.get(name);
    }
}
