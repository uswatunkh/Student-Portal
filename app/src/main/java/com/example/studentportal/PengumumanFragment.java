package com.example.studentportal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PengumumanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PengumumanFragment extends Fragment {
    RecyclerView NewsRecyclerview;
    pengumuman_newAdapter pengumuman_newAdapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    List<PengumumanItem> mData;
    private static final String URLPengumuman =Server.URL + "read_pengumuman.php";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PengumumanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PengumumanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PengumumanFragment newInstance(String param1, String param2) {
        PengumumanFragment fragment = new PengumumanFragment();
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
        View root = (ViewGroup) inflater.inflate(R.layout.pengumuman_recycle, container, false);
        NewsRecyclerview=(RecyclerView)root.findViewById(R.id.news_rv);
        NewsRecyclerview.setHasFixedSize(true);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mData=new ArrayList<>();
        pengumuman_newAdapter=new pengumuman_newAdapter(getContext(), mData);



        getMovieData();
        return root;
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
                        PengumumanItem listData=new PengumumanItem(ob.getString("isiPengumuman")
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
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}