package me.murchu.foodjournal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FoodJournalActivity extends AppCompatActivity {

  private List<EntryData> entries;
  private RecyclerView entryList;
  private EntryAdapter entryAdapter;
  FloatingActionButton fab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_journal);
    entryList = (RecyclerView) findViewById(R.id.entryList);

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    entryList.setHasFixedSize(true);
    entryList.setLayoutManager(mLayoutManager);
    entryList.setItemAnimator(new DefaultItemAnimator());

    fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //Toast.makeText(FoodJournalActivity.this, "Add Entry", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FoodJournalActivity.this, AddEntryActivity.class));
      }
    });

    initialiseSampleData();

    initialiseAdapter();

  }

  private void initialiseSampleData() {

    entries = new ArrayList<EntryData>();

    for (int i = 0; i < 30; i++) {
      EntryData newEntry = new EntryData();
      newEntry.setTimeDate("HH:MM");
      newEntry.setHungerLevel("x/x");
      newEntry.setStressLevel("x/x");
      newEntry.setFoodItems("foodItems-" + (i + 1));
      newEntry.setImmediateSatisfaction("x/x");
      newEntry.setActualSatisfaction("x/x");
      entries.add(newEntry);
    }

  }

  private void initialiseAdapter() {

    entryAdapter = new EntryAdapter(entries, new EntryAdapter.EntryItemClickListener() {
      @Override
      public void onItemClick(View v, int position) {
        //Toast.makeText(FoodJournalActivity.this, "Clicked Item: " + position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FoodJournalActivity.this, EditEntryActivity.class));
      }
    });

    entryList.setAdapter(entryAdapter);
  }
}
