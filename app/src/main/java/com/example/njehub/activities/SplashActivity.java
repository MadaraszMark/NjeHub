package com.example.njehub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.njehub.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 1900;

    private TextView logoBox, txtSplashTitle, txtSplashSubtitle;
    private ProgressBar splashProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoBox = findViewById(R.id.logoBox);
        txtSplashTitle = findViewById(R.id.txtSplashTitle);
        txtSplashSubtitle = findViewById(R.id.txtSplashSubtitle);
        splashProgress = findViewById(R.id.splashProgress);

        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_scale);
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        logoBox.startAnimation(scaleAnimation);
        txtSplashTitle.startAnimation(fadeAnimation);
        txtSplashSubtitle.startAnimation(fadeAnimation);
        splashProgress.startAnimation(fadeAnimation);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            finish();
        }, SPLASH_DELAY);
    }
}