package com.example.shailaja.multipurposehome.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shailaja.multipurposehome.R;
import com.example.shailaja.multipurposehome.pojoclass.Subject;
import com.squareup.picasso.Picasso;

import net.colindodd.gradientlayout.GradientRelativeLayout;

import java.util.List;

public class AdapterPhysics extends RecyclerView.Adapter<AdapterPhysics.MyViewHolder> {

    private List<Subject> subjectsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Context context;
        public TextView SubjectName;
        public ImageView img;
        public CardView cardViewForSingleItem;
        public GradientRelativeLayout gradientRelativeLayout;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            SubjectName =  view.findViewById(R.id.txtSubjectname);
            img =  view.findViewById(R.id.imageSubject);
            cardViewForSingleItem = view.findViewById(R.id.cardViewForSingleItem);
            gradientRelativeLayout = view.findViewById(R.id.gradientColor);
        }
    }

    public AdapterPhysics(List<Subject>subjectsList , Context context) {
        this.subjectsList = subjectsList;
    }


    @Override
    public AdapterPhysics.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_physics, parent, false);



        return new AdapterPhysics.MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final AdapterPhysics.MyViewHolder holder, int position) {
        Subject subject = subjectsList.get(position);
        holder.SubjectName.setText(subject.getSubjectName());
        Picasso.with(holder.context).load(subject.getImage()).into(holder.img);

        if(position % 3 == 0)
        {
            holder.gradientRelativeLayout.setStartColor(holder.context.getColor(R.color.gradientViolet));
            holder.gradientRelativeLayout.setEndColor(holder.context.getColor(R.color.gradientLightBlue));
        }
        else if(position % 2 == 0)
        {
            holder.gradientRelativeLayout.setStartColor(holder.context.getColor(R.color.gradientLightYellow2));
            holder.gradientRelativeLayout.setEndColor(holder.context.getColor(R.color.gradientLightOrange2));
        }
        else{
            holder.gradientRelativeLayout.setStartColor(holder.context.getColor(R.color.gradientPink));
            holder.gradientRelativeLayout.setEndColor(holder.context.getColor(R.color.gradientPinkEnd));
        }

    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }
}

