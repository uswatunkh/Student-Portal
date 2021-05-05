package com.example.studentportal.Fitur;

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

public class DaftarUlang_TableViewAdapter extends RecyclerView.Adapter {

    List<DaftarUlang_list> mData;
    Context mContext;



    public DaftarUlang_TableViewAdapter(Context mContext,List<DaftarUlang_list> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.daftar_ulang_lisitem, parent, false);

        return new DaftarUlang_TableViewAdapter.RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DaftarUlang_TableViewAdapter.RowViewHolder rowViewHolder = (DaftarUlang_TableViewAdapter.RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtPeriode.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtUkt.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtCetakKrs.setBackgroundResource(R.drawable.table_bg);

            rowViewHolder.txtPeriode.setText("Periode Akademik");
            rowViewHolder.txtPeriode.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtUkt.setText("UKT");
            rowViewHolder.txtUkt.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtStatus.setText("Status");
            rowViewHolder.txtStatus.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtCetakKrs.setText("Cetak KRS");
            rowViewHolder.txtCetakKrs.setTextColor(Color.parseColor("#FFFFFF"));

        } else {
            DaftarUlang_list modal = (DaftarUlang_list) mData.get(rowPos - 1);

            rowViewHolder.txtPeriode.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtUkt.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtCetakKrs.setBackgroundResource(R.drawable.cardview);

            rowViewHolder.txtPeriode.setText(modal.getPeriodeAkademik() + "");
            rowViewHolder.txtUkt.setText(modal.getUkt());
            rowViewHolder.txtStatus.setText(modal.getStatus() + "");
            rowViewHolder.txtCetakKrs.setText(modal.getCetakKrs() + "");
        }

    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtPeriode;
        TextView txtUkt;
        TextView txtStatus;
        TextView txtCetakKrs;

        RowViewHolder(View itemView) {
            super(itemView);
            txtPeriode = itemView.findViewById(R.id.txtPeriode);
            txtUkt = itemView.findViewById(R.id.txtUkt);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtCetakKrs = itemView.findViewById(R.id.txtCetakKrs);
        }
    }
}
