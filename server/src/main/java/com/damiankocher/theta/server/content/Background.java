package com.damiankocher.theta.server.content;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import org.jetbrains.annotations.NotNull;

public record Background(
		byte @NotNull [] data,
		@NotNull String url
) {

	public static @NotNull JsonSerializer<Background> createSerializer() {
		return (background, typeOfSrc, context) -> {
			if (background == null) {
				return null;
			}

			final var jsonBackground = new JsonObject();
			jsonBackground.addProperty("url", background.url());

			return jsonBackground;
		};
	}
}
