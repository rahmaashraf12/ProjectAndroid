package com.example.foodtime.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.foodtime.Adapter.CategoryAdapter;
import com.example.foodtime.Adapter.RecommendedAdapter;
import com.example.foodtime.Domain.CategoryDomain;
import com.example.foodtime.Domain.FoodDomain;
import com.example.foodtime.LogoutActivity;
import com.example.foodtime.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewcategorylist,recyclerViewpopularlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        racyclerviewcategory();
        recyclerViewpopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn =findViewById(R.id.homeBtn_Card);
        LinearLayout cartBtn = findViewById(R.id.CartBtn);
        LinearLayout settingBtn=findViewById(R.id.homebtn_setting);

        homeBtn.setOnClickListener((v -> {
            startActivity(new Intent(MainActivity.this,MainActivity.class));
        }));

        cartBtn.setOnClickListener((v -> {
            startActivity(new Intent(MainActivity.this,CartActivity.class));
        }));
        settingBtn.setOnClickListener((v -> {
            startActivity(new Intent(MainActivity.this, LogoutActivity.class));
        }));
    }

    private void recyclerViewpopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewpopularlist = findViewById(R.id.view2);
        recyclerViewpopularlist.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Fresh Burger","food1",
                "slices pepperoni , mozzeralla cheese, fresh oregano, ground black pepper sauce",
                13.0,5,20,1000));
        foodlist.add(new FoodDomain("Orange Juice","juice",
                "Orange,mango,strobery",
                15.20,4,18,1500));
        foodlist.add(new FoodDomain("PanCake","food2",
                "Olive Oil , Vegetable Oil , Pitted Kalamata , Cherry Tomatoes , Fresh Oregano , Basil",
                11.0,3,16,800));

        adapter2 = new RecommendedAdapter(foodlist);
        recyclerViewpopularlist.setAdapter(adapter2);
    }

    private void racyclerviewcategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewcategorylist = findViewById(R.id.view1);
        recyclerViewcategorylist.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza","cat_1"));
        categoryList.add(new CategoryDomain("Burger","cat_2"));
        categoryList.add(new CategoryDomain("Hotdog","cat_3"));
        categoryList.add(new CategoryDomain("Drink","cat_4"));
        categoryList.add(new CategoryDomain("Donut","cat_5"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewcategorylist.setAdapter(adapter);
    }
}