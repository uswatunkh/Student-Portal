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

public class AdapterPrestasi extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPrestasi> items;

    public AdapterPrestasi(Activity activity, List<DataPrestasi> items) {
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
            convertView = inflater.inflate(R.layout.prestasilist_prestasi, null);

        TextView idPrestasi = (TextView) convertView.findViewById(R.id.idPrestasi);
        TextView namaLomba = (TextView) convertView.findViewById(R.id.namaLomba);
        TextView tahun = (TextView) convertView.findViewById(R.id.tahun);
        TextView juara = (TextView) convertView.findViewById(R.id.juara);
        TextView tingkat = (TextView) convertView.findViewById(R.id.tingkatPrestasi);
        TextView jenis = (TextView) convertView.findViewById(R.id.jenisPrestasi);
        TextView verifikasi = (TextView) convertView.findViewById(R.id.vertifikasi);
        TextView scanBukti = (TextView) convertView.findViewById(R.id.scanBukti);

        DataPrestasi data = items.get(position);

        idPrestasi.setText(data.getIdPrestasi());
        namaLomba.setText(data.getNamaLomba());
        tahun.setText(data.getTahun());
        juara.setText(data.getJuara());
        tingkat.setText(data.getTingkat());
        jenis.setText(data.getJenis());
        verifikasi.setText(data.getVerifikasi());
        scanBukti.setText(data.getScanBukti());

        if(verifikasi.equals("Sudah Diverifikasi")){
            verifikasi.setTextColor(Color.parseColor("#000000"));
        }

        return convertView;
    }

}