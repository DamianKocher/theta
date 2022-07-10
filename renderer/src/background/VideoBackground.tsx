import { AbsoluteFill, OffthreadVideo, useVideoConfig } from "remotion";
import { Background } from "../Definitions";


export const VideoBackgroundComponent = (background: Background) => {
  const { width, height } = useVideoConfig();

  return (
    <AbsoluteFill>
      <OffthreadVideo
        src={`http://localhost:5000/bg/${background.url}`}
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
