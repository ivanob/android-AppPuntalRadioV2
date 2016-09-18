package com.ivanob.puntalradio.model;

import java.util.Date;

/**
 * Created by ivan on 15/8/16.
 */
public class TweetBean {
    private String user;
    private String tweet;
    private Date date;
    private String urlImgProfile;

    public TweetBean(String user, String tweet, Date createdDate, String urlImgProfile){
        this.user=user;
        this.tweet=tweet;
        this.date = createdDate;
        this.urlImgProfile = urlImgProfile;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrlImgProfile() {
        return urlImgProfile;
    }

    public void setUrlImgProfile(String urlImgProfile) {
        this.urlImgProfile = urlImgProfile;
    }
}
