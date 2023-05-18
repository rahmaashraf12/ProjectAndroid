package com.example.foodtime.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodtime.Adapter.CartListAdapter;
import com.example.foodtime.Helper.ManagementCart;
import com.example.foodtime.LogoutActivity;
import com.example.foodtime.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewlist;
    private ManagementCart managementCart;
    private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        calculateCard();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn =findViewById(R.id.homeBtn_Card);
        LinearLayout cartBtn = findViewById(R.id.CardBtn_again);
        LinearLayout settingBtn=findViewById(R.id.homebtn_setting);
        homeBtn.setOnClickListener((v -> {
            startActivity(new Intent(CartActivity.this,MainActivity.class));
        }));

        cartBtn.setOnClickListener((v -> {
            startActivity(new Intent(CartActivity.this,CartActivity.class));
        }));
        settingBtn.setOnClickListener((v -> {
            startActivity(new Intent(CartActivity.this, LogoutActivity.class));
        }));
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recyclerViewlist.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(),this,()->{
            calculateCard();
        });

        recyclerViewlist.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percent_Tax = 0.05;
        double delivery = 30.0;

        tax = Math.round((managementCart.getTotalFee()*percent_Tax)*100.0) /100.0;
        double total = Math.round((managementCart.getTotalFee()+tax+delivery)*100.0)/100.0;
        double item_Total = Math.round((managementCart.getTotalFee())*100.0)/100.0;

        totalFeeTxt.setText("$"+item_Total);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);

    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.total_Item_Count);
        taxTxt = findViewById(R.id.tax_Price);
        deliveryTxt = findViewById(R.id.delivery_Service_Price);
        totalTxt = findViewById(R.id.total_Price);
        recyclerViewlist = findViewById(R.id.food_list_view);
        scrollView = findViewById(R.id.scrollView2);
        emptyTxt = findViewById(R.id.empty_View);
    }
}