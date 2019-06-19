package com.murchu.flashchatnewfirebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ChatListAdapter extends BaseAdapter {

  private Activity mActivity;
  private DatabaseReference mDatabaseReference;
  private String mDisplayName;
  private ArrayList<DataSnapshot> mDataSnapshots;

  public ChatListAdapter(Activity activity, DatabaseReference databaseReference, String displayName) {
    mActivity = activity;
    mDatabaseReference = databaseReference.child("messages");
    mDisplayName = displayName;
  }

  // acts as a helper for our row
  static class ViewHolder {
    TextView authorName;
    TextView message;
    LinearLayout.LayoutParams params;
  }

  @Override
  public int getCount() {
    return mDataSnapshots.size();
  }

  @Override
  public InstantMessage getItem(int i) {
    return mDataSnapshots.get(i);
  }

  @Override
  public long getItemId(int i) {
    return mDataSnapshots.get(i);
  }

  // checks to see if there's an existing row that can be re-used
  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {

    if (view == null) {
      // create new row if null
      LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(R.layout.chat_msg_row, viewGroup, false);

      final ViewHolder holder = new ViewHolder();
      holder.authorName = view.findViewById(R.id.author);
      holder.message = view.findViewById(R.id.messageInput);
      holder.params = (LinearLayout.LayoutParams) holder.authorName.getLayoutParams();
      view.setTag(holder); //temporarily storing here
    }

    final InstantMessage message = getItem(i);
    final ViewHolder holder = (ViewHolder) view.getTag();

    String author = message.getAuthor();
    holder.authorName.setText(author);

    String msgText = message.getMessage();
    holder.message.setText(msgText);

    return view;
  }
}
