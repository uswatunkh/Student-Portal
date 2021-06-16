package com.example.studentportal.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studentportal.R;
import com.example.studentportal.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.app.ProgressDialog.show;

public class SignupTabFragment extends Fragment {
    private EditText npm,tgl,passwordUser, confirm_pass,date;
    private Button btn_regist;
    private ProgressBar loading;
    ImageView showPassword, confirmshowpass;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    private static String URL_REGIST= Server.URL + "aktivasi.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        npm = root.findViewById(R.id.npm);
        tgl = root.findViewById(R.id.tanggalLahir);
        passwordUser = root.findViewById(R.id.passwordUser);
        confirm_pass = root.findViewById(R.id.confirm_pass);
        btn_regist = root.findViewById(R.id.btn_regist);
        showPassword=root.findViewById(R.id.show_pass_btn);
        confirmshowpass=root.findViewById(R.id.confirm_pass_btn);
        date = root.findViewById(R.id.tanggalLahir);
        date.setOnClickListener(new View.OnClickListener() {
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
                                date.setText(year + "-" + (month + 1) + "-" + day );
                            }
                        }, year, month, dayOfMonth);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
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

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String npmUser = npm.getText().toString().trim();
                final String tglLahir = tgl.getText().toString().trim();
                final String password = passwordUser.getText().toString().trim();
                final String confirmPassword = confirm_pass.getText().toString();
                if (npmUser.isEmpty() || tglLahir.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Data Masih Ada Yang  Kosong")
                            .setNegativeButton("Retry",null)
                            .create()
                            .show();
                }else if (password.length()<6){
                    passwordUser.setError("Password Minimal 6 Karakter");

                }
                else {
                    Regist();
                }

            }
        });
        return root;
    }
    private void kosong() {
        npm.setText(null);
        tgl.setText(null);
        passwordUser.setText(null);
        confirm_pass.setText(null);
    }

    public void ShowHidePass (View view){
        if(view.getId()==R.id.show_pass_btn){

            if(passwordUser.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hide_password);

                //Show Password
                passwordUser.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.show_password);

                //Hide Password
                passwordUser.setTransformationMethod(PasswordTransformationMethod.getInstance());

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


    private void Regist(){

        final String npm = this.npm.getText().toString().trim();
        final String tgl = this.tgl.getText().toString().trim();
        final String passwordUser = this.passwordUser.getText().toString().trim();
        final String confirmPassword = confirm_pass.getText().toString();

        StringRequest  stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Aktivasi Berhasil")
                                        .setNegativeButton("Ok",null)
                                        .create()
                                        .show();
                                         kosong();


                            }else if (success.equals("2")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Npm yang anda masukkan sudah terdaftar")
                                        .setIcon(R.drawable.error)
                                        .setNegativeButton("Ok",null)
                                        .create()
                                        .show();
                            }else if (success.equals("3")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Aktivasi Gagal")
                                        .setIcon(R.drawable.error)
                                        .setNegativeButton("Ok",null)
                                        .create()
                                        .show();
                            }else if (success.equals("4")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Ulangi Konfirmasi Lagi")
                                        .setIcon(R.drawable.error)
                                        .setNegativeButton("Ok",null)
                                        .create()
                                        .show();
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Register Error" + e.toString(),Toast.LENGTH_SHORT).show();

                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Register Error" + error.toString(),Toast.LENGTH_SHORT).show();

                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", npm);
                params.put("tanggalLahir", tgl);
                params.put("passwordUser", passwordUser);
                params.put("conformPass", confirmPassword);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    public interface onFragmentInteractionListener {
    }
}





