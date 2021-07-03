package com.example.studentportal.Fitur;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MagangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MagangFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    EditText idMagang_view,judul_view,tempat_view,provinsi_view,kota_view,tanggalMulai_view,tanggalSelesai_view,ringkasan_view,scanBukti_view,uploadLaporan_view, verifikasi_magang;
    ImageView backKeterampilan;
    TextInputLayout inputVerifikasi;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<DataMagang> itemList = new ArrayList<DataMagang>();
    AdapterMagang adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_idMagang, txt_judul,txt_tempat,txt_provinsi,txt_kota,txt_TanggalMulai, txt_tanggalSelesai,txt_ringkasan,edit_cari;
    Spinner txt_jenis;
    String jenis;
    Button upload;
    String id, judul,tempat,provinsi,kota,tanggalMulai,tanggalSelesai,ringkasan,scanBukti,uploadLaporan,verifikasi;
    //String jenisItem;
    SessionManager sessionManager;
    String getId;  //updateprofil
    Bitmap bitmap;
    String idx;
    String encodedimage;
    private static final String TAG = MagangFragment.class.getSimpleName();

    private static String url_select     = Server.URLKeterampilan + "selectMagang.php";
    private static String url_edit       = Server.URLKeterampilan + "editMagang.php";
    private static String url_insert     = Server.URLKeterampilan + "insertMagang.php";
    private static String url_delete     = Server.URLKeterampilan + "deleteMagang.php";

    private static String URL_UPLOAD = Server.URL + "uploadKeterampilan.php";

    public static final String TAG_ID       = "idMagang";
    public static final String TAG_NAMA     = "namaKeterampilan";
    public static final String TAG_JENIS     = "jenis";
    public static final String TAG_TINGKAT     = "tingkat";
    public static final String TAG_Verifikasi   = "verifikasi";
    public static final String TAG_SCANBUKTI   = "scanBukti";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    private Button btnSelect, btnUpload ,btnSelect2;
    private TextView textView,textView2;
    //private static String URL_UPLOAD = "http://192.168.1.43/AndroidTutorials/upload_document.php";

    private  int REQ_PDF = 21;
    private  int REQ_PDF2 = 22;
    private  String encodedPDF, encodedPDF2;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MagangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MagangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MagangFragment newInstance(String param1, String param2) {
        MagangFragment fragment = new MagangFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_magang, container, false);

        // menghubungkan variablel pada layout dan pada java
        fab     = (FloatingActionButton) root.findViewById(R.id.fab_add);
        swipe   = (SwipeRefreshLayout) root.findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) root.findViewById(R.id.list);

        backKeterampilan= (ImageView) root.findViewById(R.id.backKeterampilan);
        backKeterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(HomeFragment.newInstance("", ""));
            }
        });

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
        adapter = new AdapterMagang(getActivity(), itemList);
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
                DialogForm("", "", "","","","","",
                        "","", "SIMPAN");

            }
        });

        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                idx = itemList.get(position).getIdMagang();

                final CharSequence[] dialogitem = {"Delete"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
//                            case 0:
//                                edit(idx);
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

    private void filter(String text) {
        ArrayList<DataMagang> filteredList = new ArrayList<>();

        for (DataMagang item : itemList) {
            if (item.getJudul().toLowerCase().contains(text.toLowerCase())) {
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
        txt_idMagang.setText(null);
        txt_judul.setText(null);
        txt_tempat.setText(null);
        txt_provinsi.setText(null);
        txt_kota.setText(null);
        txt_TanggalMulai.setText(null);
        txt_tanggalSelesai.setText(null);
        txt_ringkasan.setText(null);
    }

    // untuk menampilkan dialog from biodata
    private void DialogForm(String idx, String judulx, String tampatx,String provinsix,String kotax,
                            String tanggalMulaix,String tanggalSelesaix,String scanBuktix,String uploadLaporanx, String button) {
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.magangform_magang, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.magang);
        dialog.setTitle("Form Magang");
        txt_idMagang      = (EditText) dialogView.findViewById(R.id.txt_idMagang);
        txt_judul    = (EditText) dialogView.findViewById(R.id.txt_judul);
        txt_tempat  =  (EditText) dialogView.findViewById(R.id.txt_tempat);
        txt_provinsi  = (EditText) dialogView.findViewById(R.id.txt_provinsi);
        txt_kota=(EditText) dialogView.findViewById(R.id.txt_kota);
        txt_TanggalMulai  = (EditText) dialogView.findViewById(R.id.txt_tglMulaiMagang);
        txt_tanggalSelesai  = (EditText) dialogView.findViewById(R.id.txt_tglSelesaiMagang);
        txt_ringkasan  = (EditText) dialogView.findViewById(R.id.txt_ringkasan);

        txt_TanggalMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txt_TanggalMulai.setText(year + "-" + (month + 1) + "-" + day);
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        txt_tanggalSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txt_tanggalSelesai.setText(year + "-" + (month + 1) + "-" + day);
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });





//        txt_scanBukti  = (EditText) dialogView.findViewById(R.id.txt_scan);

        textView = dialogView.findViewById(R.id.textView);
        btnSelect = dialogView.findViewById(R.id.btnSelect);
        textView2 = dialogView.findViewById(R.id.textView2);
        btnSelect2 = dialogView.findViewById(R.id.btnSelect2);
        //btnUpload = dialogView.findViewById(R.id.btnUpload);

        btnSelect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("application/pdf");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, REQ_PDF);


            }
        });
