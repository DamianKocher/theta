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

    private @Nullable AudioSource audio;

    public RedditComment() {
        super("reddit_comment", true, false, false, true, true, 0);
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

    public void setAudio(final @NotNull AudioSource audio) {
        this.audio = audio;
        setDuration(audio.duration());
    }

    public @Nullable AudioSource audio() {
        return audio;
    }

    @Override
    protected void updateDescription() {
        String _text = audio().text().substring(0, Math.min(audio.text().length(), 32));

        String _username = this.username();

        this.setDescription(String.format("u/%s - %s", _username, _text));
    }
}
