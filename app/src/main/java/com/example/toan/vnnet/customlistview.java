package com.example.toan.vnnet;

import android.content.Context;
import android.widget.ArrayAdapter;
public class customlistview extends ArrayAdapter {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public customlistview(Context context, int resource) {
        super(context, resource);
    }
}
