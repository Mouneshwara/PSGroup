package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.psgroup.psgroup.Models.ReferralHistoryBean;
import in.psgroup.psgroup.Models.TotalPointsBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Models.VideoBean;
import in.psgroup.psgroup.Network.ReferralHistoryLoader;
import in.psgroup.psgroup.Network.TotalPointsLoader;
import in.psgroup.psgroup.Utility.Utils;

public class HappyOursActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<HashMap<String, String>>, View.OnClickListener {
    private ImageView iv_back,iv_happy_ours,iv_cupIcon,videoImage,videoplay,iv_offer;
    private TextView tv_name,platinum_member,tv_refferalId,tv_refferalNo,tv_earnedPoints,tv_points,tv_redeemNow,tv_chkHistory,
            tv_referAndEarn,refferalText,tv_refferalHistory,tv_offer;
    private Button btn_referFrd;
    private LinearLayout lLayout,ll_youtube;
    private RelativeLayout rLayout;
    private UserSessionManager session;
    private String customerCode,access_token;
    private YouTubePlayerSupportFragment youTubePlayerFragment;
    ProgressDialog loading;
    PsGroupApplication myApplication;
    private String link,VIDEO_CODE = "",points;
    private int response_code = 0;
    private int id;
    ArrayList<TotalPointsBean> totalPointsBeanArrayList = new ArrayList<>();
    ArrayList<VideoBean>videoBeans=new ArrayList<>();
    VideoBean videoBean;
    TotalPointsBean totalPointsBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_ours);
        myApplication = (PsGroupApplication) this.getApplication();
        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        customerCode=user.get(UserSessionManager.KEY_loyaltycode);
        access_token=user.get(UserSessionManager.KEY_accessToken);
        callLoader(0);
        initialize();

    }

    private void initialize() {
        iv_back = (ImageView)findViewById(R.id.iv_back);
        iv_happy_ours = (ImageView)findViewById(R.id.iv_happy_ours);
        tv_name = (TextView)findViewById(R.id.tv_name);
        platinum_member = (TextView)findViewById(R.id.platinum_member);
        tv_refferalId = (TextView)findViewById(R.id.tv_refferalId);
        tv_refferalNo = (TextView)findViewById(R.id.tv_refferalNo);
        tv_earnedPoints = (TextView)findViewById(R.id.tv_earnedPoints);
        tv_points = (TextView)findViewById(R.id.tv_points);
        tv_redeemNow = (TextView)findViewById(R.id.tv_redeemNow);
        tv_chkHistory = (TextView)findViewById(R.id.tv_chkHistory);
        iv_cupIcon = (ImageView)findViewById(R.id.iv_cupIcon);
        tv_referAndEarn = (TextView)findViewById(R.id.tv_referAndEarn);
        videoImage = (ImageView)findViewById(R.id.videoImage);
        videoplay = (ImageView)findViewById(R.id.videoplay);
        refferalText = (TextView)findViewById(R.id.refferalText);
        tv_refferalHistory = (TextView)findViewById(R.id.tv_refferalHistory);
        btn_referFrd = (Button)findViewById(R.id.btn_referFrd);
        iv_offer = (ImageView)findViewById(R.id.iv_offer);
        tv_offer = (TextView)findViewById(R.id.tv_offer);
        lLayout = (LinearLayout)findViewById(R.id.lLayout);
        ll_youtube = (LinearLayout)findViewById(R.id.ll_youtube);
        rLayout = (RelativeLayout)findViewById(R.id.rLayout);
        tv_redeemNow.setOnClickListener(this);
        tv_refferalHistory.setOnClickListener(this);
        tv_chkHistory.setOnClickListener(this);
        btn_referFrd.setOnClickListener(this);
        lLayout.setOnClickListener(this);
        rLayout.setOnClickListener(this);
        iv_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent i ;
        switch (view.getId()){
            case R.id.tv_redeemNow:

                i = new Intent(HappyOursActivity.this,RedeemNowActivity.class);
                i.putExtra("points",points);
                startActivity(i);
                break;
            case R.id.tv_refferalHistory:
                i = new Intent(HappyOursActivity.this,ReferralHistoryActivity.class);
                startActivity(i);
                break;
            case R.id.tv_chkHistory:
                i = new Intent(HappyOursActivity.this,CheckPointHistoryActivity.class);
                startActivity(i);
                break;
            case R.id.btn_referFrd:
                i= new Intent(HappyOursActivity.this,ReferPropertyActivity.class);
                startActivity(i);
                break;
            case R.id.lLayout:
                i = new Intent (HappyOursActivity.this,OffersActivity.class);
                startActivity(i);
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.rLayout:
              //  i = new Intent(HappyOursActivity.this,YouTubePlayerExampleActivity.class);
               // i.putExtra("videolink",videoBean.getVideolink());

                videoImage.setVisibility(View.GONE);
                videoplay.setVisibility(view.GONE);

                ll_youtube.setVisibility(View.VISIBLE);



                initializeYoutubePlayer();


               // startActivity(i);
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

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        switch (id) {

            case 0:
                HashMap<String, String> input = new HashMap<>();
                input.put("customerCode", customerCode);
                result = new TotalPointsLoader(this,access_token, input);

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
                                JSONObject datajson = jsonarray.getJSONObject("data");
                                for (int k = 0; k < datajson.length(); k++) {
                                    totalPointsBeanArrayList.add(gson.fromJson(datajson.toString(), TotalPointsBean.class));
                                }
                                setpoints(totalPointsBeanArrayList);

                                JSONObject videojson = jsonarray.getJSONObject("video");
                                for (int k = 0; k < videojson.length(); k++) {
                                    videoBeans.add(gson.fromJson(videojson.toString(),VideoBean.class));
                                }
                                setvideo(videoBeans);
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

    private void setvideo(ArrayList<VideoBean> totalPointsBeanArrayList) {
        videoBean =videoBeans.get(0);
        Picasso.get().load(videoBean.getVideode_image()).into(videoImage);
        refferalText.setText(videoBean.getVideodescription());
        link=videoBean.getVideolink();

    }

    private void setpoints(ArrayList<TotalPointsBean> totalPointsBeanArrayList) {
        totalPointsBean =totalPointsBeanArrayList.get(0);
        tv_points.setText(totalPointsBean.getTotalRemainingPoints()+"pts");
        tv_name.setText("Hi "+totalPointsBean.getFirstName()+"!");
        tv_refferalNo.setText(totalPointsBean.getCustomerCode());
        HashMap<String, String> user = session.getUserDetails();
        platinum_member.setText("You are a "+user.get(UserSessionManager.KEY_customertype) +" member");
        points =totalPointsBean.getTotalRemainingPoints();


    }


    private void initializeYoutubePlayer() {

        VIDEO_CODE = getYouTubeId(link);

        youTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.youtube_player_fragment);



        youTubePlayerFragment.initialize(getString(R.string.YOUTUBE_DEVELOPER_KEY), new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                if(!b){
                    youTubePlayer.loadVideo(VIDEO_CODE);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }


        });}


    private String getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }

}
