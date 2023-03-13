package com.example.suburban;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class products_adapter extends BaseAdapter {
    Context context;
    int[] images;
    String [] price;
    String [] pname;
    LayoutInflater inflater;
    public products_adapter(Context context , int[] images, String [] price , String [] p_name ) {
        this.context = context;
        this.images = images;
        this.price = price;
        this.pname = p_name;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.products,null);
        ImageView img = (ImageView)view.findViewById(R.id.image);
        TextView pric = view.findViewById(R.id.price);
        TextView pnam = view.findViewById(R.id.pname);

        img.setImageResource(images[i]);

        pric.setText(price[i]);

        pnam.setText(pname[i]);
        return view;
    }
}
