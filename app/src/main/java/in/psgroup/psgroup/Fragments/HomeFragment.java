package in.psgroup.psgroup.Fragments;

import android.animation.ObjectAnimator;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import in.psgroup.psgroup.Adapters.CardViewPagerAdapter;
import in.psgroup.psgroup.Adapters.HomeBlogerAdapter;
import in.psgroup.psgroup.Adapters.HomeOfferAdapter;
import in.psgroup.psgroup.Adapters.LaunchAdapter;
import in.psgroup.psgroup.Adapters.OffersAdapter;
import in.psgroup.psgroup.HomeActivity;
import in.psgroup.psgroup.Interfaces.CallLoaderInterface;
import in.psgroup.psgroup.Models.BlogBean;
import in.psgroup.psgroup.Models.HomeEventsBean;
import in.psgroup.psgroup.Models.LaunchBean;
import in.psgroup.psgroup.Models.MyProperty;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.HomeFragmentLoader;
import in.psgroup.psgroup.OffersActivity;
import in.psgroup.psgroup.PsGroupApplication;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.NeigbhourBean;
import in.psgroup.psgroup.Models.OffersBean;
import in.psgroup.psgroup.Models.TopPropertyBean;
import in.psgroup.psgroup.Utility.Utils;

public class HomeFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>>, CallLoaderInterface {
    Context thisContext;
    View view;
    private int response_code = 0;
    UserSessionManager session;
    Boolean expandable = true, expand = false;
    ImageView offer_next, offer_image, iv_image;
    SeekBar seekBar;
    RelativeLayout rr_moreImage, rl_myproperties;
    RecyclerView.LayoutManager layoutManager;
    ViewPager cardViewPager, blogViewPager,launchViewPager,offerViewPager;
    CardViewPagerAdapter viewPagerAdapter;
    TextView tv_viweAll, favProject, favLocation, favDate, moreImages, view_all_offer, tv_offers_event, offer, offer_read_more, tv_title,
            tv_events, date_each, month, event_landmark, tv_blog_desc, blog_read_more;
    String home_data, off_title, off_event, flag = "", access_token;
    int notification;
    private String fragment_data;
    ArrayList<TopPropertyBean> topPropertyBeanArrayList = new ArrayList<>();
    ArrayList<BlogBean> blogBeanArrayList = new ArrayList<>();
    private String sitePlan;
    HomeBlogerAdapter blogerAdapter;
    NeigbhourBean neigbhourBean;
    ArrayList<NeigbhourBean> neigbhourBeanArrayList = new ArrayList<>();
    MyProperty myProperty;
    private ArrayList<MyProperty> myProperties = new ArrayList<>();
    ImageView property_image1, property_image2, property_image;
    RelativeLayout property_image3;
    String[] items;
    private PsGroupApplication myApplication;
    ArrayList<OffersBean> offersList = new ArrayList<OffersBean>();
    private int id;
    ProgressDialog loading;
    HomeEventsBean eventsBean;
    ArrayList<HomeEventsBean> eventsList = new ArrayList<HomeEventsBean>();
    OffersBean offerbean;
    OffersAdapter offersAdapter;
    LaunchBean launchBean;
    HomeOfferAdapter homeOfferAdapter;
    Timer timer, timer1;
    private int currentpostion = 0;
    ArrayList<LaunchBean>launchBeanArrayList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        thisContext = container.getContext();
        myApplication = (PsGroupApplication) getActivity().getApplication();
        session = new UserSessionManager(thisContext);


        HashMap<String, String> user = session.getUserDetails();
        access_token = user.get(UserSessionManager.KEY_accessToken);//accessToken
        callLoader(0);
       /* if (session.checkguest()) {
            flag = "guest";
            callLoader(0);
            access_token = user.get(UserSessionManager.KEY_accessToken);//accessToken

        } else {

            access_token = user.get(UserSessionManager.KEY_accessToken);//accessToken
            callLoader(0);
        }*/

        initPropertyList();
        initBlogList();
        initView();
        initLaunchList(launchBeanArrayList);

