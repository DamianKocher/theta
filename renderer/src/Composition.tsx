import { Series, useVideoConfig } from "remotion";
import { VideoBackgroundComponent } from "./background/VideoBackground";
import { BoxComponent } from "./BoxComponent";
import { SectionComponentPicker } from "./sections/SectionComponentPicker";
import './style/style.css';
import { Content } from "./Video";

export const Comp = (content: Content) => {
	if (content === undefined) {
		return (
			<div></div>
		)
	}

	const { fps } = useVideoConfig();

	return (
		<div>
			<VideoBackgroundComponent url={content.background.url} />

			<Series>
				{content.sections.map((section, index) => (
					<Series.Sequence
						key={index}
						durationInFrames={Math.ceil(section._meta.duration * fps)}
						name={section.text}
						layout={'absolute-fill'}
					>
						{section._meta.useStandardContainer ?
							<BoxComponent sections={content.sections} index={index} /> :
							<SectionComponentPicker section={section} isPrimary={true} />}
					</Series.Sequence>
				))}
			</Series>
		</div>
	)
};
