package com.example.studentportal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class keterampilanNewAdapter extends RecyclerView.Adapter<keterampilanNewAdapter.NewsViewHolder> {

    private static final String URLDelKeterampilan ="http://192.168.0.101/studentPortal/deleteKeterampilan.php";
    SessionManager sessionManager;
    String getId;  //updateprofil
    int success;
    Context mContext;
    List<KeterampilanItem> mData;
    public static final String ID = "idKeterampilan";
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener (OnItemClickListener listener){
        mListener =listener;
    }

    public keterampilanNewAdapter(Context mContext, List<KeterampilanItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public keterampilanNewAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.keterampilan_data, viewGroup, false);
        return new NewsViewHolder(layout, mListener); //gatau
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
        //animation card
        newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));


        //bind data here
        newsViewHolder.idKeterampilan.setText(mData.get(position).getId());
        newsViewHolder.nama_keterampilan.setText(mData.get(position).getNama());
        newsViewHolder.jenis.setText(mData.get(position).getJenis());
        newsViewHolder.tingkat.setText(mData.get(position).getTingkat());
        newsViewHolder.scan.setText(mData.get(position).getScan());

        getId= mData.get(position).getId();


        newsViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(mContext, ubah_keterampilan.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NewsViewHolder extends  RecyclerView.ViewHolder {

        TextView idKeterampilan,nama_keterampilan, jenis, tingkat, scan;
        CardView container;
        LinearLayout ubah_keterampilan;
        ImageView btnDialog ;
        public NewsViewHolder( View itemVew, final OnItemClickListener listener){
            super(itemVew);
            ubah_keterampilan = itemVew.findViewById(R.id.ubah_keterampilan);
            container = itemVew.findViewById(R.id.container);
            idKeterampilan = itemVew.findViewById(R.id.idKeterampilan);
            nama_keterampilan = itemVew.findViewById(R.id.nama_keterampilan);
            jenis = itemVew.findViewById(R.id.jenis);
            tingkat = itemVew.findViewById(R.id.tingkat);
            scan = itemVew.findViewById(R.id.scan);
            btnDialog = itemVew.findViewById(R.id.btnDialog);

            btnDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog diaBox = AskOption();
                    diaBox.show();
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }

                }
            });


        }



        private void kosong() {
            nama_keterampilan.setText(null);
            jenis.setText(null);
            tingkat.setText(null);
            scan.setText(null);
        }

        private AlertDialog AskOption()
        {
            AlertDialog myQuittingDialogBox = new AlertDialog.Builder(mContext)
                    // set message, title, and icon
                    .setTitle("Delete")
                    .setMessage("Do you want to Delete")
                    .setIcon(R.drawable.delete2)

                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            //your deleting code


                            Delete();


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

    }

  

    private void Delete() {
        String idx = getId;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLDelKeterampilan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt("success");

                    // Cek error pada json
                    if (success == 1) {
                        Log.d("delete", jObj.toString());



                        Toast.makeText(mContext, jObj.getString("message"), Toast.LENGTH_LONG).show();



                    } else {
                        Toast.makeText(mContext, jObj.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", idx);

                return params;
            }
        }
                ;
        RequestQueue requestQueue= Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);
    }
}
