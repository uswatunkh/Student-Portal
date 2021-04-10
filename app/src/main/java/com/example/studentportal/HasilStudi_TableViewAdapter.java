package com.example.studentportal;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HasilStudi_TableViewAdapter  extends RecyclerView.Adapter {

    private List movieList;



    public HasilStudi_TableViewAdapter(List movieList) {

        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.hasil_studi_listitem, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtNamaKuliah.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtSks.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtNilaiAngka.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtNilaiHuruf.setBackgroundResource(R.drawable.table_bg);
            rowViewHolder.txtTotalNilai.setBackgroundResource(R.drawable.table_bg);

            rowViewHolder.txtKode.setText("Kode");
            rowViewHolder.txtKode.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtNamaKuliah.setText("Nama Matakuliah");
            rowViewHolder.txtNamaKuliah.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtSks.setText("Sks Teori/Praktek");
            rowViewHolder.txtSks.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtNilaiAngka.setText("Nilai Angka");
            rowViewHolder.txtNilaiAngka.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtNilaiHuruf.setText("Nilai Huruf");
            rowViewHolder.txtNilaiHuruf.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtTotalNilai.setText("Total Nilai");
            rowViewHolder.txtTotalNilai.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            HasilStudi_list modal = (HasilStudi_list) movieList.get(rowPos - 1);

            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_bg_dua);
            rowViewHolder.txtNamaKuliah.setBackgroundResource(R.drawable.table_bg_dua);
            rowViewHolder.txtSks.setBackgroundResource(R.drawable.table_bg_dua);
            rowViewHolder.txtNilaiAngka.setBackgroundResource(R.drawable.table_bg_dua);
            rowViewHolder.txtNilaiHuruf.setBackgroundResource(R.drawable.table_bg_dua);
            rowViewHolder.txtTotalNilai.setBackgroundResource(R.drawable.table_bg_dua);

            rowViewHolder.txtKode.setText(modal.getKode() + "");
            rowViewHolder.txtNamaKuliah.setText(modal.getNamaKuliah());
            rowViewHolder.txtSks.setText(modal.getSks() + "");
            rowViewHolder.txtNilaiAngka.setText(modal.getNilaiAngka() + "");
            rowViewHolder.txtNilaiHuruf.setText(modal.getNilaiHuruf() + "");
            rowViewHolder.txtTotalNilai.setText(modal.getTotalNilai() + "");
        }

    }

    @Override
    public int getItemCount() {
        return movieList.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtKode;
        TextView txtNamaKuliah;
        TextView txtSks;
        TextView txtNilaiAngka;
        TextView txtNilaiHuruf;
        TextView txtTotalNilai;

        RowViewHolder(View itemView) {
            super(itemView);
            txtKode = itemView.findViewById(R.id.txtKode);
            txtNamaKuliah = itemView.findViewById(R.id.txtNamaKuliah);
            txtSks = itemView.findViewById(R.id.txtSks);
            txtNilaiAngka = itemView.findViewById(R.id.txtNilaiAngka);
            txtNilaiHuruf = itemView.findViewById(R.id.txtNilaiHuruf);
            txtTotalNilai = itemView.findViewById(R.id.txtTotalNilai);
        }
    }
}