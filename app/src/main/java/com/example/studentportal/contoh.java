package com.example.studentportal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class contoh extends AppCompatActivity {
    private RadioGroup radioGroupWarna;
    private RadioButton radioButtonWarna;
    private Button btnJawab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contoh);
//        radioGroupWarna = (RadioGroup) findViewById(R.id.radioGroupWarna);
//        btnJawab = (Button) findViewById(R.id.buttonJawab);
//        addListenerOnButtonJawab();



    }

//    public void addListenerOnButtonJawab() {
//
//        btnJawab.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                // get selected radio button from radioGroup
//                int selectedId = radioGroupWarna.getCheckedRadioButtonId();
//
//                // find the radiobutton by returned id
//                radioButtonWarna = (RadioButton) findViewById(selectedId);
//
//                Toast.makeText(contoh.this,
//                        "Anda Memilih Warna " + radioButtonWarna.getText(),
//                        Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//    }
    }