//        btnSelect2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent choose = new Intent(Intent.ACTION_GET_CONTENT);
//                choose.setType("application/pdf");
//                choose = Intent.createChooser(choose, "Choose a file");
//                startActivityForResult(choose, REQ_PDF2);
//
//
//            }
//        });



        btnSelect.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
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
                                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
                                break;

                            case 2:
                                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                                chooseFile.setType("application/pdf");
                                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                                startActivityForResult(chooseFile, REQ_PDF);
                                break;
                        }
                    }
                }).show();
//

                return false;
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

//        } else {
//            kosong();
//
//        }
        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                id      = txt_idMagang.getText().toString();
                judul    = txt_judul.getText().toString();
                tempat    = txt_tempat.getText().toString();
                provinsi    = txt_provinsi.getText().toString();
                kota    = txt_kota.getText().toString();
                tanggalMulai    = txt_TanggalMulai.getText().toString();
                tanggalSelesai    = txt_tanggalSelesai.getText().toString();
                ringkasan    = txt_ringkasan.getText().toString();


//                if (nama.isEmpty() ||  jenisHide.isEmpty() ||  tingkat.isEmpty() || encodedPDF.isEmpty() ){
//                    //Toast.makeText(getActivity(), "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getContext(), "Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
//                }else{
//                    simpan_update();
//                    callVolley();
//                    dialog.dismiss();
//                }
                simpan_update();
                callVolley();
                dialog.dismiss();




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

    private void DialogForm3(String idMagang_view,String judul_view,String tempat_view,String provinsi_view,String kota_view,String tanggalMulai_view,String tanggalSelesai_view,String ringkasan_view,String scanBukti_view,String uploadLaporan_view,String verifikasi_view, String button) {
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.magang_view, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.magang);
        dialog.setTitle("Form Magang");

        EditText idMagang = (EditText) dialogView.findViewById(R.id.idMagang);
        EditText judul = (EditText) dialogView.findViewById(R.id.judul);
        EditText tempat = (EditText) dialogView.findViewById(R.id.tempat);
        EditText provinsi = (EditText) dialogView.findViewById(R.id.provinsi);
        EditText kota = (EditText) dialogView.findViewById(R.id.kota);
        EditText tanggalMulai = (EditText) dialogView.findViewById(R.id.tanggalmulaiMagang);
        EditText tanggalSelesai = (EditText) dialogView.findViewById(R.id.tanggalselesaiMagang);
        EditText ringkasan = (EditText) dialogView.findViewById(R.id.ringkasan);
        EditText scanBukti = (EditText) dialogView.findViewById(R.id.scanBukti);
        EditText uploadLaporan = (EditText) dialogView.findViewById(R.id.uploadLaporan);
         verifikasi_magang = (EditText) dialogView.findViewById(R.id.verifikasi);
        inputVerifikasi = (TextInputLayout) dialogView.findViewById(R.id.inputVerifikasi);

        if (!idMagang_view.isEmpty()){
            idMagang.setText(idMagang_view);
            judul.setText(judul_view);
            tempat.setText(tempat_view);
            provinsi.setText(provinsi_view);
            kota.setText(kota_view);
            tanggalMulai.setText(tanggalMulai_view);
            tanggalSelesai.setText(tanggalSelesai_view);
            ringkasan.setText(ringkasan_view);
            scanBukti.setText(scanBukti_view);
            uploadLaporan.setText(uploadLaporan_view);
            verifikasi_magang.setText(verifikasi_view);

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
                        DataMagang item = new DataMagang();

                        item.setIdMagang(ob.getString("idMagang"));
                        item.setJudul(ob.getString("judul"));
                        item.setTempat(ob.getString("tempat"));
                        item.setProvinsi(ob.getString("provinsi"));
                        item.setKota(ob.getString("kota"));
                        item.setTanggalmulaiMagang(ob.getString("tanggalmulaiMagang"));
                        item.setTanggalselesaiMagang(ob.getString("tanggalselesaiMagang"));
                        item.setRingkasan(ob.getString("ringkasan"));
                        item.setScanBukti(ob.getString("scanBukti"));
                        item.setUploadLaporan(ob.getString("uploadLaporan"));
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
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){

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
        else if(requestCode == REQ_PDF && resultCode == RESULT_OK && data != null){

            Uri path2 = data.getData();


            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(path2);
                byte[] pdfInBytes = new byte[inputStream.available()];
                inputStream.read(pdfInBytes);
                encodedPDF = Base64.encodeToString(pdfInBytes, Base64.DEFAULT);
                    textView2.setText("Document Selected");
                    btnSelect2.setText("Change Document");



                Toast.makeText(getActivity(), "Document Selected", Toast.LENGTH_SHORT).show();


            } catch (IOException e) {
                e.printStackTrace();

            }

        }
