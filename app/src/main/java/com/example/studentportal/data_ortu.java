package com.example.studentportal;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

public class data_ortu extends AppCompatActivity {
    private static final String TAG= data_diri.class.getSimpleName() ;  //getting the info

    private EditText namaAyah, pekerjaanAyah,instansiAyah,namaIbu,pekerjaanIbu,instansiIbu,namaWali,pekerjaanWali,instansiWali;
    Button ubah;
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readDataortu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_ortu);

        namaAyah=findViewById(R.id.namaAyah);
        pekerjaanAyah=findViewById(R.id.pekerjaanAyah);
        instansiAyah=findViewById(R.id.instansiAyah);
        namaIbu=findViewById(R.id.namaIbu);
        pekerjaanIbu=findViewById(R.id.pekerjaanIbu);
        instansiIbu=findViewById(R.id.instansiIbu);
        namaWali=findViewById(R.id.namaWali);
        pekerjaanWali=findViewById(R.id.pekerjaanWali);
        instansiWali=findViewById(R.id.instansiWali);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
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

                                    String strNA = object.getString("namaAyah").trim();
                                    String strPA = object.getString("pekerjaanAyah").trim();
                                    String strIA= object.getString("instansiAyah").trim();
                                    String strNI = object.getString("namaIbu").trim();
                                    String strPI = object.getString("pekerjaanIbu").trim();
                                    String strII = object.getString("instansiIbu").trim();
                                    String strNW = object.getString("namaWali").trim();
                                    String strPW = object.getString("pekerjaanWali").trim();
                                    String strIW = object.getString("instansiWali").trim();

                                    namaAyah.setText(strNA);
                                    pekerjaanAyah.setText(strPA);
                                    instansiAyah.setText(strIA);
                                    namaIbu.setText(strNI);
                                    pekerjaanIbu.setText(strPI);
                                    instansiIbu.setText(strII);
                                    namaWali.setText(strNW);
                                    pekerjaanWali.setText(strPW);
                                    instansiWali.setText(strIW);


                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(data_ortu.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(data_ortu.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

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
