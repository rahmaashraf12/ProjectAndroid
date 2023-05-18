package com.example.foodtime.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodtime.Domain.FoodDomain;
import com.example.foodtime.Helper.ManagementCart;
import com.example.foodtime.Interface.ChangeNumberItemsListener;
import com.example.foodtime.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    ArrayList<FoodDomain> listFoodSelected;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemListener;

    public CartListAdapter(ArrayList<FoodDomain> listFoodSelected, Context context,ChangeNumberItemsListener changeNumberItemListener) {
        this.listFoodSelected = listFoodSelected;
        managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("$"+listFoodSelected.get(position).getFee());
        holder.totalEachItem.setText("$"+Math.round((listFoodSelected.get(position).getNumberInCart()*listFoodSelected.get(position).getFee())));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(listFoodSelected.get(position).getPic(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.plusItem.setOnClickListener((v) ->managementCart.plusNumberFood(listFoodSelected, position,() ->{
            notifyDataSetChanged();
            changeNumberItemListener.changed();
        }));
        holder.minusItem.setOnClickListener((v -> managementCart.minusNumberFood(listFoodSelected,position,() ->{
            notifyDataSetChanged();
            changeNumberItemListener.changed();
        })));
    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,feeEachItem ,totalEachItem,num;
        ImageView pic , plusItem, minusItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_Txt);
            pic = itemView.findViewById(R.id.picCart);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plus_Card_Btn);
            minusItem = itemView.findViewById(R.id.minus_Card_Btn);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }


}
