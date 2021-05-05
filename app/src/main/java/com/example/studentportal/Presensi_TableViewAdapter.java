package com.example.studentportal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Presensi_TableViewAdapter extends RecyclerView.Adapter {

    List<Presensi_list> mData;
    Context mContext;



    public Presensi_TableViewAdapter(Context mContext,List<Presensi_list> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.presensi_lisitem, parent, false);

        return new Presensi_TableViewAdapter.RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Presensi_TableViewAdapter.RowViewHolder rowViewHolder = (Presensi_TableViewAdapter.RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtNomor.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtMingguKe.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtTanggalPresensi.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtNamaMK.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtKet.setBackgroundResource(R.drawable.table_bg);

            rowViewHolder.txtNomor.setText("No");
            rowViewHolder.txtNomor.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtMingguKe.setText("Minggu Ke");
            rowViewHolder.txtMingguKe.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtTanggalPresensi.setText("Tanggal Presensi");
            rowViewHolder.txtTanggalPresensi.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtNamaMK.setText("Kuliah");
            rowViewHolder.txtNamaMK.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtKet.setText("Keterangan");
            rowViewHolder.txtKet.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            Presensi_list modal = (Presensi_list) mData.get(rowPos - 1);

            rowViewHolder.txtNomor.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtMingguKe.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtTanggalPresensi.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtNamaMK.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtKet.setBackgroundResource(R.drawable.cardview);

            //rowViewHolder.txtNomor.setText(modal.getKode() + "");
            rowViewHolder.txtMingguKe.setText(modal.getMingguKe());
            rowViewHolder.txtTanggalPresensi.setText(modal.getTanggalPresensi() + "");
            rowViewHolder.txtNamaMK.setText(modal.getNamaMK() + "");
            rowViewHolder.txtKet.setText(modal.getKet() + "");
        }

    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomor;
        TextView txtMingguKe;
        TextView txtTanggalPresensi;
        TextView txtNamaMK;
        TextView txtKet;

        RowViewHolder(View itemView) {
            super(itemView);
            txtNomor = itemView.findViewById(R.id.txtNomor);
            txtMingguKe = itemView.findViewById(R.id.txtMingguKe);
            txtTanggalPresensi = itemView.findViewById(R.id.txtTanggalPresensi);
            txtNamaMK = itemView.findViewById(R.id.txtNamaMK);
            txtKet = itemView.findViewById(R.id.txtKet);
        }
    }
}