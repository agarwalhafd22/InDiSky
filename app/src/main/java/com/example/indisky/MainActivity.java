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
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    BottomNavigationView bottomNavigationView;

    TextView headerUserEmailTextView, headerUserNameTextView;

    Toolbar toolbar;

    UserDB userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDB=new UserDB(MainActivity.this);
        String userEmail = userDB.getLoggedInUserEmail();
        String userName = userDB.getLoggedInUserName(userEmail);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        bottomNavigationView=findViewById(R.id.navigation_view_bottom);
        toolbar=findViewById(R.id.toolbar);
        navigationView.bringToFront();

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        headerUserEmailTextView = headerView.findViewById(R.id.userEmailTextView);
        headerUserNameTextView = headerView.findViewById(R.id.userNameTextView);
        headerUserEmailTextView.setText(userEmail);
        headerUserNameTextView.setText(userName);
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
            menuItem.setChecked(false);
            drawerLayout.closeDrawer(GravityCompat.START);
            bottomNavigationView.getMenu().findItem(R.id.book_flight_bottom_nav).setChecked(true);
        }
//        else if(menuItem.getItemId()==R.id.home_bottom_nav)
//        {
//            drawerLayout.closeDrawer(GravityCompat.START);
//            menuItem.setChecked((false));
//        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}