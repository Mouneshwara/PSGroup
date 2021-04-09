package in.psgroup.psgroup;

import android.app.Dialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import in.psgroup.psgroup.Adapters.GalleryAdapter;
import in.psgroup.psgroup.Adapters.HighlightsAdapter;
import in.psgroup.psgroup.Adapters.PropertiesReviewAdapter;
import in.psgroup.psgroup.Adapters.SpecificationAdapter;
import in.psgroup.psgroup.Models.FloorPlans;
import in.psgroup.psgroup.Models.Gallery;
import in.psgroup.psgroup.Models.Highlight;
import in.psgroup.psgroup.Models.NearbyLandmark;
import in.psgroup.psgroup.Models.ProjectDetailBean;
import in.psgroup.psgroup.Models.ProjectSpecification;
import in.psgroup.psgroup.Models.ProjectSpecificationTwoList;
import in.psgroup.psgroup.Models.PropertyBean;
import in.psgroup.psgroup.Models.Siteplan;
import in.psgroup.psgroup.Models.SpecificationBean;
import in.psgroup.psgroup.Models.Testimonial;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ProjectDetailsLoader;
import in.psgroup.psgroup.Utility.Utils;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.text.Html.fromHtml;

public class PropertyDetailsActivity extends AppCompatActivity implements OnMapReadyCallback,View.OnClickListener,LoaderManager.LoaderCallbacks<HashMap<String, String>>{

    private ImageView back,close,project_image;
    private AppBarLayout appbar;
    private TextView tv_siteView,highlights_view,tv_floorView,imageCount,tv_block,tv_highlights,tv_family_no;
    private View targetView;
    private FrameLayout ll_milestone,ll_siteplan,rr_floorPlan;
    private TabLayout tabLayout;
    private ViewPager propertyPager;
    private LinearLayout ll_share,ll_visit,ll_pricing,ll_highlights,ll_floor_plan,ll_unit_plan,ll_highlights_view,ll_location,ll_nearby_landmarks,ll_overview,ll_banks,ll_review,ll_gallery,ll_specifications,ll_btn;
    private TextView description,project_title,address,state,btn_previous,btn_next,tv_location,review_text;
    private  String highlights,title,project_id,access_token;
    private NestedScrollView scrollView;
    private RelativeLayout rr_siteplan,rl_review;
    ArrayList<Highlight> highlightArrayList = new ArrayList<>();
    ArrayList<NearbyLandmark> nearbyLandmarkArrayList = new ArrayList<>();
    ArrayList<Siteplan> siteplanArrayLists = new ArrayList<>();
    ArrayList<Testimonial> testimonialArrayList = new ArrayList<>();
    Siteplan siteplan;
    String site_plan_url,a= "";
    ProjectDetailBean projectDetailBean;
    NearbyLandmark nearbyLandmark;
    Dialog dialog;
    View view;
    Highlight highlight;
    Testimonial testimonial;
    private ViewPager reviewViewPager;
    private double locationlat,locationlong;
    private Context context;
    private int dotscount;
    ImageView iv_review;
    private String Images[]; //= {R.drawable.apartment,R.drawable.apartment,R.drawable.apartment,R.drawable.apartment,R.drawable.apartment,R.drawable.apartment};
    private String counts[]; //= {"1 of 6","2 of 6","3 of 6","4 of 6","5 of 6","6 of 6"};

    private RecyclerView specifictaion_recyclerView,gallery_recycler,review_recycler;
    private RecyclerView.LayoutManager layoutManager,layoutManager1,layoutManager2;
    private SpecificationAdapter specificationAdapter;
    private GalleryAdapter galleryAdapter;
    private ArrayList<SpecificationBean> specificationBeans=new ArrayList<>();
    private FloorPlans floorPlans;

    private MapView mapView;
    private GoogleMap gmap;
    ProgressDialog loading;
    PsGroupApplication myApplication;
    PropertyBean myProperty;
    private ArrayList<PropertyBean> myProperties = new ArrayList<PropertyBean>();
    private ArrayList<ProjectSpecification> specifications = new ArrayList<ProjectSpecification>();
    ArrayList<FloorPlans> floorPlansList =  new ArrayList<>();

