package com.example.studentportal.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;
import com.example.studentportal.data_diri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataDiriUbahFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataDiriUbahFragment extends Fragment {

    private static final String TAG= data_diri.class.getSimpleName() ;  //getting the info

    private EditText npm,nik,namaLengkap,tempatLahir,tanggalLahir,jenisKelamin,agama,provinsiAsal,alamatAsal,kotaAsal,
            kecamatanAsal, alamatSekarang,desa, tahunAngkatan,noHp,email;
    Button tambah;
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readDatadiri.php";
    private static String URL_EDIT = Server.URL + "editDatadiri.php";
    Button selectDate;
    EditText date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    RadioGroup radio;
    RadioButton perempuan,laki;
    String jk;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataDiriUbahFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataDiriUbahFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataDiriUbahFragment newInstance(String param1, String param2) {
        DataDiriUbahFragment fragment = new DataDiriUbahFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_data_diri_ubah, container, false);
        npm=root.findViewById(R.id.npm);
        nik=root.findViewById(R.id.nik);
        namaLengkap=root.findViewById(R.id.namlep);
        tempatLahir=root.findViewById(R.id.tempatLahir);
        jenisKelamin=root.findViewById(R.id.jenisKelamin);
        agama=root.findViewById(R.id.agama);
        provinsiAsal=root.findViewById(R.id.provinsiAsal);
        alamatAsal=root.findViewById(R.id.alamatAsal);
        kotaAsal=root.findViewById(R.id.kotaAsal);
        kecamatanAsal=root.findViewById(R.id.kecamatanAsal);
        desa=root.findViewById(R.id.desa);
        tahunAngkatan=root.findViewById(R.id.tahunAngkatan);
        alamatSekarang=root.findViewById(R.id.alamatSekarang);
        noHp=root.findViewById(R.id.noHp);
        email=root.findViewById(R.id.email);

        perempuan = root.findViewById(R.id.perempuan);
        laki= root.findViewById(R.id.lakilaki);
        radio=root.findViewById(R.id.group);

        date = root.findViewById(R.id.tvSelectedDate);
        tambah =root.findViewById(R.id.tambah);





        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveEditDetail();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
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
                                date.setText(year + "-" + (month + 1) + "-" + day);
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil

        RadioGroup radioGroup = (RadioGroup) root.findViewById(R.id.group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.perempuan){
                    jk = String.valueOf("Perempuan");
                }else if (checkedId == R.id.lakilaki){
                    jk = String.valueOf("Laki-laki");
                }
            }
        });

//        jenisKelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == R.id.lakilaki){
//                    jk = String.valueOf("L");
//                }else if (checkedId == R.id.perempuan){
//                    jk = String.valueOf("P");
//                }
//            }
//        });

        return root;
    }


//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.perempuan:
//                if (checked)
//                    perempuan.setText("Perempuan");
////                    Toast.makeText(getActivity(), radio+toString(), Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.lakilaki:
//                if (checked)
//                   laki.setText("Perempuan");
////                    Toast.makeText(getActivity(), radio+toString(), Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

    private void getUserDetail(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray =jsonObject.getJSONArray("read");

                            if(success.equals("1")){
                                for (int i=0; i< jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strNpm = object.getString("npm").trim();
                                    String strNik = object.getString("nik").trim();
                                    String strName = object.getString("namaLengkap").trim();
                                    String strTemplahir = object.getString("tempatLahir").trim();
                                    String strTglLahir = object.getString("tanggalLahir").trim();
                                    String strJenkel = object.getString("jenisKelamin").trim();
                                    String strAgama = object.getString("agama").trim();
                                    String strProv = object.getString("provinsiAsal").trim();
                                    String strAlamatasal = object.getString("alamatAsal").trim();
                                    String strKotaasal = object.getString("kotaAsal").trim();
                                    String strKecamasal = object.getString("kecamatanAsal").trim();
                                    String strDesa = object.getString("desa").trim();
                                    String strAlamatSekarang = object.getString("alamatSekarang").trim();
//                                    String strTahunAngkatan = object.getString("periode").trim();
                                    String strNohp = object.getString("noHp").trim();
                                    String strEmail = object.getString("email").trim();

                                    npm.setText(strNpm);
                                    nik.setText(strNik);
                                    namaLengkap.setText(strName);
                                    tempatLahir.setText(strTemplahir);
                                    date.setText(strTglLahir);
                                    jenisKelamin.setText(strJenkel);
                                    agama.setText(strAgama);
                                    provinsiAsal.setText(strProv);
                                    alamatAsal.setText(strAlamatasal);
                                    kotaAsal.setText(strKotaasal);
                                    kecamatanAsal.setText(strKecamasal);
                                    desa.setText(strDesa);
                                    alamatSekarang.setText(strAlamatSekarang);
//                                    tahunAngkatan.setText(strTahunAngkatan);
                                    noHp.setText(strNohp);
                                    email.setText(strEmail);


                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", getId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onResume() {
        super.onResume();
        getUserDetail();
    }

    //Save
    private void SaveEditDetail() {
        final String npm = this.npm.getText().toString().trim();
        final String nik = this.nik.getText().toString().trim();
        final String namaLengkap = this.namaLengkap.getText().toString().trim();
        final String tempatLahir = this.tempatLahir.getText().toString().trim();
        final String date = this.date.getText().toString().trim();
        final String jenisKelamin = jk.toString();
        final String agama = this.agama.getText().toString().trim();
        final String provinsiAsal = this.provinsiAsal.getText().toString().trim();
        final String kotaAsal = this.kotaAsal.getText().toString().trim();
        final String kecamatanAsal = this.kecamatanAsal.getText().toString().trim();
        final String desa = this.desa.getText().toString().trim();
        final String alamatAsal = this.alamatAsal.getText().toString().trim();
        final String alamatSekarang = this.alamatSekarang.getText().toString().trim();
        final String tahunAngkatan = this.tahunAngkatan.getText().toString().trim();
        final String noHP = this.noHp.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succcess =jsonObject.getString("success");

                            if (succcess.equals("1")){
                                Toast.makeText(getActivity(), "Success!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(id);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
//                params.put("npm", npm);
                params.put("nik", nik);
                params.put("namaLengkap", namaLengkap);
                params.put("tempatLahir", tempatLahir);
                params.put("tanggalLahir", date);
                params.put("jenisKelamin", jenisKelamin);
                params.put("agama", agama);
                params.put("provinsiAsal", provinsiAsal);
                params.put("kotaAsal", kotaAsal);
                params.put("kecamatanAsal", kecamatanAsal);
                params.put("desa", desa);
                params.put("alamatAsal", alamatAsal);
                params.put("alamatSekarang", alamatSekarang);

                params.put("noHp", noHP);
                params.put("email", email);
                //                params.put("periode", periode);
                params.put("npm", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
}