package com.example.toan.vnnet.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.toan.vnnet.RSSitem.Rssparser;
import com.example.toan.vnnet.adapter.customrecycleview;
import com.example.toan.vnnet.object.rssitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toannguyen201194 on 13/05/2016.
 */
public class asyntasktinhot extends AsyncTask<String, Void, List<rssitem>> {
    public Rssparser rssparser = new Rssparser();
    private Context context;
    private List<rssitem> arr = new ArrayList<>();
    private RecyclerView recyclerView;
    private AsyncResponse asyncResponse;

    public asyntasktinhot(Context context, List<rssitem> arr , AsyncResponse asyncResponse) {
        this.context = context;
        this.arr = arr;
        this.asyncResponse = asyncResponse;
    }

    @Override
    protected List<rssitem> doInBackground(String... params) {
        List<rssitem> rssitemList = new ArrayList<>();
        String link = (String) params[0];
        rssitemList = rssparser.getRssFeedItems(link);
        Log.d("rss", rssitemList.size() + "");
        return rssitemList;
    }

    @Override
    protected void onPostExecute(List<rssitem> result) {
        super.onPostExecute(result);
        arr = result;
        asyncResponse.processFinish(result);
    }
}
