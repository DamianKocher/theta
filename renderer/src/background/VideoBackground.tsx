import { AbsoluteFill, OffthreadVideo, useVideoConfig } from "remotion";
import { Background } from "../Video";


export const VideoBackgroundComponent = (props: Background) => {
  const { width, height } = useVideoConfig();

  return (
    <AbsoluteFill>
      <OffthreadVideo
        src={`http://localhost:5000/bg/${props.url}`}
        style={{
          width,
          height
        }}
        muted
        startFrom={35}
      />
    </AbsoluteFill>
  );
};
