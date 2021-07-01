package com.example.studentportal.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingPasswordFragment extends Fragment {

    private static final String TAG= SettingPasswordFragment.class.getSimpleName() ;  //getting the info

    private EditText npm,namaLengkap,passOld,pass,confirm_pass;
    ImageView oldPassword, showPassword, confirmshowpass ;
    Button ubah;
    SessionManager sessionManager;
    String getId;  //updateprofil
    private static String URL_READ = Server.URL + "readDatadiri.php";
    private static String URL_EDIT = Server.URL + "settingPassword.php";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingPasswordFragment newInstance(String param1, String param2) {
        SettingPasswordFragment fragment = new SettingPasswordFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_setting_password, container, false);
        ubah = root.findViewById(R.id.buttonUlangPassword);
        oldPassword=root.findViewById(R.id.show_passOld_btn);
        showPassword=root.findViewById(R.id.show_pass_btn);
        confirmshowpass=root.findViewById(R.id.confirm_pass_btn);
        npm=root.findViewById(R.id.npm);
        namaLengkap=root.findViewById(R.id.namaLengkap);
        passOld=root.findViewById(R.id.passwordLama);
        pass=root.findViewById(R.id.passwordBaru);
        confirm_pass = root.findViewById(R.id.ulangi_password_baru);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwordLama = passOld.getText().toString().trim();
                String passwordBaru = pass.getText().toString().trim();
                String confirmPassword = confirm_pass.getText().toString().trim();
//                if (passwordLama.isEmpty() || passwordBaru.isEmpty() || confirmPassword.isEmpty()){
////                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////                    builder.setMessage("Password Masih Kosong")
////                            .setNegativeButton("Retry",null)
////                            .create()
////                            .show();
//                    ViewDialogNotSuccess alert = new ViewDialogNotSuccess();
//                    alert.showDialog(getActivity(), "Password Masih Ada Yang Kosong");
//
//                }
//                else
                 if (passwordBaru.length()<6){
                    pass.setError("Password Minimal 6 Karakter");

                }
                else {
                    SaveEditDetail();
                }
            }
        });
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil
        oldPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowHidePassOld(v);
            }
        });
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowHidePass(v);
            }
        });
        confirmshowpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmShowHidePass(v);
            }
        });
        return root;

    }

    private void kosong() {
        passOld.setText(null);
        pass.setText(null);
        confirm_pass.setText(null);
    }
    public void ShowHidePassOld (View view){
        if(view.getId()==R.id.show_passOld_btn){

            if(passOld.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hide_password);

                //Show Password
                passOld.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.show_password);

                //Hide Password
                passOld.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
    public void ShowHidePass (View view){
        if(view.getId()==R.id.show_pass_btn){

            if(pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hide_password);

                //Show Password
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.show_password);

                //Hide Password
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
    public void ConfirmShowHidePass (View view){
        if(view.getId()==R.id.confirm_pass_btn){

            if(confirm_pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hide_password);

                //Show Password
                confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.show_password);

                //Hide Password
                confirm_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
    public class ViewDialogNotSuccess {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_notsuccess);

            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();


        }
    }
    public class ViewDialogSuccess {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_success);

            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

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

                                    String strNpm = object.getString("npm").trim();
                                    String strName = object.getString("namaLengkap").trim();

                                    npm.setText(strNpm);
                                    namaLengkap.setText(strName);


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

    private void SaveEditDetail() {
        final String passwordlama = this.passOld.getText().toString().trim();
        final String newpassword = this.pass.getText().toString().trim();
        final String confirmPass = this.confirm_pass.getText().toString().trim();
        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
//                        message(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                builder.setMessage("Password Berhasil Diubah")
//                                        .setNegativeButton("Ok",null)
//                                        .create()
//                                        .show();
                                ViewDialogSuccess alert = new ViewDialogSuccess();
                                alert.showDialog(getActivity(), "Password Berhasil Diubah");
                                kosong();


                            }else if (success.equals("2")) {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                builder.setMessage("Password Berhasil Diubah")
//                                        .setNegativeButton("Ok",null)
//                                        .create()
//                                        .show();
                                ViewDialogNotSuccess alert = new ViewDialogNotSuccess();
                                alert.showDialog(getActivity(), "Password Lama Salah");
                                kosong();


                            }else{
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                builder.setMessage("konfirmasi password tidak sama")
//                                        .setIcon(R.drawable.error)
//                                        .setNegativeButton("Ok",null)
//                                        .create()
//                                        .show();
                                ViewDialogNotSuccess alert = new ViewDialogNotSuccess();
                                alert.showDialog(getActivity(), "Ulangi Konfirmasi Password");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                params.put("oldpass", passwordlama);
                params.put("newpass", newpassword);
                params.put("conformpass", confirmPass);
                params.put("npm", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    public void message (String message){
        Toast.makeText(getActivity(), message,Toast. LENGTH_SHORT).show();
    }
}