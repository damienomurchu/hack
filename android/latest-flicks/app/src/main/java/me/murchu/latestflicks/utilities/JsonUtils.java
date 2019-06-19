package me.murchu.latestflicks.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.murchu.latestflicks.models.Movie;
import me.murchu.latestflicks.models.MovieList;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class JsonUtils {

  /**
   * This method parses JSON from a web response and returns an array of Strings
   * describing the weather over various days from the forecast.
   * <p/>
   * Later on, we'll be parsing the JSON into structured data within the
   * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
   * now, we just convert the JSON into human-readable strings.
   *
   * @param moviesJsonStr JSON response from server
   *
   * @return Array of Strings describing movie data
   *
   * @throws JSONException If JSON data cannot be properly parsed
   */
  @SuppressLint("LongLogTag")
  public static MovieList getMovieStringsFromJson(Context context, String moviesJsonStr)
      throws JSONException {

    /* Movie information. Each day's forecast info is an element of the "list" array */
    final String MOVIE_SUCCESS = "success";
    final String MOVIE_STATUSCODE = "status_code";
    final String MOVIE_STATUSMESSAGE = "status_message";
    final String MOVIE_RESULTS = "results";
    final String MOVIE_POSTER = "poster_path";
    final String MOVIE_OVERVIEW = "overview";
    final String MOVIE_RELEASE = "release_date";
    final String MOVIE_TITLE = "title";
    final String MOVIE_BACKDROP = "backdrop_path";
    final String MOVIE_POPULARITY = "popularity";
    final String MOVIE_RATING = "vote_average";


    /* MovieList to hold each day's movie String */
    MovieList movies;

    JSONObject movieJson = new JSONObject(moviesJsonStr);

    /* Is there an error? */
    if (movieJson.has(MOVIE_SUCCESS)) {
      int errorCode = movieJson.getInt(MOVIE_STATUSCODE);
      String errorMessage = movieJson.getString(MOVIE_STATUSMESSAGE);
      Log.d("API Call: status code", String.valueOf(errorCode));
      Log.d("API Call: status message", errorMessage);
    }

    JSONArray movieArray = movieJson.getJSONArray(MOVIE_RESULTS);

    movies = new MovieList();

    for (int i = 0; i < movieArray.length(); i++) {

      /* Get the JSON object representing the movie */
      JSONObject movieDetail = movieArray.getJSONObject(i);

      Movie movie = new Movie(
          movieDetail.getString(MOVIE_TITLE),
          movieDetail.getString(MOVIE_RELEASE),
          movieDetail.getString(MOVIE_OVERVIEW),
          movieDetail.getString(MOVIE_POPULARITY),
          movieDetail.getString(MOVIE_RATING),
          movieDetail.getString(MOVIE_POSTER),
          movieDetail.getString(MOVIE_BACKDROP)
      );

      movies.addMovie(movie);
    }

    return movies;
  }

}