package me.murchu.latestflicks;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity
{
  private Toolbar toolbar;
  private ImageView movieBackdrop;
  private TextView movieName;
  private TextView movieRating;
  private TextView movieOverview;
  private TextView movieDate;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_details);

    toolbar = (Toolbar) findViewById(R.id.tb_actionbar);
    movieBackdrop = findViewById(R.id.iv_movie_backdrop);
    movieName = findViewById(R.id.tv_movie_details_name);
    movieRating = findViewById(R.id.tv_movie_details_rating);
    movieDate = findViewById(R.id.tv_movie_details_date);
    movieOverview = findViewById(R.id.tv_movie_details_overview);


    Intent intentThatStartedThisActivity = getIntent();
    if (intentThatStartedThisActivity.hasExtra("MOVIE_NAME"))
    {
      String name = intentThatStartedThisActivity.getStringExtra("MOVIE_NAME");
      String overview = intentThatStartedThisActivity.getStringExtra("MOVIE_OVERVIEW");
      String date = intentThatStartedThisActivity.getStringExtra("MOVIE_DATE");
      String backdrop = intentThatStartedThisActivity.getStringExtra("MOVIE_BACKDROP");
      String rating = intentThatStartedThisActivity.getStringExtra("MOVIE_RATING");

      String imageUrl = "http://image.tmdb.org/t/p/w342/" + backdrop;
      Picasso.with(MovieDetails.this).load(imageUrl).into(movieBackdrop);

      toolbar.setTitle(name);
      movieName.setText(name);
      String movieScore = getApplicationContext().getString(R.string.rating_format, rating);
      movieRating.setText(movieScore);
      String movieRelease = getApplicationContext().getString(R.string.date_format, date);
      movieDate.setText(movieRelease);
      movieOverview.setText(overview);
    }

    toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

  }
}
