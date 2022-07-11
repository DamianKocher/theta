package com.damiankocher.theta.server.audio;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TextReplacer {

    private static final @NotNull Logger log = LoggerFactory.getLogger(TextReplacer.class);

    private final @NotNull Map<String, String> replacements;

    public TextReplacer(final @NotNull Map<String, String> replacements) {
        this.replacements = replacements;
    }

    public @NotNull String replaceText(final @NotNull String originalText) {
        String text = originalText;
        for (final Map.Entry<String, String> entry : replacements.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text;
    }

    public void logReplacements() {
        for (final Map.Entry<String, String> entry : replacements.entrySet()) {
            log.info("TEXT REPLACEMENT: {} -> {}", entry.getKey(), entry.getValue());
        }
    }
}
