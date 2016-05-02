package com.example.toan.vnnet;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by toan on 4/26/2016.
 */
public class firstapp extends AppCompatActivity {
    ProgressBar progressBar;
    TextView txtkt;
    Button btnrefesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fistapp_layout);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        txtkt=(TextView) findViewById(R.id.editText);
        btnrefesh=(Button) findViewById(R.id.button);
        connect();
        btnrefesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnrefesh.setBackgroundResource(R.drawable.sendicon);
                btnrefesh.refreshDrawableState();
                connect();
            }
        });
    }
    public void connect(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            txtkt.setText("Kiểm Tra Lại Kết Nối Mạng");
            btnrefesh.setVisibility(View.VISIBLE);

        }
    }
}
