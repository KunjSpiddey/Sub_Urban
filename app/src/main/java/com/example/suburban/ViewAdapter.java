package com.example.suburban;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class ViewAdapter extends PagerAdapter {



    List<Integer> list;

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images={R.drawable.show,R.drawable.tuxeda,R.drawable.iperfume,R.drawable.man,R.drawable.ishoes,R.drawable.iwatch};



    ViewAdapter(List<Integer> imagelist){
        this.list = imagelist;
    }



    public ViewAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view =layoutInflater.from(container.getContext()).inflate(R.layout.item,container,false);
        ImageView imageView=view.findViewById(R.id.image_view);
        imageView.setImageResource(list.get(position));



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              switch (position){
                  case 0:
                      fragmentLoader(new FragmentIndex_1(),1, view);
                      break;
                  case 1:
                      fragmentLoader(new FragmentIndex_2(),1, view);
                      break;
                  case 2:
                      fragmentLoader(new FragmentIndex_3(),1, view);
                      break;
                  case 3:
                      fragmentLoader(new FragmentIndex_4(),1, view);
                      break;
                  case 4:
                      fragmentLoader(new FragmentIndex_5(),1, view);
                      break;
                  case 5:
                      fragmentLoader(new FrgamentIndex_6(),1, view);
                      break;
              }
            }
        });
        ViewPager viewPager=(ViewPager) container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager=(ViewPager) container;
        View view=(View) object;
        viewPager.removeView(view);
    }


    private void fragmentLoader(Fragment fragment, int flag, View view) {


        AppCompatActivity acc = (AppCompatActivity)view.getContext();

        acc.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .replace(R.id.container, fragment)
                .addToBackStack(null).commit();
    }
}
