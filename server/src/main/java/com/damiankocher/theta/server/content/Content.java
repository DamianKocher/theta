package com.damiankocher.theta.server.content;

import com.damiankocher.theta.server.audio.AudioManager;
import com.damiankocher.theta.server.content.reddit.RedditComment;
import com.damiankocher.theta.server.content.reddit.RedditTitleCard;
import com.damiankocher.theta.server.script.Script;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public record Content(
		@NotNull Background background,
		@NotNull List<Section> sections
) {

	public static Content fromScript(@NotNull Script script, @NotNull AudioManager audioManager, final @NotNull Path backgroundDirectory) throws IOException {
		final var backgroundData = Files.readAllBytes(backgroundDirectory.resolve(script.background()));
		final var background = new Background(backgroundData, script.background());

		final List<Section> sections = new ArrayList<>(script.sections().size());

		// Title card
		final var titleCard = new RedditTitleCard();
		titleCard.setSubreddit(script.subreddit());
		titleCard.setUsername(script.username());
		titleCard.setTimestamp(script.time());
		titleCard.setText(audioManager, script.text());
		titleCard.setShowNext(false);
		sections.add(titleCard);

		for (final var sectionText : script.sections()) {
			final var comment = new RedditComment(audioManager, sectionText);
			sections.add(comment);
		}

		return new Content(background, sections);
	}
}
