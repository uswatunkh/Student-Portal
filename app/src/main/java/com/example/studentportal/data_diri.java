package com.example.studentportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class data_diri extends AppCompatActivity {
    private static final String TAG= data_diri.class.getSimpleName() ;  //getting the info

    private EditText nik,namaLengkap,lahir,jenisKelamin,agama,alamatAsal,kotaAsal,alamatSekarang,noHp,email;
    Button ubah;
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readDatadiri.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_diri);
        ubah = findViewById(R.id.butProfil);
        nik=findViewById(R.id.nik);
        namaLengkap=findViewById(R.id.namaLengkap);
        lahir=findViewById(R.id.lahir);
        jenisKelamin=findViewById(R.id.jenisKelamin);
        agama=findViewById(R.id.agama);
        alamatAsal=findViewById(R.id.alamatAsal);
        kotaAsal=findViewById(R.id.kotaAsal);
        alamatSekarang=findViewById(R.id.alamatSekarang);
        noHp=findViewById(R.id.noHp);
        email=findViewById(R.id.email);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(data_diri.this,ubah_data_diri.class);
                startActivity(intent);
            }
        });
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil


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

                                    String strNik = object.getString("nik").trim();
                                    String strName = object.getString("namaLengkap").trim();
                                    String strLahir = object.getString("tempatLahir").trim();
                                    String strJenkel = object.getString("jenisKelamin").trim();
                                    String strAgama = object.getString("agama").trim();
                                    String strAlamatasal = object.getString("alamatAsal").trim();
                                    String strKotaasal = object.getString("kotaAsal").trim();
                                    String strAlamatSekarang = object.getString("alamatSekarang").trim();
                                    String strNohp = object.getString("noHp").trim();
                                    String strEmail = object.getString("email").trim();

                                    nik.setText(strNik);
                                    namaLengkap.setText(strName);
                                    lahir.setText(strLahir);
                                    jenisKelamin.setText(strJenkel);
                                    agama.setText(strAgama);
                                    alamatAsal.setText(strAlamatasal);
                                    kotaAsal.setText(strKotaasal);
                                    alamatSekarang.setText(strAlamatSekarang);
                                    noHp.setText(strNohp);
                                    email.setText(strEmail);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(data_diri.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(data_diri.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

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
}
