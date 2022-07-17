package com.damiankocher.theta.server.video.sections.reddit;

import com.damiankocher.theta.server.audio.AudioManager;
import com.damiankocher.theta.server.audio.AudioSource;
import com.damiankocher.theta.server.video.Section;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RedditComment extends Section {

    private boolean showHeader;
    private boolean showFooter;

    private int commentDepth = 0;
    private @NotNull String username = "unknown";
    private @NotNull String timestamp = "unknown";
    private int score = -1;

    private @NotNull String text = "";
    private @Nullable AudioSource audio;

    public RedditComment() {
        super("reddit_comment", true, false, false, true, true, 0);
    }

    public RedditComment(final @NotNull AudioManager audioManager, final @NotNull String text) {
        this();

        setText(audioManager, text);
    }

    public boolean showHeader() {
        return showHeader;
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public boolean showFooter() {
        return showFooter;
    }

    public void setShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
    }

    public int commentDepth() {
        return commentDepth;
    }

    public void setCommentDepth(int commentDepth) {
        this.commentDepth = commentDepth;
    }

    public String username() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public String timestamp() {
        return timestamp;
    }

    public void setTimestamp(@NotNull String timestamp) {
        this.timestamp = timestamp;
    }

    public int score() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
