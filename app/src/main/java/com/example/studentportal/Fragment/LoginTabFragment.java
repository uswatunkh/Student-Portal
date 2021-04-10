package com.example.studentportal.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.studentportal.FragmentUtama;
import com.example.studentportal.R;
import com.example.studentportal.Server;
import com.example.studentportal.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginTabFragment extends Fragment {
    EditText npmUser, passwordUser;
    TextView forgetPass;
    public Context context;
    Button login;
    ImageView showPassword;
    float v=0;
    SessionManager sessionManager;
    private static String URL_LOGIN = Server.URL + "loginUser.php";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);
        sessionManager = new SessionManager(getActivity());

//        email= root.findViewById(R.id.email);
//        pass= root.findViewById(R.id.pass);
//        forgetPass = root.findViewById(R.id.forgetPass);
        login = root.findViewById(R.id.buttonLogin);
        npmUser = root.findViewById(R.id.npm);
        passwordUser = root.findViewById(R.id.passwordUser);
        showPassword = root.findViewById(R.id.show_pass_btn);






//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iLogin = new Intent(getActivity(),HomeActivity.class);
//                startActivity(iLogin);
//            }
//        });
        //Code hide-show password
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowHidePass(v);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mNpm = npmUser.getText().toString().trim();
                String mPassword = passwordUser.getText().toString().trim();

                if (!mNpm.isEmpty() || !mPassword.isEmpty()){
                    Login(mNpm, mPassword);
                }else{
                    npmUser.setError("Please insert email");
                    passwordUser.setError("Please insert password");
                }
            }
        });


        npmUser.setTranslationY(600);
        passwordUser.setTranslationY(600);
        login.setTranslationY(600);

        npmUser.setAlpha(v);
        passwordUser.setAlpha(v);
        login.setAlpha(v);

        npmUser.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        passwordUser.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();



        return root;



    }
    //Code hide-show password
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

    private void Login(final String npm, final String passwordUser){



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")){
                                for (int i =0; i<jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String npm = object.getString("npm").trim();

                                    sessionManager.createSession(npm);

//                                    Toast.makeText(LoginActivity.this,
//                                            "Success Login. \nYour Name:"
//                                                    +name+ "\nYour Email:"
//                                                    +email,   Toast.LENGTH_SHORT).show();
//
//
                                    Intent intent = new Intent(getActivity(), FragmentUtama.class);
                                    intent.putExtra("npm", npm);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Npm atau Password salah")
                                        .setNegativeButton("Ok",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error"+ e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error"+ error.toString(), Toast.LENGTH_SHORT).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("npm", npm);
                params.put("passwordUser", passwordUser);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}

