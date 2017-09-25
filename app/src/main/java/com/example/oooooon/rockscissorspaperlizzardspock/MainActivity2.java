package com.example.oooooon.rockscissorspaperlizzardspock;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {


    EditText editName, editScore;

    boolean gameover;
    AlertDialog.Builder mBuilder;
    View mView;

    Button button_rock, button_paper, button_scissors, button_lizzard, button_spock;
    TextView text_score, text_draw;
    ImageView image_human_choice, image_computer_choice;
    int humanScore = 0;
    int computerScore = 0;
    int draw = 0;

    static String playerName;
    boolean isInserted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_rock = (Button) findViewById(R.id.button_rock);
        button_paper = (Button) findViewById(R.id.button_paper);
        button_scissors = (Button) findViewById(R.id.button_scissors);
        button_lizzard = (Button) findViewById(R.id.button_lizzard);
        button_spock = (Button) findViewById(R.id.button_Spock);

        image_computer_choice = (ImageView) findViewById(R.id.image_computer_choice);
        image_human_choice = (ImageView) findViewById(R.id.image_human_choice);

        text_score = (TextView) findViewById(R.id.text_score);
        text_draw = (TextView) findViewById(R.id.text_draw);

        button_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_human_choice.setImageResource(R.drawable.rock);
                playTurn("rock");
                updateScore();
            }
        });

        button_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_human_choice.setImageResource(R.drawable.paper);
                playTurn("paper");
                updateScore();
            }
        });

        button_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_human_choice.setImageResource(R.drawable.scissors);
                playTurn("scissors");
                updateScore();
            }
        });

        button_lizzard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_human_choice.setImageResource(R.drawable.lizard);
                playTurn("lizzard");
                updateScore();
            }
        });

        button_spock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_human_choice.setImageResource(R.drawable.spock);
                playTurn("spock");
                updateScore();

            }
        });
    }


    public void playTurn(String player_choice) {
        String computer_choice = "";
        Random r = new Random();
        int computerChoiceNumber = r.nextInt(5) + 1;

        if (computerChoiceNumber == 1) {
            computer_choice = "rock";
        } else if (computerChoiceNumber == 2) {
            computer_choice = "paper";
        } else if (computerChoiceNumber == 3) {
            computer_choice = "scissors";
        } else if (computerChoiceNumber == 4) {
            computer_choice = "lizzard";
        } else {
            computer_choice = "spock";
        }

        //set the computer image based on his choice
        if (computer_choice == "rock") {
            image_computer_choice.setImageResource(R.drawable.rock);
        } else if (computer_choice == "paper") {
            image_computer_choice.setImageResource(R.drawable.paper);
        } else if (computer_choice == "scissors") {
            image_computer_choice.setImageResource(R.drawable.scissors);
        } else if (computer_choice == "lizzard") {
            image_computer_choice.setImageResource(R.drawable.lizard);
        } else {
            image_computer_choice.setImageResource(R.drawable.spock);
        }

        //compare computer and human choices to determine who won
        if (computer_choice == player_choice) {
            Toast toast = Toast.makeText(MainActivity2.this, "it's a draw!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            draw++;
        } else if (player_choice == "paper" && computer_choice == "rock") {
            Toast toast = Toast.makeText(MainActivity2.this, "Paper covers rock, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        } else if (player_choice == "scissors" && computer_choice == "rock") {
            Toast toast = Toast.makeText(MainActivity2.this, "Rock crushes scissors, you loose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "lizzard" && computer_choice == "rock") {
            Toast toast = Toast.makeText(MainActivity2.this, "Rock crushes Lizzard, you lose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "spock" && computer_choice == "rock") {
            Toast toast = Toast.makeText(MainActivity2.this, "Spock vaporizes Rock, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        } else if (player_choice == "rock" && computer_choice == "paper") {
            Toast toast = Toast.makeText(MainActivity2.this, "Paper covers rock, you loose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "scissors" && computer_choice == "paper") {
            Toast toast = Toast.makeText(MainActivity2.this, "Scissors cut paper, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        } else if (player_choice == "lizzard" && computer_choice == "paper") {
            Toast toast = Toast.makeText(MainActivity2.this, "Lizzard eats paper, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        } else if (player_choice == "spock" && computer_choice == "paper") {
            Toast toast = Toast.makeText(MainActivity2.this, "Paper disproves Spock, you loose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "rock" && computer_choice == "scissors") {
            Toast toast = Toast.makeText(MainActivity2.this, "Rock beats scissors, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        } else if (player_choice == "paper" && computer_choice == "scissors") {
            Toast toast = Toast.makeText(MainActivity2.this, "Scissors cut paper, you loose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "lizzard" && computer_choice == "scissors") {
            Toast toast = Toast.makeText(MainActivity2.this, "scissors decapitate lizzard, you lose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "spock" && computer_choice == "scissors") {
            Toast toast = Toast.makeText(MainActivity2.this, "Spock smashes scissors, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        } else if (player_choice == "rock" && computer_choice == "lizzard") {
            Toast toast = Toast.makeText(MainActivity2.this, "Rock crushes lizzard, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        } else if (player_choice == "scissors" && computer_choice == "lizzard") {
            Toast toast = Toast.makeText(MainActivity2.this, "Scissors decapitate lizzard, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        } else if (player_choice == "paper" && computer_choice == "lizzard") {
            Toast toast = Toast.makeText(MainActivity2.this, "Lizzard eats paper, you lose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "spock" && computer_choice == "lizzard") {
            Toast toast = Toast.makeText(MainActivity2.this, "Lizzard poisons Spock, you loose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "rock" && computer_choice == "spock") {
            Toast toast = Toast.makeText(MainActivity2.this, "Spock vaporizes rock, you loose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "scissors" && computer_choice == "spock") {
            Toast toast = Toast.makeText(MainActivity2.this, "Spock smashes scissors , you loose!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            computerScore++;
        } else if (player_choice == "paper" && computer_choice == "spock") {
            Toast toast = Toast.makeText(MainActivity2.this, "Paper disproves Spock, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        } else if (player_choice == "lizzard" && computer_choice == "spock") {
            Toast toast = Toast.makeText(MainActivity2.this, "Lizzard poisons Spock, you win!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
            humanScore++;
        }


    }

    public void updateScore() {
        text_score.setText("Score: Human " + Integer.toString(humanScore) + " Computer " + Integer.toString(computerScore));
        text_draw.setText("Draws: " + Integer.toString(draw));


        if (humanScore == 10) {
            mView = getLayoutInflater().inflate(R.layout.dialog_gameover, null);
            TextView txt_winner = (TextView) mView.findViewById(R.id.text_winner);
            txt_winner.setText(R.string.winnerMessage);
            gameover = true;

            //insert into database
            DatabaseHelper myDB = new DatabaseHelper(this);
            isInserted = myDB.insertData(playerName, Integer.toString(humanScore));

            if (isInserted) {
                Toast toast = Toast.makeText(MainActivity2.this, "Data is inserted", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                toast.show();
            } else {
                Toast toast = Toast.makeText(MainActivity2.this, "Data is not inserted", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                toast.show();
            }

        }

        else if(computerScore == 10){
            mView = getLayoutInflater().inflate(R.layout.dialog_gameover, null);
            TextView txt_winner = (TextView) mView.findViewById(R.id.text_winner);
            txt_winner.setText("Game over");
            gameover = true;

            //insert into database
            DatabaseHelper myDB = new DatabaseHelper(this);
            isInserted = myDB.insertData(playerName, Integer.toString(humanScore));

            if (isInserted) {
                Toast toast = Toast.makeText(MainActivity2.this, "Data is inserted", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                toast.show();
            } else {
                Toast toast = Toast.makeText(MainActivity2.this, "Data is not inserted", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                toast.show();
            }

        }









        if(gameover) {
            mBuilder = new AlertDialog.Builder(MainActivity2.this);
            mBuilder.setTitle("Results"); //headline for popup

            //ok button
            mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }

            });



            mBuilder.setView(mView); //makes sure popup is displayed.
            AlertDialog dialog = mBuilder.create(); //creates popup.
            dialog.show(); //shows popup

        }
    }



    public static void saveName(String receivedName){
        playerName = receivedName;
    }
}


