package com.test.mohammaddvi.snappfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.mohammaddvi.snappfood.Model.Restaurant;
import com.test.mohammaddvi.snappfood.R;
import com.test.mohammaddvi.snappfood.RestaurantDetails;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> implements View.OnClickListener {

    private ArrayList<Restaurant> itemModels;
    private Context mContext;


    SectionListDataAdapter(ArrayList<Restaurant> itemModels, Context mContext) {
        this.itemModels = itemModels;
        this.mContext = mContext;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    @NonNull
    @Override
    public SectionListDataAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, null);
        v.setOnClickListener(this);
        return new SingleItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(SectionListDataAdapter.SingleItemRowHolder holder, int position) {
        Restaurant itemModel = itemModels.get(position);
        holder.restName.setText(itemModel.getName());
        holder.restPlace.setText(itemModel.getPlace());
        holder.restDeliverPrice.setText(itemModel.getDeliverPrice() + "تومان");
        holder.restFoodCat.setText(itemModel.getFoodCat());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        ////here is dynamically way for loading images from drawable
        //holder.restImage.setImageResource(mContext.getResources().getIdentifier(itemModel.getImageView(), "drawable", mContext.getPackageName()));
        holder.restImage.setImageBitmap(decodeSampledBitmapFromResource(mContext.getResources(),
                                                                        mContext.getResources().getIdentifier(itemModel.getImageView(),
                                                                                                                "drawable", mContext.getPackageName()),
                                                                                                                50, 50));
    }

    @Override
    public int getItemCount() {
        return (null != itemModels ? itemModels.size() : 0);
    }

    @Override
    public void onClick(View v) {
        mContext.startActivity(new Intent(mContext, RestaurantDetails.class));

    }

    class SingleItemRowHolder extends RecyclerView.ViewHolder {

        TextView restName;
        TextView restPlace;
        TextView restDeliverPrice;
        TextView restFoodCat;
        ImageView restImage;

        SingleItemRowHolder(View itemView) {
            super(itemView);
            this.restName = itemView.findViewById(R.id.restName);
            this.restPlace = itemView.findViewById(R.id.restPlace);
            this.restDeliverPrice = itemView.findViewById(R.id.restDeliverPrice);
            this.restFoodCat = itemView.findViewById(R.id.restFoodCat);
            this.restImage = itemView.findViewById(R.id.restImage);
        }
    }
}

