package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class pdfView extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        Intent intent=getIntent();
        String Code = intent.getStringExtra("Code");

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webView.getSettings().setJavaScriptEnabled(true);

        if(Code.equals("1"))
            webView.loadUrl("https://www.dropbox.com/scl/fi/1asqrt8hpisauntpubqj5/DBS_SRS.pdf?rlkey=6lpbolhpultn11suc1aj9m1s6&dl=0");
        else
            webView.loadUrl("https://www.dropbox.com/scl/fi/sj1zl4le9tw5a4rjsu5ga/Synopsis.pdf?rlkey=p85dt0svrzon0yhoj9knp22ug&dl=0");
    }
}