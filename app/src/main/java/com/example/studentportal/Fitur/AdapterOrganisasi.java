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

import java.util.ArrayList;
import java.util.List;

public class AdapterOrganisasi extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataOrganisasi> items;

    public AdapterOrganisasi(Activity activity, List<DataOrganisasi> items) {
        this.activity = activity;
        this.items = items;
    }

    public void filterList(ArrayList<DataOrganisasi> filteredList) {
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
            convertView = inflater.inflate(R.layout.organisasilist_organisasi, null);

        TextView idOrganisasi= (TextView) convertView.findViewById(R.id.idOrganisasi);
        TextView namaOrganisasi = (TextView) convertView.findViewById(R.id.namaOrganisasi);
//        TextView tempat = (TextView) convertView.findViewById(R.id.tempat);
//        TextView tahunMasuk = (TextView) convertView.findViewById(R.id.tahunMasuk);
//        TextView tahunKeluar = (TextView) convertView.findViewById(R.id.tahunKeluar);
//        TextView jabatan = (TextView) convertView.findViewById(R.id.jabatan);
//        TextView scanBukti = (TextView) convertView.findViewById(R.id.scanBukti);
//        TextView verifikasi = (TextView) convertView.findViewById(R.id.verifikasi);


        DataOrganisasi data = items.get(position);

        idOrganisasi.setText(data.getIdOrganisasi());
        namaOrganisasi.setText(data.getNamaOrganisasi());
//        tempat.setText(data.getTempat());
//        tahunMasuk.setText(data.getTahunMasuk());
//        tahunKeluar.setText(data.getTahunKeluar());
//        jabatan.setText(data.getJabatan());
//        scanBukti.setText(data.getScanBukti());
//        verifikasi.setText(data.getVerifikasi());
//
//
//        if(verifikasi.equals("Sudah Diverifikasi")){
//            verifikasi.setTextColor(Color.parseColor("#000000"));
//        }

        return convertView;
    }

}