        return view;
    }


    private void initView() {
        CardView card_offer  =(CardView) view.findViewById(R.id.card_offer);

        tv_viweAll = view.findViewById(R.id.tv_viewall);
        /*view_all_offer = view.findViewById(R.id.view_all_offer);
        view_all_offer.setOnClickListener(this);*/
        /*tv_viweAll.setOnClickListener(this);*/
        rl_myproperties = view.findViewById(R.id.rl_myproperties);
        cardViewPager = view.findViewById(R.id.cardView);
        cardViewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        blogViewPager = view.findViewById(R.id.blogViewPager);
        blogViewPager.addOnPageChangeListener(blogPageChangeListener);
        offer_image = view.findViewById(R.id.offer_image);
        tv_title = view.findViewById(R.id.tv_title);
        tv_events = view.findViewById(R.id.tv_events);
        iv_image = view.findViewById(R.id.iv_image);
        date_each = view.findViewById(R.id.date_each);
        month = view.findViewById(R.id.month);
        offerViewPager = view.findViewById(R.id.offerViewPager);
        event_landmark = view.findViewById(R.id.event_landmark);
        //offer_read_more = card_offer.findViewById(R.id.offer_read_more);
        launchViewPager = view.findViewById(R.id.launchViewPager);
        blog_read_more = view.findViewById(R.id.blog_read_more);
        offer = view.findViewById(R.id.offer);
        tv_offers_event = view.findViewById(R.id.tv_offers_event);
        tv_blog_desc = view.findViewById(R.id.tv_blog_desc);
        if (flag.equals("guest")) {
            rl_myproperties.setVisibility(View.GONE);
            cardViewPager.setVisibility(View.GONE);

        } else {

        }
     //   offer_read_more.setOnClickListener(this);



    }
    private void initLaunchList(ArrayList<LaunchBean>launchBeanArrayList) {
        if(launchBeanArrayList.size()!=0) {
            String messages[] = new String[launchBeanArrayList.size()];
            String headers[];
            final String image[] = new String[launchBeanArrayList.size()];
            String names[] = new String[launchBeanArrayList.size()];
            String place[] = new String[launchBeanArrayList.size()];

            for (int i = 0; i < launchBeanArrayList.size(); i++) {

                launchBean = launchBeanArrayList.get(i);

                image[i] = launchBean.getImage();
                names[i] = launchBean.getLaunch_name();
                messages[i] = launchBean.getLaunch_description();
            }
            LaunchAdapter launchAdapter = new LaunchAdapter(launchBeanArrayList, getActivity().getApplicationContext());
            launchViewPager.setAdapter(launchAdapter);
            setPagerPadding(launchViewPager, 0);
        }
    }

    private void setBlogAdapters(final ArrayList<BlogBean> blogBeanArrayList) {
        blogerAdapter = new HomeBlogerAdapter(blogBeanArrayList, getActivity().getApplicationContext());
        blogViewPager.setAdapter(blogerAdapter);
        setPagerPadding(blogViewPager,0);

    }

    private void setOfferAdapters(final ArrayList<OffersBean> offersBeanArrayList) {
        homeOfferAdapter = new HomeOfferAdapter(getActivity().getApplicationContext(),offersBeanArrayList);
        offerViewPager.setAdapter(homeOfferAdapter);
        setPagerPadding1(offerViewPager,0);

    }

    private void setAdapters(final ArrayList<MyProperty> myProperties) {
        viewPagerAdapter = new CardViewPagerAdapter(myProperties, getActivity().getApplicationContext());
        cardViewPager.setAdapter(viewPagerAdapter);
        setPagerPadding(cardViewPager,0);
       startTimer();
    }


    private void startTimer(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                cardViewPager.post(new Runnable() {

                    @Override
                    public void run() {
                        if (cardViewPager.getCurrentItem() == myProperties.size() - 1)
                            currentpostion = 0;
                        else {
                            currentpostion = cardViewPager.getCurrentItem() + 1;
                        }
                        cardViewPager.setCurrentItem((currentpostion));
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 5000, 1000);

    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentpostion = position;
            timer.cancel();
            setPagerPadding(cardViewPager, position);
            startTimer();
            // changing the next button text 'NEXT' / 'GOT IT'

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    ViewPager.OnPageChangeListener blogPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setPagerPadding(blogViewPager, position);
            // changing the next button text 'NEXT' / 'GOT IT'

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public HomeFragment() {

    }

    @Override
    public void onClick(View v) {
       /* Intent i = new Intent(getActivity().getApplicationContext(), GalleryViewActivity.class);
        i.putExtra("Images",items);*/
        switch (v.getId()) {
            /*case R.id.tv_viewall:

                Intent i = new Intent(thisContext, MyProperties.class);
                i.putParcelableArrayListExtra("myProperties", myProperties);

                i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(i);
                break;*/

            case R.id.offer_read_more:

                if (!expand) {
                    expand = true;
                    ObjectAnimator animation = ObjectAnimator.ofInt(tv_offers_event, "maxLines", 40);
                    animation.setDuration(100).start();
                    offer_read_more.setText("Read Less");
                } else {
                    expand = false;
                    ObjectAnimator animation = ObjectAnimator.ofInt(tv_offers_event, "maxLines", 4);
                    animation.setDuration(100).start();
                    offer_read_more.setText("Read More");
                }
                break;

            case R.id.view_all_offer:
                Intent k = new Intent(thisContext, OffersActivity.class);

                //Bundle bundle = new Bundle();
                // bundle.putParcelableArrayList("offer", offersList);
                // k.putExtras(bundle);
                startActivity(k);
                break;


        }
    }


    private void showToast(String msg) {
        Toast.makeText(thisContext, "clicked", Toast.LENGTH_SHORT).show();
    }

    private void initPropertyList() {
        myProperties.clear();

    }

    private void initBlogList() {
        blogBeanArrayList.clear();

    }

    private void callLoader(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(thisContext);
        }
    }

    @Override
    public android.support.v4.content.Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(thisContext, " Loading.....", "wait....", false, false);
        android.support.v4.content.Loader<HashMap<String, String>> result = null;
        this.id = id;
        switch (id) {
            case 0:
                HashMap<String, String> input = new HashMap<>();
                if (flag == "guest") {
                    input.put("ctype", "guest");
                } else {
                    input.put("ctype", "customer");
                }
                result = new HomeFragmentLoader(thisContext, access_token, input);
                break;

        }
        return result;

    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        getLoaderManager().destroyLoader(loader.getId());
        if (data != null && !data.isEmpty()) {
            Log.d("tag", "Data:" + String.valueOf(data.size()));
            try {
                setData(data, id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Utils.showToast(thisContext, "something went wrong");
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<HashMap<String, String>> loader) {

    }

    private void setData(HashMap<String, String> data, int id) throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));

        ArrayList<TopPropertyBean> topPropertyBeanArrayList = new ArrayList<>();
        ArrayList<BlogBean> blogBeanArrayList = new ArrayList<>();
        ArrayList<LaunchBean>launchBeanArrayList=new ArrayList<>();
        ArrayList<NeigbhourBean> neigbhourBeanArrayList = new ArrayList<>();
        ArrayList<OffersBean>offersBeans = new ArrayList<>();
        //   ArrayList<OffersBean> offersList = new ArrayList<OffersBean>();

        Gson gson = new Gson();
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success

                    switch (id) {
                        case 0:
                            JSONObject jsonObject = new JSONObject(data.get("data"));
                            notification=jsonObject.getInt("notification_count");
                            Type type_MyProperty = new TypeToken<ArrayList<MyProperty>>() {
                            }.getType();
                            myProperties = gson.fromJson(jsonObject.getJSONArray("myproperties").toString(),type_MyProperty);
                            setAdapters(myProperties);

                            Type type_blog = new TypeToken<ArrayList<BlogBean>>() {
                            }.getType();
                            blogBeanArrayList = gson.fromJson(jsonObject.getJSONArray("blog").toString(),type_blog);
                            setBlogAdapters(blogBeanArrayList);

                            Type type_offers = new TypeToken<ArrayList<OffersBean>>() {
                            }.getType();
                            offersList = gson.fromJson(jsonObject.getJSONArray("offers").toString(),type_offers);
                            setOfferAdapters(offersList);

                            Type type_events = new TypeToken<ArrayList<HomeEventsBean>>() {
                            }.getType();
                            if(jsonObject.has("events")) {
                                eventsList = gson.fromJson(jsonObject.getJSONArray("events").toString(), type_events);
                            }
                            eventsBean = eventsList.get(0);
                            tv_title.setText(eventsBean.getEventTitle());
                            tv_events.setText(eventsBean.getEventShortNote());
                            Picasso.get().
                                    load(eventsBean.getEventImage()).placeholder(R.drawable.thumbnail)
                                    .into(iv_image);
                            date_each.setText(eventsBean.getEventDate());
                            event_landmark.setText(eventsBean.getEventLocation());

                            String notdate = eventsBean.getEventDate();
                            String date = dateset(notdate);
                            date_each.setText(date);

                            String notdate2 = eventsBean.getEventDate();
                            String date2 = dateset1(notdate2);
                            month.setText(date2);


                            Type type_launches = new TypeToken<ArrayList<LaunchBean>>() {
                            }.getType();
                            if(jsonObject.has("newlaunch")) {
                                launchBeanArrayList = gson.fromJson(jsonObject.getJSONArray("newlaunch").toString(), type_launches);
                            }
                            initLaunchList(launchBeanArrayList);

                            HomeActivity.notificationCount=notification;
                            HomeActivity.showNotification();
                            break;

                        case 400:
                            Utils.showToast(thisContext, data.get("message"));
                            break;
                        case 401:
                            Utils.showToast(thisContext, data.get("message"));
                            break;
                        case 404:
                            Utils.showToast(thisContext, data.get("message"));
                            break;
                        case 500:
                            Utils.showToast(thisContext, data.get("message"));
                            break;
                        default:

                            break;
                    }
            }
        }

    }

    private String dateset(String notdate) {
        Date timeform = null;
        Date date = null;
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat1.parse(notdate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        simpleDateFormat1 = new SimpleDateFormat("dd");
        String date1 = simpleDateFormat1.format(date);

        String value = date1;
        return value;


    }

    private String dateset1(String notdate) {
        Date timeform = null;
        Date date = null;
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat1.parse(notdate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        simpleDateFormat1 = new SimpleDateFormat("MMM");
        String date1 = simpleDateFormat1.format(date);

        String value = date1;
        return value;


    }

    private void setPagerPadding(ViewPager viewPager, int position) {
        if (viewPager.getAdapter().getCount() > 1) {
            if (position == 0) {
                viewPager.setPadding(25, 0, 40, 0);
                viewPager.setClipToPadding(false);
                viewPager.setPageMargin(15);
            }
            else if(position==(viewPager.getAdapter().getCount())-1) {
                viewPager.setPadding(40, 0, 25, 0);
                viewPager.setClipToPadding(false);
                viewPager.setPageMargin(15);
            }
            else {
                viewPager.setPadding(40, 0, 40, 0);
                viewPager.setClipToPadding(false);
                viewPager.setPageMargin(15);
            }
        } else {
            viewPager.setPadding(25, 0, 25, 0);
            viewPager.setClipToPadding(false);
            viewPager.setPageMargin(15);
        }

    }

    private void setPagerPadding1(ViewPager viewPager, int position) {
        if (viewPager.getAdapter().getCount() > 1) {
            if (position == 0) {
                viewPager.setPadding(25, 0, 40, 0);
                viewPager.setClipToPadding(false);
                viewPager.setPageMargin(15);
            }
            else if(position==(viewPager.getAdapter().getCount())-1) {
                viewPager.setPadding(40, 0, 25, 0);
                viewPager.setClipToPadding(false);
                viewPager.setPageMargin(15);
            }
            else {
                viewPager.setPadding(40, 0, 40, 0);
                viewPager.setClipToPadding(false);
                viewPager.setPageMargin(15);
            }
        } else {
            viewPager.setPadding(25, 0, 25, 0);
            viewPager.setClipToPadding(false);
            viewPager.setPageMargin(15);
        }

    }


    @Override
    public void callLoader() {
        callLoader(0);
    }



    @Override
    public void onResume() {
        super.onResume();
        HomeActivity.showNotification();
    }
}
