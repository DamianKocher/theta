package com.damiankocher.theta.server.content;

import com.damiankocher.theta.server.Theta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ContentManager {

	private final @NotNull Theta theta;

	public ContentManager(final @NotNull Theta theta) {
		this.theta = theta;
	}

	public @Nullable Content getContent(final @NotNull String id) {
		return null;
	}
}
