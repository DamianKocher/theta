package com.damiankocher.theta.server.video;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class Video {

    private final @NotNull List<Section> sections = new ArrayList<>();

    private @NotNull String background;
    private double totalDuration;

    public Video(@NotNull String background) {
        this.background = background;
    }

    public void addSection(final @NotNull Section section) {
        sections.add(section);
        calculateTotalDuration();
    }

    public void removeSection(final @NotNull Section section) {
        sections.remove(section);
        calculateTotalDuration();
    }

    void calculateTotalDuration() {
        totalDuration = sections.stream().map(Section::duration).reduce(Double::sum).orElse(0.0d);
    }

    public @NotNull String background() {
        return background;
    }

    public void setBackground(final @NotNull String background) {
        this.background = background;
    }

    public double totalDuration() {
        return totalDuration;
    }
}
