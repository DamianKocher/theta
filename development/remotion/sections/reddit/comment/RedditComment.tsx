import {random} from 'remotion';
import {RedditComment} from '../../../Definitions';
import {playAudio} from '../../../Utils';
import './style.css';

interface Props {
	redditComment: RedditComment;
	muteAudio: boolean;
}

function getDepthPadding(redditComment: RedditComment) {
	if (redditComment.commentDepth <= 0) {
		return undefined;
	}

	return [...Array(redditComment.commentDepth)].map((_, index) => {
		return <div key={index} className="reddit-comment-depth-padding" />;
	});
}

function getHeader(redditComment: RedditComment) {
	if (!redditComment.showHeader) {
		return undefined;
	}

	const pfpId = Math.floor(random(redditComment.username) * 7);
	const pfpUrl = `https://www.redditstatic.com/avatars/defaults/v2/avatar_default_${pfpId}.png`;

	return (
		<div className="reddit-comment-header">
			<img className="reddit-comment-header-icon" src={pfpUrl} />

			<div className="labels">
				<p className="small">{redditComment.username}</p>
				<p className="small">•</p>
				<p className="small">{redditComment.timestamp}</p>
			</div>
		</div>
	);
}

function getFooter(redditComment: RedditComment) {
	if (!redditComment.showFooter) {
		return undefined;
	}

	return (
		<div style={{display: 'flex', flexDirection: 'row-reverse'}}>
			<p className="small">{redditComment.score}</p>
		</div>
	);
}

function getLines(redditComment: RedditComment) {
	return redditComment.audio.text
		.split('\n')
		.map((line: string, index: number) => <p key={index}>{line}</p>);
}

export const RedditCommentComponent = (props: Props) => {
	const redditComment = props.redditComment;
	const muteAudio = props.muteAudio;

	return (
		<div className="reddit-comment">
			{getDepthPadding(redditComment)}

			<div className="reddit-comment-content">
				{getHeader(redditComment)}
				{getLines(redditComment)}
				{getFooter(redditComment)}
			</div>

			{playAudio(redditComment.audio, muteAudio)}
		</div>
	);
};
