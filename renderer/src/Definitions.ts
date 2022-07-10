export interface Content {
	background: Background;
	sections: (RedditComment | RedditTitlecard)[]
}

export interface Background {
	url: string;
}

export interface Audio {
	url: string;
}

export interface Section {
	_meta: {
		type: string;
		useStandardContainer: boolean;
		showPrevious: boolean;
		showNext: boolean;
		fadeIn: boolean;
		fadeOut: boolean;
		duration: number;
	}
}

export interface RedditTitlecard extends Section {
	subreddit: string;
	username: string;
	text: string;
	audio: Audio;
	timestamp: string;
}

export interface RedditComment extends Section {
	text: string;
	audio: Audio;
}