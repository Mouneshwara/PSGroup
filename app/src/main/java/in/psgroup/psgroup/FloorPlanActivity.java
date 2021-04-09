package in.psgroup.psgroup;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.psgroup.psgroup.Adapters.ImagesViewpagerAdapter;

public class FloorPlanActivity extends AppCompatActivity {

    int imagepos;

    ImageView backPress,download;
    private LinearLayout dotsLayout;
    ViewPager mViewPager;
    ImageView imagePager;
    ImagesViewpagerAdapter adapterView;
    private TextView[] dots;
    private String[] Images;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
//            Images=extras.getStringArray("FloorArray");
            Log.d("Tag","FloorArray:"+Images);
//            Log.d("Tag","FloorPlan"+myProperty);
//            floorPlansList=myProperty.getFloorPlans();
//            Log.d("Tag","FloorPlan"+floorPlansList);
//            for(int i=0;i<floorPlansList.size();i++){
//                Log.d("Tag","FloorPlanImages"+floorPlansList.get(i).getDetail_desc());
//                Images[i]=floorPlansList.get(i).getDetail_desc();
//            }
        }
        initView();
        adapterView = new ImagesViewpagerAdapter(FloorPlanActivity.this,Images,imagepos);
        mViewPager.setAdapter(adapterView);
        addBottomDots(0);

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[Images.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[0]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[0]);
    }

    private void initView() {
        backPress = findViewById(R.id.ic_backFloor);
        download = findViewById(R.id.downloadFloor);
        mViewPager = (ViewPager) findViewById(R.id.viewPageFloorPlan);
        mViewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);


        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            Toast.makeText(AddsImages.this,images[position],Toast.LENGTH_LONG).show();
//            image=images[position];
            addBottomDots(position);
            // changing the next button text 'NEXT' / 'GOT IT'

        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    public void onBackPressed() {
        super.onBackPressed();
    }

    private  void  showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
