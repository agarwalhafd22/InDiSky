package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.VideoView;
import android.os.Handler;
import android.content.Context;



public class LoginPage extends AppCompatActivity {
    
    private UserDB userDB;

    TextView  typewriter, signinTextView, textView14, privacyTextView, textView18, backTextView, tOSTextView;

    CardView cardView, email, cardViewLogin, password, cardViewCreate;
    LinearLayout linearLayout;

    EditText nameCreateEditText, emailCreateEditText, passwordCreateEditText, passwordConfirmEditText, emailEditText, passwordEditText;

    Button signInButton, createAccountButton;


    private String[] texts = {
            "Fly for Experience...",
            "Fly for Safety...",
            "Fly for Connection...",
            "Fly for Comfort...",
            "Fly for Memories...",
            "Fly for Luxury...",
            "Fly for Exploration...",
            "Fly for Relaxation...",
            "Fly for Perfection...",
    };
    private int textIndex = 0;
    private int index = 0;
    private long delay = 100; // in milliseconds

    private static final String VIDEO_POSITION_KEY = "video_position";
    private int videoPosition = 0;

    int flag=-1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userDB=new UserDB(LoginPage.this);

        boolean isUserLoggedIn=userDB.isUserLoggedIn();
        if(isUserLoggedIn)
        {
            String userEmail=userDB.getLoggedInUserEmail();
            Intent intent =new Intent(LoginPage.this, MainActivity.class);
            startActivity(intent);
        }

        VideoView videoView =findViewById(R.id.videoView);

        typewriter=findViewById(R.id.typewriter);

        privacyTextView=findViewById(R.id.privacyTextView);
        textView18=findViewById(R.id.textView18);
        signinTextView=findViewById(R.id.signinTextView);
        cardView=findViewById(R.id.cardView);
        linearLayout=findViewById(R.id.linearLayout);
        backTextView=findViewById(R.id.backTextView);
        email=findViewById(R.id.email);
        cardViewLogin=findViewById(R.id.cardViewLogin);
        cardViewCreate=findViewById(R.id.cardViewCreate);
        password=findViewById(R.id.password);
        tOSTextView=findViewById(R.id.tOSTextView);
        signInButton=findViewById(R.id.signInButton);
        createAccountButton=findViewById(R.id.createAccountButton);
        nameCreateEditText=findViewById(R.id.nameCreateEditText);
        emailCreateEditText=findViewById(R.id.emailCreateEditText);
        passwordCreateEditText=findViewById(R.id.passwordCreateEditText);
        passwordConfirmEditText=findViewById(R.id.passwordConfirmEditText);
        emailEditText=findViewById(R.id.emailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);



