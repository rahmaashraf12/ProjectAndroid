package com.example.foodtime.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodtime.Domain.FoodDomain;
import com.example.foodtime.Helper.ManagementCart;
import com.example.foodtime.LogoutActivity;
import com.example.foodtime.R;

import android.content.res.Resources;

public class showDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn,titleTxt,feeTxt,describtionTxt,numberOrderTxt , totalPriceTxt,caloryTxt,starTxt,timeTxt;
    private ImageView plusBtn,minusBtn,picFood;
    private FoodDomain foodDomain;
    private int numberOrder;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        iniView();
        getBundle();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn =findViewById(R.id.homeBtn_Card);
        LinearLayout cartBtn = findViewById(R.id.CardBtn_again);
        LinearLayout settingBtn=findViewById(R.id.homebtn_setting);

        homeBtn.setOnClickListener((v -> {
            startActivity(new Intent(showDetailActivity.this,MainActivity.class));
        }));

        cartBtn.setOnClickListener((v -> {
            startActivity(new Intent(showDetailActivity.this,CartActivity.class));
        }));
        settingBtn.setOnClickListener((v -> {
            startActivity(new Intent(showDetailActivity.this, LogoutActivity.class));
        }));
    }

    private void getBundle() {
        foodDomain = (FoodDomain) getIntent().getSerializableExtra("foodDomain");

        int drawableResourceId = this.getResources().getIdentifier(foodDomain.getPic(),"drawable",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picFood);
        titleTxt.setText(foodDomain.getTitle());
        feeTxt.setText("$"+foodDomain.getFee());
        describtionTxt.setText(foodDomain.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        caloryTxt.setText(foodDomain.getCalories()+"Calories");
        starTxt.setText(String.valueOf(foodDomain.getStar()));
        timeTxt.setText(String.valueOf(foodDomain.getTime()));
        totalPriceTxt.setText("$"+Math.round(numberOrder*foodDomain.getFee()));

        plusBtn.setOnClickListener((v -> {
            numberOrder = numberOrder+1;
            numberOrderTxt.setText(String.valueOf(numberOrder));
            totalPriceTxt.setText("$"+Math.round(numberOrder*foodDomain.getFee()));
        }));

        minusBtn.setOnClickListener((v -> {
            if (numberOrder > 1){
                numberOrder -= 1;
            }
            numberOrderTxt.setText(String.valueOf(numberOrder));
            totalPriceTxt.setText("$"+Math.round(numberOrder*foodDomain.getFee()));
        }));

        addToCartBtn.setOnClickListener((v -> {
            foodDomain.setNumberInCart(numberOrder);
            managementCart.insertFood(foodDomain);
            startActivity(new Intent(showDetailActivity.this,CartActivity.class));
        }));
    }

    private void iniView() {
        addToCartBtn = findViewById(R.id.add_to_Cart_Btn);
        titleTxt = findViewById(R.id.food_Title);
        feeTxt = findViewById(R.id.priceTxt);
        describtionTxt = findViewById(R.id.describtionTxt);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plus_Card_Btn);
        minusBtn = findViewById(R.id.minus_Card_Btn);
        picFood = findViewById(R.id.foodPic);
        totalPriceTxt = findViewById(R.id.total_Price_Txt);
        starTxt = findViewById(R.id.starTxt);
        caloryTxt = findViewById(R.id.caloriesTxt);
        timeTxt = findViewById(R.id.timeTxt);
    }
}