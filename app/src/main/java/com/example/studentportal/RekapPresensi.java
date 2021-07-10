package com.example.studentportal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentportal.Fitur.DataPresensiAwal;
import com.example.studentportal.Fitur.HasilStudiAwalFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RekapPresensi extends AppCompatActivity {
    int position;
    TextView txtSemester,txtIp,txtIpk;
    private static final String TAG= RekapPresensi.class.getSimpleName() ;  //getting the info
    RecyclerView NewsRecyclerview;
    RekapPresensi_Adapter rekapPresensi_adapter;
    RecyclerView.Adapter adapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    public  static ArrayList<RekapPresensi_list> itemlist;
    SessionManager sessionManager;
    String getId, getSmst;  //updateprofil
    ImageView refresh;
    ImageView back;
    private static String URL_READ     = Server.URL+ "readRekapPresensi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_presensi);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil

//        Intent intent = getIntent();
//        position = intent.getExtras().getInt("position");
//        txtSemester.setText( HasilStudiAwalFragment.itemList.get(position).getSemester());
//        getSmst=HasilStudiAwalFragment.itemList.get(position).getSemester();

        NewsRecyclerview=(RecyclerView)findViewById(R.id.recyclerViewDeliveryProductList);
        NewsRecyclerview.setHasFixedSize(true);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        itemlist=new ArrayList<>();
        rekapPresensi_adapter=new RekapPresensi_Adapter(this, itemlist);
        refresh=findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserDetail();
            }
        });
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void getUserDetail(){
        itemlist.clear();
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
                                JSONObject json =new JSONObject(response);
                                JSONArray jsonArray =json.getJSONArray("data");



                                for (int i=0; i< jsonArray.length(); i++) {
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    int j=i+1;

                                    RekapPresensi_list listData = new RekapPresensi_list();

                                    listData.setSemester(ob.getString("semester"));
                                    listData.setIdRekapPresensi(ob.getString("idRekapPresensi"));
                                    listData.setHadir(ob.getString("hadir"));
                                    listData.setIzin(ob.getString("izin"));
                                    listData.setSakit(ob.getString("sakit"));
                                    listData.setKosong(ob.getString("kosong"));
                                    itemlist.add(listData);



                                }
                                NewsRecyclerview.setAdapter(rekapPresensi_adapter);

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
//                                itemlist.add(listData);
//
//
//
//                            }
//                            NewsRecyclerview.setAdapter(hasilStudi_tableViewAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(RekapPresensi.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(RekapPresensi.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

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
