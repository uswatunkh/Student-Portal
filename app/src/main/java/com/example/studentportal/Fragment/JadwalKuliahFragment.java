package com.example.studentportal.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Slide;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.studentportal.FragementHari.JumatFragment;
import com.example.studentportal.FragementHari.KamisFragment;
import com.example.studentportal.FragementHari.RabuFragment;
import com.example.studentportal.FragementHari.SabtuFragment;
import com.example.studentportal.FragementHari.SelasaFragment;
import com.example.studentportal.FragementHari.SeninFragment;
import com.example.studentportal.PengumumanFragment;
import com.example.studentportal.R;
import com.example.studentportal.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JadwalKuliahFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JadwalKuliahFragment extends Fragment {
    ImageView backKeterampilan;
    private final int ID_HOME = 1;
    private final int ID_MESSAGE = 2;
    private final int ID_NOTIFICATION = 3;
    private final int ID_ACCOUNT = 4;
    NavigationView navigationView,navigationView2,navigationView3;
    BottomNavigationView bottomNavigationView;
    SessionManager sessionManager;
    String getId;  //updateprofil
    ImageButton btn_drawer;
    ImageView refresh;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JadwalKuliahFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JadwalKuliahFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JadwalKuliahFragment newInstance(String param1, String param2) {
        JadwalKuliahFragment fragment = new JadwalKuliahFragment();
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_jadwal_kuliah, container, false);
        bottomNavigationView=root.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(SeninFragment.newInstance("", ""));
        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);  //updateprofil

        backKeterampilan= (ImageView) root.findViewById(R.id.backKeterampilan);
        backKeterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment2(HomeFragment.newInstance("", ""));
            }
        });


        return root;
    }

    public void openFragment2(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id  .containerhari, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
        transaction.disallowAddToBackStack();     //agar gabisa diback atau fungsi finish()
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.senin:
                            openFragment(SeninFragment.newInstance("", ""));
                            return true;
                        case R.id.selasa:
                            openFragment(SelasaFragment.newInstance("", ""));
                            return true;
                        case R.id.rabu:
                            openFragment(RabuFragment.newInstance("", ""));
                            return true;
                        case R.id.kamis:
                            openFragment(KamisFragment.newInstance("", ""));
                            return true;
                        case R.id.jumat:
                            openFragment(JumatFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };


}