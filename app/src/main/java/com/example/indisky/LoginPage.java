package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import android.os.Handler;


public class LoginPage extends AppCompatActivity {

    TextView forgotpassword, typewriter, signinTextView, textView14, textView16, textView18, backTextView;

    CardView cardView, email, cardViewLogin, password;
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

        textView14=findViewById(R.id.textView14);
        textView16=findViewById(R.id.textView16);
        textView18=findViewById(R.id.textView18);
        signinTextView=findViewById(R.id.signinTextView);
        cardView=findViewById(R.id.cardView);
        linearLayout=findViewById(R.id.linearLayout);
        backTextView=findViewById(R.id.backTextView);
        email=findViewById(R.id.email);
        cardViewLogin=findViewById(R.id.cardViewLogin);
        password=findViewById(R.id.password);

        String text2="Terms of Service.";
        String text3="Privacy Policy";
        String text4="Cookies Policy";


        SpannableString spannableString2=new SpannableString(text2);
        spannableString2.setSpan(new UnderlineSpan(), 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textView14.setText(spannableString2);

        SpannableString spannableString3=new SpannableString(text3);
        spannableString3.setSpan(new UnderlineSpan(), 0, text3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textView16.setText(spannableString3);

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
                cardView.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
                signinTextView.setVisibility(View.INVISIBLE);
                backTextView.setVisibility(View.VISIBLE);
                cardViewLogin.setVisibility(View.VISIBLE);
            }
        });

        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTextView.setVisibility(View.INVISIBLE);
                cardViewLogin.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                signinTextView.setVisibility(View.VISIBLE);
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
}