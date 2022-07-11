package com.damiankocher.theta.server.content.reddit;

import com.damiankocher.theta.server.audio.AudioManager;
import com.damiankocher.theta.server.audio.AudioSource;
import com.damiankocher.theta.server.content.Section;
import org.jetbrains.annotations.NotNull;

public class RedditTitleCard extends Section {

    private String subreddit;
    private String username;
    private String text;
    private AudioSource audio;
    private String timestamp;

    public RedditTitleCard() {
        super("reddit_titlecard", true, false, false, false, true, 0);
    }

    public RedditTitleCard(final @NotNull AudioManager audioManager, final @NotNull String text) {
        this();

        setText(audioManager, text);
    }

    public void setSubreddit(@NotNull final String subreddit) {
        this.subreddit = subreddit;
    }

    public void setUsername(@NotNull final String username) {
        this.username = username;
    }

    public void setText(@NotNull final AudioManager audioManager, @NotNull final String text) {
        this.audio = audioManager.createAudioSource(text);
        this.text = text;

        setDuration(audio.duration());
    }

    public void setTimestamp(@NotNull final String timestamp) {
        this.timestamp = timestamp;
    }

    public String subreddit() {
        return subreddit;
    }

    public String username() {
        return username;
    }

    public String text() {
        return text;
    }

    public AudioSource audio() {
        return audio;
    }

    public String timestamp() {
        return timestamp;
    }
}
