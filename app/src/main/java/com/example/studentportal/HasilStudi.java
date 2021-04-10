package com.example.studentportal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HasilStudi  extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.hasil_studi);

            RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);

            HasilStudi_TableViewAdapter adapter = new HasilStudi_TableViewAdapter(getMovieList());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            recyclerView.setAdapter(adapter);
        }

        private List getMovieList() {
            List movieList = new ArrayList<>();

            movieList.add(new HasilStudi_list("1", "Pemrograman web", "3/0", 4, "A",12));
            movieList.add(new HasilStudi_list("1", "Pemrograman web", "3/0", 4, "A",12));
            movieList.add(new HasilStudi_list("1", "Pemrograman web", "3/0", 4, "A",12));
            movieList.add(new HasilStudi_list("1", "Pemrograman web", "3/0", 4, "A",12));
            return movieList;
        }
    }

