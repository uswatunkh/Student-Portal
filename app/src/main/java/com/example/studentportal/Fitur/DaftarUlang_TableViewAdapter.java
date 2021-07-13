package com.example.studentportal.Fitur;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.DaftarUlang;
import com.example.studentportal.HomeActivity;
import com.example.studentportal.R;
import com.example.studentportal.biodata;

import java.util.List;

public class DaftarUlang_TableViewAdapter extends RecyclerView.Adapter<DaftarUlang_TableViewAdapter.RowViewHolder> {

    List<DaftarUlang_list> mData;
    Context mContext;
    private Activity activity;



    public DaftarUlang_TableViewAdapter(Context mContext,List<DaftarUlang_list> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.daftar_ulang_lisitem, parent, false);

        return new DaftarUlang_TableViewAdapter.RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

//        if (rowPos == 0) {
//            rowViewHolder.txtNomor.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtPeriode.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtPeriode.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtUkt.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_bg);
//            //rowViewHolder.txtCetakKrs.setBackgroundResource(R.drawable.table_bg);
//
//            rowViewHolder.txtNomor.setText("#");
//            rowViewHolder.txtNomor.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtPeriode.setText("Periode Akademik");
//            rowViewHolder.txtPeriode.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtUkt.setText("UKT");
//            rowViewHolder.txtUkt.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtStatus.setText("Status");
//            rowViewHolder.txtStatus.setTextColor(Color.parseColor("#FFFFFF"));
////            rowViewHolder.txtCetakKrs.setText("Cetak KRS");
////            rowViewHolder.txtCetakKrs.setTextColor(Color.parseColor("#FFFFFF"));
////            rowViewHolder.txtCetakKrs.setText("Cetak KRS");
////            rowViewHolder.txtCetakKrs.setTextColor(Color.parseColor("#FFFFFF"));
//
//        } else {
           // DaftarUlang_list modal = (DaftarUlang_list) mData.get(rowPos - 1);
            holder.txtNomor.setBackgroundResource(R.drawable.cardview);
            holder.txtPeriode.setBackgroundResource(R.drawable.cardview);
            holder.txtUkt.setBackgroundResource(R.drawable.cardview);
            holder.txtStatus.setBackgroundResource(R.drawable.cardview);
//            rowViewHolder.txtCetakKrs.setBackgroundResource(R.drawable.cardview);

            holder.txtNomor.setText(mData.get(position).getIdNomor() + "");
            holder.txtPeriode.setText(mData.get(position).getPeriodeAkademik() + "");
            holder.txtUkt.setText(mData.get(position).getUkt());
            holder.txtStatus.setText(mData.get(position).getStatus() + "");
//            rowViewHolder.txtCetakKrs.setBackgroundResource(R.drawable.download_pdf);
        //}

        String status=mData.get(position).getStatus();
        if(status.equals("Lunas")){
            holder.txtStatus.setBackgroundResource(R.drawable.aktif);
            holder.txtStatus.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (status.equals("Belum Lunas")){
            holder.txtStatus.setBackgroundResource(R.drawable.belumlunas);
            holder.txtStatus.setTextColor(Color.parseColor("#FFFFFF"));
        }

    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public static class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtPeriode;
        TextView txtUkt;
        TextView txtStatus;
        TextView txtCetakKrs;
        EditText txtNomor;
        Button btnDownload;

        RowViewHolder(View itemView) {
            super(itemView);
            txtNomor = itemView.findViewById(R.id.txtNomor);
            txtPeriode = itemView.findViewById(R.id.txtPeriode);
            txtUkt = itemView.findViewById(R.id.txtUkt);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtCetakKrs = itemView.findViewById(R.id.txtCetakKrs);
//            btnDownload=itemView.findViewById(R.id.btnDownload);
//            txtCetakKrs.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                        Intent intent = new Intent(mContext,DaftarUlang.class);
//                        mContext.startActivity(intent);
//
//                }
//            });
        }
    }
}
