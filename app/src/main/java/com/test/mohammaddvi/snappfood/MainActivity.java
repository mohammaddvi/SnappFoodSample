package com.test.mohammaddvi.snappfood;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.test.mohammaddvi.snappfood.Adapter.NavigationDrawerAdapter;
import com.test.mohammaddvi.snappfood.Adapter.RecyclerViewDataAdapter;
import com.test.mohammaddvi.snappfood.Model.Restaurant;
import com.test.mohammaddvi.snappfood.Model.SectionRestsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    String[] titles;
    int[] images;
    ProgressBar progressBar;
    private ArrayList<SectionRestsModel> allSampleData;
    private DrawerLayout drawerLayout;
    private ImageView imageView;
    private LayoutInflater layoutInflater;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        createDummyData();
    }

    public void bindViews() {
        allSampleData = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        drawerLayout = findViewById(R.id.drawerlayout);
        listView = findViewById(R.id.listView);
        layoutInflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) layoutInflater.inflate(R.layout.layout_header_navigation, listView, false);
        listView.addHeaderView(header, null, false);
        images = new int[]{R.drawable.key, R.drawable.creditcard, R.drawable.orderlist, R.drawable.favirotes, R.drawable.support};
        titles = new String[]{"ورود به حساب کاربری", "افزودن اعتبار", "لیست سفارش", "مورد علاقه ها", "پشتیبانی"};
        NavigationDrawerAdapter navigationDrawerAdapter = new NavigationDrawerAdapter(layoutInflater, titles, images);
        listView.setAdapter(navigationDrawerAdapter);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        listView.setOnItemClickListener(new DrawerItemClickListener());
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.hamburger);
        RecyclerView recyclerView = findViewById(R.id.myrecyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(allSampleData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    public void createDummyData() {
        String jsonFilePath;
        jsonFilePath = "offres.json";
        SectionRestsModel srm = new SectionRestsModel();
        SectionRestsModel srm1 = new SectionRestsModel();
        SectionRestsModel srm2 = new SectionRestsModel();
        SectionRestsModel srm3 = new SectionRestsModel();
        srm.setHeaderTitle("رستوران های دارای تخفیف");
        srm1.setHeaderTitle("رستوران های برتر");
        srm2.setHeaderTitle("رستوران های نزدیک");
        srm3.setHeaderTitle("رستوران های محبوب");
        parsJson(srm, jsonFilePath);
        parsJson(srm1, jsonFilePath);
        parsJson(srm2, jsonFilePath);
        parsJson(srm3, jsonFilePath);

    }

    public String readLocalJson(String jsonFile) {
        String json;
        try {
            InputStream is = getAssets().open(jsonFile);
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

    public void parsJson(final SectionRestsModel srm, String jsonFilePath) {
        try {
            ArrayList<Restaurant> Restaurants = new ArrayList<>();
            JSONObject obj = new JSONObject(readLocalJson(jsonFilePath));
            JSONArray jsonArray = obj.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String place = jsonObject.getString("place");
                String foodcat = jsonObject.getString("foodcat");
                String image = jsonObject.getString("image");
                String deliverPrice = jsonObject.getString("deliverprice");
                Restaurants.add(new Restaurant(name, place, foodcat, deliverPrice, image));
            }
            srm.setSomeRestInSection(Restaurants);
            allSampleData.add(srm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_hamburger) {
            drawerLayout.openDrawer(GravityCompat.END);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectitem(position);

        }

        private void selectitem(int position) {
            switch (position) {
                case 1:
                    drawerLayout.closeDrawers();
                    break;

                case 2:
                    drawerLayout.closeDrawers();
                    break;

                case 3:
                    drawerLayout.closeDrawers();
                    break;

                case 4:
                    drawerLayout.closeDrawers();
                    break;

                case 5:
                    drawerLayout.closeDrawers();
                    break;
            }
        }
    }
}
