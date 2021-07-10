package com.example.studentportal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.Fitur.DataKuisioner;

import java.util.List;

public class RekapPresensi_Adapter  extends RecyclerView.Adapter<RekapPresensi_Adapter.RowViewHolder> {

     static List<RekapPresensi_list> mData;
    Context mContext;



    public RekapPresensi_Adapter(Context mContext,List<RekapPresensi_list> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.rekap_presensi_list, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            rowViewHolder.semester.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.hadir.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.izin.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.sakit.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.kosong.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.detail.setBackgroundResource(R.drawable.table_bg);

            rowViewHolder.semester.setText("Semester");
            rowViewHolder.semester.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.hadir.setText("Hadir");
            rowViewHolder.hadir.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.izin.setText("Izin");
            rowViewHolder.izin.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.sakit.setText("Sakit");
            rowViewHolder.sakit.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.kosong.setText("Alpha");
            rowViewHolder.kosong.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.detail.setText("Detail");
            rowViewHolder.detail.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            RekapPresensi_list modal = (RekapPresensi_list) mData.get(rowPos - 1);
            rowViewHolder.semester.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.hadir.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.izin.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.sakit.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.kosong.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.detail.setBackgroundResource(R.drawable.cardview);

           // String id =mData.get(position).getIdRekapPresensi();
            rowViewHolder.semester.setText(modal.getSemester() + "");
            rowViewHolder.hadir.setText(modal.getHadir()+ "");
            rowViewHolder.izin.setText(modal.getIzin());
            rowViewHolder.sakit.setText(modal.getSakit() + "");
            rowViewHolder.kosong.setText(modal.getKosong() + "");

            rowViewHolder.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = modal.getIdRekapPresensi();
                    Toast.makeText(mContext,id,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent((RekapPresensi)mContext, Presensi.class);
                    intent.putExtra("position",position);
                    ((RekapPresensi)mContext).startActivity(intent);

                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return mData.size()+1 ;
    }

    public static class RowViewHolder extends RecyclerView.ViewHolder {
        TextView semester;
        TextView hadir;
        TextView izin;
        TextView sakit;
        TextView kosong;
        TextView detail;

        RowViewHolder(View itemView) {
            super(itemView);
            semester = itemView.findViewById(R.id.semester);
            hadir = itemView.findViewById(R.id.hadir);
            izin = itemView.findViewById(R.id.izin);
            sakit = itemView.findViewById(R.id.sakit);
            kosong = itemView.findViewById(R.id.kosong);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}