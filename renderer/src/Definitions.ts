export interface Content {
	background: string;
	sections: Section[];
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
	};
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
