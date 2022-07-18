import axios from 'axios';
import {useEffect} from 'react';
import {Audio, continueRender} from 'remotion';
import {REMOTION_URL} from './Constants';
import {AudioSource, Content} from './Definitions';

const instance = axios.create({
	baseURL: REMOTION_URL,
	timeout: 1000,
});

function playAudio(audioSource: AudioSource, muteAudio: boolean) {
	if (muteAudio) {
		return undefined;
	}

	return <Audio src={'http://localhost:5000/audio/' + audioSource.url} />;
}

async function getContent(
	videoId: string,
	fps: number,
	handle: number,
	setContent: React.Dispatch<React.SetStateAction<Content | undefined>>
) {
	useEffect(() => {
		(async () => {
			console.log('getting content');

			const content: Content = (await instance.get(`/video/${videoId}`)).data;

			let durationInFrames = 0;
			content.sections.forEach((section) => {
				const sectionMeta = section._meta;

				sectionMeta.durationInFrames = Math.ceil(section._meta.duration * fps);
				durationInFrames += sectionMeta.durationInFrames;
			});

			content.durationInFrames = durationInFrames;

			console.log(
				`composition is ${
					content.durationInFrames / fps
				} seconds long at ${fps} fps which is a total of ${
					content.durationInFrames
				} frames`
			);

			setContent(content);

			console.log('continueRender()');
			continueRender(handle);
		})();
	}, [handle]);
}

export {playAudio, getContent};
