package com.damiankocher.theta.server;

import java.util.List;

public class Script {

    public String name;
    public String background;

    public String subreddit;
    public String username;
    public String time;
    public String text;

    public List<Comment> comments;

    public static class Comment {
        public int commentDepth;
        public String username;
        public String time;

        public List<String> sections;
    }
}
