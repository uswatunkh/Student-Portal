package com.example.studentportal.Fitur;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.studentportal.Fragment.HomeFragment;
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;
import com.example.studentportal.app.AppController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
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
    String encodedimage;
    ImageView backKeterampilan;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<DataOrganisasi> itemList = new ArrayList<DataOrganisasi>();
    AdapterOrganisasi adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_idOrganisasi, txt_namaOrganisasi,txt_tempat,txt_tahunMasuk,txt_tahunKeluar,txt_jabatan,txt_scan,edit_cari,verifikasi_organisasi;
    String jenis;
    Button upload;
    String idOrganisai, namaOrganisasi,tempat,tahunMasuk,tahunKeluar,jabatan,scanBukti;
    //String jenisItem;
    SessionManager sessionManager;
    String getId;  //updateprofil
    TextInputLayout inputVerifikasi;
    Bitmap bitmap;
    String idx;
    private static final String TAG = KeterampilanFragment.class.getSimpleName();

    private static String url_select     = Server.URLKeterampilan + "selectOrganisasi.php";
    private static String url_insert     = Server.URLKeterampilan + "insertOrganisasi.php";
    private static String url_delete     = Server.URLKeterampilan + "deleteOrganisasi.php";
    private static String url_edit     = Server.URLKeterampilan + "editOrganisasi.php";

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
        edit_cari=(EditText) root.findViewById(R.id.edit_cari);
        edit_cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        backKeterampilan= (ImageView) root.findViewById(R.id.backKeterampilan);
        backKeterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(HomeFragment.newInstance("", ""));
            }
        });

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterOrganisasi(getActivity(), itemList, OrganisasiFragment.this);
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
//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(final AdapterView<?> parent, View view,
//                                           final int position, long id) {
//                // TODO Auto-generated method stub
//                idx = itemList.get(position).getIdOrganisasi();
//
//                final CharSequence[] dialogitem = {"Delete"};
//                dialog = new AlertDialog.Builder(getActivity());
//                dialog.setCancelable(true);
//                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                        switch (which) {
////                            case 0:
////                                edit(idx);
////
////                                break;
//                            case 0:
//                                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(getActivity())
//                                        // set message, title, and icon
//                                        .setTitle("Hapus")
//                                        .setMessage("Yakin mau Hapus?")
//                                        .setIcon(R.drawable.logout)
//
//                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//
//                                            public void onClick(DialogInterface dialog, int whichButton) {
//                                                delete(idx);
//
//                                            }
//
//                                        })
//                                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                                dialog.dismiss();
//
//                                            }
//                                        })
//                                        .create();
//                                myQuittingDialogBox.show();
//
//                                break;
//                        }
//                    }
//                }).show();
//                return false;
//            }
//        });

        return root;
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void filter(String text) {
        ArrayList<DataOrganisasi> filteredList = new ArrayList<>();

        for (DataOrganisasi item : itemList) {
            if (item.getNamaOrganisasi().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
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
        dialog.setIcon(R.drawable.organisasi);
        dialog.setTitle("Form Organisasi");

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

//        btnSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
//                chooseFile.setType("application/pdf");
//                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
//                startActivityForResult(chooseFile, REQ_PDF);
//
//
//            }
//        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] dialogitem = {"Kamera","Galeri","PDF"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {

                            case 0:
                                Dexter.withContext(getActivity().getApplicationContext())
                                        .withPermission(Manifest.permission.CAMERA)
                                        .withListener(new PermissionListener() {
                                            @Override
                                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                startActivityForResult( intent,111);
                                            }

                                            @Override
                                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                                            }

                                            @Override
                                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                                permissionToken.continuePermissionRequest();
                                            }
                                        }).check();

                                break;

                            case 1:

//                                Intent intent=new Intent(Intent.ACTION_PICK);
//                                      intent.setType("images/*");
//                                      startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);

                                Dexter.withContext(getActivity().getApplicationContext())
                                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                                        .withListener(new PermissionListener() {
                                            @Override
                                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                                                Intent intent=new Intent(Intent.ACTION_PICK);
//                                                intent.setType("images/*");
//                                                startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);
                                                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
                                            }

                                            @Override
                                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                                            }

                                            @Override
                                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                                permissionToken.continuePermissionRequest();
                                            }
                                        }).check();
                                break;

                            case 2:
                                Dexter.withContext(getActivity().getApplicationContext())
                                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                                        .withListener(new PermissionListener() {
                                            @Override
                                            public void onPermissionGranted(PermissionGrantedResponse response)
                                            {
//                                      Intent intent=new Intent(Intent.ACTION_PICK);
//                                      intent.setType("application/pdf");
//                                      startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);
                                                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                                                chooseFile.setType("application/pdf");
                                                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                                                startActivityForResult(chooseFile, REQ_PDF);
                                            }

                                            @Override
                                            public void onPermissionDenied(PermissionDeniedResponse response) {

                                            }

                                            @Override
                                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                                token.continuePermissionRequest();
                                            }
                                        }).check();
                                break;
                        }
                    }
                }).show();
