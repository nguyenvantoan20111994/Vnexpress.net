package com.example.toan.vnnet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toan.vnnet.R;
import com.example.toan.vnnet.object.rssitem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toan on 4/20/2016.
 */
public class customgridview extends ArrayAdapter<rssitem> {
    private Context context;
    private int layoutResourceId;
    private List<rssitem> rssitemList;

    public customgridview(Context context, int layoutresourceId) {
        super(context,layoutresourceId);
        this.context = context;
        this.layoutResourceId = layoutresourceId;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        Holder holder;
        rssitemList=new ArrayList<rssitem>();
        rssitem items=rssitemList.get(position);
        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, viewGroup, false);
            holder = new Holder();
            holder.txtTitle = (TextView) row.findViewById(R.id.txtcategory);
            holder.img = (ImageView) row.findViewById(R.id.imagegird);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }
        holder.txtTitle.setText(items.get_title());
        Picasso.with(context).load(items.get_link()).error(R.drawable.search_icon).into(holder.img);
        return row;

    }


    public class Holder {
        ImageView img;
        TextView txtTitle;
    }
}
