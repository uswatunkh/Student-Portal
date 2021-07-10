package com.example.studentportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.studentportal.Fitur.AdapterEvdos;
import com.example.studentportal.Fitur.DataEvdos;
import com.example.studentportal.Fitur.EvaluasiDosenFragment;
import com.example.studentportal.Fitur.Kuisioner;
import com.example.studentportal.Fragment.HomeFragment;
import com.example.studentportal.app.AppController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluasiDosen extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    ImageView backKeterampilan;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    public  static List<DataEvdos> itemList = new ArrayList<DataEvdos>();
    AdapterEvdos adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_idDosen, txt_namaDosen, txt_namaMK,txt_idMk;
    Button upload;
    String idDosen, namaDosen, namaMk,idMK;

    Bitmap bitmap;

    private static final String TAG = EvaluasiDosenFragment.class.getSimpleName();
    private static String url_select     = Server.URLEvaluasiDosen + "select.php";

    private static String URL_UPLOAD = Server.URL + "uploadKeterampilan.php";

    public static final String TAG_ID       = "idDosen";
    public static final String TAG_NAMADOSEN     = "namaDosen";
    public static final String TAG_NAMAMK     = "namaMK";
    public static final String TAG_NIDN     = "nidn";
    public static final String TAG_IDMATAKULIAH    = "idMataKuliah";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi_dosen);
        fab     = (FloatingActionButton) findViewById(R.id.fab_add);
        swipe   = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) findViewById(R.id.list);

        backKeterampilan= (ImageView) findViewById(R.id.backKeterampilan);
        backKeterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openFragment(HomeFragment.newInstance("", ""));
                Intent intent;
                intent = new Intent(EvaluasiDosen.this, FragmentUtama.class);
                startActivity(intent);

            }
        });

//        Bundle bundle = getArguments();
//        String message = bundle.getString("message");
//        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterEvdos(this, itemList);
        list.setAdapter(adapter);


        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                           //new AdapterEvdos(getActivity(), itemList).cekKuisioner();
                       }
                   }
        );

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }




    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
        //cekKuisioner();
        //new AdapterEvdos(getActivity(), itemList).cekKuisioner();
    }

//    void cekKuisioner(String getidPengajaran){
//        SessionManager sessionManager = new SessionManager(getActivity());
//        sessionManager.checkLogin();
//        HashMap<String, String> user = sessionManager.getUserDetail();
//        String getId = user.get(sessionManager.ID);
//        //String url_select = Server.URLEvaluasiDosen + "cekKuisioner.php?npm=" + getId + "&idPengajaran=" + idPengajaran + "&namaMK=" +  namaMK ;
//        String url_select = Server.URLEvaluasiDosen + "cekKuisioner.php";
//        StringRequest jArr = new StringRequest(Request.Method.POST,url_select, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    String success = jsonObject.getString("success");
//                    Log.e("Data: ", response.toString());
//                    AdapterEvdos adap = new AdapterEvdos(getActivity(),itemList);
//                    if (success.equals("0")) {
//
//                        adap.cekKuisioner.setText("Kuisioner sudah terisi");
//
//                    }else if (success.equals("1")) {
//                        adap.cekKuisioner.setText("Kuisioner Belum terisi");
//
//                    }
//                    //int success = jsonObject.getInt("success");
////                    if(success == 1){
////                        kuisioner = true;
////                    }else kuisioner = false;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        })
//
//        {
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("npm", getId);
//                params.put("idPengajaran", getidPengajaran);
//                //params.put("namaMK", namaMK);
//                return params;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(jArr);
//    }


    // untuk menampilkan semua data pada listview
    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);
        SessionManager sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String getId = user.get(sessionManager.ID);
        //new AdapterEvdos(getActivity(), itemList).cekKuisioner();

        // membuat request JSON
        StringRequest jArr = new StringRequest(Request.Method.POST,url_select, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
//                        Data listData=new Data(ob.getString("id"),ob.getString("nama")
//                                ,ob.getString("tlp"),ob.getString("email"));
//                        itemList.add(listData);
                        DataEvdos item = new DataEvdos();

                        item.setIdDosen(ob.getString("idPengajaran"));
                        item.setNamaDosen(ob.getString(TAG_NAMADOSEN));
                        item.setNamaMatakuliah(ob.getString("namaMk"));
                        item.setStatusKuisioner(ob.getString("statusKuisioner"));




//                        item.setNidn(ob.getString(TAG_NIDN));
//                        item.setIdMatakuliah(ob.getString(TAG_IDMATAKULIAH));

                        // menambah item ke array
                        itemList.add(item);
                    }
                    list.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
                //new AdapterEvdos(getActivity(), null).cekKuisioner();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", getId);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jArr);
    }
}