package in.psgroup.psgroup;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.psgroup.psgroup.Fragments.PastvisitFragment;
import in.psgroup.psgroup.Fragments.UpcomingFragment;
import in.psgroup.psgroup.Models.AllReferenceBean;
import in.psgroup.psgroup.Models.ApiClient;
import in.psgroup.psgroup.Models.APIUrls;
import in.psgroup.psgroup.Models.PastvisitBean;
import in.psgroup.psgroup.Models.UpcomingBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyVisitActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String AccessToken;
    private ImageView backPress;
    UpcomingBean upcomingBean;
    PastvisitBean pastvisitBean;
    private ArrayList<UpcomingBean> upcomingList = new ArrayList<>();
    private ArrayList<PastvisitBean> pastvisitList = new ArrayList<>();
    private UpcomingFragment upcomingFragment;
    private  PastvisitFragment pastvisitFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_visit);
        initView();

    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        backPress = (ImageView) findViewById(R.id.ic_backVisit);
        upcomingFragment=new UpcomingFragment();
        pastvisitFragment = new PastvisitFragment();
        backPress.setOnClickListener(this);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(upcomingFragment,"Upcoming Visits");
        adapter.addFragment(pastvisitFragment, "Past Visits");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_backVisit:
                this.onBackPressed();
                break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
