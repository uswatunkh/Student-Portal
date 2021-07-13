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
import android.widget.TextView;

import com.example.studentportal.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AdapterOrganisasi extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataOrganisasi> items;
    OrganisasiFragment fragment;
    String idx;

    public AdapterOrganisasi(Activity activity, List<DataOrganisasi> items, OrganisasiFragment fragment) {
        this.activity = activity;
        this.items = items;
        this.fragment = fragment;
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
            convertView = inflater.inflate(R.layout.organisasi_view, null);

        TextView idOrganisasi= (TextView) convertView.findViewById(R.id.idOrganisasi);
        TextView namaOrganisasi = (TextView) convertView.findViewById(R.id.namaOrganisasi);
        TextView tempat = (TextView) convertView.findViewById(R.id.tempat);
        TextView tahunMasuk = (TextView) convertView.findViewById(R.id.tahunMasuk);
        TextView tahunKeluar = (TextView) convertView.findViewById(R.id.tahunKeluar);
        TextView jabatan = (TextView) convertView.findViewById(R.id.jabatan);
        TextView scanBukti = (TextView) convertView.findViewById(R.id.scanBukti);
        TextView verifikasi = (TextView) convertView.findViewById(R.id.verifikasi);
        TextInputLayout inputVerifikasi = (TextInputLayout) convertView.findViewById(R.id.inputVerifikasi);
        Button hapus = convertView.findViewById(R.id.hapus);


        DataOrganisasi data = items.get(position);

        idOrganisasi.setText(data.getIdOrganisasi());
        namaOrganisasi.setText(data.getNamaOrganisasi());
        tempat.setText(data.getTempat());
        tahunMasuk.setText(data.getTahunMasuk());
        tahunKeluar.setText(data.getTahunKeluar());
        jabatan.setText(data.getJabatan());
        scanBukti.setText(data.getScanBukti());
        verifikasi.setText(data.getVerifikasi());

        String dataVerisfikasi= data.getVerifikasi();
//        if (dataVerisfikasi.equals("Sudah Diverifikasi")) {
//            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
//            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#7ae472"));
//            hapus.setVisibility(View.GONE);
//
//        }else if (dataVerisfikasi.equals("Belum Diverifikasi")){
//            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
//            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#F08080"));
//
//        }
        if (dataVerisfikasi.equals("Tidak Valid")) {
            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#F08080"));
            hapus.setVisibility(View.GONE);

        }else if (dataVerisfikasi.equals("Valid")){
            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#7ae472"));
            hapus.setVisibility(View.GONE);
        }
        else if (dataVerisfikasi.equals("Belum Diverifikasi")){
            verifikasi.setTextColor(Color.parseColor("#FFFFFF"));
            inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#FDB44E"));

        }

        idx = items.get(position).getIdOrganisasi();

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openFragment(EvaluasiDosenFragment.newInstance("", ""));

                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(activity)
                        // set message, title, and icon
                        .setTitle("Hapus")
                        .setMessage("Yakin mau hapus"+" "+ data.getNamaOrganisasi()+"?")
                        .setIcon(R.drawable.logout)

                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //manggil methode delete
                                fragment.delete(data.getIdOrganisasi());

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
