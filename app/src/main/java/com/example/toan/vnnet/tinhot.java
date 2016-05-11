package com.example.toan.vnnet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toan.vnnet.RSSitem.Rssparser;
import com.example.toan.vnnet.RSSitem.rssitem;
import com.example.toan.vnnet.adapter.customrecycleview;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by toan on 4/28/2016.
 */
public class tinhot extends Fragment {
    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter adapter;
    public List<rssitem> rssitemList=new ArrayList<rssitem>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tinhot_layout, container, false);
        RecyclerView mRecyclerView=(RecyclerView) view.findViewById(R.id.mrecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter=new customrecycleview(getContext(),rssitemList);
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}
