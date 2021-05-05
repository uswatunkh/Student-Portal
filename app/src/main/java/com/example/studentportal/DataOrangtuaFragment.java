package com.example.studentportal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataOrangtuaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataOrangtuaFragment extends Fragment {
    private static final String TAG= DataOrangtuaFragment.class.getSimpleName() ;  //getting the info

    private EditText namaAyah, pekerjaanAyah,instansiAyah,namaIbu,pekerjaanIbu,instansiIbu,namaWali,pekerjaanWali,instansiWali;
    Button ubah;
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readDataortu.php";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataOrangtuaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataOrangtuaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataOrangtuaFragment newInstance(String param1, String param2) {
        DataOrangtuaFragment fragment = new DataOrangtuaFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_data_orangtua, container, false);
        namaAyah=root.findViewById(R.id.namaAyah);
        pekerjaanAyah=root.findViewById(R.id.pekerjaanAyah);
        instansiAyah=root.findViewById(R.id.instansiAyah);
        namaIbu=root.findViewById(R.id.namaIbu);
        pekerjaanIbu=root.findViewById(R.id.pekerjaanIbu);
        instansiIbu=root.findViewById(R.id.instansiIbu);
        namaWali=root.findViewById(R.id.namaWali);
        pekerjaanWali=root.findViewById(R.id.pekerjaanWali);
        instansiWali=root.findViewById(R.id.instansiWali);
        ubah = root.findViewById(R.id.butOrangTua);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(DataOrangTuaUbahFragment.newInstance("oke", "oke"));
//                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
////                builder.setTitle("Welcome");
//                builder.setMessage("PILIH AKSI");
//                builder.setPositiveButton("Tambah", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        openFragment(DataOrangTuaUbahFragment.newInstance("oke", "oke"));
//                    }
//                });
//                builder.setNegativeButton("Ubah", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        openFragment(DataOrangTuaUbahFragment.newInstance("oke", "oke"));
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();

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
                            JSONObject jsonObject =new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray =jsonObject.getJSONArray("read");

                            if(success.equals("1")){
                                for (int i=0; i< jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strNA = object.getString("namaAyah").trim();
                                    String strPA = object.getString("pekerjaanAyah").trim();
                                    String strIA= object.getString("instansiAyah").trim();
                                    String strNI = object.getString("namaIbu").trim();
                                    String strPI = object.getString("pekerjaanIbu").trim();
                                    String strII = object.getString("instansiIbu").trim();
                                    String strNW = object.getString("namaWali").trim();
                                    String strPW = object.getString("pekerjaanWali").trim();
                                    String strIW = object.getString("instansiWali").trim();

                                    namaAyah.setText(strNA);
                                    pekerjaanAyah.setText(strPA);
                                    instansiAyah.setText(strIA);
                                    namaIbu.setText(strNI);
                                    pekerjaanIbu.setText(strPI);
                                    instansiIbu.setText(strII);
                                    namaWali.setText(strNW);
                                    pekerjaanWali.setText(strPW);
                                    instansiWali.setText(strIW);


                                }
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