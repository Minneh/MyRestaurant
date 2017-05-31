package com.example.minnehmugo.myrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantsActivity extends AppCompatActivity {
    public static final String TAG = RestaurantsActivity.class.getSimpleName();

    //    @Bind(R.id.locationTextView) TextView mLocationTextView;
    //    @Bind(R.id.listView) ListView mListView;

    TextView mLocationTextView;
    ListView mListView;
    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();


//    private String[] restaurants = new String[]{"Sweet Hereafter", "Cricket", "Hawthorne Fish House", "Viking Soul Food",
//            "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell", "Me Kha Noodle Bar",
//            "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche", "Kay's Bar", "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole"};
//    private String[] cuisines = new String[]{"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian", "Coffee", "English Food",
//            "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar",
//            "Breakfast", "Mexican"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);


        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
        mListView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mLocationTextView.setText("Here are all the restaurants near: " + location);

        getRestaurants(location);
    }

    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();
        yelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRestaurants = yelpService.processResults(response);

                RestaurantsActivity.this.runOnUiThread(() -> {

                        String[] restaurantNames = new String[mRestaurants.size()];
                    for (int i = 0; i < restaurantNames.length; i++) {
                        restaurantNames[i] = mRestaurants.get(i).getName();
                    }

                    ArrayAdapter adapter = new ArrayAdapter(RestaurantsActivity.this,
                            android.R.layout.simple_list_item_1, restaurantNames);
                    mListView.setAdapter(adapter);

                    for (Restaurant restaurant : mRestaurants) {
                        Log.d(TAG, "Name: " + restaurant.getName());
                        Log.d(TAG, "Phone: " + restaurant.getPhone());
                        Log.d(TAG, "Website: " + restaurant.getWebsite());
                        Log.d(TAG, "Image url: " + restaurant.getImageUrl());
                        Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
                        Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", restaurant.getAddress()));
                        Log.d(TAG, "Categories: " + restaurant.getCategories().toString());
                    }
                });
            }
        });
    }
}