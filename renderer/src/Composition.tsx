import {Series, useVideoConfig} from 'remotion';
import {VideoBackgroundComponent} from './background/VideoBackground';
import {BoxComponent} from './BoxComponent';
import {Content, Section} from './Definitions';
import {SectionComponentPicker} from './sections/SectionComponentPicker';
import './style/style.css';

export const Comp = (content: Content) => {
	// return an empty div if content is undefined. we'll get an undefined error if we don't do this
	if (!content) return <div></div>;

	const {fps} = useVideoConfig();

	const background: string = content.background;
	const sections: Section[] = content.sections;

	return (
		<div>
			<VideoBackgroundComponent url={background} />

			<Series>
				{sections.map((section, index) => (
					<Series.Sequence
						key={index}
						durationInFrames={Math.ceil(section._meta.duration * fps)}
						name={section._meta.type}
						layout={'absolute-fill'}
					>
						{section._meta.useStandardContainer ? (
							<BoxComponent sections={sections} index={index} />
						) : (
							<SectionComponentPicker section={section} isPrimary={true} />
						)}
					</Series.Sequence>
				))}
			</Series>
		</div>
	);
};
