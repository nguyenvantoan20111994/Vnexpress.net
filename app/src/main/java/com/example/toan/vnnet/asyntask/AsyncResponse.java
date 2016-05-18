package com.example.toan.vnnet.asyntask;

import com.example.toan.vnnet.object.rssitem;

import java.util.List;

/**
 * Created by toannguyen201194 on 13/05/2016.
 */
public interface AsyncResponse {
    void processFinish(List<rssitem> output);
}
