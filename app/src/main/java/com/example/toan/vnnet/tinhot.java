package com.example.toan.vnnet;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toan.vnnet.RSSitem.Rssparser;
import com.example.toan.vnnet.RSSitem.rssitem;
import com.example.toan.vnnet.adapter.customrecycleview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toan on 4/28/2016.
 */
public class tinhot extends Fragment {
    public Rssparser rssparser = new Rssparser();
    public List<rssitem> rssitemList;
    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter adapter;
    private RecyclerView mRecyclerView;
    private String url = "http://vnexpress.net/rss/thoi-su.rss";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rssitemList = new ArrayList<rssitem>();
        View view = inflater.inflate(R.layout.tinhot_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mrecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        new getlistitem(getContext()).execute();
        return view;
    }

    public class getlistitem extends AsyncTask<Void, Void, List<rssitem>> {
        Context context;

        public getlistitem(Context context) {
            this.context = context;
        }

        @Override
        protected List<rssitem> doInBackground(Void... voids) {
            rssitemList = rssparser.getRssFeedItems(url);
            Log.d("rss", rssitemList.size() + "");
            return rssitemList;
        }
        @Override
        protected void onPostExecute(List<rssitem> list) {
            adapter = new customrecycleview(context, rssitemList);
            mRecyclerView.setAdapter(adapter);
        }
    }
}
