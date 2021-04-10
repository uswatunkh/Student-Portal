package com.example.studentportal;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HasilStudiAwal extends AppCompatActivity {
    private static final String TAG= data_diri.class.getSimpleName() ;  //getting the info
    RecyclerView NewsRecyclerview;
    HasilStudiAwalNewAdapter HasilStudiAwalNewAdapter;
    List<HasilStudiAwalItem> mData;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private TextView semester;
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readSemester.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //buat fullscreen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.hasilstudi_awalrecycler);
        NewsRecyclerview=(RecyclerView)findViewById(R.id.news_rv);


        mData=new ArrayList<>();
//        sessionManager = new SessionManager(this);
//        sessionManager.checkLogin();
//
//        HashMap<String, String> user = sessionManager.getUserDetail();
//        getId = user.get(sessionManager.ID);  //updateprofil

//        getSupportActionBar().hide();

//        NewsRecyclerview =findViewById(R.id.news_rv);
//        mData = new ArrayList<>();
//        getUserDetail();


//        //data
//        mData.add(new HasilStudiAwalItem("Semester 1"));
//        mData.add(new HasilStudiAwalItem("Semester 2"));
//        mData.add(new HasilStudiAwalItem("Semester 3"));
//        mData.add(new HasilStudiAwalItem("Semester 2"));
//        mData.add(new HasilStudiAwalItem("Semester 3"));


//        HasilStudiAwalNewAdapter = new HasilStudiAwalNewAdapter(this,mData);
//        NewsRecyclerview.setAdapter(HasilStudiAwalNewAdapter);
//        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        NewsRecyclerview=(RecyclerView)findViewById(R.id.news_rv);
        NewsRecyclerview.setHasFixedSize(true);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mData=new ArrayList<>();
        HasilStudiAwalNewAdapter=new HasilStudiAwalNewAdapter(this, mData);

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
                                    HasilStudiAwalItem listData=new HasilStudiAwalItem(ob.getString("semester")
                                            );
                                    mData.add(listData);


                                }
                            NewsRecyclerview.setAdapter(HasilStudiAwalNewAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(HasilStudiAwal.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(HasilStudiAwal.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

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
