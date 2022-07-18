package com.damiankocher.theta.server;

import com.damiankocher.theta.server.audio.AudioManager;
import com.damiankocher.theta.server.audio.AudioSource;
import com.damiankocher.theta.server.video.Section;
import com.damiankocher.theta.server.video.Video;
import com.damiankocher.theta.server.video.VideoManager;
import com.damiankocher.theta.server.video.sections.reddit.RedditComment;
import com.damiankocher.theta.server.video.sections.reddit.RedditTitleCard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Theta {

    private final @NotNull Config config;

    private final @NotNull Gson gson;
    private final @NotNull VideoManager videoManager;
    private final @NotNull AudioManager audioManager;
    private final @NotNull Server server;

    public Theta(final @NotNull Config config) throws IOException {
        this.config = config;

        this.gson = createGsonInstance();
        this.audioManager = new AudioManager(this);
        this.videoManager = new VideoManager(this);

        this.server = new Server(this);

        final var script = gson().fromJson(Files.newBufferedReader(config.scriptsDirectory().resolve("script.json")), Script.class);
        final var video = new Video("bg_pk.mp4");

        final var titlecard = new RedditTitleCard();
        titlecard.setSubreddit(script.subreddit);
        titlecard.setTimestamp(script.time);
        titlecard.setUsername(script.username);
        titlecard.setAudio(audioManager.createAudioSource(script.text));
        video.addSection(titlecard);

        for (final var comment : script.comments) {
            boolean isFirst = true;
            int index = 0;

            for (final var sectionText : comment.sections) {
                final var section = new RedditComment();

                section.setCommentDepth(comment.commentDepth);
                section.setUsername(comment.username);
                section.setTimestamp(comment.time);

                if(!isFirst || comment.commentDepth != 0) {
                    section.setShowPrevious(true);
                }

                if(isFirst) {
                    section.setShowHeader(true);
                    isFirst = false;
                }

//                if(index++ == comment.sections.size() - 1) section.setShowFooter(true);

                section.setAudio(audioManager.createAudioSource(sectionText));

                video.addSection(section);
            }
        }

        videoManager.addVideo("video", video);
    }

    private static Gson createGsonInstance() {
        return new GsonBuilder()
                .registerTypeAdapter(Section.class, Section.createSerializer())
                .registerTypeAdapter(AudioSource.class, AudioSource.createSerializer())
                .create();
    }

    public static void main(String[] args) throws IOException {
        final var dataPath = Path.of("data");

        final var config = new Config.Builder()
                .setScriptsDirectory(dataPath.resolve("scripts"))
                .setBackgroundDirectory(dataPath.resolve("backgrounds"))
                .setAudioCacheDirectory(dataPath.resolve("audio_cache"))
                .setTextReplacementPath(dataPath.resolve("text-replacements.json"))
                .setPort(5000)
                .create();

        Theta instance = new Theta(config);
        instance.server().start();
    }

    public Config config() {
        return config;
    }

    public Gson gson() {
        return gson;
    }

    public VideoManager videoManager() {
        return videoManager;
    }

    public AudioManager audioManager() {
        return audioManager;
    }

    public Server server() {
        return server;
    }
}
