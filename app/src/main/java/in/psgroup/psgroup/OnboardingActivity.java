package in.psgroup.psgroup;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.psgroup.psgroup.Adapters.ViewPagerAdapter;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    TextView header,message,tv_skip;
    RelativeLayout backImage;

    //initialzing view pager elements
    private String [] messages=new String[]{"Stay updated with your payment schedules and track your ledger on the go",
            "Get ready answers to your queries or connect with our team within seconds for further assistance.",
            "Earn and redeem reward points at the click of a button. Check the status of your referral on the go."};
    private String [] headers= new String[]{"Easy Tracking","Connectivity","Happy Ours"};
    private int[] image =new int[]{R.drawable.onboarding_one,R.drawable.onboarding_two,R.drawable.onboarding_three};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //initializing method
        initView();

        //skip button click
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardingActivity.this,LoginActivity.class));
                OnboardingActivity.this.finish();
            }
        });

        //setting adapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,headers,messages);
        viewPager.setAdapter(viewPagerAdapter);

        //setting dotscount
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(18, 0, 18, 0);
            sliderDotspanel.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                showToast(String.valueOf(position));
                if(position==0){
                    backImage.setBackground(getResources().getDrawable(image[0]));
                }
                else if(position==1){
                    backImage.setBackground(getResources().getDrawable(image[1]));
                }
                else{
                    backImage.setBackground(getResources().getDrawable(image[2]));
                }

                }


            @Override
            public void onPageSelected(int position) {

               if(position==dotscount-1) {
                   tv_skip.setText("GET STARTED");

               }
               else
                   tv_skip.setText("SKIP");

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                showToast(String.valueOf(state));

            }
        });
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        tv_skip=(TextView)findViewById(R.id.tv_skip);
        backImage = (RelativeLayout) findViewById(R.id.backImage);
    }

    private  void  showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
