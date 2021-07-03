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
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AdapterPrestasi extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPrestasi> items;

    public AdapterPrestasi(Activity activity, List<DataPrestasi> items) {
        this.activity = activity;
        this.items = items;
    }

    public void filterList(ArrayList<DataPrestasi> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
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
            convertView = inflater.inflate(R.layout.prestasi_view, null);

        TextView idPrestasi = (TextView) convertView.findViewById(R.id.idPrestasi);
        TextView namaLomba = (TextView) convertView.findViewById(R.id.namaLomba);
        TextView tahun = (TextView) convertView.findViewById(R.id.tahun);
        TextView juara = (TextView) convertView.findViewById(R.id.juara);
        TextView tingkat = (TextView) convertView.findViewById(R.id.tingkatPrestasi);
        TextView jenis = (TextView) convertView.findViewById(R.id.jenisPrestasi);
        TextView verifikasi = (TextView) convertView.findViewById(R.id.verifikasi);
        TextView scanBukti = (TextView) convertView.findViewById(R.id.scanBukti);
        TextInputLayout inputVerifikasi = (TextInputLayout) convertView.findViewById(R.id.inputVerifikasi);

        DataPrestasi data = items.get(position);

        idPrestasi.setText(data.getIdPrestasi());
        namaLomba.setText(data.getNamaLomba());
        tahun.setText(data.getTahun());
        juara.setText(data.getJuara());
        tingkat.setText(data.getTingkat());
        jenis.setText(data.getJenis());
        verifikasi.setText(data.getVerifikasi());
        scanBukti.setText(data.getScanBukti());

        String dataVerisfikasi= data.getVerifikasi();
        if (dataVerisfikasi.equals("Sudah Diverifikasi")) {
            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#7ae472"));

        }else if (dataVerisfikasi.equals("Belum Diverifikasi")){
            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#F08080"));

        }



        return convertView;
    }

}