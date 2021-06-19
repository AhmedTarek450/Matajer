package com.example.matajer.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.matajer.Adapters.Favorite_DB;
import com.example.matajer.Adapters.HomeAdapter;
import com.example.matajer.Adapters.HomeModel;
import com.example.matajer.R;
import com.google.api.ResourceDescriptor;


import java.util.ArrayList;



public class HomePage extends AppCompatActivity implements View.OnClickListener {
ImageView drawerbtn,empty_heart;
DrawerLayout drawerLayout;
RecyclerView recyclerView;
HomeAdapter homeAdapter;
ArrayList<HomeModel> homeItems;
Favorite_DB favorite_db;
HomeModel homeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.homepage);
        initviews();
        HomeItems();
    }

    private void HomeItems() {
        recyclerView = findViewById(R.id.home_RV);
        showitems();
        homeAdapter = new HomeAdapter(homeItems,this);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initviews() {
        drawerbtn = findViewById(R.id.drawer_btn);
       drawerLayout = findViewById(R.id.drawer_layout);
       empty_heart = findViewById(R.id.empty_heart);
        drawerbtn.setOnClickListener(this);
        empty_heart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.drawer_btn:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.favo_txt:
        Intent intent = new Intent(this,Favorite_page.class);
        startActivity(intent);
                break;
            case R.id.history_txt:
                Intent i = new Intent(this, ResourceDescriptor.History.class);
                startActivity(i);
                break;
            case R.id.settings_txt:
                Intent ii = new Intent(this,Favorite_page.class);
                startActivity(ii);
                break;
            case R.id.logout_txt:
                Intent iii = new Intent(this,Favorite_page.class);
                startActivity(iii);
                break;

        }
    }
    private void showitems(){
       homeItems = new ArrayList<>();
        HomeModel homeModel =  new HomeModel("Cat",R.drawable.catt);
        homeItems.add(homeModel);
        HomeModel homeModel1 = new HomeModel("Dog",R.drawable.dog);
        homeItems.add(homeModel1);
        HomeModel homeModel2 = new HomeModel("Parrot",R.drawable.parrot);
        homeItems.add(homeModel2);
        HomeModel homeModel3 =  new HomeModel("Birds",R.drawable.birds);
        homeItems.add(homeModel3);
        HomeModel homeModel4 = new HomeModel("Rabbit",R.drawable.rabbit);
        homeItems.add(homeModel4);
        HomeModel homeModel5 = new HomeModel("Squirrel",R.drawable.squirrel);
        homeItems.add(homeModel5);
        HomeModel homeModel6 = new HomeModel("Tortoise",R.drawable.tortoise);
        homeItems.add(homeModel6);
        HomeModel homeModel7 = new HomeModel("Hamster",R.drawable.hamster);
        homeItems.add(homeModel7);
    }




    }
