package com.example.studentportal.Fitur;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KeterampilanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  KeterampilanFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ImageView btncamera;
    Button btnupload;
    String encodedimage;

    EditText idKeterampilan,namaKeterampilan,jenisKet,tingkatKet,verifikasiKet,scanBuktiKet,edit_cari;
    TextInputLayout inputVerifikasi;
    Toolbar toolbar;
    ImageView backKeterampilan;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<DataKeterampilan> itemList = new ArrayList<DataKeterampilan>();
    AdapterKeterampilan adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView,dialogRead;
    EditText txt_id, txt_nama,txt_jenisx, txt_tingkat,txt_scan,jenis_hide;
    Spinner txt_jenis;
    String jenis;
    Button upload;
    String id, nama,tingkat,jenisHide,scanBukti;
    //String jenisItem;
    SessionManager sessionManager;
    String getId;  //updateprofil
    Bitmap bitmap;
     String idx;
    private static final String TAG = KeterampilanFragment.class.getSimpleName();

    private static String url_select     = Server.URLKeterampilan + "select.php";
    private static String url_insert     = Server.URLKeterampilan + "insert.php";
    private static String url_edit       = Server.URLKeterampilan + "edit.php";
    private static String url_update     = Server.URLKeterampilan + "update.php";
    private static String url_delete     = Server.URLKeterampilan + "delete.php";

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
    private  int REQ_PDF2 = 1;
    private  String encodedPDF = "";
    String ext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KeterampilanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KeterampilanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KeterampilanFragment newInstance(String param1, String param2) {
        KeterampilanFragment fragment = new KeterampilanFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_keterampilan, container, false);

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

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterKeterampilan(getActivity(), itemList);
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
                DialogForm("", "", "","","", "SIMPAN");
                txt_scan.setVisibility(View.GONE);
            }
        });

        backKeterampilan= (ImageView) root.findViewById(R.id.backKeterampilan);
        backKeterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(HomeFragment.newInstance("", ""));
            }
        });

        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                idx = itemList.get(position).getIdKeterampilan();

                final CharSequence[] dialogitem = {"View","Delete"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                edit(idx);

                                break;
                            case 1:
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

    private void filter(String text) {
        ArrayList<DataKeterampilan> filteredList = new ArrayList<>();

        for (DataKeterampilan item : itemList) {
            if (item.getNamaKeterampilan().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }

    // untuk mengosongi edittext pada form
    private void kosong(){
        txt_id.setText(null);
        txt_nama.setText(null);
        jenis_hide.setText(null);
        txt_tingkat.setText(null);
      //  txt_scanBukti.setText(null);
    }

    // untuk menampilkan dialog from biodata
    private void DialogForm(String idx, String namax, String jenisx,String tingkatx,String scanBuktix, String button) {
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.keterampilanform_keterampilan, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.keterampilan);
        dialog.setTitle("Form Keterampilan");

        txt_id      = (EditText) dialogView.findViewById(R.id.txt_idKeterampilan);
        txt_nama    = (EditText) dialogView.findViewById(R.id.txt_namaKeterampilan);
        txt_jenis  = (Spinner) dialogView.findViewById(R.id.txt_jenis);
        txt_tingkat  = (EditText) dialogView.findViewById(R.id.txt_tingkat);
        txt_scan=(EditText) dialogView.findViewById(R.id.txt_scan);
        jenis_hide  = (EditText) dialogView.findViewById(R.id.jenisHide);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.jenis_keterampilan,
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


//        txt_scanBukti  = (EditText) dialogView.findViewById(R.id.txt_scan);

        textView = dialogView.findViewById(R.id.textView);
        btnSelect = dialogView.findViewById(R.id.btnSelect);
        //btnUpload = dialogView.findViewById(R.id.btnUpload);

        btnSelect.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final CharSequence[] dialogitem = {"Galeri","Kamera"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {

                            case 0:
                                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
                                break;
                            case 1:
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
                        }
                    }
                }).show();
//

                return false;
            }
        });



        if (!idx.isEmpty()){
            txt_id.setText(idx);
            txt_nama.setText(namax);
            txt_jenis.setPrompt(jenisx);

            jenis_hide.setText(jenisx);

            txt_tingkat.setText(tingkatx);
            txt_scan.setText(scanBuktix);

        } else {
            kosong();

        }
