package com.test.mohammaddvi.snappfood.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.test.mohammaddvi.snappfood.Model.FinalFood;
import com.test.mohammaddvi.snappfood.R;

import java.util.ArrayList;


public class RecyclerViewBuyBasketAdapter extends RecyclerView.Adapter<RecyclerViewBuyBasketAdapter.SingleItemBuyBasket> {

    private ArrayList<FinalFood> foodList;
    private Context mContext;
    private View view;
    private priceCalculate priceCalculate;

    public RecyclerViewBuyBasketAdapter(ArrayList<FinalFood> foodList, Context mContext, priceCalculate pricecalculate) {
        this.foodList = foodList;
        this.mContext = mContext;
        this.priceCalculate = pricecalculate;
    }

    @NonNull
    @Override
    public RecyclerViewBuyBasketAdapter.SingleItemBuyBasket onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buybasketitem, null);
        return new RecyclerViewBuyBasketAdapter.SingleItemBuyBasket(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewBuyBasketAdapter.SingleItemBuyBasket holder, int position) {
        FinalFood food = foodList.get(position);
        holder.foodName.setText(food.getName());
        holder.foodDetails.setText(food.getDetails());
        holder.foodPrice.setText(food.getPrice());
        holder.foodOrderNumber.setText(food.getOrdernumber() + "");
        handleClick(holder, view);
    }

    private void handleClick(final RecyclerViewBuyBasketAdapter.SingleItemBuyBasket holder, final View view) {
        holder.foodPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalFood food = foodList.get(holder.getAdapterPosition());
                int orderNumber = food.getOrdernumber();
                int newOrderNumber = orderNumber + 1;
                food.setOrdernumber(newOrderNumber);
                holder.foodOrderNumber.setText(newOrderNumber + "");
                priceCalculate.calPlusPrice(holder.foodPrice.getText().toString());
//                SaveSpec(holder,holder.getAdapterPosition());
            }
        });
        holder.foodMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalFood food = foodList.get(holder.getAdapterPosition());
                priceCalculate.calMinusPrice(holder.foodPrice.getText().toString());
                int orderNumber = food.getOrdernumber();
                if (orderNumber == 1) {
                    int newOrderNumber = orderNumber - 1;
                    food.setOrdernumber(newOrderNumber);
                    removeItem(holder.getAdapterPosition());
                } else {
                    int newOrderNumber = orderNumber - 1;
                    food.setOrdernumber(newOrderNumber);
                    holder.foodOrderNumber.setText(newOrderNumber + "");
//                    SaveSpec(holder,holder.getAdapterPosition());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != foodList ? foodList.size() : 0);
    }

    public void removeItem(int position) {
        foodList.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position);
        notifyItemRangeChanged(position, foodList.size());
    }

    public interface priceCalculate {
        void calPlusPrice(String price);
        void calMinusPrice(String price);
        void onSaveDataForPassingToRestaurantDetails(FinalFood finalFood, int position);
    }
    public void SaveSpec(RecyclerViewBuyBasketAdapter.SingleItemBuyBasket holder, int position) {
        FinalFood finalFood = foodList.get(position);
        finalFood.setName(holder.foodName.getText().toString());
        finalFood.setPrice(holder.foodPrice.getText().toString());
        finalFood.setDetails(holder.foodDetails.getText().toString());
        finalFood.setOrdernumber(Integer.parseInt(holder.foodOrderNumber.getText().toString()));
        priceCalculate.onSaveDataForPassingToRestaurantDetails(finalFood,position);
    }


    public class SingleItemBuyBasket extends RecyclerView.ViewHolder {

        TextView foodName;
        TextView foodPrice;
        Button foodPlusButton;
        Button foodMinusButton;
        TextView foodOrderNumber;
        TextView foodDetails;

        SingleItemBuyBasket(View itemView) {

            super(itemView);
            this.foodName = itemView.findViewById(R.id.foodNameInBuyBasket);
            this.foodPrice = itemView.findViewById(R.id.foodPriceInBuyBasket);
            this.foodDetails = itemView.findViewById(R.id.foodDetailsInBuyBasket);
            this.foodPlusButton = itemView.findViewById(R.id.plusbuttonInBuyBasket);
            this.foodMinusButton = itemView.findViewById(R.id.minusbuttonInBuyBasket);
            this.foodOrderNumber = itemView.findViewById(R.id.ordernumberInBuyBasket);
        }
    }
}