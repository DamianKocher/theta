package com.damiankocher.theta.server.video.sections.standard;

import com.damiankocher.theta.server.video.Section;
import org.jetbrains.annotations.NotNull;

public class Image extends Section {

    private @NotNull String url;

    public Image(final @NotNull String url, final int duration) {
        super("std_image", false, false, false, false, false, duration);

        this.url = url;
    }

    public String url() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void updateDescription() {
        this.setDescription(url());
    }
}
