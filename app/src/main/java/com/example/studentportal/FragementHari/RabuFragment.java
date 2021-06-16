package com.example.studentportal.FragementHari;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RabuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RabuFragment extends Fragment {
    private static final String TAG= RabuFragment.class.getSimpleName() ;  //getting the info
    TextView dataKosong;
    RecyclerView NewsRecyclerview;
    Hari_TableViewAdapter Hari_TableViewAdapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    List<Hari_list> mData;
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readRabu.php";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RabuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RabuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RabuFragment newInstance(String param1, String param2) {
        RabuFragment fragment = new RabuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_hari, container, false);
        NewsRecyclerview=(RecyclerView)root.findViewById(R.id.news_rv);
        dataKosong= root.findViewById(R.id.dataKosong);
        NewsRecyclerview.setHasFixedSize(true);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mData=new ArrayList<>();
        Hari_TableViewAdapter=new Hari_TableViewAdapter(getActivity(), mData);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil

        return root;
    }

    private void getUserDetail(){
        mData.clear();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                builder.setMessage("Data Masih Kosong")
//                                        .setNegativeButton("Ok",null)
//                                        .create()
//                                        .show();
                                dataKosong.setVisibility(View.VISIBLE);
                                dataKosong.setText("Data Jadwal Hari Rabu Masih Kosong");


                            }else if (success.equals("1"))  {
                                JSONObject json =new JSONObject(response);
                                JSONArray jsonArray =json.getJSONArray("data");



                                for (int i=0; i< jsonArray.length(); i++){
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    Hari_list listData=new Hari_list(ob.getString("idMataKuliah")
                                            ,ob.getString("namaMK"),ob.getString("namaDosen"),
                                            ob.getString("jamke"), ob.getString("namaRuang"));
                                    mData.add(listData);



                                }
                                NewsRecyclerview.setAdapter(Hari_TableViewAdapter);




                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", getId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onResume() {
        super.onResume();
        getUserDetail();
    }
}