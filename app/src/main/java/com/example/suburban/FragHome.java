package com.example.suburban;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suburban.databinding.FragmentFragHomeBinding;
import com.google.android.material.navigation.NavigationView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragHome extends Fragment {
int i = 0;
    public FragHome() {
            
        
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View view =  inflater.inflate(R.layout.fragment_frag_home, container, false);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        WormDotsIndicator dot3 = view.findViewById(R.id.dot3);
        ImageButton pick = view.findViewById(R.id.pick);
        ImageView round = view.findViewById(R.id.round);
        ImageView polo = view.findViewById(R.id.polo);
        ImageView shirt = view.findViewById(R.id.shirt);
        ImageView perfume = view.findViewById(R.id.perfume);
        ImageView shoes = view.findViewById(R.id.shoes);
        ImageView tie = view.findViewById(R.id.tie);
        ImageView goggles = view.findViewById(R.id.goggles);
        ImageView belt = view.findViewById(R.id.belt);
        ImageView watch = view.findViewById(R.id.watch);
        ImageView bag = view.findViewById(R.id.bag);
        ImageView hat= view.findViewById(R.id.hat);
        Timer timer = new Timer();
//        Handler handler = new Handler();


//        R.drawable.show,R.drawable.tuxeda,R.drawable.winter,R.drawable.man,R.drawable.shoess
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.show);
        images.add(R.drawable.tuxeda);
        images.add(R.drawable.winter);
        images.add(R.drawable.man);
        images.add(R.drawable.shoess);

        ViewAdapter viewAdapter = new ViewAdapter(images);
        viewPager.setAdapter(viewAdapter);
        dot3.setViewPager(viewPager);





   final Handler handler = new Handler();
   final Runnable r = new Runnable(){
       @Override
       public void run() {

           if (i == images.size()-1){
               i=0;
               viewPager.setCurrentItem(i,true);
           }

           else
           {
               i++;
               viewPager.setCurrentItem(i,true);
           }
       }
   };

   timer.schedule(new TimerTask() {
       @Override
       public void run() {
           handler.post(r);
       }
   },3000,3000);



                round.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fl(new Fragment_Round(),1);
                    }
                });

                 polo.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {fl(new Fragment_Polo(),1);
                     }
                 });

                 shirt.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         fl(new Fragment_shirt(),1);
                     }
                 });

                 perfume.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         fl(new Fragment_Perfume(),1);
                     }
                 });

                 shoes.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         fl(new Fragment_Shoes(),1);
                     }
                 });

                 goggles.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         fl(new Fragment_goggles(),1);
                     }
                 });

                 tie.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         fl(new Fragment_tie(),1);
                     }
                 });

                 pick.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                        fl(new Pick_Fragment(),1);
                     }
                 });


        return view;
    }

    private void fl(Fragment fragment, int flag){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, fragment);
        ft.replace(R.id.container , fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

}

