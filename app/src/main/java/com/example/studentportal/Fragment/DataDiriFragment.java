package com.example.studentportal.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;
import com.example.studentportal.data_diri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataDiriFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataDiriFragment extends Fragment {

    private static final String TAG= data_diri.class.getSimpleName() ;  //getting the info

    private EditText nik,namaLengkap,lahir,jenisKelamin,agama,alamatAsal,kotaAsal,alamatSekarang,noHp,email;
    Button ubah;
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readDatadiri.php";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataDiriFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataDiriFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataDiriFragment newInstance(String param1, String param2) {
        DataDiriFragment fragment = new DataDiriFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_data_diri, container, false);
        ubah = root.findViewById(R.id.butProfil);
        nik=root.findViewById(R.id.nik);
        namaLengkap=root.findViewById(R.id.namaLengkap);
        lahir=root.findViewById(R.id.lahir);
        jenisKelamin=root.findViewById(R.id.jenisKelamin);
        agama=root.findViewById(R.id.agama);
        alamatAsal=root.findViewById(R.id.alamatAsal);
        kotaAsal=root.findViewById(R.id.kotaAsal);
        alamatSekarang=root.findViewById(R.id.alamatSekarang);
        noHp=root.findViewById(R.id.noHp);
        email=root.findViewById(R.id.email);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(DataDiriUbahFragment.newInstance("oke", "oke"));
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

                                    String strNik = object.getString("nik").trim();
                                    String strName = object.getString("namaLengkap").trim();
                                    String strLahir = object.getString("tempatLahir").trim();
                                    String strJenkel = object.getString("jenisKelamin").trim();
                                    String strAgama = object.getString("agama").trim();
                                    String strAlamatasal = object.getString("alamatAsal").trim();
                                    String strKotaasal = object.getString("kotaAsal").trim();
                                    String strAlamatSekarang = object.getString("alamatSekarang").trim();
                                    String strNohp = object.getString("noHp").trim();
                                    String strEmail = object.getString("email").trim();

                                    nik.setText(strNik);
                                    namaLengkap.setText(strName);
                                    lahir.setText(strLahir);
                                    jenisKelamin.setText(strJenkel);
                                    agama.setText(strAgama);
                                    alamatAsal.setText(strAlamatasal);
                                    kotaAsal.setText(strKotaasal);
                                    alamatSekarang.setText(strAlamatSekarang);
                                    noHp.setText(strNohp);
                                    email.setText(strEmail);

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