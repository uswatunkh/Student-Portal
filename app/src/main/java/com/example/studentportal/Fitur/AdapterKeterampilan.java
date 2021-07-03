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

public class AdapterKeterampilan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataKeterampilan> items;

    public AdapterKeterampilan(Activity activity, List<DataKeterampilan> items) {
        this.activity = activity;
        this.items = items;
    }
    public void filterList(ArrayList<DataKeterampilan> filteredList) {
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
            convertView = inflater.inflate(R.layout.keterampilan_view, null);

        TextView idKeterampilan = (TextView) convertView.findViewById(R.id.txt_idKeterampilan);
        TextView namaKeterampilan = (TextView) convertView.findViewById(R.id.txt_namaKeterampilan);
        TextView jenis = (TextView) convertView.findViewById(R.id.txt_jenis);
        TextView tingkat = (TextView) convertView.findViewById(R.id.txt_tingkat);
        TextView verifikasi = (TextView) convertView.findViewById(R.id.vertifikasi);
        TextView scanBukti = (TextView) convertView.findViewById(R.id.txt_scan);
        TextInputLayout inputVerifikasi = (TextInputLayout) convertView.findViewById(R.id.inputVerifikasi);

        DataKeterampilan data = items.get(position);

        idKeterampilan.setText(data.getIdKeterampilan());
        namaKeterampilan.setText(data.getNamaKeterampilan());
        jenis.setText(data.getJenis());
        tingkat.setText(data.getTingkat());

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