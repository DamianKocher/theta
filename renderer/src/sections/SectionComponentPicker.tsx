import { RedditTitlecard, Section } from "../Definitions";
import { RedditCommentComponent } from "./RedditComment";
import { RedditTitlecardComponent } from "./RedditTitlecard";

function isRedditTitlecard(section: Section): section is RedditTitlecard {
  return section._meta.type === "reddit_titlecard";
}

function isRedditComment(section: Section): section is RedditTitlecard {
  return section._meta.type === "reddit_comment";
}

interface Props {
  section: Section;
  isPrimary: boolean
}

export const SectionComponentPicker = (props: Props) => {
  const section = props.section;
  const isPrimary = props.isPrimary;

  return (
    <div>
      {isRedditTitlecard(section) && <RedditTitlecardComponent redditTitlecard={section} muteAudio={!isPrimary} />}
      {isRedditComment(section) && <RedditCommentComponent redditComment={section} muteAudio={!isPrimary} />}
    </div>
  );
};
