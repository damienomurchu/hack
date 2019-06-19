package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dmurphy on 02/08/17.
 */

public class ForecastAdapter
    extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>
{
  private String[] mWeatherData;

  final private ForecastAdapterOnClickHandler mClickHandler;

  interface ForecastAdapterOnClickHandler
  {
    public void onClick(String weatherForDay);
  }

  public ForecastAdapter(ForecastAdapterOnClickHandler mClickHandler)
  {

    this.mClickHandler = mClickHandler;
  }

  @Override
  public ForecastAdapter.ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
  {
    Context context = parent.getContext();
    int layoutIdForListItem = R.layout.forecast_list_item;
    LayoutInflater inflater = LayoutInflater.from(context);
    boolean shouldAttachToParentImmediately = false;

    View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
    return new ForecastAdapterViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ForecastAdapter.ForecastAdapterViewHolder holder, int position)
  {
    String weatherForThisDay = mWeatherData[position];
    holder.mWeatherTextView.setText(weatherForThisDay);
  }

  @Override
  public int getItemCount()
  {
    if (mWeatherData != null)
    {
      return mWeatherData.length;
    }

    return 0;
  }

  public void setWeatherData(String[] weatherData)
  {
    mWeatherData = weatherData;
    notifyDataSetChanged();
  }

  public class ForecastAdapterViewHolder
      extends RecyclerView.ViewHolder
      implements View.OnClickListener
  {
    public final TextView mWeatherTextView;

    public ForecastAdapterViewHolder(View itemView)
    {
      super(itemView);
      mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
      int adapterPosition = getAdapterPosition();
      String weatherForDay = mWeatherData[adapterPosition];
      mClickHandler.onClick(weatherForDay);
    }
  }
}
