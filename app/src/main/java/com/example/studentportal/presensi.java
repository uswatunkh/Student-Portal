package com.example.studentportal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class presensi extends AppCompatActivity {
    RecyclerView NewsRecyclerview;
    presensiNewAdapter presensiNewAdapter;
    List<presensiItem> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //buat fullscreen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.presensi_recycler);

//        getSupportActionBar().hide();

        NewsRecyclerview =findViewById(R.id.news_rv);
        mData = new ArrayList<>();


        //data
        mData.add(new presensiItem("Semester 1"));
        mData.add(new presensiItem("Semester 2"));
        mData.add(new presensiItem("Semester 3"));
        mData.add(new presensiItem("Semester 4"));
        mData.add(new presensiItem("Semester 5"));


        presensiNewAdapter = new presensiNewAdapter(this,mData);
        NewsRecyclerview.setAdapter(presensiNewAdapter);
        NewsRecyclerview.setLayoutManager(new LinearLayoutManager(this));

    }
}

