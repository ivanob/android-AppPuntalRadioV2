package com.ivanob.puntalradio.fragments;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.helper.ExpandAndCollapseUtils;
import com.ivanob.puntalradio.model.RadioProgrammingManager;

/**
 * Created by ivan on 24/9/16.
 */
public class AdapterProgramas extends RecyclerView.Adapter<AdapterProgramas.ProgramViewHolder> {
    private RadioProgrammingManager progManager;
    private int expandedPosition = -1;

    public static class ProgramViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView thumbnail;
        TextView name;
        TextView description;
        View details;
        private ViewGroup linearLayoutDetails;
        private static final int DURATION = 250;
        private ImageView imageViewExpand;

        ProgramViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view_program);
            name = (TextView) itemView.findViewById(R.id.program_name);
            description = (TextView) itemView.findViewById(R.id.program_description);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail_program);
            details = itemView.findViewById(R.id.details_section);
            linearLayoutDetails = (ViewGroup) itemView.findViewById(R.id.linearLayoutDetails);
            imageViewExpand = (ImageView) itemView.findViewById(R.id.imageViewExpand);
            details.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
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
            });
        }

            private void rotate(float angle) {
                Animation animation = new RotateAnimation(0.0f, angle, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setFillAfter(true);
                animation.setDuration(DURATION);
                imageViewExpand.startAnimation(animation);
            }

    }


    public AdapterProgramas(RadioProgrammingManager pm) {
        progManager = pm;
    }


    public ProgramViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.drawable.card_programa, viewGroup, false);
        ProgramViewHolder pvh = new ProgramViewHolder(v);
        return pvh;
    }


    public void onBindViewHolder(ProgramViewHolder holder, int position) {
        holder.name.setText(progManager.getProgram(position).getNombre());
        holder.description.setText(progManager.getProgram(position).getDescripcion());
        holder.thumbnail.setImageResource(progManager.getProgram(position).getIdLogo());
    }


    public int getItemCount() {
        return progManager.getNumPrograms();
    }
}
