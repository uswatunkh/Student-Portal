package com.example.studentportal.Fitur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;
import com.example.studentportal.app.AppController;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterEvdos extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataEvdos> items;
    private Bitmap bitmap;
    Boolean activate;
    Boolean kuisioner = false;
    String idPengajaran, namaMK,statusKuisioner;
    TextView cekKuisioner;
    Button btn_isi;

    public AdapterEvdos(Activity activity, List<DataEvdos> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return
                items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.evdoslist_evdos, null);

        TextView idDosen = (TextView) convertView.findViewById(R.id.tvIdDosen);
        TextView namaDosen = (TextView) convertView.findViewById(R.id.tvNamaDosen);
        TextView namaMataKuliah = (TextView) convertView.findViewById(R.id.tvNamaMK);
         cekKuisioner = (TextView) convertView.findViewById(R.id.statusKuisioner);
        CircleImageView fotoDosen = (CircleImageView) convertView.findViewById(R.id.fotodosen);
        btn_isi = (Button) convertView.findViewById(R.id.btn_isi);
        Button btn_SudahDiisi = (Button) convertView.findViewById(R.id.btn_sudahDiisi);
//        TextView nidn = (TextView) convertView.findViewById(R.id.tvNIDN);
//        TextView idMataKuliah = (TextView) convertView.findViewById(R.id.tvIdMk);

        DataEvdos data = items.get(position);

        idDosen.setText(data.getIdDosen());
        namaDosen.setText(data.getNamaDosen());
        namaMataKuliah.setText(data.getNamaMatakuliah());

        statusKuisioner = data.getStatusKuisioner();
        cekKuisioner.setText(statusKuisioner);
        if (!statusKuisioner.isEmpty()){
            btn_isi.setVisibility(View.GONE);
            btn_SudahDiisi.setVisibility(View.VISIBLE);
        }



        btn_isi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,Kuisioner.class);
                intent.putExtra("position",position);
                activity.startActivity(intent);
                activity.finish();

            }
        });

        idPengajaran = items.get(position).getIdDosen();
        

        return convertView;
    }

    void cekKuisioner(String getidPengajaran){
        SessionManager sessionManager = new SessionManager(activity);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String getId = user.get(sessionManager.ID);
        //String url_select = Server.URLEvaluasiDosen + "cekKuisioner.php?npm=" + getId + "&idPengajaran=" + idPengajaran + "&namaMK=" +  namaMK ;
        String url_select = Server.URLEvaluasiDosen + "cekKuisioner.php";
        StringRequest jArr = new StringRequest(Request.Method.POST,url_select, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String success = jsonObject.getString("success");
                    Log.e("Data: ", response.toString());
                    if (success.equals("0")) {
                        cekKuisioner.setText(getidPengajaran);

                    }else if (success.equals("1")) {
                        cekKuisioner.setText("Kuisioner Belum terisi"+getidPengajaran);

                    }
                    //int success = jsonObject.getInt("success");
//                    if(success == 1){
//                        kuisioner = true;
//                    }else kuisioner = false;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        })

        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", getId);
                params.put("idPengajaran", getidPengajaran);
                //params.put("namaMK", namaMK);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jArr);
    }
}