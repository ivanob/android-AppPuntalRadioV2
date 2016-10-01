package com.ivanob.puntalradio.fragments;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.model.RadioProgrammingManager;

/**
 * Created by ivan on 24/9/16.
 */
public class AdapterProgramas extends RecyclerView.Adapter<AdapterProgramas.ProgramViewHolder> {
    private RadioProgrammingManager progManager;

    public static class ProgramViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView thumbnail;
        TextView name;
        TextView description;

        ProgramViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view_program);
            name = (TextView)itemView.findViewById(R.id.program_name);
            description = (TextView)itemView.findViewById(R.id.program_description);
            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail_program);
        }
    }

    public AdapterProgramas(RadioProgrammingManager pm) {
        progManager = pm;
    }

    @Override
    public ProgramViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.drawable.card_programa, viewGroup, false);
        ProgramViewHolder pvh = new ProgramViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProgramViewHolder holder, int position) {
        holder.name.setText(progManager.getProgram(position).getNombre());
        holder.description.setText(progManager.getProgram(position).getDescripcion());
        holder.thumbnail.setImageResource(progManager.getProgram(position).getIdLogo());
    }

    @Override
    public int getItemCount() {
        return progManager.getNumPrograms();
    }
}
