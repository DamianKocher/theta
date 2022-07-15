package com.damiankocher.theta.server.video;

import com.damiankocher.theta.server.Theta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VideoManager {

    private final @NotNull Theta theta;

    private final HashMap<String, Video> videos = new HashMap<>();

    public VideoManager(final @NotNull Theta theta) {
        this.theta = theta;
    }

    public void loadVideos() throws IOException {
        final var videosDirectory = theta.config().scriptsDirectory();
        final var videoFiles = videosDirectory.toFile().listFiles();

        if(videoFiles == null) {
            throw new IOException("could not list files in " + videosDirectory);
        }

        for (final var videoFile : videoFiles) {
            loadVideo(videoFile.getName());
        }
    }

    private void loadVideo(final @NotNull String id) throws IOException {
        final var videosDirectory = theta.config().scriptsDirectory();
        final var gson = theta.gson();

        final var videoPath = videosDirectory.resolve(id + ".json");
        final var bufferedReader = Files.newBufferedReader(videoPath);
        final var video = gson.fromJson(bufferedReader, Video.class);

        videos.put(id, video);
    }

    public void saveVideos() throws IOException {
        for (Map.Entry<String, Video> entry : videos.entrySet()) {
            final var id = entry.getKey();
            final var video = entry.getValue();

            saveVideo(id, video);
        }
    }

    private void saveVideo(final @NotNull String id, final @NotNull Video video) throws IOException {
        final var videosDirectory = theta.config().scriptsDirectory();
        final var gson = theta.gson();

        final var jsonVideo = gson.toJson(video);

        final var videoPath = videosDirectory.resolve(id + ".json");
        Files.writeString(videoPath, jsonVideo);
    }

    public void addVideo(final @NotNull String id, final @NotNull Video video) {
        videos.put(id, video);
    }

    public @Nullable Video getVideo(final @NotNull String id) {
        return videos.get(id);
    }

    public void removeVideo(final @NotNull String id) {
        videos.remove(id);
    }

    public @NotNull Set<String> videoIds() {
        return videos.keySet();
    }
}
