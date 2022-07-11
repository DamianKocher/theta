package com.damiankocher.theta.server.audio;

import com.damiankocher.theta.server.Theta;
import com.google.cloud.texttospeech.v1.*;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AudioManager {

	private static final @NotNull Logger log = LoggerFactory.getLogger(AudioManager.class);

	private static final @NotNull String DEFAULT_LANGUAGE_CODE = "en-US";
	private static final @NotNull String DEFAULT_VOICE_NAME = "en-US-Wavenet-H";
	private static final double DEFAULT_SPEAKING_RATE = 1.2d;

	private final @NotNull Theta theta;

	private final @NotNull TextReplacer textReplacer;

	private final @NotNull Map<String, AudioSource> audioSourceLookup = new HashMap<>();

	public AudioManager(final @NotNull Theta theta) throws IOException {
		this.theta = theta;

		final var config = theta.config();

		final var textReplacementPath = config.textReplacementPath();
		final var audioCahceDirectory = config.audioCacheDirectory();

		if (Files.exists(textReplacementPath)) {
			this.textReplacer = new Gson().fromJson(Files.newBufferedReader(textReplacementPath), TextReplacer.class);
		} else {
			this.textReplacer = new TextReplacer(Collections.emptyMap());
			log.warn("text replacement file not found, using empty text replacer");
		}

		if (!Files.exists(audioCahceDirectory)) {
			Files.createDirectories(audioCahceDirectory);
		}

		textReplacer.logReplacements();
	}

	@SuppressWarnings("UnstableApiUsage")
	private static @NotNull String getName(final @NotNull String text, final @NotNull String languageCode, final @NotNull String voiceName, final double speakingRate) {
		final var hasher = Hashing.sha256().newHasher();

		hasher.putString(text, StandardCharsets.UTF_8);
		hasher.putString(languageCode, StandardCharsets.UTF_8);
		hasher.putString(voiceName, StandardCharsets.UTF_8);
		hasher.putDouble(speakingRate);

		return hasher.hash() + ".mp3";
	}

	public @NotNull AudioSource createAudioSource(@NotNull final String text) {
		return createAudioSource(text, DEFAULT_LANGUAGE_CODE, DEFAULT_VOICE_NAME, DEFAULT_SPEAKING_RATE);
	}

	public @NotNull AudioSource createAudioSource(@NotNull String text, final @NotNull String languageCode, final @NotNull String voiceName, final double speakingRate) {
		text = textReplacer.replaceText(text);
		final var name = getName(text, languageCode, voiceName, speakingRate);

		final var shortText = text.substring(0, Math.min(text.length(), 40)) + (text.length() > 40 ? "..." : "");

		if (audioSourceLookup.containsKey(name)) {
			log.info("returning existing audio source [name: {}, text: {}]", name, shortText);
			return audioSourceLookup.get(name);
		}

		Path audioCacheDirectory = theta.config().audioCacheDirectory();
		if (Files.exists(audioCacheDirectory.resolve(name))) {
			log.info("found cached audio [filename: {}, text: {}]", name, shortText);

			try {
				return createAudioSourceObject(name, Files.readAllBytes(audioCacheDirectory.resolve(name)));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
			final var input = SynthesisInput.newBuilder().setText(text).build();
			final var voice = VoiceSelectionParams.newBuilder()
					.setLanguageCode(languageCode)
					.setName(voiceName)
					.build();
			final var config = AudioConfig.newBuilder()
					.setAudioEncoding(AudioEncoding.MP3)
					.setSpeakingRate(speakingRate)
					.addEffectsProfileId("handset-class-device")
					.build();

			final var response = textToSpeechClient.synthesizeSpeech(input, voice, config);
			byte[] data = response.getAudioContent().toByteArray();

			log.info("generated audio [language code: {}, voice name: {}, speaking rate: {}, hashed name: {}, text: {}]", languageCode, voiceName, speakingRate, name, shortText);

			Files.write(audioCacheDirectory.resolve(name), data);
			return createAudioSourceObject(name, data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private AudioSource createAudioSourceObject(@NotNull final String name, final byte @NotNull [] data) {
		final var duration = data.length / 4000.0d + 0.2d; // mp3 is 32kbps so that's 4000 bytes per second, plus 0.2 seconds to account for inaccuracy in this crude estimate

		final var audioSource = new AudioSource(name, data, duration);
		audioSourceLookup.put(name, audioSource);

		log.info("created audio source [name: {}, duration in seconds: {}]", name, duration);

		return audioSource;
	}

	public @Nullable AudioSource getAudioSource(@NotNull String name) {
		return audioSourceLookup.get(name);
	}

	public @NotNull Set<String> getAudioNames() {
		return audioSourceLookup.keySet();
	}
}
