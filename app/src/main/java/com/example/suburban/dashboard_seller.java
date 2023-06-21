package com.example.suburban;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class dashboard_seller extends BaseAdapter {


    LayoutInflater inflater;

    private List<addedProducts> datalist;

    String id;
    String sellerId;

    public dashboard_seller(List<addedProducts> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    private Context context;

    public dashboard_seller(String id, String sellerId) {
        this.id = id;
        this.sellerId = sellerId;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.seller_dashboard,null);

        ImageView img = convertView.findViewById(R.id.image);
        TextView pname = convertView.findViewById(R.id.pname);
        TextView oprice = convertView.findViewById(R.id.oprice);
        TextView dprice = convertView.findViewById(R.id.dprice);


        return convertView;
    }
}
