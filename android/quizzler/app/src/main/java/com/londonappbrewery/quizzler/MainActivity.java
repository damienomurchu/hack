package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

  // TODO: Declare constants here


  // member variables

  Toast mFeedbackToast;
  int currentQuestion;
  int currentScore;
  Button mTrueButton;
  Button mFalseButton;
  TextView mQuestion;
  TextView mScore;
  ProgressBar mProgress;


  // question bank with reference to question & answers
  private TrueFalse[] mQuestionBank = new TrueFalse[]{
      new TrueFalse(R.string.question_1, true),
      new TrueFalse(R.string.question_2, true),
      new TrueFalse(R.string.question_3, true),
      new TrueFalse(R.string.question_4, true),
      new TrueFalse(R.string.question_5, true),
      new TrueFalse(R.string.question_6, false),
      new TrueFalse(R.string.question_7, true),
      new TrueFalse(R.string.question_8, false),
      new TrueFalse(R.string.question_9, true),
      new TrueFalse(R.string.question_10, true),
      new TrueFalse(R.string.question_11, false),
      new TrueFalse(R.string.question_12, false),
      new TrueFalse(R.string.question_13, true)
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState != null) {
      currentQuestion = savedInstanceState.getInt("CurrentQuestionKey");
      currentScore = savedInstanceState.getInt("ScoreKey");
    } else {
      currentQuestion = 0;
      currentScore = 0;
    }

    mFeedbackToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    mTrueButton = findViewById(R.id.true_button);
    mFalseButton = findViewById(R.id.false_button);
    mQuestion = findViewById(R.id.question_text_view);
    mScore = findViewById(R.id.score);
    mProgress = findViewById(R.id.progress_bar);
    mProgress.setMax(mQuestionBank.length -1);
    mProgress.setProgress(currentScore);

    mQuestion.setText(mQuestionBank[currentQuestion].getQuestionId());

    String score = getResources().getString(R.string.score, String.valueOf(currentScore));
    mScore.setText(score);

    mTrueButton.setOnClickListener((v) -> {
      updateScore(true);
      updateQuestion();
    });

    mFalseButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Log.d("Quiz-App", "False button pressed");
        // Toast.makeText(getApplicationContext(), "False button pressed", Toast.LENGTH_SHORT).show();
        updateScore(false);
        updateQuestion();
      }
    });

  }

  void updateScore(Boolean guess) {
    if (mQuestionBank[currentQuestion].getAnswer() == guess) {
      mFeedbackToast.cancel();
      //mFeedbackToast.setText(R.string.correct_toast);
      mFeedbackToast = Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT);
      mFeedbackToast.show();
      currentScore += 1;
      mProgress.incrementProgressBy(1);
      String score = getResources().getString(R.string.score, String.valueOf(currentScore));
      mScore.setText(score);
    }
    else {
      mFeedbackToast.cancel();
      //mFeedbackToast.setText(R.string.incorrect_toast);
      mFeedbackToast = Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT);
      mFeedbackToast.show();
    }
  }

  void updateQuestion() {
    // random implementation of questions
    // int newQuestionId = new Random().nextInt(mQuestionBank.length -1);
    // currentQuestion = newQuestionId;
    // mQuestion.setText(mQuestionBank[currentQuestion].getQuestionId());

    currentQuestion = (currentQuestion + 1) % mQuestionBank.length;
    Log.d(this.toString(), "Current question: " + currentQuestion);

    if (currentQuestion == 0) {
      AlertDialog.Builder alert = new AlertDialog.Builder(this);
      alert.setTitle("Game Over");
      alert.setCancelable(false);
      alert.setMessage("You scored " + currentScore + " points!");
      alert.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          finish();
        }
      });
      alert.show();
    }

    mQuestion.setText(mQuestionBank[currentQuestion].getQuestionId());
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    outState.putInt("ScoreKey", currentScore);
    outState.putInt("CurrentQuestionKey", currentQuestion);

  }
}