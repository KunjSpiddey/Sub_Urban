package com.example.suburban;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.media.Image;
//import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class Home extends AppCompatActivity{
//    ViewPager viewPager;

//    WormDotsIndicator dot3;
//    ViewAdapter viewAdapter;
//    TextView wish,cart;
//    ImageButton pick;
//    ImageView round,polo,shirt,perfume,  ooblean doubleBackToExitPressedOnce = true;shoes,tie,goggles,belt,watch,bag,hat;
//        Integer[] images={R.drawable.show,R.drawable.tuxeda,R.drawable.winter,R.drawable.man,R.drawable.shoess};


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open , R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentLoader(new FragHome(),1);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        fragmentLoader(new FragHome(), 0);
        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            switch( id ){

//                case R.id.home:
//                    fragmentLoader(new FragHome(), 0);
//                    break;
                case R.id.myprofile:
                    fragmentLoader(new Profile_Fragment(), 1);
                    break;
                case R.id.order:
                    fragmentLoader(new Order_Fragment(), 1);
                    break;
                case R.id.cart:
                    fragmentLoader(new Cart_Fragment(), 1);
                    break;
                case R.id.wish:
                    fragmentLoader(new Wishlist_Fragment(), 1);
                    break;
                case R.id.notification:
                    fragmentLoader(new Notification_Fragment(), 1);
                    break;
                case R.id.seller:
                    Intent seller = new Intent(getApplicationContext(),Seller.class);
                    startActivity(seller);
                    finish();
                    break;
                case R.id.nav_logout:
                    firebaseAuth.signOut();;
                    signOutUser();
                    break;
                case R.id.terms_and_condition:
                    fragmentLoader(new TermsAndConditionFragment(), 1);
                    break;
                case R.id.share:
                    Toast.makeText(Home.this, "Share", Toast.LENGTH_SHORT).show();
                    break;

            }


            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        });

    }

    private void signOutUser() {
        Intent logout = new Intent(getApplicationContext(),Login_Activity.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logout);
        finish();
    }

    private void fragmentLoader(Fragment fragment, int flag) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, fragment);
        ft.replace(R.id.container , fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(getSupportFragmentManager().getBackStackEntryCount() == 1)
        {
            moveTaskToBack(false);
        }

        else {
            super.onBackPressed();

        }

    }

}

/*

if (!drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.openDrawer(GravityCompat.START);
    } else if (doubleBackToExitPressedOnce) {
        super.onBackPressed();
    } else {
        doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
   //////////////////////////////////////////////





















 */



