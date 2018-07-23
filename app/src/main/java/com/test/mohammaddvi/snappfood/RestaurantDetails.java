package com.test.mohammaddvi.snappfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.mohammaddvi.snappfood.Adapter.FragmentsAdapter;
import com.test.mohammaddvi.snappfood.Fragments.CommentFragment;
import com.test.mohammaddvi.snappfood.Fragments.InfoFragment;
import com.test.mohammaddvi.snappfood.Fragments.MenuFragment;
import com.test.mohammaddvi.snappfood.Model.FinalFood;

import java.util.ArrayList;

public class RestaurantDetails extends AppCompatActivity implements MenuFragment.MenuFragmentListener {
    private final static String TAG = RestaurantDetails.class.getName();
    FrameLayout frameLayout;
    private ViewPager mViewPager;
    private Snackbar snackbar;
    private android.support.v7.widget.Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private TextView totalnumber;
    private TabLayout tabLayout;
    private ArrayList<FinalFood> finalFoods1;
    private ArrayList<FinalFood> newArrayFoods;
    private sendDatatoMenuFragment sendDatatoMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        findViews();
//        if ("yes".equals(getIntent().getStringExtra("isbackpress"))){
//            newArrayFoods= (ArrayList<FinalFood>) getIntent().getSerializableExtra("newfoods");
//            Log.d(TAG,newArrayFoods.get(0).getName());
//            sendDatatoMenuFragment.sendToMenu(newArrayFoods);
//        }
//        else {
//            Log.d(TAG,"sorry there is problem in transfering data");
//        }
        setSnackBar(finalFoods1);
    }

    private void findViews() {
        mViewPager = findViewById(R.id.viewpager);
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        tabLayout = findViewById(R.id.tabs);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frmlayout);
        setupViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
        finalFoods1 = new ArrayList<>();
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());
        adapter.addFragment(new CommentFragment(), "نظرات");
        adapter.addFragment(new InfoFragment(), "اطلاعات");
        adapter.addFragment(new MenuFragment(), "منو");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2); // this code for save screen info for 2 pages except the screen page
        viewPager.setCurrentItem(2); // this code is for setting first view of view pager
    }

    private void setSnackBar(final ArrayList<FinalFood> finalFoodss) {
        LayoutInflater inflater = LayoutInflater.from(RestaurantDetails.this);
        View snackview = inflater.inflate(R.layout.snackview, null);
        snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_LONG);
        snackview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantDetails.this.startActivity(new Intent(RestaurantDetails.this, BuyBasket.class)
                        .putExtra("final", finalFoodss));
            }
        });
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.setPadding(0, 0, 0, 0);
        layout.addView(snackview, 0);
        totalnumber = snackbar.getView().findViewById(R.id.totalnumber);
    }

    @Override
    public void onMenuFragmentCount(int count) {
        totalnumber.setText(count + "");
        snackbar.show();
    }

    @Override
    public void oMenuFragmentDismiss() {
        snackbar.dismiss();
    }

    @Override
    public void onGetData(FinalFood finalFoods, int position) {

        if (finalFoods1.contains(finalFoods)) {
            finalFoods1.remove(finalFoods);
            if (finalFoods.getOrdernumber() != 0)
                finalFoods1.add(finalFoods);
        }
        if (!finalFoods1.contains(finalFoods)) {
            if (finalFoods.getOrdernumber() != 0)
                finalFoods1.add(finalFoods);
        }
    }
    public interface sendDatatoMenuFragment{
        void sendToMenu(ArrayList<FinalFood> newArrayFoods);
    }
}


