package com.damiankocher.theta.server;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public record Config(
		@NotNull Path scriptsDirectory,
		@NotNull Path backgroundDirectory,
		@NotNull Path audioCacheDirectory,
		@NotNull Path textReplacementPath,
		int port
) {

	public static class Builder {

		private @Nullable Path scriptsDirectory;
		private @Nullable Path backgroundDirectory;
		private @Nullable Path audioCacheDirectory;
		private @Nullable Path textReplacementPath;
		private int port;

		public Builder() {
			this.port = Integer.MIN_VALUE;
		}

		public Path scriptsDirectory() {
			return scriptsDirectory;
		}

		public Builder setScriptsDirectory(final @NotNull Path scriptsDirectory) {
			this.scriptsDirectory = scriptsDirectory;

			return this;
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

		public int port() {
			return port;
		}

		public Builder setPort(int port) {
			this.port = port;

			return this;
		}

		public Config create() {
			if(scriptsDirectory == null) {
				throw new IllegalStateException("scriptsDirectory is null");
			}

			if (backgroundDirectory == null) {
				throw new IllegalStateException("backgroundDirectory is null");
			}

			if (audioCacheDirectory == null) {
				throw new IllegalStateException("audioCacheDirectory is null");
			}

			if (textReplacementPath == null) {
				throw new IllegalStateException("textReplacementPath is null");
			}

			if (port == Integer.MIN_VALUE) {
				throw new IllegalStateException("port is not set");
			}

			return new Config(scriptsDirectory, backgroundDirectory, audioCacheDirectory, textReplacementPath, port);
		}
	}
}
