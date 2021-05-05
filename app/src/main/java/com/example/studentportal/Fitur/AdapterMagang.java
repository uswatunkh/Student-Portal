package com.example.studentportal.Fitur;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studentportal.R;

import java.util.List;

public class AdapterMagang extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataMagang> items;

    public AdapterMagang(Activity activity, List<DataMagang> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.maganglist_magang, null);

        TextView idMagang = (TextView) convertView.findViewById(R.id.idMagang);
        TextView judul = (TextView) convertView.findViewById(R.id.judul);
        TextView tempat = (TextView) convertView.findViewById(R.id.tempat);
        TextView provinsi = (TextView) convertView.findViewById(R.id.provinsi);
        TextView kota = (TextView) convertView.findViewById(R.id.kota);
        TextView tanggalMulai = (TextView) convertView.findViewById(R.id.tanggalmulaiMagang);
        TextView tanggalSelesai = (TextView) convertView.findViewById(R.id.tanggalselesaiMagang);
        TextView ringkasan = (TextView) convertView.findViewById(R.id.ringkasan);
        TextView scanBukti = (TextView) convertView.findViewById(R.id.scanBukti);
        TextView uploadLaporan = (TextView) convertView.findViewById(R.id.uploadLaporan);
        TextView verifikasi = (TextView) convertView.findViewById(R.id.verifikasi);


        DataMagang data = items.get(position);

        idMagang.setText(data.getIdMagang());
        judul.setText(data.getJudul());
        tempat.setText(data.getTempat());
        provinsi.setText(data.getProvinsi());
        kota.setText(data.getKota());
        tanggalMulai.setText(data.getTanggalmulaiMagang());
        tanggalSelesai.setText(data.getTanggalselesaiMagang());
        ringkasan.setText(data.getRingkasan());
        scanBukti.setText(data.getScanBukti());
        uploadLaporan.setText(data.getUploadLaporan());
        verifikasi.setText(data.getVerifikasi());

        if(verifikasi.equals("Sudah Diverifikasi")){
            verifikasi.setTextColor(Color.parseColor("#000000"));
        }

        return convertView;
    }

}