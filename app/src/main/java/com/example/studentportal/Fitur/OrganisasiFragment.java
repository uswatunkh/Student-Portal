package com.example.studentportal.Fitur;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;
import com.example.studentportal.app.AppController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganisasiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganisasiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<DataOrganisasi> itemList = new ArrayList<DataOrganisasi>();
    AdapterOrganisasi adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_idOrganisasi, txt_namaOrganisasi,txt_tempat,txt_tahunMasuk,txt_tahunKeluar,txt_jabatan,txt_scan;
    String jenis;
    Button upload;
    String idOrganisai, namaOrganisasi,tempat,tahunMasuk,tahunKeluar,jabatan,scanBukti;
    //String jenisItem;
    SessionManager sessionManager;
    String getId;  //updateprofil
    Bitmap bitmap;
    String idx;
    private static final String TAG = KeterampilanFragment.class.getSimpleName();

    private static String url_select     = Server.URLKeterampilan + "selectOrganisasi.php";
    private static String url_insert     = Server.URLKeterampilan + "insertOrganisasi.php";
    private static String url_delete     = Server.URLKeterampilan + "deleteOrganisasi.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    private Button btnSelect, btnUpload;
    private TextView textView;
    //private static String URL_UPLOAD = "http://192.168.1.43/AndroidTutorials/upload_document.php";

    private  int REQ_PDF = 21;
    private  String encodedPDF;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrganisasiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrganisasiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrganisasiFragment newInstance(String param1, String param2) {
        OrganisasiFragment fragment = new OrganisasiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_organisasi, container, false);

        // menghubungkan variablel pada layout dan pada java
        fab     = (FloatingActionButton) root.findViewById(R.id.fab_add);
        swipe   = (SwipeRefreshLayout) root.findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) root.findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterOrganisasi(getActivity(), itemList);
        list.setAdapter(adapter);

        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil

        // fungsi floating action button memanggil form biodata
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DialogForm("", "", "","","", "SIMPAN");
                DialogForm("", "", "","","","","", "SIMPAN");

            }
        });

        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                idx = itemList.get(position).getIdOrganisasi();

                final CharSequence[] dialogitem = {"Delete"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
//                            case 0:
////                                edit(idx);
//
//                                break;
                            case 0:
                                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(getActivity())
                                        // set message, title, and icon
                                        .setTitle("Hapus")
                                        .setMessage("Yakin mau Hapus?")
                                        .setIcon(R.drawable.logout)

                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                delete(idx);

                                            }

                                        })
                                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.dismiss();

                                            }
                                        })
                                        .create();
                                myQuittingDialogBox.show();

                                break;
                        }
                    }
                }).show();
                return false;
            }
        });

        return root;
    }


    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }

    // untuk mengosongi edittext pada form
    private void kosong(){
        txt_idOrganisasi.setText(null);
        txt_namaOrganisasi.setText(null);
        txt_tempat.setText(null);
        txt_tahunMasuk.setText(null);
        txt_tahunKeluar.setText(null);
        txt_jabatan.setText(null);
        //  txt_scanBukti.setText(null);
    }

    // untuk menampilkan dialog from biodata
    private void DialogForm(String idx, String namaOrganisasix, String tempatx, String tahunMasukx, String tahunKeluarx, String jabatanx, String scanBuktix, String button) {
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.organisasiform_organisasi, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Form Prestasi");

        txt_idOrganisasi      = (EditText) dialogView.findViewById(R.id.txt_idOrganisasi);
        txt_namaOrganisasi    = (EditText) dialogView.findViewById(R.id.txt_namaOrganisasi);
        txt_tempat    = (EditText) dialogView.findViewById(R.id.txt_tempat);
        txt_tahunMasuk    = (EditText) dialogView.findViewById(R.id.txt_tahunMasuk);
        txt_tahunKeluar  = (EditText) dialogView.findViewById(R.id.txt_tahunKeluar);
        txt_jabatan  = (EditText) dialogView.findViewById(R.id.txt_jabatan);
        txt_scan=(EditText) dialogView.findViewById(R.id.txt_scan);



        textView = dialogView.findViewById(R.id.textView);
        btnSelect = dialogView.findViewById(R.id.btnSelect);
        //btnUpload = dialogView.findViewById(R.id.btnUpload);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("application/pdf");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, REQ_PDF);


            }
        });
        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                idOrganisai      = txt_idOrganisasi.getText().toString();
                namaOrganisasi    = txt_namaOrganisasi.getText().toString();
                tempat    = txt_tempat.getText().toString();
                tahunMasuk    = txt_tahunMasuk.getText().toString();
                tahunKeluar=txt_tahunKeluar.getText().toString();
                jabatan= txt_jabatan.getText().toString();

                if (namaOrganisasi.isEmpty() ||tempat.isEmpty() ||tahunMasuk.isEmpty() ||  tahunKeluar.isEmpty() ||  jabatan.isEmpty() || encodedPDF.isEmpty() ){
                    //Toast.makeText(getActivity(), "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    simpan_update();
                    callVolley();
                    dialog.dismiss();
                }





            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                kosong();
            }
        });

        dialog.show();

    }





    // untuk menampilkan semua data pada listview
    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        StringRequest jArr = new StringRequest(Request.Method.POST,url_select, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        DataOrganisasi item = new DataOrganisasi();

                        item.setIdOrganisasi(ob.getString("idOrganisasi"));
                        item.setNamaOrganisasi(ob.getString("namaOrganisasi"));
                        item.setTempat(ob.getString("tempat"));
                        item.setTahunMasuk(ob.getString("tahunMasuk"));
                        item.setTahunKeluar(ob.getString("tahunKeluar"));
                        item.setJabatan(ob.getString("jabatan"));
                        item.setScanBukti(ob.getString("scanBukti"));
                        item.setVerifikasi(ob.getString("verifikasi"));



                        // menambah item ke array
                        itemList.add(item);
                    }
                    list.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", getId);
                return params;
            }
        }
                ;
        AppController.getInstance().addToRequestQueue(jArr);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_PDF && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();


            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(path);
                byte[] pdfInBytes = new byte[inputStream.available()];
                inputStream.read(pdfInBytes);
                encodedPDF = Base64.encodeToString(pdfInBytes, Base64.DEFAULT);

                textView.setText("Document Selected");
                btnSelect.setText("Change Document");

                Toast.makeText(getActivity(), "Document Selected", Toast.LENGTH_SHORT).show();


            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }



    // fungsi untuk menyimpan atau update
    private void simpan_update() {
        String url;
        // jika id kosong maka simpan, jika id ada nilainya maka update
        if (idOrganisai.isEmpty()){
            url = url_insert;


        } else {
            url = url_insert;



        }



        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    String success = jObj.getString(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success.equals("1")) {
                        //Log.d("Add/update", jObj.toString());

                        Toast.makeText(getActivity(), "Tambah Berhasil", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        callVolley();
                        kosong();

                    } else {
                        Toast.makeText(getActivity(), jObj.getString("Gagal"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                if (idOrganisai.isEmpty()){
                    params.put("npm", getId);
                    params.put("namaOrganisasi", namaOrganisasi);
                    params.put("tempat", tempat);
                    params.put("tahunMasuk", tahunMasuk);
                    params.put("tahunKeluar", tahunKeluar);
                    params.put("jabatan", jabatan);
                    params.put("PDF", encodedPDF);
                }
//                else {
//                    params.put("idKeterampilan", id);
//                    params.put("namaKeterampilan", nama);
//                    params.put("jenis", jenisHide);
//                    params.put("tingkat", tingkat);
                // params.put("scanBukti", scanBukti);
                //params.put("PDF", idPDF);
                //            }

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }



    // fungsi untuk menghapus
    private void delete(final String idx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_delete, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("delete", jObj.toString());

                        callVolley();

                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idOrganisasi", idx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }




}