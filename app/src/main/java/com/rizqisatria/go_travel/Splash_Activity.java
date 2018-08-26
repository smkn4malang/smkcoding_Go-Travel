package com.rizqisatria.go_travel;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Activity extends AppCompatActivity {

    TextView splsh;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
        logo = (ImageView)findViewById(R.id.logo);
        splsh = (TextView)findViewById(R.id.splash);

        Animation anim= AnimationUtils.loadAnimation(this,R.anim.muncul);
        logo.startAnimation(anim);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),GOTravel_Activity.class));
                finish();
            }
        },3000l);
    }
}
