package com.example.studentportal.Fitur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentportal.Presensi;
import com.example.studentportal.R;

import java.util.List;

public class AdapterPresensiAwal extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPresensiAwal> items;

    public AdapterPresensiAwal(Activity activity, List<DataPresensiAwal> items) {
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
            convertView = inflater.inflate(R.layout.presensi_awal, null);

        TextView semester = (TextView) convertView.findViewById(R.id.semester);
        TextView id = (TextView) convertView.findViewById(R.id.idRekapPresensi);
        TextView hadir = (TextView) convertView.findViewById(R.id.hadir);
        TextView izin = (TextView) convertView.findViewById(R.id.izin);
        TextView sakit = (TextView) convertView.findViewById(R.id.sakit);
        TextView kosong = (TextView) convertView.findViewById(R.id.kosong);
        Button detail = (Button) convertView.findViewById(R.id.detail);


        DataPresensiAwal data = items.get(position);

        //idDaftarUlang.setText(data.getId());
        semester.setText(data.getSemester());
        id.setText(data.getIdRekapPresensi());
        hadir.setText(data.getHadir());
        izin.setText(data.getIzin());
        sakit.setText(data.getSakit());
        kosong.setText(data.getKosong());

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Presensi.class);
                intent.putExtra("position",position);
                activity.startActivity(intent);

            }
        });



        return convertView;
    }

}
