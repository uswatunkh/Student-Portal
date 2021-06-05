package com.example.studentportal.Fitur;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterTable_Kuisioner extends RecyclerView.Adapter<AdapterTable_Kuisioner.RowViewHolder> {


    private ArrayList<DataKuisioner> mData;
    RowViewHolder row;
    static Context mContext;
    ArrayList<String> ExpNameArray = new ArrayList<String>();
    ArrayList<String> ExpAmtArray = new ArrayList<String>();
    boolean isOnTextChanged = false;
    int ExpenseFinalTotal = 0;
    SessionManager sessionManager;
    View rootView;
    Button save;
    TextView jwb;
    String getId , getIdEvdos;  //updateprofil
    private static String URL_EDIT = Server.URLEvaluasiDosen + "pengisianEvdos.php";



    public AdapterTable_Kuisioner(Context mContext,ArrayList<DataKuisioner> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.kuisionerlist_item, parent, false);
        mContext = parent.getContext();
        rootView = ((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        sessionManager = new SessionManager(mContext);
        sessionManager.checkLogin();
        Button btnSubmit;

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil
        save =(Button)rootView.findViewById(R.id.btnSubmitEval);
        jwb=rootView.findViewById(R.id.jawabKuisioner);
        //attach view to MyViewHolder

        RowViewHolder rowViewHolder = new RowViewHolder(itemView);
        return rowViewHolder;
    }


    public static class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtPertanyaan, txtNomor, txtIdEvdos;
        EditText jawaban;
        RadioGroup radioGroup;
        String jk;

        RowViewHolder(View itemView) {
            super(itemView);
            txtIdEvdos = itemView.findViewById(R.id.txtidEvdos);
            txtPertanyaan = itemView.findViewById(R.id.txtPertanyaan);
            txtNomor = itemView.findViewById(R.id.txtnomor);
            jawaban = itemView.findViewById(R.id.jawaban);
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
                    Toast.makeText(mContext,jawab,Toast.LENGTH_SHORT).show();
                }
            });

        }
    }





    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
        AdapterTable_Kuisioner.RowViewHolder rowViewHolder = (AdapterTable_Kuisioner.RowViewHolder) holder;


        int rowPos = holder.getAdapterPosition();
        TextView nomor= holder.txtNomor;
       TextView tanya = holder.txtPertanyaan;
        TextView evdosid= holder.txtIdEvdos;
       EditText jawab = holder.jawaban;

//        if (rowPos == 0) {
//
////            rowViewHolder.txtPeriode.setBackgroundResource(R.drawable.table_bg);
////            rowViewHolder.txtUkt.setBackgroundResource(R.drawable.table_bg);
////            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_bg);
////            rowViewHolder.txtCetakKrs.setBackgroundResource(R.drawable.table_bg);
////
////            rowViewHolder.txtNomor.setText("Periode Akademik");
////            rowViewHolder.txtNomor.setTextColor(Color.parseColor("#FFFFFF"));
////            rowViewHolder.txtPertanyaan.setText("UKT");
////            rowViewHolder.txtPertanyaan.setTextColor(Color.parseColor("#FFFFFF"));
//            holder.txtIdEvdos.setVisibility(View.GONE);
//            holder.txtNomor.setVisibility(View.GONE);
//            holder.txtPertanyaan.setVisibility(View.GONE);
//            holder.jawaban.setVisibility(View.GONE);
//            holder.radioGroup.setVisibility(View.GONE);
//
//
//        } else {
//            DataKuisioner modal = (DataKuisioner) mData.get(rowPos - 1);
//            holder.txtIdEvdos.setBackgroundResource(R.drawable.cardview);
//            holder.txtNomor.setBackgroundResource(R.drawable.cardview);
//            holder.txtPertanyaan.setBackgroundResource(R.drawable.cardview);
//            holder.jawaban.setBackgroundResource(R.drawable.cardview);
//            holder.radioGroup.setBackgroundResource(R.drawable.cardview);
//
////            rowViewHolder.txtIdEvdos.setText(modal.getIdEvdos() );
//            nomor.setText(String.valueOf(modal.getIdNomor()));
//            evdosid.setText(modal.getIdEvdos());
//            tanya.setText(modal.getPertanyaan());
//        }
        //costum
        nomor.setBackgroundResource(R.drawable.cardview);
        evdosid.setBackgroundResource(R.drawable.cardview);
        tanya.setBackgroundResource(R.drawable.cardview);
        jawab.setBackgroundResource(R.drawable.cardview);
        holder.radioGroup.setBackgroundResource(R.drawable.cardview);
        //read
        nomor.setText(String.valueOf(mData.get(position).getIdNomor()));
