import {RedditComment, RedditTitlecard, Section} from '../Definitions';
import {RedditCommentComponent} from './reddit/comment/RedditComment';
import {RedditTitlecardComponent} from './reddit/titlecard/RedditTitlecard';

function isSectionType<Type>(section: any, type: string): section is Type {
	return section._meta.type === type;
}

interface Props {
	section: Section;
	isPrimary: boolean;
}

export const SectionComponentPicker = (props: Props) => {
	const section = props.section;
	const isPrimary = props.isPrimary;

	return (
		<div>
			{isSectionType<RedditTitlecard>(section, 'reddit_titlecard') && (
				<RedditTitlecardComponent
					redditTitlecard={section}
					muteAudio={!isPrimary}
				/>
			)}
			{isSectionType<RedditComment>(section, 'reddit_comment') && (
				<RedditCommentComponent
					redditComment={section}
					muteAudio={!isPrimary}
				/>
			)}
		</div>
	);
};
