package com.example.studentportal;

import android.content.Context;
import android.content.Intent;
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

public class HasilStudiAwalNewAdapter extends RecyclerView.Adapter<HasilStudiAwalNewAdapter.NewsViewHolder> {

    Context mContext;
    List<HasilStudiAwalItem> mData;

    public HasilStudiAwalNewAdapter(Context mContext, List<HasilStudiAwalItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public HasilStudiAwalNewAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.hasilstudi_awal, viewGroup, false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
        //animation card
        newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));


        //bind data here
        newsViewHolder.semester.setText(mData.get(position).getSemester());
        newsViewHolder.ubah_keterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(mContext, HasilStudi.class);
                mContext.startActivity(intent);
            }
        });


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
