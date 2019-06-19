package me.murchu.latestflicks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import me.murchu.latestflicks.models.Movie;
import me.murchu.latestflicks.models.MovieList;

/**
 * {@link MoviesAdapter} exposes a list of movie details
 * {@link android.support.v7.widget.RecyclerView}
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>
{
  private MovieList mMovieData;
  private Context context;

  private final MovieAdapterOnClickHandler mClickHandler;

  public interface MovieAdapterOnClickHandler {
    void onClick(Movie movie);
  }

  public MoviesAdapter(Context context, MovieAdapterOnClickHandler clickHandler)
  {
    this.context = context;
    mClickHandler = clickHandler;
  }

  public void sortByPopularity()
  {
    mMovieData.sortByPopularity();
    notifyDataSetChanged();
  }

  public void sortByRating()
  {
    mMovieData.sortByRating();
    notifyDataSetChanged();
  }

  /**
   * Cache of the children views for a forecast list item.
   */
  public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener
  {
    public final ImageView mMoviePoster;

    public MoviesAdapterViewHolder(View view)
    {
      super(view);

      mMoviePoster = view.findViewById(R.id.iv_movie_poster);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
      int adapterPosition = getAdapterPosition();
      Movie movie = mMovieData.getMovie(adapterPosition);
      mClickHandler.onClick(movie);
    }
  }

  @Override
  public MoviesAdapter.MoviesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
  {
    Context context = viewGroup.getContext();
    int layoutIdForListItem = R.layout.movies_list_item;
    LayoutInflater inflater = LayoutInflater.from(context);
    boolean shouldAttachToParentImmediately = false;

    View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
    return new MoviesAdapterViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MoviesAdapterViewHolder moviesAdapterViewHolder, int position)
  {
    Movie movie = mMovieData.getMovie(position);

    String imageUrl = "http://image.tmdb.org/t/p/w342/" + movie.getPoster();
    Picasso.with(context).load(imageUrl).into(moviesAdapterViewHolder.mMoviePoster);
  }

  @Override
  public int getItemCount()
  {
    if (null == mMovieData) return 0;
    return mMovieData.getSize();
  }

  public void setMovieData(MovieList movieData) {
    mMovieData = movieData;
    notifyDataSetChanged();
  }

}

