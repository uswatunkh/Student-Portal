package com.example.studentportal;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        newsViewHolder.tv_title.setText(mData.get(position).getTitle());

        newsViewHolder.tv_content.setText(mData.get(position).getBody());
        newsViewHolder.tv_date.setText(mData.get(position).getTanggalPengumuman());
        if (newsViewHolder.tv_title.getText().toString().equals("INFORMASI")){
            newsViewHolder.tv_title.setBackgroundResource(R.drawable.informasi);
            newsViewHolder.tv_title.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (newsViewHolder.tv_title.getText().toString().equals("PERINGATAN")){
            newsViewHolder.tv_title.setBackgroundResource(R.drawable.peringatan);
            newsViewHolder.tv_title.setTextColor(Color.parseColor("#FFFFFF"));
        }else if (newsViewHolder.tv_title.getText().toString().equals("KUISIONER")){
            newsViewHolder.tv_title.setBackgroundResource(R.drawable.kuisioner);
            newsViewHolder.tv_title.setTextColor(Color.parseColor("#FFFFFF"));
        }else if (newsViewHolder.tv_title.getText().toString().equals("DOWNLOAD")){
            newsViewHolder.tv_title.setBackgroundResource(R.drawable.download_pengumuman);
            newsViewHolder.tv_title.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NewsViewHolder extends  RecyclerView.ViewHolder {

        TextView tv_title, tv_content, tv_date;
        CardView container;
        public NewsViewHolder(@NonNull View itemVew){
            super(itemVew);
            container = itemVew.findViewById(R.id.container);
            tv_title = itemVew.findViewById(R.id.tv_title);
            tv_content = itemVew.findViewById(R.id.tv_description);
            tv_date = itemVew.findViewById(R.id.tv_date);

        }
    }
}
