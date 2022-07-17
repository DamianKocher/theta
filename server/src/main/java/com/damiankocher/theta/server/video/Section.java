package com.damiankocher.theta.server.video;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import org.jetbrains.annotations.NotNull;

public abstract class Section {

    private final @NotNull String type;
    private boolean useStandardContainer;
    private boolean showPrevious;
    private boolean showNext;
    private boolean fadeIn;
    private boolean fadeOut;
    private double duration;

    private @NotNull String description = "no description";

    public Section(final @NotNull String type, final boolean useStandardContainer, final boolean showPrevious, final boolean showNext, final boolean fadeIn, final boolean fadeOut, final int duration) {
        this.type = type;
        this.useStandardContainer = useStandardContainer;
        this.showPrevious = showPrevious;
        this.showNext = showNext;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.duration = duration;
    }

    public static @NotNull JsonSerializer<Section> createSerializer() {
        return (section, typeOfSrc, context) -> {
            if (section == null) {
                return null;
            }

            final var jsonSection = context.serialize(section);

            final var jsonSectionObject = jsonSection.getAsJsonObject();
            JsonObject jsonMeta = new JsonObject();

            addMetaString(jsonSectionObject, jsonMeta, "type", section.type);
            addMetaBoolean(jsonSectionObject, jsonMeta, "useStandardContainer", section.useStandardContainer());
            addMetaBoolean(jsonSectionObject, jsonMeta, "showPrevious", section.showPrevious());
            addMetaBoolean(jsonSectionObject, jsonMeta, "showNext", section.showNext());
            addMetaBoolean(jsonSectionObject, jsonMeta, "fadeIn", section.fadeIn());
            addMetaBoolean(jsonSectionObject, jsonMeta, "fadeOut", section.fadeOut());
            addMetaDouble(jsonSectionObject, jsonMeta, "duration", section.duration());
            addMetaString(jsonSectionObject, jsonMeta, "description", section.description());

            jsonSectionObject.add("_meta", jsonMeta);

            return jsonSection;
        };
    }

    private static void addMetaString(final @NotNull JsonObject jsonSection, final @NotNull JsonObject jsonMeta, final @NotNull String key, String value) {
        jsonSection.remove(key);
        jsonMeta.addProperty(key, value);
    }

    private static void addMetaBoolean(final @NotNull JsonObject jsonSection, final @NotNull JsonObject jsonMeta, final @NotNull String key, boolean value) {
        jsonSection.remove(key);
        jsonMeta.addProperty(key, value);
    }

    private static void addMetaDouble(final @NotNull JsonObject jsonSection, final @NotNull JsonObject jsonMeta, final @NotNull String key, double value) {
        jsonSection.remove(key);
        jsonMeta.addProperty(key, value);
    }

    public boolean useStandardContainer() {
        return useStandardContainer;
    }

    public void setUseStandardContainer(boolean useStandardContainer) {
        this.useStandardContainer = useStandardContainer;
    }

    public boolean showPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean showNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean fadeIn() {
        return fadeIn;
    }

    public void setFadeIn(boolean fadeIn) {
        this.fadeIn = fadeIn;
    }

    public boolean fadeOut() {
        return fadeOut;
    }

    public void setFadeOut(boolean fadeOut) {
        this.fadeOut = fadeOut;
    }

    public double duration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
