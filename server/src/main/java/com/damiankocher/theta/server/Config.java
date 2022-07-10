package com.damiankocher.theta.server;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public record Config(
		@NotNull Path backgroundDirectory,
		@NotNull Path audioCacheDirectory,
		@NotNull Path textReplacementPath,
		@NotNull Path scriptPath,
		int port
) {

	public static class Builder {

		private @Nullable Path backgroundDirectory;
		private @Nullable Path audioCacheDirectory;
		private @Nullable Path textReplacementPath;
		private @Nullable Path scriptPath;
		private int port;

		public Builder() {
			this.port = Integer.MIN_VALUE;
		}

		public Path backgroundDirectory() {
			return backgroundDirectory;
		}

		public Builder setBackgroundDirectory(final @NotNull Path backgroundDirectory) {
			this.backgroundDirectory = backgroundDirectory;

			return this;
		}

		public Path audioCacheDirectory() {
			return audioCacheDirectory;
		}

		public Builder setAudioCacheDirectory(final @NotNull Path audioCacheDirectory) {
			this.audioCacheDirectory = audioCacheDirectory;

			return this;
		}

		public Path textReplacementPath() {
			return textReplacementPath;
		}

		public Builder setTextReplacementPath(@NotNull Path textReplacementPath) {
			this.textReplacementPath = textReplacementPath;

			return this;
		}

		public Path scriptPath() {
			return scriptPath;
		}

		public Builder setScriptPath(final @NotNull Path scriptPath) {
			this.scriptPath = scriptPath;

			return this;
		}

		public int port() {
			return port;
		}

		public Builder setPort(int port) {
			this.port = port;

			return this;
		}

		public Config create() {
			if (backgroundDirectory == null) {
				throw new IllegalStateException("backgroundDirectory is null");
			}

			if (audioCacheDirectory == null) {
				throw new IllegalStateException("audioCacheDirectory is null");
			}

			if (textReplacementPath == null) {
				throw new IllegalStateException("textReplacementPath is null");
			}

			if (scriptPath == null) {
				throw new IllegalStateException("scriptPath is null");
			}

			if (port == Integer.MIN_VALUE) {
				throw new IllegalStateException("port is not set");
			}

			return new Config(backgroundDirectory, audioCacheDirectory, textReplacementPath, scriptPath, port);
		}
	}
}
