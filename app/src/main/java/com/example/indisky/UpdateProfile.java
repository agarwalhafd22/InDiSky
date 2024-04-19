package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UpdateProfile extends AppCompatActivity {

    TextView nameUpdateProfile, emailUpdateProfile;

    ImageView backButton;

    UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        nameUpdateProfile=findViewById(R.id.nameUpdateProfile);
        emailUpdateProfile=findViewById(R.id.emailUpdateProfile);
        backButton=findViewById(R.id.backButton);

        userDB =new UserDB(UpdateProfile.this);

        String userEmail = userDB.getLoggedInUserEmail();
        String userName = userDB.getLoggedInUserName(userEmail);

        nameUpdateProfile.setText(userName);
        emailUpdateProfile.setText(userEmail);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}