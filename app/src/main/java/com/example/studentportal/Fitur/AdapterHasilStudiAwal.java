package com.example.studentportal.Fitur;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studentportal.Fragment.DataDiriUbahFragment;
import com.example.studentportal.R;

import java.util.List;

public class AdapterHasilStudiAwal extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataHasilStudiAwal> items;

    public AdapterHasilStudiAwal(Activity activity, List<DataHasilStudiAwal> items) {
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
            convertView = inflater.inflate(R.layout.hasilstudi_awal, null);

        TextView semester = (TextView) convertView.findViewById(R.id.semester);


        DataHasilStudiAwal data = items.get(position);

        //idDaftarUlang.setText(data.getId());
        semester.setText(data.getSemester());



        return convertView;
    }

}
