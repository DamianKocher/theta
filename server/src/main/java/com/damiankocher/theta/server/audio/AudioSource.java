package com.damiankocher.theta.server.audio;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import org.jetbrains.annotations.NotNull;

public record AudioSource(
		@NotNull String url,
		byte @NotNull [] data,
		double duration
) {

	public static @NotNull JsonSerializer<AudioSource> createSerializer() {
		return (audio, typeOfSrc, context) -> {
			if(audio == null) {
				return null;
			}

			final var jsonAudio = new JsonObject();

			jsonAudio.addProperty("url", audio.url());

			return jsonAudio;
		};
	}
}
