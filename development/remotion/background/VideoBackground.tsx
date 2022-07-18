import {AbsoluteFill, OffthreadVideo, useVideoConfig} from 'remotion';

export const VideoBackgroundComponent = (props: {url: string}) => {
	const {width, height} = useVideoConfig();

	return (
		<AbsoluteFill>
			<OffthreadVideo
				src={`http://localhost:5000/bg/${props.url}`}
				style={{
					width,
					height,
				}}
				muted
			/>
		</AbsoluteFill>
	);
};
