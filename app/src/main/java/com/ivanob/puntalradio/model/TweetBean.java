package com.ivanob.puntalradio.model;

/**
 * Created by ivan on 15/8/16.
 */
public class TweetBean {
    private String user;
    private String tweet;

    public TweetBean(String user, String tweet){
        this.user=user;
        this.tweet=tweet;
    }
    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
