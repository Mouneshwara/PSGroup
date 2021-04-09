package in.psgroup.psgroup;

import android.Manifest;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;import android.content.pm.PackageManager;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import in.psgroup.psgroup.Adapters.OffersAdapter;
import in.psgroup.psgroup.Models.CheckPointHistoryBean;
import in.psgroup.psgroup.Models.OffersBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.GuestLoader;
import in.psgroup.psgroup.Network.OfferLoader;
import in.psgroup.psgroup.Utility.Utils;

public class OffersActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<HashMap<String, String>>{

    private View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ImageView backPress;
    private int REQUEST_PHONE_CALL=100;
    private String activity_data;
    private String accesss_token,offer;
    OffersBean offersBean;
    private int response_code = 0;
    private int id,adapterposition=0;
    private ProgressDialog loading;
    OffersAdapter offersAdapter;
    UserSessionManager session;
    PsGroupApplication myApplication;
    ArrayList<OffersBean> offersBeanArrayList = new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        session = new UserSessionManager(getApplicationContext());
        accesss_token=session.getUserDetails().get(UserSessionManager.KEY_accessToken);
        myApplication = (PsGroupApplication) this.getApplication();
        Bundle extras = getIntent().getExtras();

        if(getIntent().hasExtra("position")) {
            adapterposition = extras.getInt("position");
        }

        //offersBeanArrayList.add(offersBean);
        final PsGroupApplication myApplication = (PsGroupApplication)getApplication();
        activity_data=myApplication.getJson_data();
      /*  initOffers();*/
        initView();
        callLodar(0);
    }

    private void callLodar(int i) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }

    }

    private void initOffers() {


    }

    private void initView() {

        backPress = findViewById(R.id.ic_backClick);

        recyclerView = findViewById(R.id.offers_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getLayoutManager().scrollToPosition(adapterposition);





                backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private  void  showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        result = new OfferLoader(this,accesss_token);
        return result;

    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        getLoaderManager().destroyLoader(0);
        if (data != null && !data.isEmpty()) {
            try {
                setData(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    private void setData(HashMap<String, String> data) throws JSONException{
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        offersBeanArrayList=new ArrayList<>();
        Type listType = new TypeToken<ArrayList<OffersBean>>() {}.getType();
        Gson gson = new Gson();
        if(response_code!=0)
        {
            switch (response_code)
            {
                case 200:
                offersBeanArrayList.clear();
                JSONObject jsonObject=new JSONObject(data.get("data"));
                JSONArray jsonArray=jsonObject.getJSONArray("offers");
                    for (int k = 0; k < jsonArray.length(); k++) {
                        offersBeanArrayList.add(gson.fromJson(jsonArray.getJSONObject(k).toString(), OffersBean.class));
                    }
                setAdapters(offersBeanArrayList);
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

    private void setAdapters(ArrayList<OffersBean> offersBeanArrayList) {
        offersAdapter=new OffersAdapter(this,offersBeanArrayList);
        recyclerView.setAdapter(offersAdapter);

    }

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }
}
