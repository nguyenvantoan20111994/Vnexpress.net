package com.example.toan.vnnet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.toan.vnnet.R;
import com.example.toan.vnnet.adapter.customrecycleview;
import com.example.toan.vnnet.asyntask.AsyncResponse;
import com.example.toan.vnnet.asyntask.asyntasktinhot;
import com.example.toan.vnnet.object.rssitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toan on 4/28/2016.
 */
public class tinhot extends Fragment {
    public asyntasktinhot asytinhot;
    customrecycleview adapter;

    RecyclerView.LayoutManager mLayoutManager;
    private List<rssitem> arr = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private String url = "http://vnexpress.net/rss/thoi-su.rss";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tinhot_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mrecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        fetch();
        return view;

    }

    public void fetch() {
        asytinhot = new asyntasktinhot(getContext(), arr, new AsyncResponse() {
            @Override
            public void processFinish(List<rssitem> output) {
                arr = output;
                Log.i("arr", "processFinish: " + arr);
                adapter = new customrecycleview(getContext(), arr);
                mRecyclerView.setAdapter(adapter);
            }
        });
        asytinhot.execute(url);


/*        adapter.setOnitemclicklistener(new customrecycleview.Clicklistener() {
            @Override
            public void onitemclick(View view, int position) {

            }

            @Override
            public void onitemlongclick(View view, int position) {

            }
        });*/

    }
}
