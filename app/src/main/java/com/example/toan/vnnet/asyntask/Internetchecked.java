package com.example.toan.vnnet.asyntask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.telecom.ConnectionService;
import android.widget.Button;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by toannguyen201194 on 18/05/2016.
 */
public class Internetchecked extends AsyncTask< String,Void,Boolean>{
    private Context context;
    Button btn;

    public Internetchecked(Context context) {
        this.context = context;
    }
    @Override
    protected Boolean doInBackground(String...params) {
            if(networkConnectivity()){
                try{
                    URL url=new URL("http://google/cpm");
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection() ;
                    conn.setRequestProperty("User -Agent","Test");
                    conn.setRequestProperty("Connection" ,"close");
                    conn.setConnectTimeout(3000);
                    conn.setReadTimeout(4000);
                    conn.connect();
                    return  (conn.getResponseCode()==200);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    return (false);
                }
            }
            return false;
        }
    public boolean networkConnectivity(){
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo !=null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
