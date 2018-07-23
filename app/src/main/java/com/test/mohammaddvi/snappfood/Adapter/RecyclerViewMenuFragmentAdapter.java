package com.test.mohammaddvi.snappfood.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.mohammaddvi.snappfood.Model.FinalFood;
import com.test.mohammaddvi.snappfood.Model.Food;
import com.test.mohammaddvi.snappfood.Model.OfferList;
import com.test.mohammaddvi.snappfood.R;

import java.util.ArrayList;
import java.util.List;

import static com.test.mohammaddvi.snappfood.Adapter.SectionListDataAdapter.decodeSampledBitmapFromResource;

public class RecyclerViewMenuFragmentAdapter extends RecyclerView.Adapter<RecyclerViewMenuFragmentAdapter.SingleItemInMenuFragment> {

    private ArrayList<Food> foodList;
    private ArrayList<FinalFood> finalFoodList;
    private Context mContext;
    private List<OfferList> offers;
    private OnClickListener onClickListener;
    private int totalOrderNumber = 0;

    public RecyclerViewMenuFragmentAdapter(ArrayList<Food> foodList, Context mContext, List<OfferList> offers, ArrayList<FinalFood> finalFoods, OnClickListener onClickListener) {
        this.foodList = foodList;
        this.mContext = mContext;
        this.finalFoodList = finalFoods;
        this.offers = offers;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewMenuFragmentAdapter.SingleItemInMenuFragment onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodlist, null);
        return new RecyclerViewMenuFragmentAdapter.SingleItemInMenuFragment(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewMenuFragmentAdapter.SingleItemInMenuFragment holder, int position) {
        Food food = foodList.get(position);
        final OfferList offerList = offers.get(position);
        holder.foodName.setText(food.getName());
        holder.foodDetails.setText(food.getDetails());
        holder.foodPrice.setText(food.getPrice() + " تومان ");
        holder.foodOrderNumber.setVisibility(View.INVISIBLE);
        holder.foodMinusButton.setVisibility(View.INVISIBLE);
        holder.foodImage.setImageBitmap(decodeSampledBitmapFromResource(mContext.getResources(), mContext.getResources().getIdentifier(food.getImage(),
                "drawable", mContext.getPackageName()), 50, 50));

        if (offerList.isVisibilityOrder()) {
            holder.foodMinusButton.setVisibility(View.VISIBLE);
            holder.foodOrderNumber.setText(offerList.getNumber() + "");
            holder.foodOrderNumber.setVisibility(View.VISIBLE);
        } else {
            holder.foodMinusButton.setVisibility(View.INVISIBLE);
        }
        handleClick(holder, position);
    }

    private void handleClick(final RecyclerViewMenuFragmentAdapter.SingleItemInMenuFragment holder, final int position) {
        holder.foodPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalOrderNumber += 1;
                OfferList offerList = offers.get(position);
                int orderNumber = offerList.getNumber();
                int newOrderNumber = orderNumber + 1;
                holder.foodOrderNumber.setText(newOrderNumber + "");
                holder.foodOrderNumber.setVisibility(View.VISIBLE);
                holder.foodMinusButton.setVisibility(View.VISIBLE);
                offerList.setNumber(newOrderNumber);
                offerList.setVisibilityOrder(true);
                onClickListener.onCounter(totalOrderNumber);
                SaveSpec(holder, position);
            }
        });
        holder.foodMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalOrderNumber -= 1;
                OfferList offerList = offers.get(position);
                int orderNumber = offerList.getNumber();
                int newOrderNUmber = orderNumber - 1;
                holder.foodOrderNumber.setText(newOrderNUmber + "");
                offerList.setNumber(newOrderNUmber);

                if (orderNumber > 1) {
                    holder.foodOrderNumber.setVisibility(View.VISIBLE);
                    offerList.setVisibilityOrder(true);
                    onClickListener.onCounter(totalOrderNumber);
                    SaveSpec(holder, position);
                }

                if (orderNumber == 1) {
                    holder.foodOrderNumber.setVisibility(View.INVISIBLE);
                    holder.foodMinusButton.setVisibility(View.INVISIBLE);
                    offerList.setVisibilityOrder(false);
                    SaveSpec(holder, position);
                    if (totalOrderNumber == 0)
                        onClickListener.onDismiss();
                    else
                        onClickListener.onCounter(totalOrderNumber);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != foodList ? foodList.size() : 0);
    }

    public void SaveSpec(RecyclerViewMenuFragmentAdapter.SingleItemInMenuFragment holder, int position) {
        FinalFood finalFood = finalFoodList.get(position);
        finalFood.setName(holder.foodName.getText().toString());
        finalFood.setPrice(holder.foodPrice.getText().toString());
        finalFood.setDetails(holder.foodDetails.getText().toString());
        finalFood.setOrdernumber(Integer.parseInt(holder.foodOrderNumber.getText().toString()));
        onClickListener.onSaveData(finalFood, position);
    }


    public interface OnClickListener {

        void onCounter(int number);

        void onDismiss();

        void onSaveData(FinalFood finalFood, int position);

    }

    public class SingleItemInMenuFragment extends RecyclerView.ViewHolder {

        TextView foodName;
        TextView foodPrice;
        Button foodPlusButton;
        Button foodMinusButton;
        TextView foodOrderNumber;
        ImageView foodImage;
        TextView foodDetails;

        SingleItemInMenuFragment(View itemView) {

            super(itemView);
            this.foodName = itemView.findViewById(R.id.foodName);
            this.foodImage = itemView.findViewById(R.id.imageFood);
            this.foodPrice = itemView.findViewById(R.id.foodPrice);
            this.foodDetails = itemView.findViewById(R.id.foodDetails);
            this.foodPlusButton = itemView.findViewById(R.id.plusbutton);
            this.foodMinusButton = itemView.findViewById(R.id.minusbutton);
            this.foodOrderNumber = itemView.findViewById(R.id.ordernumber);

        }
    }
}