package com.example.studentportal;

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

import com.example.studentportal.Fitur.DataKuisioner;

import java.util.List;

public class HasilStudi_TableViewAdapter  extends RecyclerView.Adapter<HasilStudi_TableViewAdapter.RowViewHolder> {

    List<HasilStudi_list> mData;
    Context mContext;



    public HasilStudi_TableViewAdapter(Context mContext,List<HasilStudi_list> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.hasil_studi_listitem, parent, false);

        return new RowViewHolder(itemView);
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
//            rowViewHolder.txtNamaKuliah.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtSks.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtNilaiAngka.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtNilaiHuruf.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtTotalNilai.setBackgroundResource(R.drawable.table_bg);
//
//            rowViewHolder.txtNomor.setText("#");
//            rowViewHolder.txtNomor.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtKode.setText("Kode");
//            rowViewHolder.txtKode.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtNamaKuliah.setText("Nama Matakuliah");
//            rowViewHolder.txtNamaKuliah.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtSks.setText("Sks Teori/Praktek");
//            rowViewHolder.txtSks.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtNilaiAngka.setText("Nilai Angka");
//            rowViewHolder.txtNilaiAngka.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtNilaiHuruf.setText("Nilai Huruf");
//            rowViewHolder.txtNilaiHuruf.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtTotalNilai.setText("Total Nilai");
//            rowViewHolder.txtTotalNilai.setTextColor(Color.parseColor("#FFFFFF"));
//        } else {
//            HasilStudi_list modal = (HasilStudi_list) mData.get(rowPos - 1);
            holder.txtNomor.setBackgroundResource(R.drawable.cardview);
            holder.txtKode.setBackgroundResource(R.drawable.cardview);
            holder.txtNamaKuliah.setBackgroundResource(R.drawable.cardview);
            holder.txtSks.setBackgroundResource(R.drawable.cardview);
            holder.txtNilaiAngka.setBackgroundResource(R.drawable.cardview);
            holder.txtNilaiHuruf.setBackgroundResource(R.drawable.cardview);
            holder.txtTotalNilai.setBackgroundResource(R.drawable.cardview);


            String kode =mData.get(position).getKode();
            holder.txtNomor.setText(mData.get(position).getIdNomor() + "");
            holder.txtKode.setText(kode+ "");
            holder.txtNamaKuliah.setText(mData.get(position).getNamaKuliah());
            holder.txtSks.setText(mData.get(position).getSks() + "");
            holder.txtNilaiAngka.setText(mData.get(position).getNilaiAngka() + "");
            holder.txtNilaiHuruf.setText(mData.get(position).getNilaiHuruf() + "");
            holder.txtTotalNilai.setText(mData.get(position).getTotalNilai() + "");


            if (kode.isEmpty()){

            }
      //  }

    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public static class RowViewHolder extends RecyclerView.ViewHolder {
        EditText txtNomor;
        EditText txtKode;
        EditText txtNamaKuliah;
        EditText txtSks;
        EditText txtNilaiAngka;
        EditText txtNilaiHuruf;
        EditText txtTotalNilai;

        RowViewHolder(View itemView) {
            super(itemView);
            txtNomor = itemView.findViewById(R.id.txtNomor);
            txtKode = itemView.findViewById(R.id.txtKode);
            txtNamaKuliah = itemView.findViewById(R.id.txtNamaMataKuliah);
            txtSks = itemView.findViewById(R.id.txtSks);
            txtNilaiAngka = itemView.findViewById(R.id.txtNilaiAngka);
            txtNilaiHuruf = itemView.findViewById(R.id.txtNilaiHuruf);
            txtTotalNilai = itemView.findViewById(R.id.txtTotalNilai);
        }
    }
}