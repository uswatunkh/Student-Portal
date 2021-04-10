package com.example.studentportal;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class HomeActivity extends AppCompatActivity {

    private static final String TAG= HomeActivity.class.getSimpleName() ;  //getting the info\
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = "http://192.168.1.43/studentPortal/readDatadiri.php";
    private Menu action;
    private Bitmap bitmap;
    CircleImageView profile_image;

    private TextView name, email,greetText;
    private ImageView btn_logout;
    ImageView greetImg,data_diri, keterampilan,pengumuman, daftarulang,hasilStudi,presensi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        greetImg = findViewById(R.id.greeting_img);
        data_diri=findViewById(R.id.data_diri);
        greetText = findViewById(R.id.greeting_text);
        name = findViewById(R.id.name);
        btn_logout = findViewById(R.id.btn_logout);
        keterampilan=findViewById(R.id.keterampilan);
        pengumuman= findViewById(R.id.pengumuman);
        daftarulang= findViewById(R.id.daftarulang);
        hasilStudi = findViewById(R.id.hasilStudi);
        presensi = findViewById(R.id.presensi);
        profile_image = findViewById(R.id.profile_imageHome);



        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil

//        Intent intent = getIntent();
//        String extraName = intent.getStringExtra("npm");
//
//        name.setText(extraName);
        greeting();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });
        data_diri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(HomeActivity.this,biodata.class);
                startActivity(intent);
            }
        });
        hasilStudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(HomeActivity.this,HasilStudiAwal.class);
                startActivity(intent);
            }
        });
        presensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(HomeActivity.this,presensi.class);
                startActivity(intent);
            }
        });
        daftarulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(HomeActivity.this,DaftarUlang.class);
                startActivity(intent);
            }
        });
        keterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(HomeActivity.this,Keterampilan.class);
                startActivity(intent);
            }
        });
        pengumuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(HomeActivity.this,pengumuman.class);
                startActivity(intent);
            }
        });
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
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray =jsonObject.getJSONArray("read");

                            if(success.equals("1")){
                                for (int i=0; i< jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strPhoto = object.getString("imageProfil").trim();
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
                            Toast.makeText(HomeActivity.this, "Error Reading Detail"+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(HomeActivity.this, "Error Reading Detail"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", getId);
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
    }

}
