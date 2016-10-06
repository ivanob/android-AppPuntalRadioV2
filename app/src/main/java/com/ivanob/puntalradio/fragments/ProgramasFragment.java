package com.ivanob.puntalradio.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.helper.ExpandAndCollapseUtils;
import com.ivanob.puntalradio.model.RadioProgrammingManager;

import java.io.IOException;

/**
 * Created by ivan on 7/6/16.
 */
public class ProgramasFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ViewGroup linearLayoutDetails;
    private static final int DURATION = 250;
    private ImageView imageViewExpand;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.programas_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        linearLayoutDetails = (ViewGroup) getActivity().findViewById(R.id.linearLayoutDetails);
        imageViewExpand = (ImageView) getActivity().findViewById(R.id.imageViewExpand);
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_programas);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        RadioProgrammingManager pm = null;
        try {
            pm = RadioProgrammingManager.getInstance(getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mAdapter = new AdapterProgramas(pm);
        mRecyclerView.setAdapter(mAdapter);
        super.onActivityCreated(savedInstanceState);
    }

    public void toggleDetails(View view) {
        if (linearLayoutDetails.getVisibility() == View.GONE) {
            ExpandAndCollapseUtils.expand(linearLayoutDetails, DURATION);
            imageViewExpand.setImageResource(R.mipmap.more);
            rotate(-180.0f);
        } else {
            ExpandAndCollapseUtils.collapse(linearLayoutDetails, DURATION);
            imageViewExpand.setImageResource(R.mipmap.less);
            rotate(180.0f);
        }
    }

    private void rotate(float angle) {
        Animation animation = new RotateAnimation(0.0f, angle, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(DURATION);
        imageViewExpand.startAnimation(animation);
    }

}
