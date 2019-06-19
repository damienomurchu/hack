package me.murchu.dice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button rollButton;

    rollButton = (Button) findViewById(R.id.button_roll);

    final ImageView leftDice = (ImageView) findViewById(R.id.image_leftDice);
    final ImageView rightDice = (ImageView) findViewById(R.id.image_rightDice);

    final int[] diceArray = {R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6
    };

    rollButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("Dice", "Roll button pressed!");

        Random randomNumGen = new Random();
        int leftRoll = randomNumGen.nextInt(6);
        int rightRoll = randomNumGen.nextInt(6);

        Log.d("Dice", "leftRoll was " + (leftRoll + 1));
        Log.d("Dice", "rightDice was " + (rightRoll + 1));

        leftDice.setImageResource(diceArray[leftRoll]);
        rightDice.setImageResource(diceArray[rightRoll]);
      }
    });

  }
}
