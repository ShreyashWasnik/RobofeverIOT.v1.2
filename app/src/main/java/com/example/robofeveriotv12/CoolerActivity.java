package com.example.robofeveriotv12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class CoolerActivity extends AppCompatActivity {
    private WebView Wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooler);
        Wv = findViewById(R.id.webView);
        Wv.setWebChromeClient(new WebChromeClient());
        Wv.loadUrl("http://192.168.4.1/");
        Button powerOn = findViewById(R.id.button2);
        Button powerOff = findViewById(R.id.button);
        Button logout = findViewById(R.id.signout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent i = new Intent(CoolerActivity.this,MainActivity.class);
                startActivity(i);

            }
        });

        powerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wv.loadUrl("http://192.168.4.1/power/on");
            }
        });

        powerOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wv.loadUrl("http://192.168.4.1/power/off");
            }
        });


    }
}
