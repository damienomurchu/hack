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
package com.example.android.reposearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.reposearch.utilities.NetworkUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText mSearchEditText;

    TextView mUrlDisplayTextView;

    TextView mSearchResultsTextView;

    TextView mErrorMessage;

    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchEditText = (EditText) findViewById(R.id.et_search_box);
        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);
        mErrorMessage = (TextView) findViewById(R.id.tv_error_message_display);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

    }

    private void makeGithubSearchQuery() throws IOException
    {
        String search = mSearchEditText.getText().toString();
        URL url = NetworkUtils.buildUrl(search);
        mUrlDisplayTextView.setText(url.toString());
        new GithubQueryTask().execute(url);
    }

    private void showJsonDataView()
    {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage()
    {
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String>
    {

        @Override
        protected void onPreExecute()
        {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls)
        {
            URL url = urls[0];

            String s = null;
            try {
                s = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return s;
        }

        @Override
        protected void onPostExecute(String s)
        {
            mProgressBar.setVisibility(View.INVISIBLE);

            if (s != null && !s.equals(""))
            {
                showJsonDataView();
                mSearchResultsTextView.setText(s);
            }
            else if (s == null)
            {
                showErrorMessage();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_search:
                try {
                    makeGithubSearchQuery();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mSearchEditText.setText("");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}