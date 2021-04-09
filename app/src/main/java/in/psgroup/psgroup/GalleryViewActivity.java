package in.psgroup.psgroup;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.psgroup.psgroup.Adapters.ImagesViewpagerAdapter;
import in.psgroup.psgroup.Models.Gallery;

public class GalleryViewActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager mViewPager;
    ImageView imagePager, ic_backMore;
    ImagesViewpagerAdapter adapterView;
    private LinearLayout dotsLayout;
    private TextView tv_image_number;
    private TextView[] dots;
    private String Images[];
    private LinearLayout layout_empty;
    private TextView tv_empty,title;
    private AppBarLayout appbar;
    private int imagepos;
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            Toast.makeText(AddsImages.this,images[position],Toast.LENGTH_LONG).show();
//        image=images[position];
            addBottomDots(position);
            // changing the next button text 'NEXT' / 'GOT IT'

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_view);
        Images = getIntent().getExtras().getStringArray("Images");
        imagepos = getIntent().getExtras().getInt("position");
        initView();
        if (Images.length!=0){
            adapterView = new ImagesViewpagerAdapter(GalleryViewActivity.this, Images,imagepos);
            mViewPager.setAdapter(adapterView);
            mViewPager.setCurrentItem(imagepos);
            addBottomDots(imagepos);
           // title.setText(getIntent().getStringExtra("name"));
        } else {

        }
    }

    private void initView() {
        title = findViewById(R.id.title);
        tv_image_number= findViewById(R.id.tv_image_number);
        mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
        mViewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        ic_backMore = (ImageView) findViewById(R.id.ic_backMore);
        ic_backMore.setOnClickListener(this);
        appbar = findViewById(R.id.appbar);
    }

    private void addBottomDots(int currentPage) {
        String text =currentPage+1 +" of "+Images.length;
        tv_image_number.setText(text);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_backMore:
                this.onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
