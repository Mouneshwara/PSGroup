package in.psgroup.psgroup;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.psgroup.psgroup.Fragments.PaidFragment;
import in.psgroup.psgroup.Fragments.PastvisitFragment;
import in.psgroup.psgroup.Fragments.PendingFragment;
import in.psgroup.psgroup.Fragments.UpcomingFragment;
import in.psgroup.psgroup.Models.PaidBean;
import in.psgroup.psgroup.Models.PastvisitBean;
import in.psgroup.psgroup.Models.PendingBean;
import in.psgroup.psgroup.Models.UpcomingBean;
import in.psgroup.psgroup.Models.UserSessionManager;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabs;
    private ImageView ic_back;
    private UserSessionManager session;
    private ArrayList<PendingBean> pendingList = new ArrayList<>();
    private ArrayList<PaidBean> paidList = new ArrayList<>();
    PendingBean pendingBean;
    PaidBean paidBean;
    private PendingFragment pendingFragment;
    private PaidFragment paidFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initialize();
        setupViewPager(viewPager);
    }

    private void initialize() {
        ic_back = (ImageView)findViewById(R.id.ic_back);
        tabs = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        pendingFragment=new PendingFragment();
        paidFragment = new PaidFragment();
        ic_back.setOnClickListener(this);
        tabs.setupWithViewPager(viewPager);
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(pendingFragment,"Pending");
        adapter.addFragment(paidFragment, "Paid");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_back:
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
