package com.test.mohammaddvi.snappfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.mohammaddvi.snappfood.Adapter.RecyclerViewBuyBasketAdapter;
import com.test.mohammaddvi.snappfood.Model.FinalFood;

import java.util.ArrayList;

public class BuyBasket extends AppCompatActivity implements RecyclerViewBuyBasketAdapter.priceCalculate {

    private static final String TAG = BuyBasket.class.getName();
    ArrayList<FinalFood> foods = new ArrayList<>();
    ArrayList<FinalFood> newfoods=new ArrayList<>();
    RecyclerViewBuyBasketAdapter recyclerViewBuyBasketAdapter;
    RecyclerView recyclerView;
    BottomSheetBehavior bottomSheetBehavior;
    ImageView imageView;
    TextView total;
    RecyclerViewBuyBasketAdapter.priceCalculate priceCalculate;
    int firstresult = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_basket);
        findViews();
        foods = (ArrayList<FinalFood>) getIntent().getSerializableExtra("final");
        recyclerViewBuyBasketAdapter = new RecyclerViewBuyBasketAdapter(foods, BuyBasket.this, priceCalculate);
        recyclerView.setLayoutManager(new LinearLayoutManager(BuyBasket.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewBuyBasketAdapter);
        setFirstPrice();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(this,RestaurantDetails.class);
//        if (newfoods==null)
//            Log.d(TAG,"soryyyyyyyyyyyyyyyyyyy");
//        intent.putExtra("newfoods",newfoods);
//        intent.putExtra("isbackpress","yes");
//        startActivity(intent);
//        finish();
    }

    @Override
    public void calPlusPrice(String price) {
        String[] part = price.split(" ");
        String[] newtotal = total.getText().toString().split(" ");
        int result = Integer.parseInt(newtotal[0]) + Integer.parseInt(part[0]);
        total.setText(result + " تومان ");
    }

    @Override
    public void calMinusPrice(String price) {
        String[] part = price.split(" ");
        String[] newtotal = total.getText().toString().split(" ");
        int result = Integer.parseInt(newtotal[0]) - Integer.parseInt(part[0]);
        total.setText(result + " تومان ");
    }

    @Override
    public void onSaveDataForPassingToRestaurantDetails(FinalFood finalFood, int position) {
        if (newfoods.contains(finalFood)) {
            newfoods.remove(finalFood);
            if (finalFood.getOrdernumber() != 0)
                newfoods.add(finalFood);
        }
        if (!newfoods.contains(finalFood)) {
            if (finalFood.getOrdernumber() != 0)
                newfoods.add(finalFood);
        }
    }


    public void findViews() {
        recyclerView = findViewById(R.id.recyclerview);
        View view = findViewById(R.id.layoutbottomsheet);
        imageView = findViewById(R.id.arrowup);
        total = findViewById(R.id.totalsumcost);
        priceCalculate = this;
        bottomSheetBehavior = BottomSheetBehavior.from(view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                changeImage();
                return false;
            }
        });
    }

    public void changeImage() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            imageView.setImageResource(R.drawable.arrow_up);
        }
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            imageView.setImageResource(R.drawable.arrow_down);
        }
    }

    public void setFirstPrice() {
        for (int i = 0; i < foods.size(); i++) {
            int ordernumber = foods.get(i).getOrdernumber();
            String foodCost = foods.get(i).getPrice();
            String[] part = foodCost.split(" ");
            int pureprice = Integer.parseInt(part[0]);
            firstresult += (ordernumber * pureprice);
        }
        total.setText(firstresult + " تومان ");
    }

}
