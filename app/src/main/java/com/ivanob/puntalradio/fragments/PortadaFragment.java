package com.ivanob.puntalradio.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.helper.TwitterHandler;

import twitter4j.auth.RequestToken;

/**
 * Created by ivan on 7/6/16.
 */
public class PortadaFragment extends Fragment {

    private RequestToken requestToken;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TwitterHandler.getInstance().loadTweets();
        return inflater.inflate(R.layout.parrilla_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


}
