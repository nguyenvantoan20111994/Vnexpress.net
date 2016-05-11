package com.example.toan.vnnet.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toan.vnnet.R;
import com.example.toan.vnnet.RSSitem.rssitem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class customrecycleview extends RecyclerView.Adapter<customrecycleview.Myviewholder> {
   private Context mcontext;
    private List<rssitem> rssitemList;
    public customrecycleview(Context mcontext, List<rssitem> rssitemList) {
        this.mcontext = mcontext;
        this.rssitemList = rssitemList;
    }
    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customlistview_layout,null);
        Myviewholder myviewholder= new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, int position) {
        rssitem rssitem=rssitemList.get(position);
        holder.mtitle.setText(rssitem.get_title());
        holder.mdes.setText(rssitem.get_description());
        holder.mhouser.setText(rssitem.get_pubdate());
        Picasso.with(mcontext).load(rssitem.get_link()).error(R.drawable.search_icon).into(holder.mimage);
    }

    @Override
    public int getItemCount() {
        return (null != rssitemList ? rssitemList.size() : 0);
    }

    public class Myviewholder extends RecyclerView.ViewHolder{
        public TextView mtitle,mdes,mhouser;
        public ImageView mimage;
        public Myviewholder(View view) {
            super(view);
            mtitle=(TextView) view.findViewById(R.id.txttitle);
            mdes=(TextView) view.findViewById(R.id.txtdes);
            mhouser=(TextView) view.findViewById(R.id.txthourse);
            mimage=(ImageView) view.findViewById(R.id.imageanh);
        }
    }
}
