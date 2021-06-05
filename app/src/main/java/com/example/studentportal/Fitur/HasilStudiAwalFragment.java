package com.example.studentportal.Fitur;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.studentportal.Fragment.HomeFragment;
import com.example.studentportal.HasilStudi;
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;
import com.example.studentportal.app.AppController;
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
 * Use the {@link HasilStudiAwalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HasilStudiAwalFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    Toolbar toolbar;
    ImageView backKeterampilan;
    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    public  static List<DataHasilStudiAwal> itemList = new ArrayList<DataHasilStudiAwal>();
    AdapterHasilStudiAwal adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_idDosen, txt_namaDosen, txt_namaMK,txt_idMk;
    Button upload;
    String idDosen, namaDosen, namaMk,idMK;
    SessionManager sessionManager;
    String getId;  //updateprofil

    Bitmap bitmap;

    private static final String TAG = EvaluasiDosenFragment.class.getSimpleName();

    private static String URL_READ = Server.URL + "readSemester.php";

    public static final String TAG_ID       = "idDaftarUlang";
    public static final String TAG_SEMESTER     = "semester";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HasilStudiAwalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HasilStudiAwalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HasilStudiAwalFragment newInstance(String param1, String param2) {
        HasilStudiAwalFragment fragment = new HasilStudiAwalFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_hasil_studi_awal, container, false);

//        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        // setSupportActionBar(toolbar);

        // menghubungkan variablel pada layout dan pada java
        fab     = (FloatingActionButton) root.findViewById(R.id.fab_add);
        swipe   = (SwipeRefreshLayout) root.findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) root.findViewById(R.id.list);
        backKeterampilan= (ImageView) root.findViewById(R.id.backKeterampilan);
        backKeterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(HomeFragment.newInstance("", ""));
            }
        });

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil
        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new AdapterHasilStudiAwal(getActivity(), itemList);
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
                       }
                   }
        );





        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getSemester();
                startActivity(new Intent(getActivity(), HasilStudi.class).
                        putExtra("position",position));
//                final CharSequence[] dialogitem = {"View Hasil Studi Semester "+idx};
//                dialog = new AlertDialog.Builder(getActivity());
//                dialog.setCancelable(true);
//                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        // TODO Auto-generated method stub
//                        switch (which) {
//                            case 0:
//                                startActivity(new Intent(getActivity(), HasilStudi.class).
//                                        putExtra("position",position));
//
//                                break;
//                        }
//                    }
//                }).show();
                //return false;
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



    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }



    // untuk menampilkan semua data pada listview
    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        StringRequest jArr = new StringRequest(Request.Method.POST,URL_READ, new Response.Listener<String>() {
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
                        DataHasilStudiAwal item = new DataHasilStudiAwal();

//                        item.setId(ob.getString(TAG_ID));
                        item.setSemester(ob.getString(TAG_SEMESTER));

                        // menambah item ke array
                        itemList.add(item);
                    }
                    list.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);

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
        }

                ;
        AppController.getInstance().addToRequestQueue(jArr);
    }


    // fungsi untuk menyimpan atau update
}