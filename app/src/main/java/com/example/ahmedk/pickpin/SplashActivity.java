package com.example.ahmedk.pickpin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;

public class SplashActivity extends AppCompatActivity {

    LottieAnimationView mlottieAnimationView;
    String mAnimFile = "world_locations.json";
    private static int SPLASH_TIME_OUT = 3000;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mlottieAnimationView = findViewById(R.id.lottie_animation_view);
        
        playAnim();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void playAnim() {
        LottieComposition.Factory.fromAssetFileName(this, mAnimFile, composition -> {
            mlottieAnimationView.setComposition(composition);
            mlottieAnimationView.playAnimation();
        });
    };

    @Override
    protected void onDestroy() {
        mlottieAnimationView.cancelAnimation();
        super.onDestroy();
    }
}


