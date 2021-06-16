package com.example.studentportal.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataOrangTuaUbahFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataOrangTuaUbahFragment extends Fragment {

    private static final String TAG= DataOrangTuaUbahFragment.class.getSimpleName() ;  //getting the info
    private EditText nikAyah,namaAyah,tglLahirAyah,pendidikanAyah, pekerjaanAyah,nipAyah,pangkatAyah,penghasilanAyah,instansiAyah,
            nikIbu,namaIbu,tglLahirIbu,pendidikanIbu, pekerjaanIbu,nipIbu,pangkatIbu,penghasilanIbu,instansiIbu,
            nikWali,namaWali,tglLahirWali,pendidikanWali, pekerjaanWali,nipWali,pangkatWali,penghasilanWali,instansiWali;
    Spinner spin_pendidikanAyah,spin_pekerjaanAyah,spin_penghasilanAyah,spin_pendidikanIbu,spin_pekerjaanIbu,spin_penghasilanIbu
            ,spin_pendidikanWali,spin_pekerjaanWali,spin_penghasilanWali;
    SessionManager sessionManager;
    String getId;  //updateprofil
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    private static String URL_READ = Server.URL + "readDataortu.php";
    private static String URL_EDIT = Server.URL + "editDataortu.php";
    Button tambah;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataOrangTuaUbahFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataOrangTuaUbahFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataOrangTuaUbahFragment newInstance(String param1, String param2) {
        DataOrangTuaUbahFragment fragment = new DataOrangTuaUbahFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_data_orang_tua_ubah, container, false);
        nikAyah=root.findViewById(R.id.nikAyah);
        namaAyah=root.findViewById(R.id.namaAyah);
        tglLahirAyah=root.findViewById(R.id.tglLahirAyah);
        pendidikanAyah=root.findViewById(R.id.pendidikanAyah);
        spin_pendidikanAyah=root.findViewById(R.id.spin_pendidikanAyah);
        pekerjaanAyah=root.findViewById(R.id.pekerjaanAyah);
        spin_pekerjaanAyah=root.findViewById(R.id.spin_pekerjaanAyah);
        nipAyah=root.findViewById(R.id.nipAyah);
        pangkatAyah=root.findViewById(R.id.pangkatAyah);
        penghasilanAyah=root.findViewById(R.id.penghasilanAyah);
        spin_penghasilanAyah=root.findViewById(R.id.spin_penghasilanAyah);
        instansiAyah=root.findViewById(R.id.instansiAyah);
        nikIbu=root.findViewById(R.id.nikIbu);
        namaIbu=root.findViewById(R.id.namaIbu);
        tglLahirIbu=root.findViewById(R.id.tglLahirIbu);
        pendidikanIbu=root.findViewById(R.id.pendidikanIbu);
        spin_pendidikanIbu=root.findViewById(R.id.spin_pendidikanIbu);
        pekerjaanIbu=root.findViewById(R.id.pekerjaanIbu);
        spin_pekerjaanIbu=root.findViewById(R.id.spin_pekerjaanIbu);
        nipIbu=root.findViewById(R.id.nipIbu);
        pangkatIbu=root.findViewById(R.id.pangkatIbu);
        penghasilanIbu=root.findViewById(R.id.penghasilanIbu);
        spin_penghasilanIbu=root.findViewById(R.id.spin_penghasilanIbu);
        instansiIbu=root.findViewById(R.id.instansiIbu);
        nikWali=root.findViewById(R.id.nikWali);
        namaWali=root.findViewById(R.id.namaWali);
        tglLahirWali=root.findViewById(R.id.tglLahirWali);
        pendidikanWali=root.findViewById(R.id.pendidikanWali);
        spin_pendidikanWali=root.findViewById(R.id.spin_pendidikanWali);
        pekerjaanWali=root.findViewById(R.id.pekerjaanWali);
        spin_pekerjaanWali=root.findViewById(R.id.spin_pekerjaanWali);
        nipWali=root.findViewById(R.id.nipWali);
        pangkatWali=root.findViewById(R.id.pangkatWali);
        penghasilanWali=root.findViewById(R.id.penghasilanWali);
        spin_penghasilanWali=root.findViewById(R.id.spin_penghasilanWali);
        instansiWali=root.findViewById(R.id.instansiWali);
        tambah =root.findViewById(R.id.tambah);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.ortu_pendidikan,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_pendidikanAyah.setAdapter(adapter);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        //AYAH
        spin_pendidikanAyah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
               // Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                pendidikanAyah.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //IBU
        spin_pendidikanIbu.setAdapter(adapter);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        //AYAH
        spin_pendidikanIbu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                pendidikanIbu.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //PENDIDIKAN WALI
        spin_pendidikanWali.setAdapter(adapter);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        //AYAH
        spin_pendidikanWali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                pendidikanWali.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //////PEKERJAAN/////
        ArrayAdapter<CharSequence> adapterPekerjaan = ArrayAdapter.createFromResource(getActivity(), R.array.ortu_pekerjaan,
                android.R.layout.simple_spinner_item);
        adapterPekerjaan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_pekerjaanAyah.setAdapter(adapterPekerjaan);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        spin_pekerjaanAyah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                pekerjaanAyah.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //PEKERJAAN IBU
        spin_pekerjaanIbu.setAdapter(adapterPekerjaan);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        spin_pekerjaanIbu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                pekerjaanIbu.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //PEKERJAAN WALI
        spin_pekerjaanWali.setAdapter(adapterPekerjaan);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        spin_pekerjaanWali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                pekerjaanWali.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapterPenghasilan = ArrayAdapter.createFromResource(getActivity(), R.array.ortu_penghasilan,
                android.R.layout.simple_spinner_item);
        adapterPenghasilan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_penghasilanAyah.setAdapter(adapterPenghasilan);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        spin_penghasilanAyah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                penghasilanAyah.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ///PENGHASILAN IBU
        spin_penghasilanIbu.setAdapter(adapterPenghasilan);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        spin_penghasilanIbu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                penghasilanIbu.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //PENGHASILAN WALI
        spin_penghasilanWali.setAdapter(adapterPenghasilan);
//        txt_jenis.setOnItemSelectedListener(getActivity());
        spin_penghasilanWali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jenisItem =parent.getItemAtPosition(position).toString();
                // Toast.makeText(parent.getContext(), jenisItem,Toast.LENGTH_SHORT).show();
                penghasilanWali.setText(jenisItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tglLahirAyah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tglLahirAyah.setText(year + "-" + (month + 1) + "-" + day);
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        tglLahirIbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tglLahirIbu.setText(year + "-" + (month + 1) + "-" + day);
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        tglLahirWali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tglLahirWali.setText(year + "-" + (month + 1) + "-" + day);
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveEditDetail();
            }
        });
        return root;
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
                                    String strNK = object.getString("nikAyah").trim();
                                    String strNA = object.getString("namaAyah").trim();
                                    String strTA = object.getString("tglLahirAyah").trim();
                                    String strPENA = object.getString("pendidikanAyah").trim();
                                    String strPnA = object.getString("pekerjaanAyah").trim();
                                    String strNIP = object.getString("nipAyah").trim();
                                    String strPa = object.getString("pangkatAyah").trim();
                                    String strPn = object.getString("penghasilanAyah").trim();
                                    String strIA= object.getString("instansiAyah").trim();
                                    String strNI = object.getString("nikIbu").trim();
                                    String strNmI = object.getString("namaIbu").trim();
                                    String strTI = object.getString("tglLahirIbu").trim();
                                    String strPENI = object.getString("pendidikanIbu").trim();
                                    String strPI = object.getString("pekerjaanIbu").trim();
                                    String strNII = object.getString("nipIbu").trim();
                                    String strPtI = object.getString("pangkatIbu").trim();
                                    String strPnI = object.getString("penghasilanIbu").trim();
                                    String strIi= object.getString("instansiIbu").trim();
                                    String strNkW = object.getString("nikWali").trim();
                                    String strNW = object.getString("namaWali").trim();
                                    String strTW = object.getString("tglLahirWali").trim();
                                    String strPENW = object.getString("pendidikanWali").trim();
                                    String strPW = object.getString("pekerjaanWali").trim();
                                    String strNIW = object.getString("nipWali").trim();
                                    String strPtW = object.getString("pangkatWali").trim();
                                    String strPnW = object.getString("penghasilanWali").trim();
                                    String strIW= object.getString("instansiWali").trim();


                                    nikAyah.setText(strNK);
                                    namaAyah.setText(strNA);
                                    tglLahirAyah.setText(strTA);
                                    pendidikanAyah.setText(strPENA);
                                    pekerjaanAyah.setText(strPnA);
                                    nipAyah.setText(strNIP);
                                    pangkatAyah.setText(strPa);
                                    penghasilanAyah.setText(strPn);
                                    instansiAyah.setText(strIA);
                                    nikIbu.setText(strNI);
                                    namaIbu.setText(strNmI);
                                    tglLahirIbu.setText(strTI);
                                    pendidikanIbu.setText(strPENI);
                                    pekerjaanIbu.setText(strPI);
                                    nipIbu.setText(strNII);
                                    pangkatIbu.setText(strPtI);
                                    penghasilanIbu.setText(strPnI);
                                    instansiIbu.setText(strIi);
                                    nikWali.setText(strNkW);
                                    namaWali.setText(strNW);
                                    tglLahirWali.setText(strTW);
                                    pendidikanWali.setText(strPENW);
                                    pekerjaanWali.setText(strPW);
                                    nipWali.setText(strNIW);
                                    pangkatWali.setText(strPtW);
                                    penghasilanWali.setText(strPnW);
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
    //Save
    private void SaveEditDetail() {
        final String nikAyah = this.nikAyah.getText().toString().trim();
        final String namaAyah = this.namaAyah.getText().toString().trim();
        final String tglLahirAyah = this.tglLahirAyah.getText().toString().trim();
        final String pendidikanAyah = this.pendidikanAyah.getText().toString().trim();
        final String pekerjaanAyah = this.pekerjaanAyah.getText().toString().trim();
        final String nipAyah = this.nipAyah.getText().toString().trim();
        final String pangkatAyah = this.pangkatAyah.getText().toString().trim();
        final String penghasilanAyah = this.penghasilanAyah.getText().toString().trim();
        final String instansiAyah= this.instansiAyah.getText().toString().trim();
        final String nikIbu = this.nikIbu.getText().toString().trim();
        final String namaIbu = this.namaIbu.getText().toString().trim();
        final String tglLahirIbu = this.tglLahirIbu.getText().toString().trim();
        final String pendidikanIbu = this.pendidikanIbu.getText().toString().trim();
        final String pekerjaanIbu = this.pekerjaanIbu.getText().toString().trim();
        final String nipIbu = this.nipIbu.getText().toString().trim();
        final String pangkatIbu = this.pangkatIbu.getText().toString().trim();
        final String penghasilanIbu = this.penghasilanIbu.getText().toString().trim();
        final String instansiIbu= this.instansiIbu.getText().toString().trim();
        final String nikWali = this.nikWali.getText().toString().trim();
        final String namaWali = this.namaWali.getText().toString().trim();
        final String tglLahirWali = this.tglLahirWali.getText().toString().trim();
        final String pendidikanWali = this.pendidikanWali.getText().toString().trim();
        final String pekerjaanWali = this.pekerjaanWali.getText().toString().trim();
        final String nipWali = this.nipWali.getText().toString().trim();
        final String pangkatWali = this.pangkatWali.getText().toString().trim();
        final String penghasilanWali = this.penghasilanWali.getText().toString().trim();
        final String instansiWali= this.instansiWali.getText().toString().trim();

        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
                                Toast.makeText(getActivity(), "Success!", Toast.LENGTH_SHORT).show();
                                
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();

                params.put("nikAyah", nikAyah);
                params.put("namaAyah", namaAyah);
                params.put("tglLahirAyah", tglLahirAyah);
                params.put("pendidikanAyah", pendidikanAyah);
                params.put("pekerjaanAyah", pekerjaanAyah);
                params.put("nipAyah", nipAyah);
                params.put("pangkatAyah", pangkatAyah);
                params.put("penghasilanAyah", penghasilanAyah);
                params.put("instansiAyah", instansiAyah);
                params.put("nikIbu", nikIbu);
                params.put("namaIbu", namaIbu);
                params.put("tglLahirIbu", tglLahirIbu);
                params.put("pendidikanIbu", pendidikanIbu);
                params.put("pekerjaanIbu", pekerjaanIbu);
                params.put("nipIbu", nipIbu);
                params.put("pangkatIbu", pangkatIbu);
                params.put("penghasilanIbu", penghasilanIbu);
                params.put("instansiIbu", instansiIbu);
                params.put("nikWali", nikWali);
                params.put("namaWali", namaWali);
                params.put("tglLahirWali", tglLahirWali);
                params.put("pendidikanWali", pendidikanWali);
                params.put("pekerjaanWali", pekerjaanWali);
                params.put("nipWali", nipWali);
                params.put("pangkatWali", pangkatWali);
                params.put("penghasilanWali", penghasilanWali);
                params.put("instansiWali", instansiWali);


                params.put("npm", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
}