    private int response_code = 0;
    private int id;

    private String[] floor_Plan;
    UserSessionManager session;


  //  private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyCeIqPXoquxGZjG52AQiNaoTllOJDcrOjo";
    //AIzaSyCHBNZ4Qz5HES4V5qfplVTrDGyNBnHSGWY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        myApplication = (PsGroupApplication) this.getApplication();
        session = new UserSessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        access_token = user.get(UserSessionManager.KEY_accessToken);//accessToken

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            title =extras.getString("projectName");
            project_id =extras.getString("projectid");
        }
        callLoader(0);
        initView();
        setValues();


        //Regarding MapView
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void setValues() {
        //project_title.setText(myProperty.getProject_title());

    }



    private void initView() {


        description = (TextView) findViewById(R.id.tv_description);
        project_title = (TextView) findViewById(R.id.tv_name);
        address = (TextView) findViewById(R.id.tv_landmark);
        btn_previous = (TextView)  findViewById(R.id.btn_previous);
        btn_next = (TextView)  findViewById(R.id.btn_next);
        tv_location=(TextView)  findViewById(R.id.tv_location);
        tv_family_no=(TextView)  findViewById(R.id.tv_family_no);


        back=(ImageView)findViewById(R.id.ic_back);
        project_image=(ImageView)findViewById(R.id.project_image);
        tv_siteView = (TextView)findViewById(R.id.tv_siteview);
        highlights_view = (TextView)findViewById(R.id.highlights_view);
        tv_floorView = (TextView)findViewById(R.id.tv_floorview);
        ll_overview = (LinearLayout) findViewById(R.id.ll_overview);
        ll_pricing = (LinearLayout) findViewById(R.id.ll_pricing);
        ll_highlights = (LinearLayout) findViewById(R.id.ll_highlights);
        ll_floor_plan = (LinearLayout) findViewById(R.id.ll_floor_plan);
        ll_unit_plan = (LinearLayout) findViewById(R.id.ll_unit_plan);
        ll_gallery = (LinearLayout) findViewById(R.id.ll_gallery);
        ll_nearby_landmarks = (LinearLayout) findViewById(R.id.ll_nearby_landmarks);
        ll_banks = (LinearLayout) findViewById(R.id.ll_banks);
        ll_specifications = (LinearLayout) findViewById(R.id.ll_specifications);
        ll_location= (LinearLayout) findViewById(R.id.ll_location);
        ll_highlights_view=(LinearLayout)findViewById(R.id.ll_highlights_view);
        rr_siteplan=(RelativeLayout) findViewById(R.id.rr_siteplan);

        ll_share = (LinearLayout)findViewById(R.id.ll_share);
        ll_visit = (LinearLayout) findViewById(R.id.ll_visit);
        scrollView =(NestedScrollView) findViewById(R.id.scrollView);
        review_text = (TextView)findViewById(R.id.review_text);

        appbar =(AppBarLayout) findViewById(R.id.appbar);

        targetView = (View) findViewById(R.id.framelayout);

        tabLayout = (TabLayout) findViewById(R.id.propertyTabs);
        tv_siteView.setOnClickListener(this);
        tv_floorView.setOnClickListener(this);
        highlights_view.setOnClickListener(this);

        iv_review = (ImageView) findViewById(R.id.iv_review);
        rl_review = (RelativeLayout) findViewById(R.id.rl_review);
        ll_btn = (LinearLayout) findViewById(R.id.ll_btn);
        reviewViewPager = (ViewPager)findViewById(R.id.reviewViewPager);

        description = (TextView) findViewById(R.id.tv_description);
        project_title = (TextView) findViewById(R.id.tv_name);
        btn_previous = (TextView)  findViewById(R.id.btn_previous);
        btn_next = (TextView)  findViewById(R.id.btn_next);

        btn_next.setOnClickListener(this);
        btn_previous.setOnClickListener(this);

        tabLayout.addTab(tabLayout.newTab().setText("       Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Highlights"));
        tabLayout.addTab(tabLayout.newTab().setText("Location"));
        tabLayout.addTab(tabLayout.newTab().setText("Plans"));

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorHeight(0);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                               @Override
                                               public void onTabSelected(TabLayout.Tab tab) {
                                                   switch (tab.getPosition()) {
                                                       case 0:
                                                           scrollToView(scrollView,ll_overview);
                                                           break;

                                                       case 1:
                                                           scrollToView(scrollView,ll_highlights_view);
                                                           break;

                                                       case 2:
                                                           scrollToView(scrollView,ll_location);
                                                           break;
                                                       case 3:
                                                           scrollToView(scrollView,rr_siteplan);
                                                           break;
                                                   }
                                               }

                                               @Override
                                               public void onTabUnselected(TabLayout.Tab tab) {

                                               }

                                               @Override
                                               public void onTabReselected(TabLayout.Tab tab) {
                                                   switch (tab.getPosition()) {
                                                       case 0:
                                                           scrollToView(scrollView,ll_overview);
                                                           break;

                                                       case 1:
                                                           scrollToView(scrollView,ll_highlights_view);
                                                           break;

                                                       case 2:
                                                           scrollToView(scrollView,ll_location);
                                                           break;
                                                       case 3:
                                                           scrollToView(scrollView,rr_siteplan);
                                                           break;
                                                   }

                                               }
                                           });


            specifictaion_recyclerView = findViewById(R.id.specification_recycler);


        gallery_recycler= findViewById(R.id.gallery_recycler);


        LayoutInflater inflater = LayoutInflater.from(this);

        View inflatedLayout4 = inflater.inflate(R.layout.unit_plan_layout, null, false);
        ll_unit_plan.addView(inflatedLayout4);

        View inflatedLayout5 = inflater.inflate(R.layout.banks_layout, null, false);
        ll_banks.addView(inflatedLayout5);

    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private  void  showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


    public  void onClick(View view){
        switch (view.getId()){
            case R.id.ll_share :
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = projectDetailBean.getSharelink();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "PS Group");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
            case R.id.ll_visit :

                Intent k=new Intent(PropertyDetailsActivity.this, ScheduleVisitActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("projectName",title);
                bundle.putString("projectid",project_id);
                k.putExtras(bundle);
                startActivity(k);
                break;

            case R.id.tv_floorview:
                String[] array= a.split(",");
                Intent i=new Intent(this, GalleryViewActivity.class);
                i.putExtra("Images", array);
                i.putExtra("position", 0);
                i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
            case R.id.highlights_view:
                showDialog(highlightArrayList);
                break;
            case R.id.tv_siteview:
                Intent j=new Intent(PropertyDetailsActivity.this, SitePlanActivity.class);
                j.putExtra("property", site_plan_url);
                startActivity(j);
                break;
            case R.id.ic_back:
                onBackPressed();
                break;

            case R.id.btn_next:
                reviewViewPager.setCurrentItem(getItem(+1), true);
                break;
            case R.id.btn_previous:
                reviewViewPager.setCurrentItem(getItem(-1), true);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;

    }


    public Dialog showDialog(ArrayList<Highlight> highlightArrayList) {

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        Dialog returnDialog = null;


                dialog = new Dialog(this);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(false);

                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
              LayoutInflater inflater = this.getLayoutInflater();
                view = inflater.inflate(R.layout.layout_highlights, null);
                dialog.setContentView(view);


                close = (ImageView) view.findViewById(R.id.close);

                RecyclerView highlight_recyler;


        highlight_recyler= view.findViewById(R.id.highlight_recyler);
        HighlightsAdapter highlightsAdapter;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.VERTICAL,false);
        highlight_recyler.setLayoutManager(layoutManager);
        highlightsAdapter = new HighlightsAdapter(highlightArrayList, getApplicationContext());
        highlight_recyler.setAdapter(highlightsAdapter);

        close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.getWindow().setBackgroundDrawableResource(R.color.dialogbg);
                dialog.getWindow().setAttributes(layoutParams);
                dialog.show();
                returnDialog = dialog;




        return returnDialog;
    }

    public static void scrollToView(final NestedScrollView scrollView, final View view) {

        // View needs a focus
        view.requestFocus();

        // Determine if scroll needs to happen

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                //  scrollView.smoothScrollTo(10, view.getBottom());

                scrollView.fling(0);
                scrollView.smoothScrollTo(0, view.getTop() + 20);
            }
        });
    }


    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        switch (id) {

            case 0:
                HashMap<String, String> input = new HashMap<>();
                input.put("title", title);
                input.put("id", project_id);
                result = new ProjectDetailsLoader(this, input,access_token);

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
        ArrayList<ProjectDetailBean> projectdetails = new ArrayList<>();
        ArrayList<Gallery> galleryArrayList = new ArrayList<>();
        ArrayList<ProjectSpecificationTwoList> projectSpecificationTwoListArrayList = new ArrayList<>();


        Intent i;
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    switch (id) {
                        case 0:
//                            JSONObject jsonObject = new JSONObject(data.get("data"));

                            if (data.get("data") != null) {
                                JSONObject jsonarray = new JSONObject(data.get("data"));
                                for (int k = 0; k < jsonarray.length();k++) {
                                    projectdetails.add(gson.fromJson(jsonarray.toString(), ProjectDetailBean.class));
                                }

                                setlocation(projectdetails);
                                setstarting(projectdetails);

                                JSONArray gallery = jsonarray.getJSONArray("gallery");
                                for (int k = 0; k < gallery.length();k++) {
                                    galleryArrayList.add(gson.fromJson(gallery.getJSONObject(k).toString(), Gallery.class));
                                }
                                setgalleryapadter(galleryArrayList);

                                JSONArray highlight_json =  jsonarray.getJSONArray("highlights");
                                for (int k = 0; k < highlight_json.length();k++) {
                                    highlightArrayList.add(gson.fromJson(highlight_json.getJSONObject(k).toString(), Highlight.class));
                                }
                                sethighligths(highlightArrayList);



                                JSONArray specification_json =  jsonarray.getJSONArray("ProjectSpecification_two_list");
                                for (int k = 0; k < specification_json.length();k++) {
                                    projectSpecificationTwoListArrayList.add(gson.fromJson(specification_json.getJSONObject(k).toString(), ProjectSpecificationTwoList.class));
                                }
                                setspecification(projectSpecificationTwoListArrayList);




                                JSONArray floorPlansjson = jsonarray.getJSONArray("floorplan");
                                for (int k = 0; k < floorPlansjson.length();k++) {
                                    floorPlansList.add(gson.fromJson(floorPlansjson.getJSONObject(k).toString(), FloorPlans.class));
                                }
                                setfloorplan(floorPlansList);


                                JSONArray nearbyjson = jsonarray.getJSONArray("nearbyLandmarks");
                                for (int k = 0; k < nearbyjson.length();k++) {
                                    nearbyLandmarkArrayList.add(gson.fromJson(nearbyjson.getJSONObject(k).toString(), NearbyLandmark.class));
                                }
                                setnearby(nearbyLandmarkArrayList);



                                JSONArray testimonialsjson = jsonarray.getJSONArray("testimonials");
                                for (int k = 0; k < testimonialsjson.length();k++) {
                                    testimonialArrayList.add(gson.fromJson(testimonialsjson.getJSONObject(k).toString(), Testimonial.class));
                                }
                                settestimonial(testimonialArrayList);



                                JSONObject sitejson = jsonarray.getJSONObject("siteplan");
                                for (int k = 0; k < sitejson.length();k++) {
                                    siteplanArrayLists.add(gson.fromJson(sitejson.toString(), Siteplan.class));
                                }
                                siteplan = siteplanArrayLists.get(0);
                                site_plan_url=siteplan.getDetailDesc();



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

    private void setlocation(ArrayList<ProjectDetailBean> projectdetails) {


        projectDetailBean = projectdetails.get(0);
        description.setText(projectDetailBean.getMetaDesc());
        Picasso.get().
                load(projectDetailBean.getBannerImage()).placeholder(R.drawable.thumbnail)
                .into(project_image);
        project_title.setText(projectDetailBean.getPostTitle());
        tv_location.setText(projectDetailBean.getBrowserTitle());
        tv_family_no.setText(projectDetailBean.getFamiliesbooked()+"+ ");
        locationlat =Double.parseDouble(projectDetailBean.getLatitude());
        locationlong=Double.parseDouble(projectDetailBean.getLongitude());




        LatLng location = new LatLng(locationlat,locationlong);

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null; //1 num of possible location returned
        try {
            addresses = geocoder.getFromLocation(locationlat, locationlong,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String address = addresses.get(0).getAddressLine(0); //0 to obtain first possible address
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        //create your custom title
        String title = address +"-"+city+"-"+state;


        gmap.addMarker(new MarkerOptions().position(location).icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title(title)).showInfoWindow();
        gmap.moveCamera(CameraUpdateFactory.newLatLng(location));



        gmap.getUiSettings().setZoomControlsEnabled(true);
        gmap.animateCamera(CameraUpdateFactory.zoomTo(14.0f));
    }

    private void setstarting(ArrayList<ProjectDetailBean> projectdetails) {
        projectDetailBean = projectdetails.get(0);
        TextView bhk,price;
        LayoutInflater inflater = LayoutInflater.from(this);
        View inflatedLayout = inflater.inflate(R.layout.pricing_layout, null, false);
        bhk=inflatedLayout.findViewById
                (R.id.bhk);
        price=inflatedLayout.findViewById
                (R.id.price);
        ll_pricing.addView(inflatedLayout);
        bhk.setText(projectDetailBean.getPostContent());
        price.setText("Starting Rs. "+ projectDetailBean.getStartprice());
    }

    private void settestimonial(ArrayList<Testimonial> testimonialArrayList) {


        if(testimonialArrayList.size()!=0) {
            String messages[] = new String[testimonialArrayList.size()];
            String headers[];
            final String image[] = new String[testimonialArrayList.size()];
            String names[] = new String[testimonialArrayList.size()];
            String place[] = new String[testimonialArrayList.size()];

            for (int i = 0; i < testimonialArrayList.size(); i++) {

                testimonial = testimonialArrayList.get(i);

                image[i] = testimonial.getImage();
                names[i] = testimonial.getName();
                messages[i] = testimonial.getQuote();
            }

            PropertiesReviewAdapter propertiesReviewAdapter = new PropertiesReviewAdapter(this, messages, names, place);
            reviewViewPager.setAdapter(propertiesReviewAdapter);
            dotscount = propertiesReviewAdapter.getCount();

            reviewViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    Picasso.get().
                            load(image[position]).placeholder(R.drawable.thumbnail)
                            .into(iv_review);

                }

                @Override
                public void onPageSelected(int position) {

                    if (position == 0) {
                        btn_next.setTextColor(getResources().getColor(R.color.onClick));
                        btn_previous.setTextColor(getResources().getColor(R.color.grey_text));
                        btn_previous.setEnabled(false);
                        btn_next.setEnabled(true);
                    } else if (position == dotscount - 1) {
                        btn_next.setTextColor(getResources().getColor(R.color.grey_text));
                        btn_previous.setTextColor(getResources().getColor(R.color.onClick));
                        btn_next.setEnabled(false);
                        btn_previous.setEnabled(true);
                    } else {
                        btn_next.setTextColor(getResources().getColor(R.color.onClick));
                        btn_previous.setTextColor(getResources().getColor(R.color.onClick));
                        btn_next.setEnabled(true);
                        btn_previous.setEnabled(true);

                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {
//                showToast(String.valueOf(state));

                }
            });
        }else{

            rl_review.setVisibility(View.VISIBLE);
            review_text.setVisibility(View.VISIBLE);
            ll_btn.setVisibility(View.GONE);
            iv_review.setVisibility(View.GONE);
            reviewViewPager.setVisibility(View.GONE);

        }


    }

    private void setnearby(ArrayList<NearbyLandmark> nearbyLandmarkArrayList) {
        int count =nearbyLandmarkArrayList.size();
        TextView title,name,distance;
        LinearLayout ll_nearby_text;


            for (int i = 0; i < count; i++) {
                nearbyLandmark = nearbyLandmarkArrayList.get(i);
                String[] array,dis;

                LayoutInflater inflater = LayoutInflater.from(this);
                View inflatedLayout2 = inflater.inflate(R.layout.neaby_landmarks_layout, null, false);
                title= inflatedLayout2.findViewById(R.id.title);
                ll_nearby_text = inflatedLayout2.findViewById(R.id.ll_nearby_text);
                title.setText(nearbyLandmark.getProperty());
                array= nearbyLandmark.getProperties().split("#");
                dis= nearbyLandmark.getDistance().split(",");
                ll_nearby_landmarks.addView(inflatedLayout2);
                for (int k = 0; k < array.length; k++) {

                    View inflatedLayout6 = inflater.inflate(R.layout.nearbylandmarks_text, null, false);
                    name= inflatedLayout6.findViewById(R.id.name);
                    distance= inflatedLayout6.findViewById(R.id.distance);
                    name.setText(array[k]);
                    distance.setText(dis[k]);
                    ll_nearby_text.addView(inflatedLayout6);


                }

            }

    }

    private void setfloorplan(ArrayList<FloorPlans> floorPlansArrayList) {



        int count =floorPlansArrayList.size();


       TextView tv_tower,tv_view;

        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i=0;i<count;i++){
            floorPlans =floorPlansArrayList.get(i);
            View inflatedLayout3 = inflater.inflate(R.layout.floor_plan_layout, null, false);
            tv_tower=inflatedLayout3.findViewById
(R.id.tv_tower);
            tv_view=inflatedLayout3.findViewById
                    (R.id.view);
            ll_floor_plan.addView(inflatedLayout3);
            String text =floorPlans.getDetail_title()+" "+floorPlans.getDetail_name();
            tv_tower.setText(text);
            final String floor_plan_url = floorPlans.getDetail_desc();
            a = floorPlans.getDetail_desc()+","+ a;
            tv_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent j=new Intent(PropertyDetailsActivity.this, SitePlanActivity.class);
                    j.putExtra("property", floor_plan_url);
                    startActivity(j);

                }
            });
        }

    }

    private void setspecification(ArrayList<ProjectSpecificationTwoList> projectSpecificationTwoListArrayList) {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false);
        specifictaion_recyclerView.setLayoutManager(layoutManager);
        specificationAdapter = new SpecificationAdapter(projectSpecificationTwoListArrayList, getApplicationContext());
        specifictaion_recyclerView.setAdapter(specificationAdapter);
    }

    private void sethighligths(ArrayList<Highlight> highlightArrayList) {
        int count =highlightArrayList.size();

        highlights_view.setText("see all " +count+" amenities");
        String[] array_highlight;


        for (int i=0;i<2;i++) {
            highlight = highlightArrayList.get(i);
            array_highlight = highlight.getAmenities().split(",");
            highlights = "<font color=#000000><b>" + highlight.getName() + "</b></font>" + array_highlight[0]+ "<font color=#22438A><b> " +" & "+ (array_highlight.length-1) + " more...</b></font>";
            LayoutInflater inflater = LayoutInflater.from(this);

            View inflatedLayout1 = inflater.inflate(R.layout.highlights_layout, null, false);

            tv_highlights = (TextView) inflatedLayout1.findViewById(R.id.tv_higlights);
            // tv_highlights.setText(fromHtml(highlights));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_highlights.setText(Html.fromHtml(highlights, Html.FROM_HTML_MODE_COMPACT));
            } else {
                tv_highlights.setText(Html.fromHtml(highlights));
            }
            ll_highlights.addView(inflatedLayout1);
        }
    }

    private void setgalleryapadter(ArrayList<Gallery> galleryArrayList) {
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false);
        gallery_recycler.setLayoutManager(layoutManager1);
        galleryAdapter = new GalleryAdapter(galleryArrayList, getApplicationContext());
        gallery_recycler.setAdapter(galleryAdapter);
    }

    private int getItem(int i) {
        return reviewViewPager.getCurrentItem() + i;
    }




    }



