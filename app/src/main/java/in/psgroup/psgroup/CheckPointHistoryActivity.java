package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import in.psgroup.psgroup.Adapters.CheckPointHistoryAdapter;
import in.psgroup.psgroup.Adapters.ViewTicketAdapter;
import in.psgroup.psgroup.Models.CheckPointHistoryBean;
import in.psgroup.psgroup.Models.ReferralHistoryBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Models.ViewTicketBean;
import in.psgroup.psgroup.Network.CheckPointHistoryLoader;
import in.psgroup.psgroup.Utility.Utils;

import static in.psgroup.psgroup.Utility.Utils.showToast;

public class CheckPointHistoryActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    private RecyclerView recyclerView;
    private ImageView ic_back;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CheckPointHistoryBean> list = new ArrayList<>();
    private CheckPointHistoryAdapter historyAdapter;
    String access_token= null,customerCode;
    private ProgressDialog loading;
    private int id;
    private PsGroupApplication myApplication;
    private int response_code = 0;
    private UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_point_history);
        myApplication = (PsGroupApplication) this.getApplication();
        sessionManager = new UserSessionManager(this);
        if (sessionManager.checkLogin()){
            Utils.showToast(this,"You are not logged in please Login once Again");
            this.finish();
        } else {
            access_token = sessionManager.getUserDetails().get(UserSessionManager.KEY_accessToken);
            customerCode = sessionManager.getUserDetails().get(UserSessionManager.KEY_loyaltycode);
            initialize();
            callApi(0);
        }

        }

    private void initialize() {
        ic_back = (ImageView)findViewById(R.id.ic_back);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        ic_back.setOnClickListener(this);

    }

    private void initializeRecyclerView() {
        layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        historyAdapter = new CheckPointHistoryAdapter(list,getApplicationContext());
        recyclerView.setAdapter(historyAdapter);
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
        HashMap<String, String> input = new HashMap<>();
        input.put("customerCode", customerCode);
        result = new CheckPointHistoryLoader(this,access_token, input);
        return result;
    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        getLoaderManager().destroyLoader(loader.getId());
        if (data != null && !data.isEmpty()) {
            try {
                setData(data, id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            showToast(this, "something went wrong");
        }

    }

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }

    private void callApi(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().initLoader(id, null,this).forceLoad();
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

                    if (data.get("data") != null) {
                        list.clear();
                        JSONObject jsonObject = new JSONObject(data.get("data"));
                        JSONArray datajson = jsonObject.getJSONArray("data");
                        for (int k = 0; k < datajson.length(); k++) {
                            list.add(gson.fromJson(datajson.getJSONObject(k).toString(), CheckPointHistoryBean.class));
                        }
                        initializeRecyclerView();
                    }
                   /* Type type = new TypeToken<ArrayList<CheckPointHistoryBean>>(){
                    }.getType();
                    list = gson.fromJson(data.get("data"),type);*/

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
                    Intent j = new Intent(this, ServerErrorActivity.class);
                    j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.startActivity(j);
                    this.finish();
                    break;
            }
        }
    }
}
