package com.example.studentportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import com.example.studentportal.Fitur.HasilStudiAwalFragment;
import com.example.studentportal.Fitur.PresensiSemesterFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Presensi extends AppCompatActivity {
    int position;
    private static final String TAG= Presensi.class.getSimpleName() ;  //getting the info
    RecyclerView NewsRecyclerview;
    Presensi_TableViewAdapter presensi_tableViewAdapter;
    RecyclerView.Adapter adapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    List<Presensi_list> mData;
    SessionManager sessionManager;
    String getId, getSmst;  //updateprofil
    CardView refresh;
    private static String URL_READ     = Server.URL+ "readPresensi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presensi);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        //txtSemester.setText( HasilStudiAwalFragment.itemList.get(position).getSemester());
        getSmst= PresensiSemesterFragment.itemList.get(position).getSemester();

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil


        NewsRecyclerview=(RecyclerView)findViewById(R.id.recyclerViewDeliveryProductList);
        NewsRecyclerview.setHasFixedSize(true);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mData=new ArrayList<>();
        presensi_tableViewAdapter=new Presensi_TableViewAdapter(this, mData);
        refresh=findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserDetail();
            }
        });
    }

    private void getUserDetail(){
        mData.clear();
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
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("0")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Presensi.this);
                                builder.setMessage("Data Masih Kosong")
                                        .setNegativeButton("Ok",null)
                                        .create()
                                        .show();
//                                dataKosong.setVisibility(View.VISIBLE);
//                                dataKosong.setText("Data Jadwal Hari Senin Masih Kosong");


                            }else if (success.equals("1"))  {
                                JSONObject json =new JSONObject(response);
                                JSONArray jsonArray =json.getJSONArray("data");



                                for (int i=0; i< jsonArray.length(); i++){
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    Presensi_list listData=new Presensi_list(ob.getString("mingguKe"),ob.getString("tanggalPresensi")
                                            ,ob.getString("namaMK"),ob.getString("ket"));
                                    mData.add(listData);



                                }
                                NewsRecyclerview.setAdapter(presensi_tableViewAdapter);
                            }
//                        try {
//                            JSONObject jsonObject =new JSONObject(response);
//                            JSONArray jsonArray =jsonObject.getJSONArray("data");
//
//
//                            for (int i=0; i< jsonArray.length(); i++){
//                                JSONObject ob = jsonArray.getJSONObject(i);
//                                String strIp = ob.getString("ip").trim();
//                                String strIpk = ob.getString("ipk").trim();
//
//                                txtIp.setText(strIp);
//                                txtIpk.setText(strIpk);
//                                HasilStudi_list listData=new HasilStudi_list(ob.getString("idMataKuliah"),ob.getString("namaMK")
//                                ,ob.getString("sks"),ob.getString("nilaiAngka"),ob.getString("nilaiHuruf")
//                                        ,ob.getString("totalNilai"));
//                                mData.add(listData);
//
//
//
//                            }
//                            NewsRecyclerview.setAdapter(hasilStudi_tableViewAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(Presensi.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Presensi.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", getId);
                params.put("semester", getSmst);
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
