package com.example.studentportal;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.AccessNetworkConstants;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class ubah_data_diri extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubah_data_diri);

        npm=findViewById(R.id.npm);
        nik=findViewById(R.id.nik);
        namaLengkap=findViewById(R.id.namlep);
        tempatLahir=findViewById(R.id.tempatLahir);
        jenisKelamin=findViewById(R.id.jenisKelamin);
        agama=findViewById(R.id.agama);
        provinsiAsal=findViewById(R.id.provinsiAsal);
        alamatAsal=findViewById(R.id.alamatAsal);
        kotaAsal=findViewById(R.id.kotaAsal);
        kecamatanAsal=findViewById(R.id.kecamatanAsal);
        desa=findViewById(R.id.desa);
        tahunAngkatan=findViewById(R.id.tahunAngkatan);
        alamatSekarang=findViewById(R.id.alamatSekarang);
        noHp=findViewById(R.id.noHp);
        email=findViewById(R.id.email);

         perempuan = findViewById(R.id.perempuan);
         laki= findViewById(R.id.lakilaki);
         radio=findViewById(R.id.group);

        date = findViewById(R.id.tvSelectedDate);
        tambah =findViewById(R.id.tambah);


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
                datePickerDialog = new DatePickerDialog(ubah_data_diri.this,
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

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();


        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.perempuan:
                if (checked)
                    // Pirates are the best
                    Toast.makeText(ubah_data_diri.this, radio+toString(), Toast.LENGTH_SHORT).show();
                    break;
            case R.id.lakilaki:
                if (checked)
                    // Ninjas rule
                    Toast.makeText(ubah_data_diri.this, radio+toString(), Toast.LENGTH_SHORT).show();
                    break;
        }
    }
    private void getUserDetail(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
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
                            Toast.makeText(ubah_data_diri.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ubah_data_diri.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", getId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onResume() {
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
        final String jenisKelamin = this.jenisKelamin.getText().toString().trim();
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

        final ProgressDialog progressDialog = new ProgressDialog(this);
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
                                Toast.makeText(ubah_data_diri.this, "Success!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(id);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(ubah_data_diri.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ubah_data_diri.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}