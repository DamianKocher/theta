package com.damiankocher.theta.server.script;

import com.damiankocher.theta.server.Theta;
import com.damiankocher.theta.server.content.Content;
import com.damiankocher.theta.server.content.Section;
import com.damiankocher.theta.server.content.reddit.RedditComment;
import com.damiankocher.theta.server.content.reddit.RedditTitleCard;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class RedditLongformScript implements ContentGenerator {

	private final @NotNull List<String> sections;
	private @NotNull String ttsLanguageCode;
	private @NotNull String ttsVoiceName;
	private double ttsSpeakingRate;
	private @NotNull String background;
	private @NotNull String subreddit;
	private @NotNull String username;
	private @NotNull String text;
	private @NotNull String time;

	public RedditLongformScript(
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

	public void setTtsLanguageCode(final @NotNull String ttsLanguageCode) {
		this.ttsLanguageCode = ttsLanguageCode;
	}

	public String ttsVoiceName() {
		return ttsVoiceName;
	}

	public void setTtsVoiceName(final @NotNull String ttsVoiceName) {
		this.ttsVoiceName = ttsVoiceName;
	}

	public double ttsSpeakingRate() {
		return ttsSpeakingRate;
	}

	public void setTtsSpeakingRate(final double ttsSpeakingRate) {
		this.ttsSpeakingRate = ttsSpeakingRate;
	}

	public String background() {
		return background;
	}

	public void setBackground(final @NotNull String background) {
		this.background = background;
	}

	public String subreddit() {
		return subreddit;
	}

	public void setSubreddit(final @NotNull String subreddit) {
		this.subreddit = subreddit;
	}

	public String username() {
		return username;
	}

	public void setUsername(final @NotNull String username) {
		this.username = username;
	}

	public String text() {
		return text;
	}

	public void setText(final @NotNull String text) {
		this.text = text;
	}

	public String time() {
		return time;
	}

	public void setTime(final @NotNull String time) {
		this.time = time;
	}

	public List<String> sections() {
		return sections;
	}

	@Override
	public @NotNull Content createContent(final @NotNull Theta theta) {
		final var audioManager = theta.audioManager();

		final List<Section> sections = new ArrayList<>(sections().size());

		// Title card
		final var titleCard = new RedditTitleCard();
		titleCard.setSubreddit(subreddit());
		titleCard.setUsername(username());
		titleCard.setTimestamp(time());
		titleCard.setText(audioManager, text());
		titleCard.setShowNext(false);
		sections.add(titleCard);

		for (final var sectionText : sections()) {
			final var comment = new RedditComment(audioManager, sectionText);
			sections.add(comment);
		}

		return new Content(background, sections);
	}
}
