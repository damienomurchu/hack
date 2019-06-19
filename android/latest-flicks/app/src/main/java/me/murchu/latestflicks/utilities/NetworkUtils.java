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
package me.murchu.latestflicks.utilities;

import android.net.Uri;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import me.murchu.latestflicks.BuildConfig;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

  final static String MOVIEDB_BASE_URL =
      "https://api.themoviedb.org/3/movie/now_playing";

  final static String PARAM_KEY = "api_key";

  /*
   * The sort field. One of stars, forks, or updated.
   * Default: results are sorted by best match if no field is specified.
   */
  final static String PARAM_LANG = "language";

  static String API_KEY;

  /**
   * Builds the URL used to query the movie db.
   *
   * @return The URL to use to query the movie server.
   */
  public static URL buildUrl() throws MalformedURLException, JSONException
  {
    API_KEY = BuildConfig.THE_MOVIE_DB_API_KEY;
    Uri uri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
        .appendQueryParameter(PARAM_KEY, API_KEY)
        .appendQueryParameter(PARAM_LANG, "en-US")
        .build();

    URL url = null;
    try {
      url = new URL(uri.toString());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return url;
  }

  /**
   * This method returns the entire result from the HTTP response.
   *
   * @param url The URL to fetch the HTTP response from.
   * @return The contents of the HTTP response.

   * @throws IOException Related to network and stream reading
   */
  public static String getResponseFromHttpUrl(URL url) throws IOException {
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    try {
      InputStream in = urlConnection.getInputStream();

      Scanner scanner = new Scanner(in);
      scanner.useDelimiter("\\A");

      boolean hasInput = scanner.hasNext();
      if (hasInput) {
        return scanner.next();
      } else {
        return null;
      }
    } finally {
      urlConnection.disconnect();
    }
  }
}