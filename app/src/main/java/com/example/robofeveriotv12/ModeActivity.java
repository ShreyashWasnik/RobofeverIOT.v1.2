package com.example.robofeveriotv12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ModeActivity extends AppCompatActivity {
    private WebView Wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        final WebView Wv= findViewById(R.id.webview2);
        Wv.setWebChromeClient(new WebChromeClient());
        Wv.loadUrl("http://192.168.4.1/");


        final Button auto = findViewById(R.id.auto);
        Button manual = findViewById(R.id.manual);
        Button logout = findViewById(R.id.signout1);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent i = new Intent(ModeActivity.this,MainActivity.class);
                startActivity(i);

            }
        });

        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CoolerActivity.class));
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wv.loadUrl("http://192.168.4.1/mode/auto");
            }
        });
        Wv.setVisibility(View.GONE);


    }
}
