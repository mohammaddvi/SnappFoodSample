package com.test.mohammaddvi.snappfood.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.mohammaddvi.snappfood.Adapter.RecyclerViewCommentAdapter;
import com.test.mohammaddvi.snappfood.Model.Comment;
import com.test.mohammaddvi.snappfood.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

//fragment has created twice and v4 is better than that


public class CommentFragment extends Fragment {
    private static final String TAG = "commentfragment";
    RecyclerView recyclerviewcomment;
    ArrayList<Comment> comments = new ArrayList<>();
    String foodname[] = new String[]{"a", "b", "c"};
    private ProgressBar progressbarhappy, progressbarsad, progressbarpokerface;
    private TextView percentagehappy, percentagesad, percentagepokerface;
    private int totalNumber = 0;
    private int sadNumber, happyNumber, pokerfaceNumber=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.commentsfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerviewcomment = getActivity().findViewById(R.id.recyclerviewcomment);
        progressbarhappy = getActivity().findViewById(R.id.happyProgress);
        progressbarsad = getActivity().findViewById(R.id.sadProgress);
        percentagehappy = getActivity().findViewById(R.id.percentagehappy);
        percentagesad = getActivity().findViewById(R.id.percentagesad);
        percentagepokerface = getActivity().findViewById(R.id.percentagepokerface);
        progressbarpokerface = getActivity().findViewById(R.id.pokerfaceProgress);
        RecyclerViewCommentAdapter adapter = new RecyclerViewCommentAdapter(comments, getContext());
        recyclerviewcomment.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerviewcomment.setHasFixedSize(true);
        String jsonFilePath = "hugecomments.json";
        parsJson(jsonFilePath);
        recyclerviewcomment.setAdapter(adapter);
        calculateFeelings(happyNumber,sadNumber,pokerfaceNumber,totalNumber);
    }


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
                String username = jsonObject.getString("name");
                String feeling = jsonObject.getString("feeling");
                String comment = jsonObject.getString("comment");
                String date = jsonObject.getString("date");
                JSONArray jsonArrayFoot = jsonObject.getJSONArray("foots");
                for (int j = 0; j < jsonArrayFoot.length(); j++) {
                    JSONObject jsonObjectFoot = jsonArrayFoot.getJSONObject(j);
                    foodname[j] = jsonObjectFoot.getString("name");
                }
                comments.add(new Comment(username, date, comment, feeling, foodname));
                if ("happy".equals(feeling)){
                    happyNumber++;
                }
                if ("sad".equals(feeling)){
                    sadNumber++;
                }
                if ("pokerface".equals(feeling)){
                    pokerfaceNumber++;
                }
            }
            totalNumber = jsonArray.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void calculateFeelings(int happyNumber,int sadNumber,int pokerfaceNumber,int totalNumber){
        float resulthappy= (happyNumber*100)/totalNumber;
        float resultsad = (sadNumber*100)/totalNumber;
        float resultpokerface=(pokerfaceNumber*100)/totalNumber;
        progressbarhappy.setProgress(happyNumber);
        progressbarsad.setProgress(sadNumber);
        progressbarpokerface.setProgress(pokerfaceNumber);
        percentagehappy.setText("%"+resulthappy);
        percentagesad.setText("%"+resultsad);
        percentagepokerface.setText("%"+resultpokerface);
    }
}
