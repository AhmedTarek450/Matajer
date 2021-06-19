package com.example.matajer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.matajer.R;
import com.example.matajer.UI.DetailsPage;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    ArrayList<HomeModel> allItems ;
    Context context;

    public HomeAdapter(ArrayList<HomeModel> allItems, Context context) {
        this.allItems = allItems;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_view,parent,false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.petname.setText(allItems.get(position).name);
        Glide.with(context).load(allItems.get(position).Image).into(holder.petimg);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetailsPage.class);



            }
        });
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

    CardView cardView;
    ImageView petimg;
    TextView petname;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            petimg = itemView.findViewById(R.id.pet_img);
            petname = itemView.findViewById(R.id.pet_name);
        }
    }
}
