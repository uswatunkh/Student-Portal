package com.example.studentportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class pengumuman_newAdapter  extends RecyclerView.Adapter<pengumuman_newAdapter.NewsViewHolder>{

    Context mContext;
    List<PengumumanItem> mData;

    public pengumuman_newAdapter(Context mContext, List<PengumumanItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public pengumuman_newAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.pengumuman_item_news, viewGroup, false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
      //animation card
        newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));


        //bind data here
        newsViewHolder.tv_content.setText(mData.get(position).getIsi());
        newsViewHolder.tv_date.setText(mData.get(position).getTgl());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NewsViewHolder extends  RecyclerView.ViewHolder {

        TextView tv_title, tv_content, tv_date;
        RelativeLayout container;
        public NewsViewHolder(@NonNull View itemVew){
            super(itemVew);
            container = itemVew.findViewById(R.id.container);
//            tv_title = itemVew.findViewById(R.id.tv_title);
            tv_content = itemVew.findViewById(R.id.tv_description);
            tv_date = itemVew.findViewById(R.id.tv_date);

        }
    }
}
