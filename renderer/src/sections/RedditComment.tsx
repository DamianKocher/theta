import {Audio, random} from 'remotion';
import {RedditComment} from '../Definitions';

interface Props {
	redditComment: RedditComment;
	muteAudio: boolean;
}

export const RedditCommentComponent = (props: Props) => {
	const redditComment = props.redditComment;
	const muteAudio = props.muteAudio;

	const pfpId = Math.floor(random(redditComment.username) * 7);
	const pfpUrl = `https://www.redditstatic.com/avatars/defaults/v2/avatar_default_${pfpId}.png`;

	return (
		<div>
			<div style={{display: 'flex'}}>
				{redditComment.commentDepth > 0 &&
					[...Array(redditComment.commentDepth)].map((_, index) => {
						return (
							<div
								key={index}
								style={{
									marginRight: 'var(--reddit-container-padding)',
									width: '10px',
									backgroundColor: 'var(--reddit-color-subtext)',
								}}
							/>
						);
					})}

				<div
					style={{
						flexGrow: '1',
						display: 'flex',
						flexDirection: 'column',
						gap: '1.5em',
					}}
				>
					{redditComment.showHeader && (
						<div
							style={{
								display: 'flex',
								alignItems: 'center',
								gap: '1.5em',
							}}
						>
							<img
								src={pfpUrl}
								style={{height: '68px', borderRadius: '100vh'}}
							/>
							<p className="small">
								<span style={{fontWeight: '600'}}>
									{redditComment.username}
								</span>{' '}
								• {redditComment.timestamp}
							</p>
						</div>
					)}

					{redditComment.text.split('\n').map((line: string, index: number) => (
						<p key={index}>{line}</p>
					))}

					{redditComment.showFooter && (
						<div style={{display: 'flex', flexDirection: 'row-reverse'}}>
							<p className="small">{redditComment.score}</p>
						</div>
					)}
				</div>
			</div>

			{!muteAudio && (
				<Audio
					src={'http://localhost:5000/audio/' + redditComment.audio.url}
				></Audio>
			)}
		</div>
	);
};
