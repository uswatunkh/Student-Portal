package com.example.studentportal.Fitur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentportal.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AdapterMagang extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataMagang> items;
    MagangFragment fragment;
    String idx;

    public AdapterMagang(Activity activity, List<DataMagang> items, MagangFragment fragment) {
        this.activity = activity;
        this.items = items;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    public void filterList(ArrayList<DataMagang> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
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
            convertView = inflater.inflate(R.layout.magang_view, null);

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
        TextInputLayout inputVerifikasi = (TextInputLayout) convertView.findViewById(R.id.inputVerifikasi);
        Button hapus = convertView.findViewById(R.id.hapus);



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

        String dataVerisfikasi= data.getVerifikasi();
        if (dataVerisfikasi.equals("Sudah Diverifikasi")) {
            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#7ae472"));
            hapus.setVisibility(View.GONE);

        }else if (dataVerisfikasi.equals("Belum Diverifikasi")){
            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#F08080"));

        }



        idx = items.get(position).getIdMagang();

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openFragment(EvaluasiDosenFragment.newInstance("", ""));

                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(activity)
                        // set message, title, and icon
                        .setTitle("Hapus")
                        .setMessage("Yakin mau Hapus?")
                        .setIcon(R.drawable.logout)

                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //manggil methode delete
                                fragment.delete(idx);

                            }

                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        })
                        .create();
                myQuittingDialogBox.show();
            }

        });

        return convertView;
    }

}
