package com.test.mohammaddvi.snappfood.Fragments;


//fragment has created twice and v4 is better than that

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.mohammaddvi.snappfood.Adapter.RecyclerViewMenuFragmentAdapter;
import com.test.mohammaddvi.snappfood.Model.FinalFood;
import com.test.mohammaddvi.snappfood.Model.Food;
import com.test.mohammaddvi.snappfood.Model.OfferList;
import com.test.mohammaddvi.snappfood.R;
import com.test.mohammaddvi.snappfood.RestaurantDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MenuFragment extends Fragment implements RecyclerViewMenuFragmentAdapter.OnClickListener {
    private final String TAG = MenuFragment.class.getName();
    ArrayList<Food> allfoods = new ArrayList<>();
    ArrayList<OfferList> offerLists = new ArrayList<>();
    ArrayList<FinalFood> finalFoods = new ArrayList<>();
    ArrayList<FinalFood> sendbackfoodsmenu = new ArrayList<>();
    RecyclerView recyclerview;
    RecyclerViewMenuFragmentAdapter.OnClickListener listener;
    MenuFragmentListener fragmentListener;
    RecyclerViewMenuFragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menufragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String jsonFilePath = "foods.json";
        recyclerview = getActivity().findViewById(R.id.lstitems);
        if (listener == null) {
            Log.d(TAG, "listener is null");
            listener = this;
        }
        adapter = new RecyclerViewMenuFragmentAdapter(allfoods, getContext(), offerLists, finalFoods, listener);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerview.setHasFixedSize(true);
        parsJson(jsonFilePath);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void onCounter(int number) {
        if (fragmentListener != null) {
            fragmentListener.onMenuFragmentCount(number);
        }
    }

    @Override
    public void onDismiss() {
        if (fragmentListener != null) {
            fragmentListener.oMenuFragmentDismiss();
        }
    }

    @Override
    public void onSaveData(FinalFood finalFood, int position) {
        if (fragmentListener != null) {
            fragmentListener.onGetData(finalFood, position);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MenuFragment.MenuFragmentListener) {
            fragmentListener = (MenuFragment.MenuFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    //this method is for read a local json and return a string
    public String readLocalJson(String jsonFile) {
        String json;
        try {
            InputStream is = getActivity().getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public void parsJson(String jsonFilePath) {
        try {
            JSONObject obj = new JSONObject(readLocalJson(jsonFilePath));
            JSONArray jsonArray = obj.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String image = jsonObject.getString("image");
                JSONArray jsonArrayFoot = jsonObject.getJSONArray("foots");
                for (int j = 0; j < jsonArrayFoot.length(); j++) {
                    JSONObject jsonObjectFoot = jsonArrayFoot.getJSONObject(j);
                    String foodName = jsonObjectFoot.getString("name");
                    String fooddetails = jsonObjectFoot.getString("fooddetails");
                    String price = jsonObjectFoot.getString("price");
                    allfoods.add(new Food(foodName, price, fooddetails, image));
                    offerLists.add(new OfferList(0, false));
                    finalFoods.add(new FinalFood(foodName, price, fooddetails, 0));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public interface MenuFragmentListener {
        void onMenuFragmentCount(int count);

        void oMenuFragmentDismiss();

        void onGetData(FinalFood finalFoods, int position);
    }

}