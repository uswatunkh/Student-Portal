package com.example.studentportal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

import static android.content.ContentValues.TAG;

public class Keterampilan extends AppCompatActivity {
    RecyclerView NewsRecyclerview;
    keterampilanNewAdapter keterampilanNewAdapter;
    List<KeterampilanItem> mData;
    private static final String URLKeterampilan =Server.URL + "read_keterampilan.php";
    private static final String URLDelKeterampilan ="http://192.168.0.101/studentPortal/deleteKeterampilan.php";


    SessionManager sessionManager;
    String getId;  //updateprofil
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    int success;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //buat fullscreen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.keterampilan_recycle);

//       getSupportActionBar().hide();

        NewsRecyclerview =findViewById(R.id.news_rv);
        mData = new ArrayList<>();


        //data
//        mData.add(new KeterampilanItem("Silat","Olahraga","Kabupaten", ".pdf"));
//        mData.add(new KeterampilanItem("Silat","Olahraga","Kabupaten", ".pdf"));
//        mData.add(new KeterampilanItem("Silat","Olahraga","Kabupaten", ".pdf"));
//        mData.add(new KeterampilanItem("Silat","Olahraga","Kabupaten", ".pdf"));
//        mData.add(new KeterampilanItem("Silat","Olahraga","Kabupaten", ".pdf"));
//        mData.add(new KeterampilanItem("Silat","Olahraga","Kabupaten", ".pdf"));
//
        keterampilanNewAdapter = new keterampilanNewAdapter(this,mData);
        NewsRecyclerview.setAdapter(keterampilanNewAdapter);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));

//        sessionManager = new SessionManager(this);
//        sessionManager.checkLogin();
//
//        HashMap<String, String> user = sessionManager.getUserDetail();
//        getId = user.get(sessionManager.ID);  //updateprofil

        keterampilanNewAdapter.setOnItemClickListener(new keterampilanNewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                getId= mData.get(position).getId();
                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(getApplicationContext())
                        // set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete")
                        .setIcon(R.drawable.delete2)

                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //your deleting code
                                Delete(getId);
                            }

                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        })
                        .create();

            }
        });
        getUserDetail();

    }



    private void getUserDetail() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URLKeterampilan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        KeterampilanItem listData=new KeterampilanItem(ob.getString("idKeterampilan"),ob.getString("namaKeterampilan")
                                ,ob.getString("jenis"),ob.getString("tingkat"),ob.getString("scanBukti"));
                        mData.add(listData);
                    }
                    NewsRecyclerview.setAdapter(keterampilanNewAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
//        {
//            protected Map<String, String> getParams() throws AuthFailureError {
//            Map<String, String> params = new HashMap<>();
//            params.put("npm", getId);
//            return params;
//        }
//        }
        ;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        getUserDetail();
//    }


    private void Delete(final String idx) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLDelKeterampilan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equalsIgnoreCase("Data Deleted")){
                    Toast.makeText(Keterampilan.this, "Data Sukses Dihapus", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Keterampilan.this, "Failed Delete", Toast.LENGTH_LONG).show();
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Keterampilan.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("idKeterampilan", idx);

                return params;
            }
        }
                ;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
