package com.example.suburban;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

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
        ImageView wallet = view.findViewById(R.id.wallet);
        ImageView turtle = view.findViewById(R.id.turtle);
        ImageView shoe = view.findViewById(R.id.bag);
        ImageView tiees= view.findViewById(R.id.hat);
        SearchView searchView = view.findViewById(R.id.searchView);
        Timer timer = new Timer();
//        Handler handler = new Handler();


//        R.drawable.show,R.drawable.tuxeda,R.drawable.winter,R.drawable.man,R.drawable.shoess
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.show);
        images.add(R.drawable.tuxeda);
        images.add(R.drawable.iperfume);
        images.add(R.drawable.man);
        images.add(R.drawable.iwatch);
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

                 wallet.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         fl(new Wallet(),1);
                     }
                 });

                 turtle.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         fl(new Turtle(),1);
                     }
                 });

                 shoe.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         fl(new Fragment_Shoes(),1);
                     }
                 });

                 tiees.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         fl(new Fragment_tie(),1);
                     }
                 });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putString("query", query);
                search_item fragment = new search_item();
                fragment.setArguments(bundle);

                // hide the keyboard before navigating to the new fragment
                hideKeyboard();

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // do something when the text changes
                return true;
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
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            View currentFocus = getActivity().getCurrentFocus();
            if (currentFocus != null) {
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SearchView searchView = getActivity().findViewById(R.id.searchView);
        searchView.setQuery("", false);
        searchView.clearFocus();
    }


}

