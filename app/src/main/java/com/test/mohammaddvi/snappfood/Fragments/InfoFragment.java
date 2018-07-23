package com.test.mohammaddvi.snappfood.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.mohammaddvi.snappfood.R;

//fragment has created twice and v4 is better than that

//////////////////////////////////////////////////////
public class InfoFragment extends Fragment {
    private static final String TAG = "infofragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infofragment, container, false);
        return view;
    }
}
