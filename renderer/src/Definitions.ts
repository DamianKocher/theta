export interface Content {
	background: string;
	sections: Section[];
	durationInFrames: number;
}

export interface AudioSource {
	url: string;
	text: string;
}

export interface Section {
	_meta: SectionMeta;
}

export interface SectionMeta {
	type: string;
	useStandardContainer: boolean;
	showPrevious: boolean;
	showNext: boolean;
	fadeIn: boolean;
	fadeOut: boolean;
	duration: number;
	description: string;

	durationInFrames: number;
}

export interface RedditTitlecard extends Section {
	subreddit: string;
	username: string;
	timestamp: string;

	audio: AudioSource;
}

export interface RedditComment extends Section {
	showHeader: boolean;
	showFooter: boolean;

	commentDepth: number;
	username: string;
	timestamp: string;
	score: number;

	audio: AudioSource;
}
