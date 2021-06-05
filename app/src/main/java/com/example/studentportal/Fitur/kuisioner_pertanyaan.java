package com.example.studentportal.Fitur;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class kuisioner_pertanyaan extends AppCompatActivity {

    EditText txtKesan, txtPesan;
    TextView idDosen;
    int position;
    String getId,getIdDosen;  //updateprofil
    private  RadioGroup Rg1_1, Rg1_2, Rg1_3, Rg1_4, Rg2_1, Rg2_2, Rg2_3, Rg2_4, Rg3_1, Rg3_2, Rg3_3, Rg3_4;
    private RadioButton Rb1_1, Rb1_2, Rb1_3, Rb1_4, Rb2_1, Rb2_2, Rb2_3, Rb2_4, Rb3_1, Rb3_2, Rb3_3, Rb3_4;
    private static String URL_KUISIONER= Server.URL + "evaluasiKuisioner.php";
    Button btnSubmit;
    SessionManager sessionManager;
    //String coba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kuisioner_pertanyaan);
        idDosen = findViewById(R.id.idDosen);
        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        idDosen.setText("ID:"+EvaluasiDosenFragment.itemList.get(position).getNamaDosen());
        getIdDosen=EvaluasiDosenFragment.itemList.get(position).getIdDosen();
        sessionManager = new SessionManager(kuisioner_pertanyaan.this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil

        Rg1_1 = (RadioGroup) findViewById(R.id.R1_1);
        Rg1_2 = (RadioGroup) findViewById(R.id.R1_2);
        Rg1_3 = (RadioGroup) findViewById(R.id.R1_3);
        Rg1_4 = (RadioGroup) findViewById(R.id.R1_4);
        Rg2_1 = (RadioGroup) findViewById(R.id.R2_1);
        Rg2_2 = (RadioGroup) findViewById(R.id.R2_2);
        Rg2_3 = (RadioGroup) findViewById(R.id.R2_3);
        Rg2_4 = (RadioGroup) findViewById(R.id.R2_4);
        Rg3_1 = (RadioGroup) findViewById(R.id.R3_1);
        Rg3_2 = (RadioGroup) findViewById(R.id.R3_2);
        Rg3_3 = (RadioGroup) findViewById(R.id.R3_3);
        Rg3_4 = (RadioGroup) findViewById(R.id.R3_4);
        txtKesan = (EditText) findViewById(R.id.kesan);
        txtPesan = (EditText) findViewById(R.id.pesan);
        btnSubmit= findViewById(R.id.btnSubmitEval);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveJawaban();
                //kuisioner_pertanyaan.this.finish();
                //Toast.makeText(getApplicationContext(), "Sudah Terisi", Toast.LENGTH_SHORT).show();


            }
        });

        Rg1_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                int selectedId1 = Rg1_1.getCheckedRadioButtonId();
                Rb1_1 = (RadioButton) findViewById(selectedId1);
                Toast.makeText(kuisioner_pertanyaan.this, "Cobaa:::"+Rb1_1.getText(), Toast.LENGTH_SHORT).show();
            }
        });


//        int selectedId1 = Rg1_1.getCheckedRadioButtonId();
//        Rb1_1 = (RadioButton) findViewById(selectedId1);
//        Toast.makeText(kuisioner_pertanyaan.this, "Cobaa:::"+Rb1_1.getText(), Toast.LENGTH_SHORT).show();




    }
    private void SaveJawaban(){

        int selectedId1 = Rg1_1.getCheckedRadioButtonId();
        Rb1_1 = (RadioButton) findViewById(selectedId1);

        final String id = getId;
        final String idDosen = getIdDosen;
        final ProgressDialog progressDialog = new ProgressDialog(kuisioner_pertanyaan.this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_KUISIONER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(kuisioner_pertanyaan.this);
                                builder.setMessage("Kuisioner Berhasil")
                                        .setNegativeButton("Ok",null)
                                        .create()
                                        .show();


                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(kuisioner_pertanyaan.this, "Register Error" + e.toString(),Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(kuisioner_pertanyaan.this, "Register Error" + error.toString(),Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", id);
                params.put("idDosen", idDosen);
                params.put("jawaban1_1", Rb1_1.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(kuisioner_pertanyaan.this);
        requestQueue.add(stringRequest);

    }
}
