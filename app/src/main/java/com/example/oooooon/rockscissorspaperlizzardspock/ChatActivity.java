package com.example.oooooon.rockscissorspaperlizzardspock;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    URLConnection connection;
    String charset = "UTF-8";

    EditText text;
    Button btn;
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        text = (EditText) findViewById(R.id.editText1);
        btn = (Button) findViewById(R.id.button1);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (text.getText().toString().length() <1){
            Toast.makeText(this, "please enter something", Toast.LENGTH_LONG).show();
        } else {
            pb.setVisibility(View.VISIBLE);
            //anropa metod async
            new MyAsyncTask().execute(text.getText().toString());

        }

    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double>{


        @Override
        protected Double doInBackground(String... params) {
            //postData
            postData(params[0]);
            return null;
        }
        protected void onPostExecute(Double result){
            pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"Thank you!", Toast.LENGTH_LONG).show();
        }

        protected void onProgressUpdate(Integer... progress){
            pb.setProgress(progress[0]);

        }

        public void postData(String message){
            try{
                connection = new URL("https://hooks.slack.com/services/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").openConnection();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                // never happends if our code is valid -- our url is hardcoded
            } catch (IOException e) {
                e.printStackTrace();
            }

            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset",charset);
            connection.setRequestProperty("Content-Type", "application/json");

            try{
                OutputStream output = connection.getOutputStream();
                output.write(String.format("{\"text\":\"%s\"}",message).getBytes("UTF-8"));
                connection.getInputStream();

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Could not post");
            }

        }



    }




}
