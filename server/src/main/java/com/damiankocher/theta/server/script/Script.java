package com.damiankocher.theta.server.script;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Script {

	private @NotNull String ttsLanguageCode;
	private @NotNull String ttsVoiceName;
	private double ttsSpeakingRate;

	private @NotNull String background;
	private @NotNull String subreddit;
	private @NotNull String username;
	private @NotNull String text;
	private @NotNull String time;

	private final @NotNull List<String> sections;

	public Script(
			@NotNull String ttsLanguageCode,
			@NotNull String ttsVoiceName,
			double ttsSpeakingRate,
			@NotNull String background,
			@NotNull String subreddit,
			@NotNull String username,
			@NotNull String text,
			@NotNull String time,
			@NotNull List<String> sections
	) {
		this.ttsLanguageCode = ttsLanguageCode;
		this.ttsVoiceName = ttsVoiceName;
		this.ttsSpeakingRate = ttsSpeakingRate;
		this.background = background;
		this.subreddit = subreddit;
		this.username = username;
		this.text = text;
		this.time = time;
		this.sections = sections;
	}

	public String ttsLanguageCode() {
		return ttsLanguageCode;
	}

	public String ttsVoiceName() {
		return ttsVoiceName;
	}

	public double ttsSpeakingRate() {
		return ttsSpeakingRate;
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

	public void setTtsLanguageCode(final @NotNull String ttsLanguageCode) {
		this.ttsLanguageCode = ttsLanguageCode;
	}

	public void setTtsVoiceName(final @NotNull String ttsVoiceName) {
		this.ttsVoiceName = ttsVoiceName;
	}

	public void setTtsSpeakingRate(final double ttsSpeakingRate) {
		this.ttsSpeakingRate = ttsSpeakingRate;
	}

	public void setBackground(final @NotNull String background) {
		this.background = background;
	}

	public void setSubreddit(final @NotNull String subreddit) {
		this.subreddit = subreddit;
	}

	public void setUsername(final @NotNull String username) {
		this.username = username;
	}

	public void setText(final @NotNull String text) {
		this.text = text;
	}

	public void setTime(final @NotNull String time) {
		this.time = time;
	}
}
