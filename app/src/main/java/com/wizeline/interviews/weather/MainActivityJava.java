package com.wizeline.interviews.weather;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.wizeline.interviews.weather.locked.FakeWeatherClient;
import com.wizeline.interviews.weather.locked.WeatherClient;

public class MainActivityJava extends AppCompatActivity {

    private WeatherClient weatherClient;

    public MainActivityJava() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        weatherClient = new FakeWeatherClient();
        loadCities();
    }

    private void loadCities() {
        try {
            Log.d("MainActivityJava", weatherClient.getCities().toString());
        } catch (Exception e) {
            Log.e("MainActivityJava", "Error getting cities", e);
        }
    }
}
