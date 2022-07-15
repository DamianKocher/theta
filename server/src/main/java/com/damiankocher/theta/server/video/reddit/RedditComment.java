package com.damiankocher.theta.server.video.reddit;

import com.damiankocher.theta.server.audio.AudioManager;
import com.damiankocher.theta.server.audio.AudioSource;
import com.damiankocher.theta.server.video.Section;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RedditComment extends Section {

    private @NotNull String text = "";
    private @Nullable AudioSource audio;

    public RedditComment() {
        super("reddit_comment", true, false, false, true, true, 0);
    }

    public RedditComment(final @NotNull AudioManager audioManager, final @NotNull String text) {
        this();

        setText(audioManager, text);
    }

    public void setText(final @NotNull AudioManager audioManager, final @NotNull String text) {
        this.text = text;
        this.audio = audioManager.createAudioSource(text);

        setDuration(audio.duration());
    }

    public @NotNull String text() {
        return text;
    }

    public @Nullable AudioSource audio() {
        return audio;
    }
}
