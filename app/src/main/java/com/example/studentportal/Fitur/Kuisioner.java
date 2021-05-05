package com.example.studentportal.Fitur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kuisioner extends AppCompatActivity {
    int position;
    TextView idDosen,coba,txtPertanyaan;
    private static final String TAG= Kuisioner.class.getSimpleName() ;  //getting the info
    RecyclerView NewsRecyclerview,NewsRecyclerview2,NewsRecyclerview3;
    AdapterTable_Kuisioner adapterTable_kuisioner, adapterTable_kuisioner2,adapterTable_kuisioner3;
    RecyclerView.Adapter adapter;
    AdapterTable_Kuisioner.RowViewHolder adap;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    List<DataKuisioner> mData;
    List<DataKuisioner> mData2;
    List<DataKuisioner> mData3;
    SessionManager sessionManager;
    String getId,getIdDosen;  //updateprofil
    String jk;
    RadioGroup radioGroup;
    RadioButton Tidakpernah, pernah,sering,selalu;
    TextView jawaban,txtIdEvdos;
    private static String URL_READ     = Server.URLEvaluasiDosen + "selectEvdos.php";
    private static String URL_READ2     = Server.URLEvaluasiDosen + "selectEvdos2.php";
    private static String URL_READ3     = Server.URLEvaluasiDosen + "selectEvdos3.php";
    private static String URL_EDIT = Server.URLEvaluasiDosen + "pengisianEvdos.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kuisioner);
        idDosen = findViewById(R.id.idDosen);
        coba = findViewById(R.id.coba);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
       // idDosen.setText("ID:"+EvaluasiDosenFragment.itemList.get(position).getIdDosen());
        getIdDosen=EvaluasiDosenFragment.itemList.get(position).getIdDosen();


        Button button = findViewById(R.id.btnSubmitEval);
        NewsRecyclerview=(RecyclerView)findViewById(R.id.news_rv);
        NewsRecyclerview.setHasFixedSize(true);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mData=new ArrayList<>();
        adapterTable_kuisioner=new AdapterTable_Kuisioner(this, mData);
        NewsRecyclerview2=(RecyclerView)findViewById(R.id.news_rv2);
        NewsRecyclerview2.setHasFixedSize(true);
        NewsRecyclerview2.setLayoutManager(new LinearLayoutManager(this));
        mData2=new ArrayList<>();
        adapterTable_kuisioner2=new AdapterTable_Kuisioner(this, mData2);
        NewsRecyclerview3=(RecyclerView)findViewById(R.id.news_rv3);
        NewsRecyclerview3.setHasFixedSize(true);
        NewsRecyclerview3.setLayoutManager(new LinearLayoutManager(this));
        mData3=new ArrayList<>();
        adapterTable_kuisioner3=new AdapterTable_Kuisioner(this, mData3);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEditDetail();
            }
        });


        LayoutInflater inflater = LayoutInflater.from(this);
        View layout= inflater.inflate(R.layout.kuisionerlist_item,null,false);
        jawaban = layout.findViewById(R.id.jawaban);
        txtIdEvdos=layout.findViewById(R.id.txtidEvdos);
        txtPertanyaan=layout.findViewById(R.id.txtPertanyaan);
        String jawab= jawaban.getText().toString().trim();
        Toast.makeText(Kuisioner.this, jawab,Toast.LENGTH_SHORT).show();



        Tidakpernah =layout.findViewById(R.id.tidakPernah);
        pernah =layout.findViewById(R.id.pernah);
        sering =layout.findViewById(R.id.sering);
        selalu =layout.findViewById(R.id.selalu);

        // txtIdEvdos.setText("ID:"+EvaluasiDosenFragment.itemList.get(position).getIdDosen());
//        radioGroup = (RadioGroup) layout.findViewById(R.id.Rad_group);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//        {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // checkedId is the RadioButton selected
//                if (checkedId == R.id.tidakPernah){
//                    jk = String.valueOf("Tidak Pernah");
//                    Toast.makeText(Kuisioner.this, "OKeeee",Toast.LENGTH_SHORT).show();
//                }if (checkedId == R.id.pernah){
//                    jk = String.valueOf("Pernah");
//                }if (checkedId == R.id.sering){
//                    jk = String.valueOf("Sering");
//                }if (checkedId == R.id.selalu){
//                    jk = String.valueOf("Selalu");
//                }
//                jawaban.setText(jk);
//                String coba= jawaban.getText().toString().trim();
//
//
//
//            }
//        });


    }


    public void SaveEditDetail() {
        final String idEvdos = txtIdEvdos.getText().toString().trim();
        final String jawaban = this.jawaban.getText().toString().trim();

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
                                Toast.makeText(Kuisioner.this, "Success!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(id);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(Kuisioner.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Kuisioner.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", id);
                params.put("idEvdos", idEvdos);
                params.put("jawaban", jawaban);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

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
                            JSONObject jsonObject =new JSONObject(response);
                            JSONArray jsonArray =jsonObject.getJSONArray("data");


                            for (int i=0; i< jsonArray.length(); i++){
                                JSONObject ob = jsonArray.getJSONObject(i);
                                DataKuisioner listData=new DataKuisioner(ob.getString("idEvdos"),ob.getString("pertanyaan"));
                                mData.add(listData);



                            }
                            NewsRecyclerview.setAdapter(adapterTable_kuisioner);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(Kuisioner.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Kuisioner.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idDosen", getIdDosen);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    private void getUserDetail2(){
        mData2.clear();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ2,
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
                                DataKuisioner listData=new DataKuisioner(ob.getString("idEvdos"),ob.getString("pertanyaan"));
                                mData2.add(listData);



                            }
                            NewsRecyclerview2.setAdapter(adapterTable_kuisioner2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(Kuisioner.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Kuisioner.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idDosen", getIdDosen);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getUserDetail3(){
        mData3.clear();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ3,
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
                                DataKuisioner listData=new DataKuisioner(ob.getString("idEvdos"),ob.getString("pertanyaan"));
                                mData3.add(listData);



                            }
                            NewsRecyclerview3.setAdapter(adapterTable_kuisioner3);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(Kuisioner.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Kuisioner.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idDosen", getIdDosen);
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
        getUserDetail2();
        getUserDetail3();
    }
}