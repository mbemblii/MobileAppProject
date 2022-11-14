package tn.esprit.presto;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_splash);


        h.postDelayed(new Runnable() {
                          @Override
                          public void run() {
                              Intent i = new Intent(SplashActivity.this,MainActivity.class);
                              startActivity(i);
                              finish();
                          }
                      },2000);


    }
}