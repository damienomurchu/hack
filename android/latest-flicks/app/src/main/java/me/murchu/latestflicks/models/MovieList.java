package me.murchu.latestflicks.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 */
public class MovieList
{
  private ArrayList<Movie> movies;

  /**
   * initialises a MovieList from an existing arraylist of movies
   *
   * @param movies arraylist of movies to initialise MovieList with
   */
  public MovieList(ArrayList<Movie> movies)
  {
    this.movies = movies;
  }

  /**
   * initialises a new MovieList with an empty arraylist of movies
   */
  public MovieList()
  {
    this.movies = new ArrayList<Movie>();
  }

  /**
   * adds a movie to the movie list
   */
  public void addMovie(Movie movie)
  {
    this.movies.add(movie);
  }

  /**
   * returns movie at specified index in list
   *
   * @param index the int position of the movie item in the list
   * @return the movie at that index position
   */
  public Movie getMovie(int index)
  {
    return movies.get(index);
  }

  /**
   * returns size of movie list
   */
  public int getSize()
  {
    return movies.size();
  }

  /**
   * Sorts the movie list by popularity
   */
  public void sortByPopularity()
  {
    Collections.sort(movies, new Comparator<Movie>() {
      @Override
      public int compare(Movie movie1, Movie movie2)
      {
        Float m1 = Float.valueOf(movie1.popularity);
        Float m2 = Float.valueOf(movie2.popularity);
        return m2.compareTo(m1);
      }
    });
  }

  /**
   * Sorts the movie list by rating
   */
  public void sortByRating()
  {
    Collections.sort(movies, new Comparator<Movie>() {
      @Override
      public int compare(Movie movie1, Movie movie2)
      {
        Float m1 = Float.valueOf(movie1.vote);
        Float m2 = Float.valueOf(movie2.vote);
        return m2.compareTo(m1);
      }
    });
  }

}
