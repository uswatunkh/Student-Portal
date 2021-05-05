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

public class AdapterEvdos extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataEvdos> items;

    public AdapterEvdos(Activity activity, List<DataEvdos> items) {
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
            convertView = inflater.inflate(R.layout.evdoslist_evdos, null);

        TextView idDosen = (TextView) convertView.findViewById(R.id.tvIdDosen);
        TextView namaDosen = (TextView) convertView.findViewById(R.id.tvNamaDosen);
        TextView namaMataKuliah = (TextView) convertView.findViewById(R.id.tvNamaMK);
        TextView nidn = (TextView) convertView.findViewById(R.id.tvNIDN);
        TextView idMataKuliah = (TextView) convertView.findViewById(R.id.tvIdMk);


        DataEvdos data = items.get(position);

        idDosen.setText(data.getIdDosen());
        namaDosen.setText(data.getNamaDosen());
        namaMataKuliah.setText(data.getNamaMatakuliah());
        nidn.setText(data.getNidn());
        idMataKuliah.setText(data.getIdMatakuliah());


        return convertView;
    }

}