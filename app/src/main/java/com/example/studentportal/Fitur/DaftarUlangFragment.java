package com.example.studentportal.Fitur;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.studentportal.FragementHari.Hari_list;
import com.example.studentportal.Fragment.HomeFragment;
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;
import com.example.studentportal.data_diri;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DaftarUlangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaftarUlangFragment extends Fragment {
    private static final String TAG= DaftarUlangFragment.class.getSimpleName() ;  //getting the info
    TextView dataKosong;
    RecyclerView NewsRecyclerview;
    com.example.studentportal.Fitur.DaftarUlang_TableViewAdapter DaftarUlang_TableViewAdapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    List<DaftarUlang_list> mData;
    SessionManager sessionManager;
    String getId;  //updateprofil
    ImageView refresh;
    ImageView backKeterampilan;
    private static String URL_READ = Server.URL + "readDaftarUlang.php";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DaftarUlangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DaftarUlangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DaftarUlangFragment newInstance(String param1, String param2) {
        DaftarUlangFragment fragment = new DaftarUlangFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_daftar_ulang, container, false);
        NewsRecyclerview=(RecyclerView)root.findViewById(R.id.news_rv);
        dataKosong= root.findViewById(R.id.dataKosong);
        NewsRecyclerview.setHasFixedSize(true);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mData=new ArrayList<>();
        DaftarUlang_TableViewAdapter=new DaftarUlang_TableViewAdapter(getActivity(), mData);

        backKeterampilan= (ImageView) root.findViewById(R.id.backKeterampilan);
        backKeterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(HomeFragment.newInstance("", ""));
            }
        });

        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil
        refresh=root.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserDetail();
            }
        });
        return root;
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
                                dataKosong.setText("Data Daftar Ulang Masih Kosong");


                            }else if (success.equals("1"))  {
                                JSONObject json =new JSONObject(response);
                                JSONArray jsonArray =json.getJSONArray("data");

                                for (int i=jsonArray.length()-1; i>=0; i--){
                                JSONObject ob = jsonArray.getJSONObject(i);
                                int j=i+1;

                                DaftarUlang_list listData=new DaftarUlang_list(String.valueOf(j),ob.getString("periode")
                                        ,ob.getString("ukt"),ob.getString("statusBayar"),ob.getString("cetakKrs"));
                                mData.add(listData);



                            }
                            NewsRecyclerview.setAdapter(DaftarUlang_TableViewAdapter);
                            }
//                        try {
//                            JSONObject jsonObject =new JSONObject(response);
//                            JSONArray jsonArray =jsonObject.getJSONArray("data");
//
//
//                            for (int i=0; i< jsonArray.length(); i++){
//                                JSONObject ob = jsonArray.getJSONObject(i);
//                                DaftarUlang_list listData=new DaftarUlang_list(ob.getString("periode")
//                                        ,ob.getString("ukt"),ob.getString("statusBayar"),ob.getString("cetakKrs"));
//                                mData.add(listData);
//
//
//
//                            }
//                            NewsRecyclerview.setAdapter(DaftarUlang_TableViewAdapter);

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