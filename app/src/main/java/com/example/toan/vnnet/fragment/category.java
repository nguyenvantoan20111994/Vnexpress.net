package com.example.toan.vnnet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toan.vnnet.R;

/**
 * Created by toannguyen201194 on 13/05/2016.
 */
public class category extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.category_layout, container, false);
    }
}
