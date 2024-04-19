package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ManageUsers extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton addNewUser, removeUser;
    
    EditText emailEditTextAdmin, nameEditTextAdmin, passwordEditTextAdmin, userIDEditTextAdmin;

    TextView enterEmailTextView, enterNameTextView, enterPasswordTextView, enterUserIDTextViewAdmin;
    
    UserDB userDB;

    BookingDB bookingDB;

    PassengerDB passengerDB;

    PaymentDB paymentDB;
    
    Button addButtonAdmin, deleteUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        radioGroup=findViewById(R.id.radioGroup);
        addNewUser=findViewById(R.id.addNewUser);
        removeUser=findViewById(R.id.removeUser);
        emailEditTextAdmin=findViewById(R.id.emailEditTextAdmin);
        nameEditTextAdmin=findViewById(R.id.nameEditTextAdmin);
        passwordEditTextAdmin=findViewById(R.id.passwordEditTextAdmin);
        addButtonAdmin=findViewById(R.id.addButtonAdmin);
        enterEmailTextView=findViewById(R.id.enterEmailTextView);
        enterNameTextView=findViewById(R.id.enterNameTextView);
        enterPasswordTextView=findViewById(R.id.enterPasswordTextView);
        enterUserIDTextViewAdmin=findViewById(R.id.enterUserIDTextViewAdmin);
        userIDEditTextAdmin=findViewById(R.id.userIDEditTextAdmin);
        deleteUserButton=findViewById(R.id.deleteUserButton);
        
        userDB =new UserDB(ManageUsers.this);
        bookingDB =new BookingDB(ManageUsers.this);
        passengerDB =new PassengerDB(ManageUsers.this);
        paymentDB =new PaymentDB(ManageUsers.this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.addNewUser)
                {
                    enterNameTextView.setVisibility(View.VISIBLE);
                    enterPasswordTextView.setVisibility(View.VISIBLE);
                    nameEditTextAdmin.setVisibility(View.VISIBLE);
                    passwordEditTextAdmin.setVisibility(View.VISIBLE);
                    addButtonAdmin.setVisibility(View.VISIBLE);
                    enterUserIDTextViewAdmin.setVisibility(View.INVISIBLE);
                    userIDEditTextAdmin.setVisibility(View.INVISIBLE);
                    deleteUserButton.setVisibility(View.INVISIBLE);
                    addButtonAdmin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String email = emailEditTextAdmin.getText().toString();
                            String name = nameEditTextAdmin.getText().toString();
                            String password = passwordEditTextAdmin.getText().toString();
                            if(email.isEmpty()||name.isEmpty()||password.isEmpty())
                                Toast.makeText(ManageUsers.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                            else{
                                userDB.addNewUser(name, email, password);
                                Toast.makeText(ManageUsers.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else if(i==R.id.removeUser)
                {
                    enterNameTextView.setVisibility(View.INVISIBLE);
                    enterPasswordTextView.setVisibility(View.INVISIBLE);
                    nameEditTextAdmin.setVisibility(View.INVISIBLE);
                    passwordEditTextAdmin.setVisibility(View.INVISIBLE);
                    addButtonAdmin.setVisibility(View.INVISIBLE);
                    enterUserIDTextViewAdmin.setVisibility(View.VISIBLE);
                    userIDEditTextAdmin.setVisibility(View.VISIBLE);
                    deleteUserButton.setVisibility(View.VISIBLE);
                    deleteUserButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String email = emailEditTextAdmin.getText().toString();
                            String stringUserID = userIDEditTextAdmin.getText().toString();
                            if(email.isEmpty()||stringUserID.isEmpty())
                                Toast.makeText(ManageUsers.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                            else {
                                int userID = Integer.parseInt(stringUserID);
                                if(userID==userDB.getSessionUserID()){
                                    List<Integer> deletedBookingIDs = bookingDB.deleteBookingByUserID(userID);
                                    passengerDB.deletePassengerByBookingIDs(deletedBookingIDs);
                                    paymentDB.deletePaymentByBookingIDs(deletedBookingIDs);
                                    userDB.deleteUser(userID);
                                    Toast.makeText(ManageUsers.this, "User Deleted", Toast.LENGTH_SHORT).show();
                                    userDB.logout();
                                    Intent intent=new Intent(ManageUsers.this, LoginPage.class);
                                    startActivity(intent);
                                    finish();
                                }
                                int checkDelete=userDB.deleteUser(userID);
                                if(checkDelete==0)
                                    Toast.makeText(ManageUsers.this, "Enter Valid User ID", Toast.LENGTH_SHORT).show();
                                else {
                                    List<Integer> deletedBookingIDs = bookingDB.deleteBookingByUserID(userID);
                                    passengerDB.deletePassengerByBookingIDs(deletedBookingIDs);
                                    paymentDB.deletePaymentByBookingIDs(deletedBookingIDs);
                                    Toast.makeText(ManageUsers.this, "User Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });
        


    }
}