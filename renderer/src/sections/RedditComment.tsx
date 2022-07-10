import { Audio } from "remotion";
import { RedditComment } from "../Video";


interface Props {
  redditComment: RedditComment;
  muteAudio: boolean;
}

export const RedditCommentComponent = (props: Props) => {
  // const frame = useCurrentFrame();
  // const { durationInFrames, fps } = useVideoConfig();
  // const opacity = interpolate(
  //   frame,
  //   [0, 0.2 * fps, durationInFrames - 0.2 * fps, durationInFrames],
  //   [0, 1, 1, 0]
  // );

  const redditComment = props.redditComment;
  const muteAudio = props.muteAudio;

  return (
    <div>
      <div
        style={{
          display: 'flex',
          flexDirection: 'column',
          gap: '1.5em',
        }}
      >
        {redditComment.text.split('\n').map((line, index) => (
          <p key={index}>{line}</p>
        ))}
      </div>

      {!muteAudio && <Audio src={'http://localhost:5000/' + redditComment.audio.url}></Audio>}
    </div>
  );
};
