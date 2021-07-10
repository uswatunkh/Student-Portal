package com.example.studentportal;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.studentportal.Fragment.BiodataFragment;
import com.example.studentportal.Fragment.HomeFragment;
import com.example.studentportal.Fragment.SettingPasswordFragment;
import com.example.studentportal.Fragment.TentangFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class FragmentUtama extends AppCompatActivity {
    private final int ID_HOME = 1;
    private final int ID_MESSAGE = 2;
    private final int ID_NOTIFICATION = 3;
    private final int ID_ACCOUNT = 4;
    BottomNavigationView bottomNavigationView;
    SessionManager sessionManager;
    String getId;  //updateprofil


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("pengumuman");
        setContentView(R.layout.fragment_utama);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance("", ""));
                sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil




    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
//        transaction.disallowAddToBackStack();     //agar gabisa diback atau fungsi finish()
    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.home:
                            openFragment(HomeFragment.newInstance("oke", "oke"));

                            return true;
                        case R.id.notif:
                            openFragment(PengumumanFragment.newInstance("", ""));
                            return true;
                        case R.id.user:
                            openFragment(BiodataFragment.newInstance("", ""));
                            return true;
                        case R.id.setting:
                            openFragment(SettingPasswordFragment.newInstance("", ""));
                            return true;
                        case R.id.tentang:
                            openFragment(TentangFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

}
