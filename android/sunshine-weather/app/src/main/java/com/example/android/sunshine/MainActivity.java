/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

public class MainActivity
    extends AppCompatActivity
    implements ForecastAdapter.ForecastAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private ForecastAdapter mForecastAdapter;

    TextView mWeatherLoadingError;

    ProgressBar mWeatherLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_forecast);

        mWeatherLoadingError = (TextView) findViewById(R.id.tv_loading_error);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mForecastAdapter = new ForecastAdapter(this);

        mRecyclerView.setAdapter(mForecastAdapter);

        mWeatherLoadingBar = (ProgressBar) findViewById(R.id.pb_loading_weather);

        loadWeatherData();

    }

    private void loadWeatherData()
    {
        showWeatherDataView();
        String location = SunshinePreferences.getPreferredWeatherLocation(this);
        new FetchWeatherData().execute(location);
    }

    private void showWeatherDataView()
    {
        mRecyclerView.setVisibility(View.VISIBLE);
        mWeatherLoadingError.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage()
    {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mWeatherLoadingError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(String weatherForDay)
    {
        Toast.makeText(this, weatherForDay, Toast.LENGTH_SHORT).show();
    }

    public class FetchWeatherData extends AsyncTask<String, Void, String[]>
    {

        @Override
        protected void onPreExecute()
        {
            mWeatherLoadingBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params)
        {
            String location = params[0];
            String[] simpleJsonWeatherData = null;
            try {
                URL weatherRequestUrl = NetworkUtils.buildUrl(location);
                String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);
                simpleJsonWeatherData = OpenWeatherJsonUtils
                    .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return simpleJsonWeatherData;
        }

        @Override
        protected void onPostExecute(String[] weatherData)
        {
            mWeatherLoadingBar.setVisibility(View.INVISIBLE);

            if (weatherData != null)
            {
                showWeatherDataView();
                mForecastAdapter.setWeatherData(weatherData);
            }

            else
            {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_refresh:
                mForecastAdapter.setWeatherData(null);
                loadWeatherData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}