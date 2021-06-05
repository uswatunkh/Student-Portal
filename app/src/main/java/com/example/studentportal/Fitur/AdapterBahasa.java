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

public class AdapterBahasa extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataBahasa> items;

    public AdapterBahasa(Activity activity, List<DataBahasa> items) {
        this.activity = activity;
        this.items = items;
    }
    public void filterList(ArrayList<DataBahasa> filteredList) {
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
            convertView = inflater.inflate(R.layout.bahasalist_bahasa, null);

        TextView idBahasa= (TextView) convertView.findViewById(R.id.idBahasa);
//        TextView periodeWisuda = (TextView) convertView.findViewById(R.id.periodeWisuda);
//        TextView tahunWisuda = (TextView) convertView.findViewById(R.id.tahunWisuda);
        TextView namaBahasa = (TextView) convertView.findViewById(R.id.namaBahasa);
//        TextView skor = (TextView) convertView.findViewById(R.id.skor);
//        TextView tanggalTes = (TextView) convertView.findViewById(R.id.tanggalTes);
//        TextView scanBukti = (TextView) convertView.findViewById(R.id.scanBukti);
//        TextView verifikasi = (TextView) convertView.findViewById(R.id.verifikasi);


        DataBahasa data = items.get(position);

        idBahasa.setText(data.getIdBahasa());
//        periodeWisuda.setText(data.getPeriodeBahasa());
//        tahunWisuda.setText(data.getTahunWisuda());
        namaBahasa.setText(data.getNamaBahasa());
//        skor.setText(data.getSkor());
//        tanggalTes.setText(data.getTanggalTes());
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

