package com.example.studentportal.FragementHari;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;

import java.util.List;

public class Hari_TableViewAdapter extends RecyclerView.Adapter {

    List<Hari_list> mData;
    Context mContext;



    public Hari_TableViewAdapter(Context mContext,List<Hari_list> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.hari_list, parent, false);

        return new Hari_TableViewAdapter.RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Hari_TableViewAdapter.RowViewHolder rowViewHolder = (Hari_TableViewAdapter.RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtNamaMataKuliah.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtDosen.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtJamke.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtRuang.setBackgroundResource(R.drawable.table_bg);

            rowViewHolder.txtKode.setText("Kode");
            rowViewHolder.txtKode.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtNamaMataKuliah.setText("Nama Matakuliah");
            rowViewHolder.txtNamaMataKuliah.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtDosen.setText("Dosen");
            rowViewHolder.txtDosen.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtJamke.setText("Jam Ke");
            rowViewHolder.txtJamke.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtRuang.setText("Ruang");
            rowViewHolder.txtRuang.setTextColor(Color.parseColor("#FFFFFF"));

        } else {
            Hari_list modal = (Hari_list) mData.get(rowPos - 1);

            rowViewHolder.txtKode.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtNamaMataKuliah.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtDosen.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtJamke.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtRuang.setBackgroundResource(R.drawable.cardview);

            rowViewHolder.txtKode.setText(modal.getKode());
            rowViewHolder.txtNamaMataKuliah.setText(modal.getNamaMatakuliah());
            rowViewHolder.txtDosen.setText(modal.getDosen());
            rowViewHolder.txtJamke.setText(modal.getJamke());
            rowViewHolder.txtRuang.setText(modal.getRuang());
        }

    }

    @Override
    public int getItemCount() {

        return mData.size()+1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtKode;
        TextView txtNamaMataKuliah;
        TextView txtDosen;
        TextView txtJamke;
        TextView txtRuang;

        RowViewHolder(View itemView) {
            super(itemView);
            txtKode = itemView.findViewById(R.id.txtKode);
            txtNamaMataKuliah = itemView.findViewById(R.id.txtNamaMataKuliah);
            txtDosen = itemView.findViewById(R.id.txtDosen);
            txtJamke = itemView.findViewById(R.id.txtJamke);
            txtRuang = itemView.findViewById(R.id.txtRuang);
        }
    }
}
