package com.ivanob.puntalradio.helper;

import com.ivanob.puntalradio.model.TweetBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by ivan on 11/8/16.
 */
public class TwitterHandler {
    private Twitter twitter;

    public TwitterHandler(){
        TwitterFactory tf = new TwitterFactory();
        twitter = tf.getInstance();
    }

    public List<TweetBean> loadTweets(){
        List<TweetBean> tweets = new ArrayList<TweetBean>();
        try {
            List<Status> statuses;
            String user;
            user = "puntalradio";
            statuses = twitter.getUserTimeline(user);
            System.out.println("Showing @" + user + "'s user timeline.");
            for (Status status : statuses) {
                //System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                tweets.add(new TweetBean(status.getUser().getScreenName(), status.getText(),
                        status.getCreatedAt(), status.getUser().getProfileImageURL()));
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
        return tweets;
    }


}
