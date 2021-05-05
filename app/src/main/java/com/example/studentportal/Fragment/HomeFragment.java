package com.example.studentportal.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentportal.DaftarUlang;
import com.example.studentportal.Fitur.DaftarUlangFragment;
import com.example.studentportal.Fitur.EvaluasiDosenFragment;
import com.example.studentportal.Fitur.HasilStudiAwalFragment;
import com.example.studentportal.Fitur.KeterampilanFragment;
import com.example.studentportal.Fitur.MagangFragment;
import com.example.studentportal.Fitur.PresensiSemesterFragment;
import com.example.studentportal.Fitur.PrestasiFragment;
import com.example.studentportal.HomeActivity;
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String TAG= HomeActivity.class.getSimpleName() ;  //getting the info\
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readDatadiri.php";
    private Menu action;
    private Bitmap bitmap;
    CircleImageView profile_image;

    private TextView name, email,greetText;
    private ImageView btn_logout;
    ImageView greetImg,data_diri, keterampilan,pengumuman, daftarulang,hasilStudi,presensi,jadwalKuliah,evdos,magang
            ,prestasi;
    TextView status;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        greetImg = root.findViewById(R.id.greeting_img);
        data_diri=root.findViewById(R.id.data_diri);
        greetText = root.findViewById(R.id.greeting_text);
        name = root.findViewById(R.id.name);
        btn_logout = root.findViewById(R.id.btn_logout);
        keterampilan=root.findViewById(R.id.keterampilan);
        pengumuman= root.findViewById(R.id.pengumuman);
        daftarulang= root.findViewById(R.id.daftarulang);
        hasilStudi = root.findViewById(R.id.hasilStudi);
        jadwalKuliah = root.findViewById(R.id.jadwalKuliah);
        evdos = root.findViewById(R.id.evdos);
        magang = root.findViewById(R.id.magang);
        presensi = root.findViewById(R.id.presensi);
        prestasi = root.findViewById(R.id.prestasi);
        profile_image = root.findViewById(R.id.profile_imageHome);
        status = root.findViewById(R.id.status);


        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil

        greeting();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // sessionManager.logout();
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });

        hasilStudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent;
//                intent = new Intent(getActivity(), HasilStudiAwal.class);
//                startActivity(intent);
                openFragment(HasilStudiAwalFragment.newInstance("", ""));
            }
        });

        presensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(PresensiSemesterFragment.newInstance("", ""));
            }
        });
        daftarulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(DaftarUlangFragment.newInstance("", ""));
            }
        });
        keterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(KeterampilanFragment.newInstance("", ""));
            }
        });
        jadwalKuliah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(JadwalKuliahFragment.newInstance("", ""));
            }
        });
        evdos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(EvaluasiDosenFragment.newInstance("", ""));
            }
        });
        magang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(MagangFragment.newInstance("", ""));
            }
        });
        prestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(PrestasiFragment.newInstance("", ""));
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

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(getActivity())
                // set message, title, and icon
                .setTitle("Logout")
                .setMessage("Yakin mau Logout?")
                .setIcon(R.drawable.logout)

                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        sessionManager.logout();

                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }



    @SuppressLint("ResourceAsColor")
    private void greeting(){
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay>= 0 && timeOfDay<12){
            greetText.setText("Selamat Pagi ");
            greetText.setTextColor(Color.parseColor("#000000"));
            greetImg.setImageResource(R.drawable.pagioke);
//            greetText.setTextColor(R.color.colorText);
        } else if(timeOfDay>= 12 && timeOfDay<15){
            greetText.setText("Selamat Siang \n ");
            greetText.setTextColor(Color.parseColor("#000000"));
            greetImg.setBackgroundResource(R.drawable.siang);
        }else if(timeOfDay>= 15 && timeOfDay<18){
            greetText.setText("Selamat Sore \n ");
            greetText.setTextColor(Color.parseColor("#000000"));
            greetImg.setBackgroundResource(R.drawable.soreoke);
        }else if(timeOfDay>= 18 && timeOfDay<24){
            greetText.setText("Selamat Malam \n ");
            greetText.setTextColor(R.color.white);
            greetImg.setImageResource(R.drawable.malamoke);



        }
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

                                    String strPhoto = object.getString("imageProfil").trim();
                                    String strStatus = object.getString("statusDiri").trim();
                                    status.setText(strStatus);
                                    //status.setVisibility(View.GONE);

                                    if(strStatus.isEmpty()){
                                        status.setVisibility(View.GONE);
                                    }else if (strStatus.equals("Aktif")){
                                        status.setBackgroundResource(R.drawable.aktif);
                                        status.setTextColor(Color.parseColor("#FFFFFF"));

                                    }else if (strStatus.equals("Tidak Aktif")){
                                        status.setBackgroundResource(R.drawable.tidakaktif);
                                        status.setTextColor(Color.parseColor("#FFFFFF"));

                                    }

//                                    code Photo
                                    if (strPhoto.isEmpty()) {
                                        profile_image.setImageResource(R.drawable.ayah);
                                    } else {
                                        Picasso.get().load(strPhoto).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).fit().centerCrop().into(profile_image);
                                    }

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