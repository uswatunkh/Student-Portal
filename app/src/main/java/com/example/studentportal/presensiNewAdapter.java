package com.example.studentportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class presensiNewAdapter extends RecyclerView.Adapter<presensiNewAdapter.NewsViewHolder> {

    Context mContext;
    List<presensiItem> mData;

    public presensiNewAdapter(Context mContext, List<presensiItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public presensiNewAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.presensi, viewGroup, false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull presensiNewAdapter.NewsViewHolder newsViewHolder, int position) {
        //animation card
        newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));


        //bind data here
        newsViewHolder.semester.setText(mData.get(position).getSemester());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView semester;
        CardView container;
        LinearLayout ubah_keterampilan;


        public NewsViewHolder(@NonNull View itemVew) {
            super(itemVew);
            ubah_keterampilan = itemVew.findViewById(R.id.ubah_keterampilan);
            container = itemVew.findViewById(R.id.container);
            semester = itemVew.findViewById(R.id.semester);



        }
    }
}

