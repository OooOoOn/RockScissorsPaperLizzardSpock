package com.example.oooooon.rockscissorspaperlizzardspock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import static com.example.oooooon.rockscissorspaperlizzardspock.MainActivity2.saveName;


/**
 * Created by OooOoOn on 07/04/2017.
 */

public class MainActivity extends Activity {

    AlertDialog.Builder mBuilder;
    View mView;
    Button btn_newGame;
    Button btn_Highscore;
    DatabaseHelper myDB;
    EditText playerName;
    AlertDialog dialog;
    ProgressBar pb;
    EditText txt_username;
    Button btn_chat;
    Button btn_youtube;



    final Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        /*
        editName = (EditText) findViewById(R.id.editText_name);
        editScore = (EditText) findViewById(R.id.editText_score);
        btnAddPlayer = (Button) findViewById(R.id.button_add);
        btnHighscore = (Button) findViewById(R.id.button_Highscore);
        addPlayerData();*/

        //create data base
        myDB = new DatabaseHelper(this);

        //function to view the current highscore
        viewHighscore();

        //display main menu
        showNextActivity();

        //display the chat window
        viewChat();

        //display the youtube rules
        viewYoutube();

    }

    public void viewYoutube(){

        btn_youtube = (Button)findViewById(R.id.button_youtube);
        btn_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });
    }

    //displays the chat window
    public void viewChat(){

        btn_chat = (Button) findViewById(R.id.button_chat);
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    //displays the highscore list
    public void viewHighscore() {
        btn_Highscore = (Button) findViewById(R.id.button_highscore);
        btn_Highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();
                if (res.getColumnCount() == 0) {
                    showMessage("Error", "No data found");
                    return;
                }

                StringBuilder sb = new StringBuilder();
                while (res.moveToNext()) {
                    sb.append("id : " + res.getString(0) + "\n");
                    sb.append("Name : " + res.getString(1) + "\n");
                    sb.append("Score : " + res.getString(2) + "\n");

                }

                showMessage("Highscore", sb.toString());

            }
        });
    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }

    //shows popup for player name and thereafter starts the new game
    private void showNextActivity() {

        mView = getLayoutInflater().inflate(R.layout.dialog_newplayer, null);

        //create and hide progress bar until player clicks ok to submit name
        pb = (ProgressBar) findViewById(R.id.pb_progressbar);
        //hide progress bar
        pb.setVisibility(View.GONE);

        txt_username = (EditText) mView.findViewById(R.id.text_username);


        btn_newGame = (Button) findViewById(R.id.button_newgame);

        btn_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setTitle("New player"); //headline for popup

                //cancellation button
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        txt_username.setText("");


                    }
                });

                //ok button
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!txt_username.getText().toString().isEmpty()) {
                            playerName = (EditText) mView.findViewById(R.id.text_username); //get whatever name player added

                            saveName(playerName.getText().toString());
                            //saveName("lilly");

                            pb.setVisibility(View.VISIBLE); //make progress bar visible

                            //delay transition to next page to give progress bar time to display

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    pb.setVisibility(View.GONE);
                                    Toast toast = Toast.makeText(MainActivity.this, R.string.name_confirmed, Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                    toast.show();
                                }
                            }, 1500);


                            //delay transition to next page to give toast a time to display.
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                    startActivity(intent);
                                }
                            }, 3500);

                        } else {
                            Toast toast = Toast.makeText(MainActivity.this, R.string.name_prompt, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();

                        }
                        txt_username.setText(""); //clears name field for next player
                    }
                });

                mBuilder.setView(mView); //makes sure popup is displayed.

                if (dialog == null) {
                    dialog = mBuilder.create(); //creates popup.
                }

                dialog.show(); //shows popup


            }
        });


    }


    }

