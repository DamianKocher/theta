import {
	AbsoluteFill,
	Easing,
	interpolate,
	useCurrentFrame,
	useVideoConfig,
} from 'remotion';
import {Section, SectionMeta} from './Definitions';
import {SectionComponentPicker} from './sections/SectionComponentPicker';

const INACTIVE_SECTION_OPACITY = 1 / 3;

const FADE_IN_OUT_TIME_IN_SECONDS = 1 / 5;
const FADE_IN_OUT_EASING = Easing.bezier(0.79, 0.57, 0.91, 0.78);

function getOpacity(
	fadeIn: boolean,
	fadeOut: boolean,
	fps: number,
	durationInFrames: number,
	frame: number
) {
	const startFadeInTime = 0;
	const finishFadeInTime = Math.round(fps * FADE_IN_OUT_TIME_IN_SECONDS);
	const startFadeOutTime = Math.round(durationInFrames - finishFadeInTime);
	const finishFadeOutTime = durationInFrames;

	return interpolate(
		frame,
		[startFadeInTime, finishFadeInTime, startFadeOutTime, finishFadeOutTime],
		[fadeIn ? 0 : 1, 1, 1, fadeOut ? 0 : 1],
		{easing: FADE_IN_OUT_EASING}
	);
}

function previousSection(sections: Section[], index: number) {
	const sectionMeta = sections[index]._meta;
	const showPrevious = sectionMeta.showPrevious && index > 0;

	if (!showPrevious) return undefined;

	return (
		<div style={{opacity: INACTIVE_SECTION_OPACITY}}>
			<SectionComponentPicker section={sections[index - 1]} isPrimary={false} />
		</div>
	);
}

function nextSection(sections: Section[], index: number) {
	const sectionMeta = sections[index]._meta;
	const showPrevious = sectionMeta.showNext && index < sections.length - 1;

	if (!showPrevious) return undefined;

	return (
		<div style={{opacity: INACTIVE_SECTION_OPACITY}}>
			<SectionComponentPicker section={sections[index + 1]} isPrimary={false} />
		</div>
	);
}

export const BoxComponent = (props: {sections: Section[]; index: number}) => {
	const sections = props.sections;
	const index = props.index;

	const section: Section = sections[index];
	const sectionMeta: SectionMeta = section._meta;

	const fadeIn = sectionMeta.fadeIn;
	const fadeOut = sectionMeta.fadeOut;

	const frame = useCurrentFrame();
	const {durationInFrames, fps} = useVideoConfig();

	const opacity = getOpacity(fadeIn, fadeOut, fps, durationInFrames, frame);

	return (
		<AbsoluteFill className="box-container" style={{opacity}}>
			<div className="box">
				{previousSection(sections, index)}
				<SectionComponentPicker section={section} isPrimary={true} />
				{nextSection(sections, index)}
			</div>
		</AbsoluteFill>
	);
};
