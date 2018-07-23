package com.test.mohammaddvi.snappfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.mohammaddvi.snappfood.R;

/**
 * Created by Asus on 16/07/2018.
 */

public class NavigationDrawerAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private String[] titles;
    private int[] images;

    public NavigationDrawerAdapter(LayoutInflater layoutInflater, String[] titles, int[] images) {
        this.layoutInflater = layoutInflater;
        this.titles = titles;
        this.images = images;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.navigation_drawer_layout, null);
        } else {
            view = convertView;
        }
        TextView textView = view.findViewById(R.id.list_text);
        ImageView imageView = view.findViewById(R.id.list_image);
        textView.setText(titles[position]);
        imageView.setImageResource(images[position]);
        return view;
    }
}
