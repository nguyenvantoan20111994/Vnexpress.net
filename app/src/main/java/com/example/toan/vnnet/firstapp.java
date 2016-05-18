package com.example.toan.vnnet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toan.vnnet.asyntask.Internetchecked;

/**
 * Created by toan on 4/26/2016.
 */
public class firstapp extends AppCompatActivity {
    ProgressBar progressBar;
    TextView txtkt;
    Button btnrefesh;
    Internetchecked internetchecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fistapp_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtkt = (TextView) findViewById(R.id.editText);
        btnrefesh = (Button) findViewById(R.id.button);
        Intent homeintent = new Intent(this, MainActivity.class);
        startActivity(homeintent);
        //checkinternet();
        if (checkinternet() == false) {

        } else {
            Toast.makeText(getApplication(), "chua co", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkinternet() {
        internetchecked= new Internetchecked(getBaseContext());
       return true;

    }

}
