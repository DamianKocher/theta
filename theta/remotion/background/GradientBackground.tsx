import {AbsoluteFill, useCurrentFrame, useVideoConfig} from 'remotion';

export const GradientBackgroundComponent = () => {
	const {width, height, fps} = useVideoConfig();
	const frame = useCurrentFrame();

	return (
		<AbsoluteFill
			style={{
				background: `linear-gradient(${
					(frame / fps) * -8.5
				}deg, #f38ba8, #89b4fa)`,
			}}
		/>
	);
};
