package com.example.studentportal.Fitur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentportal.Keterampilan;
import com.example.studentportal.Presensi;
import com.example.studentportal.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AdapterKeterampilan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataKeterampilan> items;
    KeterampilanFragment fragment;
    String idx;

    public AdapterKeterampilan(Activity activity, List<DataKeterampilan> items, KeterampilanFragment fragment) {
        this.activity = activity;
        this.items = items;
        this.fragment = fragment;
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
        TextView verifikasi = (TextView) convertView.findViewById(R.id.txt_verifikasi);
        TextView scanBukti = (TextView) convertView.findViewById(R.id.txt_scan);
        TextInputLayout inputVerifikasi = (TextInputLayout) convertView.findViewById(R.id.inputVerifikasi);
        Button hapus =convertView.findViewById(R.id.hapus);

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
            hapus.setVisibility(View.GONE);

        }else if (dataVerisfikasi.equals("Belum Diverifikasi")){
            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#F08080"));

        }



        idx = items.get(position).getIdKeterampilan();

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