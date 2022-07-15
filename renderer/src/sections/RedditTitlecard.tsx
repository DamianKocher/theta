import {Audio} from 'remotion';
import {RedditTitlecard} from '../Definitions';
import redditLogo from './reddit_logo.png';

interface Props {
	redditTitlecard: RedditTitlecard;
	muteAudio: boolean;
}

export const RedditTitlecardComponent = (props: Props) => {
	const redditTitlecard = props.redditTitlecard;
	const muteAudio = props.muteAudio;

	return (
		<div>
			<div
				style={{
					display: 'flex',
					flexDirection: 'row',
					gap: '30px',
					marginBottom: '30px',
				}}
			>
				<img
					src={redditLogo}
					style={{
						height: '70px',
					}}
				/>

				<div
					style={{
						display: 'flex',
						flexDirection: 'column',
						gap: '10px',
					}}
				>
					<p
						className="small"
						style={{
							fontWeight: 'bold',
						}}
					>
						r/{redditTitlecard.subreddit}
					</p>
					<p className="small">
						<span className="user">u/{redditTitlecard.username}</span> •{' '}
						{redditTitlecard.timestamp}
					</p>
				</div>
			</div>

			<h1
				style={{
					fontWeight: 'bold',
					textAlign: 'left',
				}}
			>
				{redditTitlecard.text}
			</h1>

			{!muteAudio && (
				<Audio
					src={'http://localhost:5000/audio/' + redditTitlecard.audio.url}
				></Audio>
			)}
		</div>
	);
};
