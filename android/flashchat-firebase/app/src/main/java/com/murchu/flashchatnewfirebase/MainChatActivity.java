package com.murchu.flashchatnewfirebase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainChatActivity extends AppCompatActivity {

  // TODO: Add member variables here:
  private String mDisplayName;
  private ListView mChatListView;
  private EditText mInputText;
  private ImageButton mSendButton;
  private DatabaseReference mDatabaseReference;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_chat);

    // TODO: Set up the display name and get the Firebase reference
    setUpDisplayName();
    mDatabaseReference = FirebaseDatabase.getInstance().getReference();

    // Link the Views in the layout to the Java code
    mInputText = (EditText) findViewById(R.id.messageInput);
    mSendButton = (ImageButton) findViewById(R.id.sendButton);
    mChatListView = (ListView) findViewById(R.id.chat_list_view);

    // TODO: Send the message when the "enter" button is pressed
    mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        /*if (i == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
          Log.d("FlashChat", "Enter pressed. Message: " + mInputText.getText().toString());
        }*/
        sendMessage();
        return true;
      }
    });


    // TODO: Add an OnClickListener to the sendButton to send a message
    mSendButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        sendMessage();
      }
    });

  }

  // TODO: Retrieve the display name from the Shared Preferences
  private void setUpDisplayName() {
    SharedPreferences prefs = getSharedPreferences(RegisterActivity.CHAT_PREFS, 0);
    String defaultUserName = "Anonymous";
    mDisplayName = prefs.getString(RegisterActivity.DISPLAY_NAME_KEY, defaultUserName);
  }


  private void sendMessage() {

    Log.d("FlashChat", "Message just sent!");
    // TODO: Grab the text the user typed in and push the message to Firebase
    String input = mInputText.getText().toString();
    if (!input.equals("")) {
      InstantMessage chat = new InstantMessage(input, mDisplayName);
      mDatabaseReference.child("messages").push().setValue(chat);
      mInputText.setText("");
    }

  }

  // TODO: Override the onStart() lifecycle method. Setup the adapter here.


  @Override
  public void onStop() {
    super.onStop();

    // TODO: Remove the Firebase event listener on the adapter.

  }

}
