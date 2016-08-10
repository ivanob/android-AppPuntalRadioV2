package com.ivanob.puntalradio.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ivanob.puntalradio.R;

/**
 * Created by ivan on 7/6/16.
 */
public class ParrillaFragment extends Fragment{
    private WebView webViewTimetable;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.parrilla_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        webViewTimetable= (WebView)getActivity().findViewById(R.id.webViewTimetable);
        webViewTimetable.loadUrl("file:///android_asset/time_table.html");
        WebSettings webSettings = webViewTimetable.getSettings();
        webSettings.setTextSize(WebSettings.TextSize.SMALLEST);
        super.onActivityCreated(savedInstanceState);
    }
}
