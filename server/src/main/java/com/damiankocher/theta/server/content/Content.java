package com.damiankocher.theta.server.content;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Content {

    private final @NotNull String background;
    private final double totalDuration;

    private final @NotNull List<Section> sections;

    public Content(
            @NotNull String background,
            @NotNull List<Section> sections
    ) {
        this.background = background;
        this.sections = sections;

        this.totalDuration = sections.stream().map(Section::duration).reduce(Double::sum).orElse(0.0d);
    }

    public @NotNull String background() {
        return background;
    }

    public @NotNull List<Section> sections() {
        return sections;
    }

    public double totalDuration() {
        return totalDuration;
    }
}
