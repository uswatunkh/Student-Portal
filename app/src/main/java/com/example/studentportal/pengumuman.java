package com.example.studentportal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

//panggil di manifest
public class pengumuman extends AppCompatActivity {
    RecyclerView NewsRecyclerview;
    pengumuman_newAdapter pengumuman_newAdapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    List<PengumumanItem> mData;
    private static final String URLPengumuman =Server.URL + "read_pengumuman.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengumuman_recycle);
        NewsRecyclerview=(RecyclerView)findViewById(R.id.news_rv);
        NewsRecyclerview.setHasFixedSize(true);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mData=new ArrayList<>();
        pengumuman_newAdapter=new pengumuman_newAdapter(this, mData);

        getMovieData();

    }

    private void getMovieData() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URLPengumuman, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        PengumumanItem listData=new PengumumanItem(ob.getString("title"),ob.getString("body")
                                ,ob.getString("tanggalPengumuman"));
                        mData.add(listData);
                    }
                    NewsRecyclerview.setAdapter(pengumuman_newAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