//        btnUpload.setOnClickListener(button ,new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                id      = txt_id.getText().toString();
//                nama    = txt_nama.getText().toString();
//                //jenis  = txt_jenis.getSelectedItem().toString();
//                jenisHide=jenis_hide.getText().toString();
//                tingkat= txt_tingkat.getText().toString();
//                simpan_update();
//
//
//                callVolley();
//                dialog.dismiss();
//            }
//
//
//            });

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                id      = txt_id.getText().toString();
                nama    = txt_nama.getText().toString();
                //jenis  = txt_jenis.getSelectedItem().toString();
                jenisHide=jenis_hide.getText().toString();
                tingkat= txt_tingkat.getText().toString();
                //scanBukti  = txt_scanBukti.getText().toString();
                if (nama.isEmpty() ||  jenisHide.isEmpty() ||  tingkat.isEmpty()){
                    //Toast.makeText(getActivity(), "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else{
                    simpan_update();
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


    // untuk menampilkan dialog from biodata
    private void DialogForm3(String idx, String namax, String jenisx,String tingkatx,String scanBuktix,String verifikasix, String button) {
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.keterampilan_view, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.keterampilan);
        dialog.setTitle("Form Keterampilan");

        txt_id      = (EditText) dialogView.findViewById(R.id.txt_idKeterampilan);
        txt_nama    = (EditText) dialogView.findViewById(R.id.txt_namaKeterampilan);
        txt_jenisx  = (EditText) dialogView.findViewById(R.id.txt_jenis);
        txt_tingkat  = (EditText) dialogView.findViewById(R.id.txt_tingkat);
        txt_scan=(EditText) dialogView.findViewById(R.id.txt_scan);
        verifikasiKet  = (EditText) dialogView.findViewById(R.id.vertifikasi);
        inputVerifikasi = (TextInputLayout) dialogView.findViewById(R.id.inputVerifikasi);




//        txt_scanBukti  = (EditText) dialogView.findViewById(R.id.txt_scan);





        if (!idx.isEmpty()){
            txt_id.setText(idx);
            txt_nama.setText(namax);

            txt_jenisx.setText(jenisx);
            verifikasiKet.setText(verifikasix);
            txt_tingkat.setText(tingkatx);
            txt_scan.setText(scanBuktix);

        } else {
            kosong();

        }
//        btnUpload.setOnClickListener(button ,new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                id      = txt_id.getText().toString();
//                nama    = txt_nama.getText().toString();
//                //jenis  = txt_jenis.getSelectedItem().toString();
//                jenisHide=jenis_hide.getText().toString();
//                tingkat= txt_tingkat.getText().toString();
//                simpan_update();
//
//
//                callVolley();
//                dialog.dismiss();
//            }
//
//
//            });



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
                        DataKeterampilan item = new DataKeterampilan();

                        item.setIdKeterampilan(ob.getString(TAG_ID));
                        item.setNamaKeterampilan(ob.getString(TAG_NAMA));
//                        item.setJenis(ob.getString(TAG_JENIS));
//                        item.setTingkat(ob.getString(TAG_TINGKAT));
//                        item.setVerifikasi(ob.getString(TAG_Verifikasi));
//                        item.setScanBukti(ob.getString(TAG_SCANBUKTI));


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


                String lok = path.getLastPathSegment();
                ext = lok.substring(lok.lastIndexOf(".")+1);

                if(ext.equals("png") || ext.equals("jpg") || ext.equals("jpeg")){
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                    encodebitmap(bitmap);
                }else {
                    encodedPDF = Base64.encodeToString(pdfInBytes, Base64.DEFAULT);
                }


                textView.setText("Document Selected");
                btnSelect.setText("Change Document");

                Toast.makeText(getActivity(), "Document Selected", Toast.LENGTH_SHORT).show();


            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        else if(requestCode == 1 && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                encodebitmap(bitmap);

                textView.setText("Document Selected");
                btnSelect.setText("Change Document");

                Toast.makeText(getActivity(), "Document Selected", Toast.LENGTH_SHORT).show();


            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        else if(requestCode==111 && resultCode==RESULT_OK)
        {
            bitmap=(Bitmap)data.getExtras().get("data");
            //img.setImageBitmap(bitmap);
            encodebitmap(bitmap);
            textView.setText("Document Selected");
            btnSelect.setText("Change Document");
        }
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
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
//                Log.d(TAG, "Response: " + response.toString());


                try {
                    JSONObject jObj = new JSONObject(response);
                    String success = jObj.getString(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success.equals("1")) {
                        //Log.d("Add/update", jObj.toString());

                        Toast.makeText(getActivity(), "Tambah Berhasil", Toast.LENGTH_SHORT).show();
                        callVolley();
                        kosong();
                        adapter.notifyDataSetChanged();


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
                    params.put("namaKeterampilan", nama);
                    params.put("jenis", jenisHide);
                    params.put("tingkat", tingkat);
                    //params.put("scanBukti", scanBukti);
//                    if (REQ_PDF==0) {
//                        params.put("PDF", encodedimage);
//                    }else if (REQ_PDF!=0){
//                        params.put("PDF", encodedPDF);
//                    }
//                    if(encodedPDF.isEmpty()) {
//
//                    }

//                if(bitmap==null){
//                        params.put("PDF", encodedPDF);
//                        params.put("namaKeterampilan", nama + ".pdf");
//                        encodedPDF = "";
//                        encodedimage = "";
//                    } else{
                        params.put("PDF", encodedimage);
//                        params.put("namaKeterampilan", nama + ".jpg");
//                        encodedPDF = "";
//                        encodedimage = "";
//                    }

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

     //fungsi untuk get edit data
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

                            String idx      = object.getString(TAG_ID);
                            String namax    = object.getString(TAG_NAMA);
                            String jenisx  = object.getString(TAG_JENIS);
                            String tingkatx  = object.getString(TAG_TINGKAT);
                            String scanBuktix  = object.getString(TAG_SCANBUKTI);
                            String verifikasix  = object.getString(TAG_Verifikasi);

                            DialogForm3(idx, namax, jenisx,tingkatx,scanBuktix,verifikasix, "");
                            //txt_jenis.setVisibility(View.GONE);
                            if(verifikasix.equals("Sudah Diverifikasi")){
                                verifikasiKet.setTextColor(Color.parseColor("#FFFFFF"));
                                inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#7ae472"));
                            }else if(verifikasix.equals("Belum Diverifikasi")){
                                inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#F08080"));
                                verifikasiKet.setTextColor(Color.parseColor("#FFFFFF"));
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
                params.put("idKeterampilan", idx);

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
                params.put("idKeterampilan", idx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }




}