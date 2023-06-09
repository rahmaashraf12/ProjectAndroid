package com.example.foodtime.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodtime.Activity.showDetailActivity;
import com.example.foodtime.Domain.FoodDomain;
import com.example.foodtime.R;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {

    ArrayList<FoodDomain> RecommendedDomain;

    public RecommendedAdapter(ArrayList<FoodDomain> RecommendedDomain) {
        this.RecommendedDomain = RecommendedDomain;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recommended,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, int position) {
        holder.title.setText(RecommendedDomain.get(position).getTitle());
        holder.fee.setText("$"+(RecommendedDomain.get(position).getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(RecommendedDomain.get(position).getPic(),"drawable",
                holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), showDetailActivity.class);
            intent.putExtra("foodDomain",RecommendedDomain.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return RecommendedDomain.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee;
        ImageView pic , addBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recommended_title);
            pic = itemView.findViewById(R.id.picCart);
            fee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