//        evdosid.setText(modal.getIdEvdos());
        evdosid.setText(mData.get(position).getIdEvdos());
        tanya.setText(mData.get(position).getPertanyaan());
            int id = mData.get(position).getIdNomor();
            //evdosid.setText(mData.get(position).getIdEvdos());
            jawab.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    isOnTextChanged = true;
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (isOnTextChanged) {
                        isOnTextChanged = false;
                        try {
                            ExpenseFinalTotal = 0;
                            for (int i = 0; i <= id; i++) {
                                if (i != id) {
                                    ExpNameArray.add("0");
                                    ExpAmtArray.add("0");
                                } else {
                                    ExpAmtArray.add("0");
                                    ExpNameArray.add("0");
                                    ExpNameArray.set(id, mData.get(id).getIdEvdos());
                                    ExpAmtArray.set(id, editable.toString());
                                }
                            }
//                        Log.d("ExpAmt",ExpAmtArray.toString());
//                        Log.d("ExpName",ExpNameArray.toString());
//                        for(int i = 0;i<=ExpAmtArray.size()-1;i++){
//                            int tempTotalExpense = Integer.parseInt(ExpAmtArray.get(i));
//                            ExpenseFinalTotal = ExpenseFinalTotal +tempTotalExpense;
//                        }

                            //lihat array
                            //textviewTotalExpense.setText("Total Expense : " +String.valueOf(ExpAmtArray));
                        } catch (NumberFormatException e) {
                            // catch is used because, when used enter value in editText and remove the value it
                            // it will trigger NumberFormatException, so to prevent it and remove data value from array ExpAmtArray
                            //then
                            // re-perform loop total expense calculation and display the total.
                            ExpenseFinalTotal = 0;
                            for (int i = 0; i <= id; i++) {
                                Log.d("TimesRemoved", " : " + i);
                                if (i == id) {
                                    ExpNameArray.set(id, "0");
                                    ExpAmtArray.set(id, "0");
                                }
                            }
                            Log.d("ExpAmt", ExpAmtArray.toString());
                            Log.d("ExpName", ExpNameArray.toString());
                            for (int i = 0; i <= ExpAmtArray.size() - 1; i++) {
                                int tempTotalExpense = Integer.parseInt(ExpAmtArray.get(i));
                                ExpenseFinalTotal = ExpenseFinalTotal + tempTotalExpense;
                            }
                            //textviewTotalExpense.setText("Total Expense : " +String.valueOf(ExpenseFinalTotal));
                        }
                    }
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<String> FExpenseAmtArray = new ArrayList<>();
                    ArrayList<String> FExpenseNameArray = new ArrayList<>();
                    if (!ExpAmtArray.isEmpty()) {
                        for (int i = 0; i < ExpAmtArray.size(); i++) {
                            if (!ExpAmtArray.get(i).equals("0") && !ExpAmtArray.get(i).equals("")) {
                                FExpenseAmtArray.add(ExpAmtArray.get(i));
                                FExpenseNameArray.add(ExpNameArray.get(i));
                            }
                        }
                    }
                    Log.d("ArrayExpName", FExpenseNameArray.toString());
                    Log.d("ArrayExpAmt", FExpenseAmtArray.toString());
                    JSONArray jsonArrayName = new JSONArray();
                    for (String Expname : FExpenseNameArray) {
                        jsonArrayName.put(Expname);
                    }
                    JSONArray jsonArrayExpAmt = new JSONArray();
                    for (String ExpAmt : FExpenseAmtArray) {
                        jsonArrayExpAmt.put(ExpAmt);
                    }
                    SubmitExpenseData( jsonArrayName.toString(), jsonArrayExpAmt.toString());
                }
            });






    }
    private void SubmitExpenseData(String idEv,String jawab)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //we get the successful in String response
                        Log.e("response", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("sellresponseerror", ""+error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("npm", getId);
                params.put("idEvdos", idEv);
                params.put("jawaban", jawab);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);
    }
    public  void SaveEditDetail(String idEv,String jawab) {


        //final String id = getId;

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
                                //sessionManager.createSession(id);
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
                params.put("npm", getId);
                params.put("idEvdos", idEv);
                params.put("jawaban", jawab);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);

    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }






}