//        else if(requestCode == REQ_PDF2 && resultCode == RESULT_OK && data != null){
//
//            Uri path2 = data.getData();
//
//
//            try {
//                InputStream inputStream = getActivity().getContentResolver().openInputStream(path2);
//                byte[] pdfInBytes2 = new byte[inputStream.available()];
//                inputStream.read(pdfInBytes2);
//                encodedPDF2 = Base64.encodeToString(pdfInBytes2, Base64.DEFAULT);
//                    textView2.setText("Document Selected");
//                    btnSelect2.setText("Change Document");
//
//
//                Toast.makeText(getActivity(), "Document Selected", Toast.LENGTH_SHORT).show();
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//
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
        String url ;
        // jika id kosong maka simpan, jika id ada nilainya maka update

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();



        StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
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
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
//                if (id.isEmpty()){
                    params.put("npm", getId);
                    params.put("judul", judul);
                    params.put("tempat", tempat);
                    params.put("provinsi", provinsi);
                    params.put("kota", kota);
                    params.put("tanggalmulaiMagang", tanggalMulai);
                    params.put("tanggalselesaiMagang", tanggalSelesai);
                    params.put("ringkasan", ringkasan);
                    //params.put("PDF", encodedimage);
                    params.put("PDFLaporan", encodedPDF);

                if(bitmap==null){
                    params.put("PDF", encodedPDF);
                    params.put("file", judul + ".pdf");
                    encodedPDF = "";
                    encodedimage = "";
                } else{
                    params.put("PDF", encodedimage);
                    params.put("file", judul + ".jpg");
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
            protected Map<String, String> getParams() throws AuthFailureError{
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idMagang", idx);

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

                            String idx      = object.getString("idMagang");
                            String judulx    = object.getString("judul");
                            String tempatx  = object.getString("tempat");
                            String provinsix  = object.getString("provinsi");
                            String kotax  = object.getString("kota");
                            String tanggalmulaiMagangx  = object.getString("tanggalmulaiMagang");
                            String tanggalselesaiMagangx     = object.getString("tanggalselesaiMagang");
                            String ringkasanx    = object.getString("ringkasan");
                            String scanBuktix  = object.getString("scanBukti");
                            String uploadLaporanx  = object.getString("uploadLaporan");
                            String verifikasix  = object.getString("verifikasi");

                            DialogForm3(idx, judulx, tempatx,provinsix,kotax,tanggalmulaiMagangx,tanggalselesaiMagangx,ringkasanx,scanBuktix,uploadLaporanx,verifikasix, "");
                            //txt_jenis.setVisibility(View.GONE);
                            if(verifikasix.equals("Sudah Diverifikasi")){
                                verifikasi_magang.setTextColor(Color.parseColor("#FFFFFF"));
                                inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#7ae472"));
                            }else if(verifikasix.equals("Belum Diverifikasi")){
                                inputVerifikasi.setBoxBackgroundColor(Color.parseColor("#F08080"));
                                verifikasi_magang.setTextColor(Color.parseColor("#FFFFFF"));
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
                params.put("idMagang", idx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


}