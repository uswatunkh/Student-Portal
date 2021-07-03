package com.example.studentportal.FragementHari;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;

import org.w3c.dom.Text;

import java.util.List;

public class Hari_TableViewAdapter extends RecyclerView.Adapter<Hari_TableViewAdapter.RowViewHolder> {

    List<Hari_list> mData;
    Context mContext;



    public Hari_TableViewAdapter(Context mContext,List<Hari_list> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.hari_list, parent, false);

        return new Hari_TableViewAdapter.RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

//        int rowPos = rowViewHolder.getAdapterPosition();
//
//        if (rowPos == 0) {
//            rowViewHolder.txtNomor.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtNamaMataKuliah.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtDosen.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtJamke.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtRuang.setBackgroundResource(R.drawable.table_bg);
//
//            rowViewHolder.txtNomor.setText("#");
//            rowViewHolder.txtNomor.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtKode.setText("Kode");
//            rowViewHolder.txtKode.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtNamaMataKuliah.setText("Nama Matakuliah");
//            rowViewHolder.txtNamaMataKuliah.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtDosen.setText("Dosen");
//            rowViewHolder.txtDosen.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtJamke.setText("Jam Ke");
//            rowViewHolder.txtJamke.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtRuang.setText("Ruang");
//            rowViewHolder.txtRuang.setTextColor(Color.parseColor("#FFFFFF"));
//
//        } else {
//            Hari_list modal = (Hari_list) mData.get(rowPos - 1);

            holder.txtNomor.setBackgroundResource(R.drawable.cardview);
            holder.txtKode.setBackgroundResource(R.drawable.cardview);
            holder.txtNamaMataKuliah.setBackgroundResource(R.drawable.cardview);
            holder.txtDosen.setBackgroundResource(R.drawable.cardview);
            holder.txtJamke.setBackgroundResource(R.drawable.cardview);
            holder.txtRuang.setBackgroundResource(R.drawable.cardview);

            holder.txtNomor.setText(mData.get(position).getIdNomor());
            holder.txtKode.setText(mData.get(position).getKode());
            holder.txtNamaMataKuliah.setText(mData.get(position).getNamaMatakuliah());
            holder.txtDosen.setText(mData.get(position).getDosen());
            holder.txtJamke.setText(mData.get(position).getJamke());
            holder.txtRuang.setText(mData.get(position).getRuang());
      //  }

    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    public static class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtKode;
        TextView txtNamaMataKuliah;
        TextView txtDosen;
        TextView txtJamke;
        TextView txtRuang;
        TextView txtNomor;

        RowViewHolder(View itemView) {
            super(itemView);
            txtNomor = itemView.findViewById(R.id.txtNomor);
            txtKode = itemView.findViewById(R.id.txtKode);
            txtNamaMataKuliah = itemView.findViewById(R.id.txtNamaMataKuliah);
            txtDosen = itemView.findViewById(R.id.txtDosen);
            txtJamke = itemView.findViewById(R.id.txtJamke);
            txtRuang = itemView.findViewById(R.id.txtRuang);
        }
    }
}
