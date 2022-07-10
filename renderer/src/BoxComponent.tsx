import { AbsoluteFill, Easing, interpolate, useCurrentFrame, useVideoConfig } from "remotion";
import { Section } from "./Definitions";
import { SectionComponentPicker } from "./sections/SectionComponentPicker";

interface Props {
  sections: Section[];
  index: number;
}

export const BoxComponent = (props: Props) => {
  const sections = props.sections;
  const index = props.index;

  const section: Section = sections[index];

  const fadeIn = section._meta.fadeIn;
  const fadeOut = section._meta.fadeOut;

  const frame = useCurrentFrame();
  const { durationInFrames, fps } = useVideoConfig();

  const opacity = interpolate(
    frame,
    [0, fps * (1 / 5), durationInFrames - (1 / 5) * fps, durationInFrames],
    [fadeIn ? 0 : 1, 1, 1, fadeOut ? 0 : 1],
    { easing: Easing.bezier(0.8, 0.22, 0.96, 0.65) }
  );

  return (
    <AbsoluteFill
      style={{
        opacity: opacity,
        alignItems: 'center',
        justifyContent: 'center',
        flexDirection: 'column',
      }}
    >
      <div className="box" style={{ display: 'flex', flexDirection: 'column', gap: '3em' }}>
        {section._meta.showPrevious && index > 0 &&
          <div style={{ opacity: 1 / 3 }}>
            <SectionComponentPicker section={props.sections[index - 1]} isPrimary={false} />
          </div>
        }

        <SectionComponentPicker section={section} isPrimary={true} />

        {section._meta.showNext && index < sections.length - 1 &&
          <div style={{ opacity: 1 / 3 }}>
            <SectionComponentPicker section={sections[index + 1]} isPrimary={false} />
          </div>
        }
      </div>
    </AbsoluteFill>
  );
};