        final Animation slideUpCreate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up_create);

        final Animation slideDownCreate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down_create);

        final Animation slideUpLogin = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up_login);

        final Animation slideDownLogin = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down_login);

        String text2="Terms of Service.";
        String text3="Privacy Policy";
        String text4="Cookies Policy";


        SpannableString spannableString2=new SpannableString(text2);
        spannableString2.setSpan(new UnderlineSpan(), 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        tOSTextView.setText(spannableString2);

        SpannableString spannableString3=new SpannableString(text3);
        spannableString3.setSpan(new UnderlineSpan(), 0, text3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        privacyTextView.setText(spannableString3);

        SpannableString spannableString4=new SpannableString(text4);
        spannableString4.setSpan(new UnderlineSpan(), 0, text4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textView18.setText(spannableString4);




        String videoPath = "android.resource://"+ getPackageName() + "/"+ R.raw.flightvideo;
        videoView.setVideoURI(Uri.parse(videoPath));

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Restart the video when it finishes
                videoView.start();
            }
        });
        videoView.start();

        if (savedInstanceState != null) {
            videoPosition = savedInstanceState.getInt(VIDEO_POSITION_KEY);
        }

        int delayMillis = 1500;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                typeWriterAnimation();
            }
        }, delayMillis);


        signinTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                int delayMillis = 175;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cardViewLogin.setVisibility(View.VISIBLE);
                        backTextView.setVisibility(View.VISIBLE);
                        signInButton.setVisibility(View.VISIBLE);
                    }
                }, delayMillis);
                linearLayout.startAnimation(slideDownLogin);
                cardView.setVisibility(View.INVISIBLE);
                signinTextView.setVisibility(View.INVISIBLE);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                int delayMillis = 175;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cardViewCreate.setVisibility(View.VISIBLE);
                        backTextView.setVisibility(View.VISIBLE);
                        createAccountButton.setVisibility(View.VISIBLE);
                    }
                }, delayMillis);
                linearLayout.startAnimation(slideDownCreate);
                cardView.setVisibility(View.INVISIBLE);
                signinTextView.setVisibility(View.INVISIBLE);
            }
        });

        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int delayMillis = 175;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cardView.setVisibility(View.VISIBLE);
                        signinTextView.setVisibility(View.VISIBLE);
                    }
                }, delayMillis);
                if(flag==1)
                    linearLayout.startAnimation(slideUpCreate);
                else
                    linearLayout.startAnimation(slideUpLogin);
                createAccountButton.setVisibility(View.INVISIBLE);
                signInButton.setVisibility(View.INVISIBLE);
                backTextView.setVisibility(View.INVISIBLE);
                cardViewLogin.setVisibility(View.INVISIBLE);
                cardViewCreate.setVisibility(View.INVISIBLE);
            }
        });

        tOSTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNetworkAvailable(LoginPage.this)) {
                    Toast.makeText(LoginPage.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else {
                    String Code = "1";
                    Intent intent = new Intent(LoginPage.this, com.example.indisky.pdfView.class);
                    intent.putExtra("Code", Code);
                    startActivity(intent);
                }
            }
        });

        privacyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNetworkAvailable(LoginPage.this)) {
                    Toast.makeText(LoginPage.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else {
                    String Code = "2";
                    Intent intent = new Intent(LoginPage.this, com.example.indisky.pdfView.class);
                    intent.putExtra("Code", Code);
                    startActivity(intent);
                }
            }
        });

//        slideUpCreate.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//        slideDownCreate.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                String name = nameCreateEditText.getText().toString();
                String email = emailCreateEditText.getText().toString();
                String password = passwordCreateEditText.getText().toString();
                String passwordConfirm = passwordConfirmEditText.getText().toString();
                if(name.isEmpty()||email.isEmpty()||password.isEmpty()||passwordConfirm.isEmpty())
                    Toast.makeText(LoginPage.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                else if(!password.equals(passwordConfirm))
                    Toast.makeText(LoginPage.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                else {
                    userDB.addNewUser(name, email, password);
                    nameCreateEditText.setText(null);
                    emailCreateEditText.setText(null);
                    passwordCreateEditText.setText(null);
                    passwordConfirmEditText.setText(null);
                    Toast.makeText(LoginPage.this, "Account Created! Sign-In to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                String email=emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if(email.isEmpty()||password.isEmpty())
                    Toast.makeText(LoginPage.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                else
                {
                    int check=userDB.loginUser(email, password);
                    if(check!=0)
                    {
                        emailEditText.setText(null);
                        passwordEditText.setText(null);
                        Toast.makeText(LoginPage.this, "Logged In...", Toast.LENGTH_SHORT).show();
                        userDB.createSession(email, check);
                        Intent intent=new Intent(LoginPage.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(LoginPage.this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        VideoView videoView =findViewById(R.id.videoView);
        // Start or resume video playback
        videoView.seekTo(videoPosition);
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        VideoView videoView =findViewById(R.id.videoView);
        // Pause video playback and save current position
        videoPosition = videoView.getCurrentPosition();
        videoView.pause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save current video position
        outState.putInt(VIDEO_POSITION_KEY, videoPosition);
    }


    private void typeWriterAnimation() {
        if (textIndex < texts.length) {
            if (index < texts[textIndex].length()) {
                typewriter.setText(texts[textIndex].subSequence(0, index + 1));
                index++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        typeWriterAnimation();
                    }
                }, delay);
            } else {
                // Start erasing animation after typing is complete
                eraseTextAnimation();
            }
        }
        else
        {
            textIndex =0;
            typeWriterAnimation();
        }
    }

    private void eraseTextAnimation() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index >= 7) {
                    typewriter.setText(texts[textIndex].subSequence(0, index));
                    index--;
                    eraseTextAnimation();
                } else {
                    // Move to the next text
                    textIndex++;
                    index = 8;
                    typeWriterAnimation();
                }
            }
        }, delay);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}