//

                //return false;
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

                if (namaOrganisasi.isEmpty() ||tempat.isEmpty() ||tahunMasuk.isEmpty() ||  tahunKeluar.isEmpty() ||  jabatan.isEmpty()  ){
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


    private void DialogForm3(String idOrganisasi_view,String namaOrganisasi_view,String tempat_view,String tahunMasuk_view,String tahunKeluar_view,String jabatan_view,String scanBukti_view,String verifikasi_view, String button) {
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.organisasi_view, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.organisasi);
        dialog.setTitle("Form Organisasi");

        EditText idOrganisasi= (EditText) dialogView.findViewById(R.id.idOrganisasi);
        EditText namaOrganisasi = (EditText) dialogView.findViewById(R.id.namaOrganisasi);
        EditText tempat = (EditText) dialogView.findViewById(R.id.tempat);
        EditText tahunMasuk = (EditText) dialogView.findViewById(R.id.tahunMasuk);
        EditText tahunKeluar = (EditText) dialogView.findViewById(R.id.tahunKeluar);
        EditText jabatan = (EditText) dialogView.findViewById(R.id.jabatan);
        EditText scanBukti = (EditText) dialogView.findViewById(R.id.scanBukti);
        verifikasi_organisasi = (EditText) dialogView.findViewById(R.id.verifikasi);
        inputVerifikasi = (TextInputLayout) dialogView.findViewById(R.id.inputVerifikasi);

        if (!idOrganisasi_view.isEmpty()){
            idOrganisasi.setText(idOrganisasi_view);
            namaOrganisasi.setText(namaOrganisasi_view);
            tempat.setText(tempat_view);
            tahunMasuk.setText(tahunMasuk_view);
            tahunKeluar.setText(tahunKeluar_view);
            jabatan.setText(jabatan_view);
            scanBukti.setText(scanBukti_view);
            verifikasi_organisasi.setText(verifikasi_view);

        } else {
            kosong();

        }

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //kosong();
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
        if(requestCode == REQ_PDF && resultCode == RESULT_OK ){

            Uri path = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(path);
                byte[] pdfInBytes = new byte[inputStream.available()];
                inputStream.read(pdfInBytes);
                encodedPDF = Base64.encodeToString(pdfInBytes, Base64.DEFAULT);



                textView.setText("Terpilih");
                btnSelect.setText("Ubah File");

                Toast.makeText(getActivity(), "Terpilih", Toast.LENGTH_SHORT).show();


            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        else if(requestCode == 1 && resultCode == RESULT_OK ){

            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                encodebitmap(bitmap);

                textView.setText("Terpilih");
                btnSelect.setText("Ubah File");

                Toast.makeText(getActivity(), "Terpilih", Toast.LENGTH_SHORT).show();


            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        else if(requestCode==111 && resultCode==RESULT_OK)
        {
            bitmap=(Bitmap)data.getExtras().get("data");
            //img.setImageBitmap(bitmap);
            encodebitmap(bitmap);
            textView.setText("Terpilih");
            btnSelect.setText("Ubah File");
        }

//        if(requestCode == REQ_PDF && resultCode == RESULT_OK && data != null){
//
//            Uri path = data.getData();
//
//
//            try {
//                InputStream inputStream = getActivity().getContentResolver().openInputStream(path);
//                byte[] pdfInBytes = new byte[inputStream.available()];
//                inputStream.read(pdfInBytes);
//                encodedPDF = Base64.encodeToString(pdfInBytes, Base64.DEFAULT);
//
//                textView.setText("Document Selected");
//                btnSelect.setText("Change Document");
//
//                Toast.makeText(getActivity(), "Document Selected", Toast.LENGTH_SHORT).show();
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//        }
    }

    private void encodebitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byteofimages=byteArrayOutputStream.toByteArray();
        encodedimage=android.util.Base64.encodeToString(byteofimages, Base64.DEFAULT);
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
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();




        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                Log.d(TAG, "Response: " + response.toString());
                progressDialog.dismiss();

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
            protected Map<String, String> getParams() throws AuthFailureError{
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update

                    params.put("npm", getId);
                    params.put("namaOrganisasi", namaOrganisasi);
                    params.put("tempat", tempat);
                    params.put("tahunMasuk", tahunMasuk);
                    params.put("tahunKeluar", tahunKeluar);
                    params.put("jabatan", jabatan);
//                    params.put("PDF", encodedimage);
                if(bitmap==null){
                    params.put("PDF", encodedPDF);
                    params.put("file", namaOrganisasi + ".pdf");
                    encodedPDF = "";
                    encodedimage = "";
                } else{
                    params.put("PDF", encodedimage);
                    params.put("file", namaOrganisasi + ".jpg");
                    encodedPDF = "";
                    encodedimage = "";
                    bitmap = null;
                }



                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }



    // fungsi untuk menghapus
    public void delete(final String idx){
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

    private void edit(final String idx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_edit, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray =jsonObject.getJSONArray("read");

                    if(success.equals("1")){
                        for (int i=0; i< jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            String idx      = object.getString("idOrganisasi");
                            String namaOrganisasix    = object.getString("namaOrganisasi");
                            String tempatx  = object.getString("tempat");
                            String tahunMasukx  = object.getString("tahunMasuk");
                            String tahunKeluarx  = object.getString("tahunKeluar");
                            String jabatanx  = object.getString("jabatan");
                            String scanBuktix  = object.getString("scanBukti");
                            String verifikasix  = object.getString("verifikasi");

                            DialogForm3(idx, namaOrganisasix,tempatx,tahunMasukx,tahunKeluarx,jabatanx,scanBuktix,verifikasix, "");
                            //txt_jenis.setVisibility(View.GONE);
                            if(verifikasix.equals("Sudah Diverifikasi")){
                                verifikasi_organisasi.setTextColor(Color.parseColor("#FFFFFF"));
                                inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#7ae472"));
                            }else if(verifikasix.equals("Belum Diverifikasi")){
                                inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#F08080"));
                                verifikasi_organisasi.setTextColor(Color.parseColor("#FFFFFF"));
                            }

                            adapter.notifyDataSetChanged();

                        }
                    }
                }
                catch (JSONException e) {
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