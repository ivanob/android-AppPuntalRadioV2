package com.ivanob.puntalradio.fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.helper.CustomListAdapter;
import com.ivanob.puntalradio.helper.TwitterHandler;
import com.ivanob.puntalradio.model.TweetBean;

import java.util.List;

import twitter4j.auth.RequestToken;

/**
 * Created by ivan on 7/6/16.
 */
public class PortadaFragment extends Fragment {

    private RequestToken requestToken;
    private TwitterHandler twitterHandler = new TwitterHandler();

    private void fillTweetList(){
        List<TweetBean> listTweets = twitterHandler.loadTweets();
        ListView listView = (ListView) getActivity().findViewById(R.id.list);
        CustomListAdapter adapter = new CustomListAdapter(getActivity(), listTweets);
        listView.setAdapter(adapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        return inflater.inflate(R.layout.portada_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        fillTweetList();
    }


}
