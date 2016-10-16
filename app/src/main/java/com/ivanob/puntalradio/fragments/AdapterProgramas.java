package com.ivanob.puntalradio.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.helper.ExpandAndCollapseUtils;
import com.ivanob.puntalradio.model.RadioProgram;
import com.ivanob.puntalradio.model.RadioProgrammingManager;

/**
 * Created by ivan on 24/9/16.
 */
public class AdapterProgramas extends RecyclerView.Adapter<AdapterProgramas.ProgramViewHolder> {
    private RadioProgrammingManager progManager;
    private int expandedPosition = -1;
    private Context ctx;

    public static class ProgramViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView thumbnail;
        TextView name;
        TextView description;
        TextView info;
        View details;
        ImageButton btnFacebook, btnTwitter, btnBlog, btnPodcast;
        LinearLayout socialDetalles;
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
            info = (TextView) itemView.findViewById(R.id.textDetalles);
            btnFacebook = (ImageButton) itemView.findViewById(R.id.socialBtnFB);
            btnTwitter = (ImageButton) itemView.findViewById(R.id.socialBtnTwitter);
            btnPodcast = (ImageButton) itemView.findViewById(R.id.socialBtnPodcast);
            btnBlog = (ImageButton) itemView.findViewById(R.id.socialBtnBlogger);
            socialDetalles = (LinearLayout)itemView.findViewById(R.id.socialDetalles);
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
            });}

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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_programa, viewGroup, false);
        ctx = v.getContext();
        ProgramViewHolder pvh = new ProgramViewHolder(v);
        return pvh;
    }


    public void onBindViewHolder(ProgramViewHolder holder, int position) {
        final RadioProgram prog = progManager.getProgram(position);
        holder.name.setText(prog.getNombre());
        String[] descrChunks = splitDescription(prog.getDescripcion());
        holder.description.setText(descrChunks[0]);
        holder.thumbnail.setImageResource(prog.getIdLogo());
        holder.info.setText(Html.fromHtml(buildDetailsSection(holder, position, descrChunks[1])));
        holder.linearLayoutDetails.removeView(holder.btnFacebook);
        if(prog.getMediaURL("facebook")!=null) {
            holder.btnFacebook.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(prog.getMediaURL("facebook")));
                    ctx.startActivity(i);
                }
            });
        }else{
            holder.btnFacebook.setVisibility(View.GONE);
        }
        if(prog.getMediaURL("twitter")!=null) {
            holder.btnTwitter.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(prog.getMediaURL("twitter")));
                    ctx.startActivity(i);
                }
            });
        }else{
            holder.btnTwitter.setVisibility(View.GONE);
        }
        if(prog.getMediaURL("podcast")!=null) {
            holder.btnPodcast.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(prog.getMediaURL("podcast")));
                    ctx.startActivity(i);
                }
            });
        }else{
            holder.btnPodcast.setVisibility(View.GONE);
        }
        if(prog.getMediaURL("blog")!=null) {
            holder.btnBlog.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(prog.getMediaURL("blog")));
                    ctx.startActivity(i);
                }
            });
        }else{
            holder.btnBlog.setVisibility(View.GONE);
        }
    }

    /**
     * Just to avoid cutting one word in a half when we split the description in 2 chunks. I also
     * add the String "..." at the end of the first part.
     * @param str
     * @param idx
     * @return
     */
    private int calcIndexNextWord(String str, int idx){
        while(str.charAt(idx)!=' ' && idx<str.length()){
            idx++;
        }
        return idx;
    }

    private String[] splitDescription(String description){
        String[] chunks = new String[2];
        if(description.length() > 250){
            int idxNextWord = calcIndexNextWord(description, 200);
            chunks[0] = description.substring(0, idxNextWord) + "...";
            chunks[1] = "..." + description.substring(idxNextWord+1);
        }else{
            chunks[0] = description;
        }
        return chunks;
    }

    private String buildDetailsSection(ProgramViewHolder holder, int position, String descr){
        String details = "";
        if(descr!=null){
            details += descr + "<br>";
        }
        details += "<b>Presentador: </b>" + progManager.getProgram(position).getPresentador() + "<br>";
        String colab = progManager.getProgram(position).getColaborador();
        if(colab!=null) details += "<b>Colaboración: </b>" + colab + "<br>";
        details += "<b>Emisión: </b>" + progManager.getProgram(position).getHorarioEmision() + "<br>";
        String redif = progManager.getProgram(position).getHorarioRedifusion();
        if(redif != null) details += "<b>Redifusión: </b>" + redif;
        return details;
    }

    public int getItemCount() {
        return progManager.getNumPrograms();
    }
}
