package com.example.indisky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    BottomNavigationView bottomNavigationView;

    TextView headerUserEmailTextView, headerUserNameTextView;

    Button headerHomeButton;

    Toolbar toolbar;

    UserDB userDB;

    tempFlightDB tmpFlightDB;

    int count = 0;



    BookFragment bookFragment;

    HomeFragment homeFragment;

    int bookFragmentLoaded=0;
    int homeFragmentLoaded=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDB=new UserDB(MainActivity.this);

//        tmpFlightDB = new tempFlightDB(MainActivity.this);
//        tmpFlightDB.addOrigin("From");
//        tmpFlightDB.addDest("To");


        bookFragment=new BookFragment();
        homeFragment=new HomeFragment();


        String userEmail = userDB.getLoggedInUserEmail();
        String userName = userDB.getLoggedInUserName(userEmail);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        bottomNavigationView=findViewById(R.id.navigation_view_bottom);
        toolbar=findViewById(R.id.toolbar);
        navigationView.bringToFront();

        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (Exception e){}

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        headerUserEmailTextView = headerView.findViewById(R.id.userEmailTextView);
        headerUserNameTextView = headerView.findViewById(R.id.userNameTextView);
        headerHomeButton = headerView.findViewById(R.id.home_button);
        headerUserEmailTextView.setText(userEmail);
        headerUserNameTextView.setText(userName);

        int fragmentIndex = getIntent().getIntExtra("fragmentIndex", 1); // Default to 0 if not found

        // Navigate to the desired fragment based on the index
        if (fragmentIndex == 0) {
            // Navigate to Fragment A
            replaceFragment(homeFragment);
            homeFragmentLoaded = 1; // Update loaded flag
        } else if (fragmentIndex == 1) {
            // Navigate to Fragment B
            replaceFragment(bookFragment);
            bookFragmentLoaded = 1;
            bottomNavigationView.getMenu().findItem(R.id.book_flight_bottom_nav).setChecked(true);// Update loaded flag
        }

        headerHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookFragmentLoaded=0;
                navigationView.getMenu().findItem(R.id.nav_book_flight).setChecked(false);
                drawerLayout.closeDrawer(GravityCompat.START);
                bottomNavigationView.getMenu().findItem(R.id.home_bottom_nav).setChecked(true);
                if(homeFragmentLoaded==0)
                {
                    replaceFragment(homeFragment);
                    homeFragmentLoaded=1;
                }
            }
        });
    }

    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        if(menuItem.getItemId()==R.id.logout)
        {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();
            userDB.logout();
            Intent intent=new Intent(MainActivity.this, LoginPage.class);
            startActivity(intent);
            finish();
            menuItem.setChecked(false);
        }
        else if(menuItem.getItemId()==R.id.nav_book_flight)
        {
            if(bookFragmentLoaded==0) {
                replaceFragment(bookFragment);
                bookFragmentLoaded=1;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            bottomNavigationView.getMenu().findItem(R.id.book_flight_bottom_nav).setChecked(true);
            menuItem.setChecked(false);
        }
        else if(menuItem.getItemId()==R.id.book_flight_bottom_nav)
        {
            homeFragmentLoaded=0;
            if(bookFragmentLoaded==0) {
                replaceFragment(bookFragment);
                bookFragmentLoaded=1;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            navigationView.getMenu().findItem(R.id.nav_book_flight).setChecked(true);
            menuItem.setChecked(false);
        }
        else if(menuItem.getItemId()==R.id.home_bottom_nav)
        {
            bookFragmentLoaded=0;
            if(homeFragmentLoaded==0)
            {
                replaceFragment(homeFragment);
                homeFragmentLoaded=1;
            }
            navigationView.getMenu().findItem(R.id.nav_book_flight).setChecked(false);
        }
        else if(menuItem.getItemId()==R.id.admin_nav)
        {
            Intent intent =new Intent(MainActivity.this, Admin.class);
            startActivity((intent));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.book_flight_fragment, fragment);
        fragmentTransaction.commit();
    }

}