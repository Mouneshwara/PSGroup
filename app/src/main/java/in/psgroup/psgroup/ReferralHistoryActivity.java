package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.psgroup.psgroup.Fragments.AllReferenceFragment;
import in.psgroup.psgroup.Fragments.PastvisitFragment;
import in.psgroup.psgroup.Fragments.ProjectWiseReferenceFragment;
import in.psgroup.psgroup.Fragments.UpcomingFragment;
import in.psgroup.psgroup.Models.AllReferenceBean;
import in.psgroup.psgroup.Models.FloorPlans;
import in.psgroup.psgroup.Models.Gallery;
import in.psgroup.psgroup.Models.Highlight;
import in.psgroup.psgroup.Models.NearbyLandmark;
import in.psgroup.psgroup.Models.PastvisitBean;
import in.psgroup.psgroup.Models.ProjectDetailBean;
import in.psgroup.psgroup.Models.ProjectSpecificationTwoList;
import in.psgroup.psgroup.Models.ProjectWiseBean;
import in.psgroup.psgroup.Models.ReferralHistoryBean;
import in.psgroup.psgroup.Models.Siteplan;
import in.psgroup.psgroup.Models.Testimonial;
import in.psgroup.psgroup.Models.TotalPointsBean;
import in.psgroup.psgroup.Models.UpcomingBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ProjectDetailsLoader;
import in.psgroup.psgroup.Network.ReferralHistoryLoader;
import in.psgroup.psgroup.Utility.Utils;

public class ReferralHistoryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<HashMap<String, String>>, View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView ic_back;
    private ArrayList<AllReferenceBean> upcomingList = new ArrayList<>();
    AllReferenceBean allReferenceBean;
    ProjectWiseBean projectWiseBean;
    private AllReferenceFragment allReferenceFragment;
    private ProjectWiseReferenceFragment projectWiseReferenceFragment;
    private UserSessionManager session;
    private String customerCode,access_token;
    ArrayList<ReferralHistoryBean> referralHistoryBeanArrayList = new ArrayList<>();
    ProgressDialog loading;
    PsGroupApplication myApplication;

    private int response_code = 0;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral_history);
        myApplication = (PsGroupApplication) this.getApplication();

        session = new UserSessionManager(getApplicationContext());
        initialize();

        HashMap<String, String> user = session.getUserDetails();
        customerCode= user.get(UserSessionManager.KEY_loyaltycode);
        access_token= user.get(UserSessionManager.KEY_accessToken);

        callLoader(0);
    }

    private void initialize() {
        ic_back =(ImageView)findViewById(R.id.ic_back);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        allReferenceFragment=new AllReferenceFragment();
        projectWiseReferenceFragment = new ProjectWiseReferenceFragment();
        tabLayout.setupWithViewPager(viewPager);
        ic_back.setOnClickListener(this);

    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(allReferenceFragment,"All References");
        adapter.addFragment(projectWiseReferenceFragment, "Project Wise Reference");
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

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        switch (id) {

            case 0:
                HashMap<String, String> input = new HashMap<>();
                input.put("customerCode", customerCode);
                result = new ReferralHistoryLoader(this,access_token, input);

                break;

        }
        return result;

    }


    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        if (data != null && !data.isEmpty()) {
            try {
                setData(data, id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Utils.showToast(this,"something went wrong");
        }
    }

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }


    private void callLoader(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }
    }

    private void setData(HashMap<String, String> data, int id) throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        Gson gson = new Gson();


        Intent i;
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    switch (id) {
                        case 0:


                            if (data.get("data") != null) {
                                JSONObject jsonarray = new JSONObject(data.get("data"));

                                JSONArray datajson = jsonarray.getJSONArray("data");
                                for (int k = 0; k < datajson.length();k++) {

                                    referralHistoryBeanArrayList.add(gson.fromJson(datajson.getJSONObject(k).toString(), ReferralHistoryBean.class));
                                }

                                Bundle b = new Bundle();
                                b.putParcelableArrayList("referralHistoryBeanArrayList", referralHistoryBeanArrayList);
                                allReferenceFragment.setArguments(b);


                                Bundle c = new Bundle();
                                c.putParcelableArrayList("referralHistoryBeanArrayList", referralHistoryBeanArrayList);
                                projectWiseReferenceFragment.setArguments(c);
                                setupViewPager(viewPager);


                            }




                            break;


                    }
                    break;
                case 400:
                    Utils.showToast(this, data.get("message"));
                    break;
                case 401:
                    Utils.showToast(this, data.get("message"));
                    break;
                case 404:
                    Utils.showToast(this, data.get("message"));
                    break;
                case 500:
                    Intent serverIntent = new Intent(this, ServerErrorActivity.class);
                    //serverIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.startActivity(serverIntent);
                    this.finish();
                    break;
                default:
                    Intent intent = new Intent(this, ServerErrorActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.startActivity(intent);
                    this.finish();
                    break;
            }
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
