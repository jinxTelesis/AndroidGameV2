package app.calcounter.com.individualproject3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.calcounter.com.individualproject3.Constants.Constant;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        /**
         * lambda that starts thread to op login
         * after splash screen
         */

        new Handler().postDelayed( ()-> {

            Intent startInt = new Intent(Splash.this, LoginActivity.class);
            startActivity(startInt);
            finish();

            // delay for splash screen
        }, Constant.SPLASH_TIME_OUT);
    }
}
