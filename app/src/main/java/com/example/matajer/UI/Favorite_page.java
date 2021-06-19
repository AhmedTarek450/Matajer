package com.example.matajer.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.matajer.Adapters.Favorite_DB;
import com.example.matajer.Adapters.HomeAdapter;
import com.example.matajer.Adapters.HomeModel;
import com.example.matajer.R;

import java.util.ArrayList;

public class Favorite_page extends AppCompatActivity {

    ArrayList<HomeModel> homeModels = new ArrayList<>();
    Favorite_DB favorite_db;
    HomeAdapter homeAdapter;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_page);
        favorite_db =new Favorite_DB(this);
        RecyclerView();
    }
    private void RecyclerView(){
        recycler = findViewById(R.id.rv_fav);
        homeAdapter = new HomeAdapter(homeModels,this);
        recycler.setAdapter(homeAdapter);
        recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recycler.setLayoutManager(layoutManager);
    }

}