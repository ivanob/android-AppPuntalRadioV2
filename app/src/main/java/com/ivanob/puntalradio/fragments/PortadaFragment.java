package com.ivanob.puntalradio.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.helper.CustomListAdapter;
import com.ivanob.puntalradio.helper.TwitterHandler;
import com.ivanob.puntalradio.model.ConfigBean;
import com.ivanob.puntalradio.model.TweetBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import twitter4j.auth.RequestToken;

/**
 * Created by ivan on 7/6/16.
 */
public class PortadaFragment extends Fragment {

    private RequestToken requestToken;
    private TwitterHandler twitterHandler = new TwitterHandler();

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    private ConfigBean config;

    public PortadaFragment(){}

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

    private void initializeSocialButtons(FragmentActivity act) {
        ImageButton imgBtnFB = (ImageButton) act.findViewById(R.id.socialBtnFB);
        imgBtnFB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(config.getFacebook()));
                startActivity(i);
            }
        });

        ImageButton imgBtnTweeter = (ImageButton) act.findViewById(R.id.socialBtnTwitter);
        imgBtnTweeter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(config.getTwitter()));
                startActivity(i);
            }
        });

        ImageButton imgBtnBlog = (ImageButton) act.findViewById(R.id.socialBtnBlog);
        imgBtnBlog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(config.getBlog()));
                startActivity(i);
            }
        });

        ImageButton imgBtnEmail = (ImageButton) act.findViewById(R.id.socialBtnEmail);
        imgBtnEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{config.getEmail()});
                try {
                    startActivity(Intent.createChooser(i, "Enviando correo..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "No hay ningún cliente de correo instalado.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        fillTweetList();
        initializeSocialButtons(getActivity());
    }


}
