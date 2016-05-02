package com.example.toan.vnnet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by toan on 4/20/2016.
 */
public class gridviewadapter extends ArrayAdapter {
    private Context context;
    private int resultid;
    private ArrayList data= new ArrayList();

    public gridviewadapter(Context context, int resultid, ArrayList data){
        super(context,resultid,data);
        this.context=context;
        this.data=data;
        this.resultid=resultid;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        holder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resultid, parent, false);
            holder = new holder();
            holder.tieude = (TextView) row.findViewById(R.id.text);
            holder.imageanh = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (holder) row.getTag();
        }

        ImageItem item = (ImageItem) data.get(position);
        holder.tieude.setText(item.getImagetitle());
        String link=item.getImageanh();
        //Picasso.with(holder.imageanh.getContext()).load(link).into(holder.imgAvatar);
        return row;

    }
    public static class holder{
        ImageView imageanh;
        TextView tieude;
    }
}
