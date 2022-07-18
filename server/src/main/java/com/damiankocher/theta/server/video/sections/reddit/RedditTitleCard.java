package com.damiankocher.theta.server.video.sections.reddit;

import com.damiankocher.theta.server.audio.AudioSource;
import com.damiankocher.theta.server.video.Section;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RedditTitleCard extends Section {

    private @NotNull String subreddit = "";
    private @NotNull String username = "";
    private @Nullable AudioSource audio;
    private @NotNull String timestamp = "";

    public RedditTitleCard() {
        super("reddit_titlecard", true, false, false, false, true, 0);
    }

    public void setSubreddit(@NotNull final String subreddit) {
        this.subreddit = subreddit;
    }

    public void setUsername(@NotNull final String username) {
        this.username = username;
        updateDescription();
    }

    public void setAudio(@NotNull final AudioSource audio) {
        this.audio = audio;
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

    public AudioSource audio() {
        return audio;
    }

    public String timestamp() {
        return timestamp;
    }

    @Override
    protected void updateDescription() {
        if(audio() == null) {
            setDescription("no audio");
            return;
        }

        String text = audio().text().substring(0, Math.min(audio.text().length(), 32));

        this.setDescription(String.format("u/%s - %s", username(), text));
    }
}
