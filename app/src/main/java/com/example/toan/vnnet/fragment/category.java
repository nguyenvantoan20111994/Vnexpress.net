package com.example.toan.vnnet.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.toan.vnnet.R;
import com.example.toan.vnnet.object.rssitem;
import java.util.List;
/**
 * Created by toannguyen201194 on 13/05/2016.
 */
public class category extends Fragment {
    private List<rssitem> arr;
    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.girdcategory);
        return view;
    }

}
