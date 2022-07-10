package com.damiankocher.theta.server;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Script {

	private final @NotNull String background;
	private final @NotNull String subreddit;
	private final @NotNull String username;
	private final @NotNull String text;
	private final @NotNull String time;
	private final @NotNull List<String> sections;

	public Script(@NotNull String background, @NotNull String subreddit, @NotNull String username, @NotNull String text, @NotNull String time, @NotNull List<String> sections) {
		this.background = background;
		this.subreddit = subreddit;
		this.username = username;
		this.text = text;
		this.time = time;
		this.sections = sections;
	}

	public @NotNull String background() {
		return background;
	}

	public @NotNull String subreddit() {
		return subreddit;
	}

	public @NotNull String username() {
		return username;
	}

	public @NotNull String text() {
		return text;
	}

	public @NotNull String time() {
		return time;
	}

	public @NotNull List<String> sections() {
		return sections;
	}
}
