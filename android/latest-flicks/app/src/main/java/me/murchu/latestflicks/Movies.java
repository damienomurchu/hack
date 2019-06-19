package me.murchu.latestflicks;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

import me.murchu.latestflicks.MoviesAdapter.MovieAdapterOnClickHandler;
import me.murchu.latestflicks.models.Movie;
import me.murchu.latestflicks.models.MovieList;
import me.murchu.latestflicks.utilities.JsonUtils;
import me.murchu.latestflicks.utilities.NetworkUtils;


public class Movies extends AppCompatActivity
  implements MovieAdapterOnClickHandler
{
  private Toolbar toolbar;

  private RecyclerView mRecyclerView;
  private MoviesAdapter mMoviesAdapter;

  private TextView mErrorMessageDisplay;

  private ProgressBar mLoadingIndicator;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movies);

    toolbar = findViewById(R.id.tb_movies_toolbar);
    /*
     * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
     * do things like set the adapter of the RecyclerView and toggle the visibility.
     */
    mRecyclerView = findViewById(R.id.recyclerview_movies);

    /* This TextView is used to display errors and will be hidden if there are no errors */
    mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);

    /*
     * LinearLayoutManager can support HORIZONTAL or VERTICAL orientations. The reverse layout
     * parameter is useful mostly for HORIZONTAL layouts that should reverse for right to left
     * languages.
     */
    LinearLayoutManager layoutManager
        = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

    mRecyclerView.setLayoutManager(layoutManager);

    /*
     * Use this setting to improve performance if you know that changes in content do not
     * change the child layout size in the RecyclerView
     */
    mRecyclerView.setHasFixedSize(true);

    /*
     * The ForecastAdapter is responsible for linking our movie data with the Views that
     * will end up displaying our movie data.
     */
    mMoviesAdapter = new MoviesAdapter(this, this);

    /* Setting the adapter attaches it to the RecyclerView in our layout. */
    mRecyclerView.setAdapter(mMoviesAdapter);

    /*
     * The ProgressBar that will indicate to the user that we are loading data. It will be
     * hidden when no data is loading.
     *
     * Please note: This so called "ProgressBar" isn't a bar by default. It is more of a
     * circle. We didn't make the rules (or the names of Views), we just follow them.
     */
    mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

    /* Once all of our views are setup, we can load the movie data. */

    if (isOnline())
    {
      try {
        loadMovieData();
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
    else
    {
      Toast.makeText(getApplicationContext(), "Currently offline/ not connected yet", Toast.LENGTH_SHORT).show();
    }

    toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
    setSupportActionBar(toolbar);
  }


  public boolean isOnline()
  {
    ConnectivityManager cm =
        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    return netInfo != null && netInfo.isConnectedOrConnecting();
  }

  /**
   * This method will get the user's preferred location for movie, and then tell some
   * background method to get the movie data in the background.
   */
  private void loadMovieData() throws MalformedURLException
  {
    new MoviesQueryTask().execute("damienomurchu");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_movies, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.sort_popularity:
        mMoviesAdapter.sortByPopularity();
        Toast.makeText(this, "Sort by Popularity selected", Toast.LENGTH_SHORT).show();
        break;
      case R.id.sort_rating:
        mMoviesAdapter.sortByRating();
        Toast.makeText(this, "Sort by Rating selected", Toast.LENGTH_SHORT).show();
        break;
    }
    return true;
  }

  @Override
  public void onClick(Movie movie) {
    Context context = Movies.this;
    Class destinationActivity = MovieDetails.class;
    Intent startMovieDetailsActivity = new Intent(context, destinationActivity);

    startMovieDetailsActivity.putExtra("MOVIE_NAME", movie.getTitle());
    startMovieDetailsActivity.putExtra("MOVIE_OVERVIEW", movie.getOverview());
    startMovieDetailsActivity.putExtra("MOVIE_DATE", movie.getReleased());
    startMovieDetailsActivity.putExtra("MOVIE_BACKDROP", movie.getBackdrop());
    startMovieDetailsActivity.putExtra("MOVIE_RATING", movie.getVote());

    startActivity(startMovieDetailsActivity);
  }

  /**
   * This method will make the View for the movie data visible and
   * hide the error message.
   * <p>
   * Since it is okay to redundantly set the visibility of a View, we don't
   * need to check whether each view is currently visible or invisible.
   */
  private void showMovieDataView()
  {
    mErrorMessageDisplay.setVisibility(View.GONE);
    mRecyclerView.setVisibility(View.VISIBLE);
  }

  /**
   * This method will make the error message visible and hide the movie
   * View.
   * <p>
   * Since it is okay to redundantly set the visibility of a View, we don't
   * need to check whether each view is currently visible or invisible.
   */
  private void showErrorMessage()
  {
    mRecyclerView.setVisibility(View.GONE);
    mErrorMessageDisplay.setVisibility(View.VISIBLE);
  }


  public class MoviesQueryTask extends AsyncTask<String, Void, MovieList>
  {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    protected MovieList doInBackground(String... params)
    {
      /* If there's no zip code, there's nothing to look up. */
      if (params.length == 0) {
        return null;
      }

      URL movieRequestUrl = null;
      try {
        movieRequestUrl = NetworkUtils.buildUrl();
      } catch (Exception e) {
        e.printStackTrace();
      }

      try {
        String jsonMovieResponse = NetworkUtils
            .getResponseFromHttpUrl(movieRequestUrl);

        MovieList movies = JsonUtils.getMovieStringsFromJson(Movies.this, jsonMovieResponse);

        /*String[] simpleJsonMovieData = JsonUtils
            .getMovieStringsFromJson(Movies.this, jsonMovieResponse);*/

        return movies;
        //return simpleJsonMovieData;

      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }

    }

    @Override
    protected void onPostExecute(MovieList movieData)
    {
      mLoadingIndicator.setVisibility(View.GONE);
      if (movieData != null) {
        mMoviesAdapter.setMovieData(movieData);
        showMovieDataView();
        Toast.makeText(getApplicationContext(), "retrieved movies from network", Toast.LENGTH_SHORT).show();
      } else {
        showErrorMessage();
        Toast.makeText(getApplicationContext(), "network call failed!", Toast.LENGTH_SHORT).show();
      }
    }

  }
}
