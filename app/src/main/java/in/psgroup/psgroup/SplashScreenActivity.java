package in.psgroup.psgroup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import in.psgroup.psgroup.Models.UserSessionManager;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2500;
    Animation animFadeIn,animBlink,animZoomIn,bounce;
   UserSessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sessionManager=new UserSessionManager(getApplicationContext());
        bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        animFadeIn= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                if (sessionManager.checkLogin()) {

                    Intent i = new Intent(SplashScreenActivity.this, OnboardingActivity.class);
                    startActivity(i);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                } else {
                    Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(i);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                }//

            }
        }, SPLASH_TIME_OUT);
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
