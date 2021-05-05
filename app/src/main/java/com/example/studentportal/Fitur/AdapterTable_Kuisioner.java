package com.example.studentportal.Fitur;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;
import java.util.Map;

public class AdapterTable_Kuisioner extends RecyclerView.Adapter {

    
    List<DataKuisioner> mData;
    RowViewHolder row;
    Context mContext;
    SessionManager sessionManager;
    String getId , getIdEvdos;  //updateprofil
    private static String URL_EDIT = Server.URLEvaluasiDosen + "pengisisanEvdos.php";


    public AdapterTable_Kuisioner(Context mContext,List<DataKuisioner> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.kuisionerlist_item, parent, false);

        return new AdapterTable_Kuisioner.RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterTable_Kuisioner.RowViewHolder rowViewHolder = (AdapterTable_Kuisioner.RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

//            rowViewHolder.txtPeriode.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtUkt.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_bg);
//            rowViewHolder.txtCetakKrs.setBackgroundResource(R.drawable.table_bg);
//
//            rowViewHolder.txtNomor.setText("Periode Akademik");
//            rowViewHolder.txtNomor.setTextColor(Color.parseColor("#FFFFFF"));
//            rowViewHolder.txtPertanyaan.setText("UKT");
//            rowViewHolder.txtPertanyaan.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.txtIdEvdos.setVisibility(View.GONE);
            rowViewHolder.txtNomor.setVisibility(View.GONE);
            rowViewHolder.txtPertanyaan.setVisibility(View.GONE);
            rowViewHolder.jawaban.setVisibility(View.GONE);
            rowViewHolder.radioGroup.setVisibility(View.GONE);


        } else {
            DataKuisioner modal = (DataKuisioner) mData.get(rowPos - 1);
            rowViewHolder.txtIdEvdos.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtNomor.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.txtPertanyaan.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.jawaban.setBackgroundResource(R.drawable.cardview);
            rowViewHolder.radioGroup.setBackgroundResource(R.drawable.cardview);

            rowViewHolder.txtIdEvdos.setText(modal.getIdEvdos() );
            rowViewHolder.txtNomor.setText(modal.getA()+"" );
            rowViewHolder.txtPertanyaan.setText(modal.getPertanyaan());


        }

      //  rowViewHolder.SaveEditDetail();

    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }


    public void SaveEditDetail() {
        final String idEvdos = row.txtIdEvdos.getText().toString().trim();
        final String jawaban = row.jawaban.getText().toString().trim();

        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
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
                                Toast.makeText(mContext, "Success!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(id);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(mContext, "Error"+e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(mContext, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", id);
                params.put("idEvdos", idEvdos);
                params.put("jawaban", jawaban);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);

    }


    public  class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtPertanyaan, txtNomor, txtIdEvdos, jawaban;
        RadioGroup radioGroup;
        String jk;

        RowViewHolder(View itemView) {
            super(itemView);
            txtIdEvdos = itemView.findViewById(R.id.txtidEvdos);
            txtPertanyaan = itemView.findViewById(R.id.txtPertanyaan);
            txtNomor = itemView.findViewById(R.id.txtnomor);
            jawaban = itemView.findViewById(R.id.jawaban);




            sessionManager = new SessionManager(mContext);
            sessionManager.checkLogin();


            HashMap<String, String> user = sessionManager.getUserDetail();
            getId = user.get(sessionManager.ID);  //updateprofil

           // getIdEvdos= getIdEvdos.getIdEvdos();



            radioGroup = (RadioGroup) itemView.findViewById(R.id.Rad_group);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    if (checkedId == R.id.tidakPernah){
                        jk = String.valueOf("Tidak Pernah");

                    }if (checkedId == R.id.pernah){
                        jk = String.valueOf("Pernah");
                    }if (checkedId == R.id.sering){
                        jk = String.valueOf("Sering");
                    }if (checkedId == R.id.selalu){
                        jk = String.valueOf("Selalu");
                    }
                    jawaban.setText(jk);
                    String jawab = jawaban.getText().toString().trim();
                    Toast.makeText(mContext,getId+ jawab,Toast.LENGTH_SHORT).show();
                }
            });

        }





        public  void SaveEditDetail() {
            final String idEvdos = txtIdEvdos.getText().toString().trim();
            final String jawaban = this.jawaban.getText().toString().trim();

            final String id = getId;

            final ProgressDialog progressDialog = new ProgressDialog(mContext);
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
                                    Toast.makeText(mContext, "Success!", Toast.LENGTH_SHORT).show();
                                    sessionManager.createSession(id);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                                Toast.makeText(mContext, "Error"+e.toString(), Toast.LENGTH_SHORT).show();

                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(mContext, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("npm", id);
                    params.put("idEvdos", idEvdos);
                    params.put("jawaban", jawaban);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            requestQueue.add(stringRequest);

        }

    }
}
