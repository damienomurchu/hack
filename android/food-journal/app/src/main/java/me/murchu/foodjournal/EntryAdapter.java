package me.murchu.foodjournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

  private List<EntryData> entryList;
  EntryItemClickListener listener;

  public EntryAdapter(List<EntryData> entryList, EntryItemClickListener listener) {
    this.entryList = entryList;
    this.listener = listener;
  }

  @NonNull
  @Override
  public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View entryView = LayoutInflater.
        from(parent.getContext()).
        inflate(R.layout.entry_layout, parent, false);
    final EntryViewHolder entryViewHolder = new EntryViewHolder(entryView);
    entryView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        listener.onItemClick(v, entryViewHolder.getAdapterPosition());
      }
    });
    return entryViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull EntryViewHolder entryViewHolder, int position) {
    EntryData ed = entryList.get(position);
    entryViewHolder.timeDate.setText(ed.timeDate);
    entryViewHolder.hungerLevel.setText(ed.hungerLevel);
    entryViewHolder.stressLevel.setText(ed.stressLevel);
    entryViewHolder.foodItems.setText(ed.foodItems);
    entryViewHolder.immediateSatisfaction.setText(ed.immediateSatisfaction);
    entryViewHolder.actualSatisfaction.setText(ed.actualSatisfaction);

  }

  @Override
  public int getItemCount() {
    return entryList.size();
  }

  class EntryViewHolder extends RecyclerView.ViewHolder {

    TextView timeDate;
    TextView hungerLevel;
    TextView foodItems;
    TextView stressLevel;
    TextView immediateSatisfaction;
    TextView actualSatisfaction;

    public EntryViewHolder(View v) {
      super(v);
      timeDate = v.findViewById(R.id.timeDate);
      hungerLevel = v.findViewById(R.id.hungerLevel);
      stressLevel = v.findViewById(R.id.stressLevel);
      foodItems = v.findViewById(R.id.foodItems);
      immediateSatisfaction = v.findViewById(R.id.immediateSatisfaction);
      actualSatisfaction = v.findViewById(R.id.actualSatisfaction);

    }
  }

  interface EntryItemClickListener {
    void onItemClick(View v, int position);
  }
}
