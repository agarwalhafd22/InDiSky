package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateProfile extends AppCompatActivity {

    TextView nameUpdateProfile, emailUpdateProfile, doneTextView, textView72, donePasswordTextView;

    ImageView backButton, updateNameImageView, userImageView;

    CardView updateNameCardView, cardView2, cardView3, updatePasswordCardView;

    EditText newNameEditText, oldPasswordEditText, newPasswordEditText;

    UserDB userDB;

    Button updatePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        nameUpdateProfile = findViewById(R.id.nameUpdateProfile);
        emailUpdateProfile = findViewById(R.id.emailUpdateProfile);
        backButton = findViewById(R.id.backButton);
        updateNameImageView = findViewById(R.id.updateNameImageView);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);
        updateNameCardView = findViewById(R.id.updateNameCardView);
        doneTextView = findViewById(R.id.doneTextView);
        userImageView = findViewById(R.id.userImageView);
        textView72 = findViewById(R.id.textView72);
        newNameEditText = findViewById(R.id.newNameEditText);
        updatePasswordButton=findViewById(R.id.updatePasswordButton);
        updatePasswordCardView=findViewById(R.id.updatePasswordCardView);
        donePasswordTextView=findViewById(R.id.donePasswordTextView);
        oldPasswordEditText=findViewById(R.id.oldPasswordEditText);
        newPasswordEditText=findViewById(R.id.newPasswordEditText);

        updateNameCardView.setVisibility(View.INVISIBLE);

        userDB = new UserDB(UpdateProfile.this);

        String userEmail = userDB.getLoggedInUserEmail();
        String userName = userDB.getLoggedInUserName(userEmail);
        int userID = userDB.getSessionUserID();
        

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

        updateNameImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePasswordButton.setVisibility(View.INVISIBLE);
                cardView2.setVisibility(View.INVISIBLE);
                cardView3.setVisibility(View.INVISIBLE);
                updateNameCardView.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.INVISIBLE);
                userImageView.setVisibility(View.INVISIBLE);
                textView72.setVisibility(View.INVISIBLE);
            }
        });

        doneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = newNameEditText.getText().toString();
                if(newName.isEmpty()) {
                    hideKeyboard();
                    Toast.makeText(UpdateProfile.this, "Name not updated", Toast.LENGTH_SHORT).show();
                    updateNameCardView.setVisibility(View.INVISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.VISIBLE);
                    backButton.setVisibility(View.VISIBLE);
                    userImageView.setVisibility(View.VISIBLE);
                    textView72.setVisibility(View.VISIBLE);
                    updatePasswordButton.setVisibility(View.VISIBLE);
                }
                else
                {
                    hideKeyboard();
                    userDB.editUserName(userID, newName);
                    String userName = userDB.getLoggedInUserName(userEmail);
                    nameUpdateProfile.setText(userName);
                    updateNameCardView.setVisibility(View.INVISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.VISIBLE);
                    backButton.setVisibility(View.VISIBLE);
                    userImageView.setVisibility(View.VISIBLE);
                    textView72.setVisibility(View.VISIBLE);
                    updatePasswordButton.setVisibility(View.VISIBLE);
                    Toast.makeText(UpdateProfile.this, "Name Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });


        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePasswordButton.setVisibility(View.INVISIBLE);
                cardView2.setVisibility(View.INVISIBLE);
                cardView3.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.INVISIBLE);
                userImageView.setVisibility(View.INVISIBLE);
                textView72.setVisibility(View.INVISIBLE);
                updatePasswordCardView.setVisibility(View.VISIBLE);
            }
        });


        donePasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = oldPasswordEditText.getText().toString();
                String oldPasswordFromDataBase = userDB.getPasswordByUserID(userID);
                String newPassword = newPasswordEditText.getText().toString();
                if(oldPassword.isEmpty()||newPassword.isEmpty()) {
                    hideKeyboard();
                    Toast.makeText(UpdateProfile.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!oldPassword.equals(oldPasswordFromDataBase)) {
                    hideKeyboard();
                    Toast.makeText(UpdateProfile.this, "Old Password is wrong", Toast.LENGTH_SHORT).show();
                }
                else {
                    hideKeyboard();
                    Toast.makeText(UpdateProfile.this, "Password Changed... Login Again", Toast.LENGTH_SHORT).show();
                    userDB.editPasswordByUserID(userID, newPassword);
                    userDB.logout();
                    Intent intent =new Intent(UpdateProfile.this, LoginPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Your custom code here
        // For example, you can navigate to another activity, show a dialog, or handle any specific logic
        Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed(); // This line is optional and can be omitted if you want to prevent the default back button behavior
    }



    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}


