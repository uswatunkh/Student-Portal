package com.example.studentportal;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentportal.Fitur.DaftarUlang_TableViewAdapter;
import com.example.studentportal.Fitur.DaftarUlang_list;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaftarUlang extends AppCompatActivity {
    private static final String TAG= data_diri.class.getSimpleName() ;  //getting the info
    RecyclerView NewsRecyclerview;
    com.example.studentportal.Fitur.DaftarUlang_TableViewAdapter DaftarUlang_TableViewAdapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    List<DaftarUlang_list> mData;
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readDaftarUlang.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_ulang);

        NewsRecyclerview=(RecyclerView)findViewById(R.id.news_rv);
        NewsRecyclerview.setHasFixedSize(true);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mData=new ArrayList<>();
        DaftarUlang_TableViewAdapter=new DaftarUlang_TableViewAdapter(this, mData);

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
                            JSONArray jsonArray =jsonObject.getJSONArray("data");


                                for (int i=0; i< jsonArray.length(); i++){
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    DaftarUlang_list listData=new DaftarUlang_list(ob.getString("idPeriode")
                                            ,ob.getString("ukt"),ob.getString("statusBayar"),ob.getString("cetakKrs"));
                                    mData.add(listData);



                                }
                                NewsRecyclerview.setAdapter(DaftarUlang_TableViewAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(DaftarUlang.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(DaftarUlang.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

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
