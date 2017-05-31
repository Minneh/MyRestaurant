package com.example.minnehmugo.myrestaurant;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//        @BindView(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
//        @BindView(R.id.locationEditText) EditText mLocationEditText;
//        @BindView(R.id.appNameTextView) TextView mAppNameTextView;

        Button mFindRestaurantsButton;
        EditText mLocationEditText;
        TextView mAppNameTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
//            ButterKnife.bind(this);

            mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
            mFindRestaurantsButton = (Button) findViewById(R.id.findRestaurantsButton);
            mLocationEditText = (EditText) findViewById(R.id.locationEditText);

            Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
            mAppNameTextView.setTypeface(ostrichFont);

            mFindRestaurantsButton.setOnClickListener(this);
        }
            @Override
            public void onClick(View v) {
                if (v == mFindRestaurantsButton) {
                    String location = mLocationEditText.getText().toString();
                    Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                }
            }


        }

