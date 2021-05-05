package com.example.studentportal.Fitur;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
 * Use the {@link PrestasiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrestasiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<DataPrestasi> itemList = new ArrayList<DataPrestasi>();
    AdapterPrestasi adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_idPrestasi, txt_namaLomba,txt_tahun,txt_juara,txt_scan,jenis_hide,tingkat_Hide;
    Spinner txt_jenis,txt_tingkat;
    String jenis;
    Button upload;
    String idPrestasi, namaLomba,tahunPrestasi,juaraPrestasi,tingkatHide,jenisHide,scanBukti;
    //String jenisItem;
    SessionManager sessionManager;
    String getId;  //updateprofil
    Bitmap bitmap;
    String idx;
    private static final String TAG = KeterampilanFragment.class.getSimpleName();

    private static String url_select     = Server.URLKeterampilan + "selectPrestasi.php";
    private static String url_insert     = Server.URLKeterampilan + "insertPrestasi.php";
    private static String url_delete     = Server.URLKeterampilan + "deletePrestasi.php";

    private static String URL_UPLOAD = Server.URL + "uploadKeterampilan.php";

    public static final String TAG_ID       = "idKeterampilan";
    public static final String TAG_NAMA     = "namaKeterampilan";
    public static final String TAG_JENIS     = "jenis";
    public static final String TAG_TINGKAT     = "tingkat";
    public static final String TAG_Verifikasi   = "verifikasi";
    public static final String TAG_SCANBUKTI   = "scanBukti";
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

    public PrestasiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrestasiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrestasiFragment newInstance(String param1, String param2) {
        PrestasiFragment fragment = new PrestasiFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_prestasi, container, false);

        // menghubungkan variablel pada layout dan pada java
        fab     = (FloatingActionButton) root.findViewById(R.id.fab_add);
        swipe   = (SwipeRefreshLayout) root.findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) root.findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterPrestasi(getActivity(), itemList);
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
                DialogForm("","", "", "","","","", "SIMPAN");

            }
        });

        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                idx = itemList.get(position).getIdPrestasi();

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
        txt_idPrestasi.setText(null);
        txt_namaLomba.setText(null);
        txt_tahun.setText(null);
        txt_juara.setText(null);
        jenis_hide.setText(null);
        tingkat_Hide.setText(null);
        //  txt_scanBukti.setText(null);
    }

    // untuk menampilkan dialog from biodata
    private void DialogForm(String idx, String namaLombax,String tahun, String juara,String tingkatx,String jenisx,String scanBuktix, String button) {
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.prestasiform_prestasi, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Form Prestasi");

        txt_idPrestasi      = (EditText) dialogView.findViewById(R.id.txt_idPrestasi);
        txt_namaLomba    = (EditText) dialogView.findViewById(R.id.txt_namaLomba);
        txt_tahun    = (EditText) dialogView.findViewById(R.id.txt_tahun);
        txt_juara    = (EditText) dialogView.findViewById(R.id.txt_juara);
        txt_jenis  = (Spinner) dialogView.findViewById(R.id.txt_jenis);
        txt_tingkat  = (Spinner) dialogView.findViewById(R.id.txt_tingkat);
        txt_scan=(EditText) dialogView.findViewById(R.id.txt_scan);
        jenis_hide  = (EditText) dialogView.findViewById(R.id.jenisHide);
        tingkat_Hide= (EditText) dialogView.findViewById(R.id.tingkatHide);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.jenis_prestasi,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txt_jenis.setAdapter(adapter);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        txt_jenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                jenis_hide.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapterTingkat = ArrayAdapter.createFromResource(getActivity(), R.array.tingkat_prestasi,
                android.R.layout.simple_spinner_item);
        adapterTingkat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txt_tingkat.setAdapter(adapterTingkat);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        txt_tingkat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tingkatItem =parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), tingkatItem,Toast.LENGTH_SHORT).show();
                tingkat_Hide.setText(tingkatItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        txt_scanBukti  = (EditText) dialogView.findViewById(R.id.txt_scan);

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



//        if (!idx.isEmpty()){
//            txt_id.setText(idx);
//            txt_nama.setText(namax);
//            txt_jenis.setPrompt(jenisx);
//
//            jenis_hide.setText(jenisx);
//
//            txt_tingkat.setText(tingkatx);
//            txt_scan.setText(scanBuktix);
//
//        } else {
//            kosong();
//
//        }

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                idPrestasi      = txt_idPrestasi.getText().toString();
                namaLomba    = txt_namaLomba.getText().toString();
                tahunPrestasi    = txt_tahun.getText().toString();
                juaraPrestasi    = txt_juara.getText().toString();
                //jenis  = txt_jenis.getSelectedItem().toString();
                jenisHide=jenis_hide.getText().toString();
                tingkatHide= tingkat_Hide.getText().toString();
                //scanBukti  = txt_scanBukti.getText().toString();
                if (namaLomba.isEmpty() ||tahunPrestasi.isEmpty() ||juara.isEmpty() ||  jenisHide.isEmpty() ||  tingkatHide.isEmpty() || encodedPDF.isEmpty() ){
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
                        DataPrestasi item = new DataPrestasi();

                        item.setIdPrestasi(ob.getString("idPrestasi"));
                        item.setNamaLomba(ob.getString("namaLomba"));
                        item.setTahun(ob.getString("tahun"));
                        item.setJuara(ob.getString("juara"));
                        item.setTingkat(ob.getString("tingkat"));
                        item.setJenis(ob.getString("jenis"));
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
            //  simpan_update(encodedPDF);
//            btnUpload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    id      = txt_id.getText().toString();
//                    nama    = txt_nama.getText().toString();
//                    //jenis  = txt_jenis.getSelectedItem().toString();
//                    jenisHide=jenis_hide.getText().toString();
//                    tingkat= txt_tingkat.getText().toString();
//                    simpan_update();
//
//
//                    callVolley();
//
//                }
//            });



        }
    }



    // fungsi untuk menyimpan atau update
    private void simpan_update() {
        String url;
        // jika id kosong maka simpan, jika id ada nilainya maka update
        if (idPrestasi.isEmpty()){
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
                if (idPrestasi.isEmpty()){
                    params.put("npm", getId);
                    params.put("namaLomba", namaLomba);
                    params.put("tahun", tahunPrestasi);
                    params.put("juara", juaraPrestasi);
                    params.put("tingkat", tingkatHide);
                    params.put("jenis", jenisHide);
                    //params.put("scanBukti", scanBukti);
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
                params.put("idPrestasi", idx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }




}