package com.example.studentportal.Fitur;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studentportal.R;

import java.util.List;

public class AdapterKeterampilan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataKeterampilan> items;

    public AdapterKeterampilan(Activity activity, List<DataKeterampilan> items) {
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
            convertView = inflater.inflate(R.layout.keterampilanlist_keterampilan, null);

        TextView idKeterampilan = (TextView) convertView.findViewById(R.id.idKeterampilan);
        TextView namaKeterampilan = (TextView) convertView.findViewById(R.id.namaKeterampilan);
        TextView jenis = (TextView) convertView.findViewById(R.id.jenis);
        TextView tingkat = (TextView) convertView.findViewById(R.id.tingkat);
        TextView scanBukti = (TextView) convertView.findViewById(R.id.scanBukti);

        DataKeterampilan data = items.get(position);

        idKeterampilan.setText(data.getIdKeterampilan());
        namaKeterampilan.setText(data.getNamaKeterampilan());
        jenis.setText(data.getJenis());
        tingkat.setText(data.getTingkat());
        scanBukti.setText(data.getScanBukti());

        return convertView;
    }

}