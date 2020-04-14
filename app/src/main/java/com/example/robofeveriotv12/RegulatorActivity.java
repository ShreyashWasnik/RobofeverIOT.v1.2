package com.example.robofeveriotv12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class RegulatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulator);

        Switch switch2 = findViewById(R.id.switch2);
        final WebView webView= findViewById(R.id.webview1);
        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setProgress(0);
        seekBar.incrementProgressBy(50);
        seekBar.setMax(100);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://192.168.4.1/");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress>0 & progress<50)
                    webView.loadUrl("http://192.168.4.1/speed/1");
                if (progress>50 & progress<100)
                    webView.loadUrl("http://192.168.4.1/speed/2");


            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked== true){
                    webView.loadUrl("http://192.168.4.1/pump/on");
                }
                else {
                    webView.loadUrl("http://192.168.4.1/pump/off");
                }
            }
        });


    }
}
