import axios from 'axios';
import { useEffect, useState } from 'react';
import { Composition, continueRender, delayRender } from 'remotion';
import { Comp } from './Composition';

const FPS = 30;

export interface Content {
	background: Background;
	sections: (RedditComment | RedditTitlecard)[]
}

export interface Background {
	url: string;
}

export interface Section {
	_meta: {
		type: string;
		useStandardContainer: boolean;
		showPrevious: boolean;
		showNext: boolean;
		fadeIn: boolean;
		fadeOut: boolean;
		duration: number;
	}
}

export interface Audio {
	url: string;
}

export interface RedditTitlecard extends Section {
	subreddit: string;
	username: string;
	text: string;
	audio: Audio;
	timestamp: string;
}

export interface RedditComment extends Section {
	text: string;
	audio: Audio;
}

const instance = axios.create({
  baseURL: 'http://localhost:5000',
  timeout: 1000,
});

export const Index: React.FC = () => {
	const [handle] = useState<number>(() => delayRender());
	const [content, setContent] = useState<Content>();
	const [durationInFrames, setDurationInFrames] = useState<number>(0);

	useEffect(() => {
		(async () => {
			console.log('getting content');
			const con: Content = (await instance.get('/content')).data;

			let _durationInFrames = 0;
			con.sections.forEach((section) => _durationInFrames += Math.ceil(section._meta.duration * FPS)); // this should probably use .reduce()
			console.log(`composition is ${_durationInFrames / FPS} seconds long at ${FPS} fps which is a total of ${_durationInFrames} frames`)

			setDurationInFrames(_durationInFrames);
			setContent(con);

			console.log('continueRender()')
			continueRender(handle);
		})();
	}, [handle]);

	return (
		content !== undefined ?
			<Composition
				id="RedditTTS"
				component={Comp}
				defaultProps={content}
				durationInFrames={durationInFrames}
				fps={FPS}
				width={1080}
				height={1920}
			/>
			: <div />
	);
};
