package me.murchu.latestflicks.models;

/**
 * A Movie model class to contain the details of a Movie object
 */
public class Movie
{
  String title;
  String released;
  String overview;
  String popularity;
  String vote;
  String poster;
  String backdrop;

  public Movie(String title, String released, String overview, String popularity, String vote, String poster, String backdrop)
  {
    this.title = title;
    this.released = released;
    this.overview = overview;
    this.popularity = popularity;
    this.vote = vote;
    this.poster = poster;
    this.backdrop = backdrop;
  }

  public String getTitle()
  {
    return title;
  }

  public String getReleased()
  {
    return released;
  }

  public String getOverview()
  {
    return overview;
  }

  public String getPopularity()
  {
    return popularity;
  }

  public String getVote()
  {
    return vote;
  }

  public String getPoster()
  {
    return poster;
  }

  public String getBackdrop()
  {
    return backdrop;
  }
}
