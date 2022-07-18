import {Img, staticFile} from 'remotion';
import {RedditTitlecard} from '../../../Definitions';
import {playAudio} from '../../../Utils';
import './style.css';

interface Props {
	redditTitlecard: RedditTitlecard;
	muteAudio: boolean;
}

export const RedditTitlecardComponent = (props: Props) => {
	const titlecard = props.redditTitlecard;
	const muteAudio = props.muteAudio;

	return (
		<div>
			<div className="reddit-titlecard-header">
				<Img
					className="reddit-titlecard-header-icon"
					src={staticFile('reddit_logo.svg')}
				/>

				<div className="reddit-titlecard-header-text-container">
					<p className="reddit-titlecard-header-subreddit small">
						r/{titlecard.subreddit}
					</p>
					<div className="labels">
						<p className="small user">u/{titlecard.username}</p>
						<p className="small">•</p>
						<p className="small">{titlecard.timestamp}</p>
					</div>
				</div>
			</div>

			<h1 className="reddit-titlecard-title">{titlecard.audio.text}</h1>

			{playAudio(titlecard.audio, muteAudio)}
		</div>
	);
};
