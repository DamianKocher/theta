import {useState} from 'react';
import {Composition, delayRender, getInputProps} from 'remotion';
import {Comp} from './Composition';
import {DEFAULT_FPS, DEFAULT_HEIGHT, DEFAULT_WIDTH} from './Constants';
import {Content} from './Definitions';
import {getContent} from './Utils';

export const Index: React.FC = () => {
	const [handle] = useState<number>(() => delayRender());
	const [content, setContent] = useState<Content>();

	const {fps, width, height} = {
		...getInputProps(),
		fps: DEFAULT_FPS,
		height: DEFAULT_HEIGHT,
		width: DEFAULT_WIDTH,
	};

	getContent('video', fps, handle, setContent);

	return content !== undefined ? (
		<Composition
			id="Theta"
			component={Comp}
			defaultProps={content}
			durationInFrames={content.durationInFrames}
			fps={fps}
			width={width}
			height={height}
		/>
	) : (
		<div />
	);
};
