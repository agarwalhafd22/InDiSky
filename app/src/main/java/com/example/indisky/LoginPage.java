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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.os.Handler;
import android.content.Context;



public class LoginPage extends AppCompatActivity {

    TextView forgotpassword, typewriter, signinTextView, textView14, privacyTextView, textView18, backTextView, tOSTextView;

    CardView cardView, email, cardViewLogin, password, cardViewCreate;
    LinearLayout linearLayout;


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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

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

        final Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);

        final Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);

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
                int delayMillis = 475;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cardViewLogin.setVisibility(View.VISIBLE);
                        backTextView.setVisibility(View.VISIBLE);
                    }
                }, delayMillis);
                linearLayout.startAnimation(slideUp);
                cardView.setVisibility(View.INVISIBLE);
                signinTextView.setVisibility(View.INVISIBLE);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int delayMillis = 475;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cardViewCreate.setVisibility(View.VISIBLE);
                        backTextView.setVisibility(View.VISIBLE);
                    }
                }, delayMillis);
                linearLayout.startAnimation(slideUp);
                cardView.setVisibility(View.INVISIBLE);
                signinTextView.setVisibility(View.INVISIBLE);
            }
        });

        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int delayMillis = 475;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cardView.setVisibility(View.VISIBLE);
                        signinTextView.setVisibility(View.VISIBLE);
                    }
                }, delayMillis);
                linearLayout.startAnimation(slideDown);
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

        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        slideDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

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
}