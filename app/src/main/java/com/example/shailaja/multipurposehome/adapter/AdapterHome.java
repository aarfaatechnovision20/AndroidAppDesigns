package com.example.shailaja.multipurposehome.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shailaja.multipurposehome.pojoclass.Home;
import com.example.shailaja.multipurposehome.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.MyViewHolder> {

    private List<Home> moviesList;
    public AdapterHome(List<Home> moviesList, Context context) {
        this.moviesList = moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Context context;
        public TextView title, genre;
        public ImageView img;
        public CardView cardViewForSingleItem;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            title =  view.findViewById(R.id.txttitle);
            genre =  view.findViewById(R.id.txtinfo);
            img =  view.findViewById(R.id.image);
            cardViewForSingleItem = view.findViewById(R.id.cardViewForSingleItem);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Home movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getDetails());

        Picasso.with(holder.context).load(movie.getImageurl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

