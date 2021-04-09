package in.psgroup.psgroup.NotificationsModule;

import android.app.LoaderManager;
import android.app.ProgressDialog;
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

import in.psgroup.psgroup.HomeActivity;
import in.psgroup.psgroup.Network.NotificationStatusLoader;
import in.psgroup.psgroup.NotificationsModule.Adapters.NotificationsAdapter;
import in.psgroup.psgroup.NotificationsModule.Interface.NotificationInterface;
import in.psgroup.psgroup.NotificationsModule.Models.NotificationsBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.NotificationsLoader;
import in.psgroup.psgroup.PsGroupApplication;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Utility.Utils;

public class NotificationsActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>>, NotificationInterface {
    private ImageView ic_back,ic_notification;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<NotificationsBean> notificationsBeans;
    private NotificationsAdapter notificationsAdapter;
    private ProgressDialog loading;
    private UserSessionManager session;
    private PsGroupApplication myApplication;
    private String accesss_token,notificationStatus;
    private int response_code = 0;
    NotificationsBean notificationsBean;
    private HashMap<String, String> input = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        session = new UserSessionManager(getApplicationContext());
        accesss_token=session.getUserDetails().get(UserSessionManager.KEY_accessToken);
        myApplication = (PsGroupApplication) this.getApplication();
        initialize();
        callloader(0);
    }

    private void callloader(int i) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(i);
            getLoaderManager().initLoader(i, null, this).forceLoad();
        } else {
                Utils.showAlert(this,getResources().getString(R.string.no_internet_message));
        }
    }

    private void initialize() {
        ic_back = (ImageView)findViewById(R.id.ic_back);
        ic_notification = (ImageView)findViewById(R.id.ic_notification);
        recycler = (RecyclerView)findViewById(R.id.recycler);
        initializeRecyclerView();
        ic_back.setOnClickListener(this);
    }

    private void initializeRecyclerView() {

        layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        notificationsBeans = new ArrayList<>();
        recycler.setAdapter(notificationsAdapter);
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
        if(id==0) {
            loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
            Loader<HashMap<String, String>> result = null;
            result = new NotificationsLoader(this, accesss_token);
            return result;
        }else {
            Loader<HashMap<String, String>> result = null;
            HashMap<String,String> input = new HashMap<>();
            input.put("sk_notification_id",notificationsBean.getSkNotificationId());
            result = new NotificationStatusLoader(this, accesss_token,input);
            return result;
        }
    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {

        if(loader.getId()==0) {
            loading.cancel();

        }
        if (data != null && !data.isEmpty()) {
                try {
                    setData(data, loader.getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

            }
    }

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }

    private void setData(HashMap<String, String> data,int id) throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        notificationsBeans=new ArrayList<>();
        Type listType = new TypeToken<ArrayList<NotificationsBean>>() {}.getType();
        Gson gson = new Gson();
        if(response_code!=0)
        {
            switch (response_code)
            {
                case 200:
                    if(id==0) {
                        notificationsBeans.clear();
                        JSONObject jsonObject = new JSONObject(data.get("data"));
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int k = 0; k < jsonArray.length(); k++) {
                            notificationsBeans.add(gson.fromJson(jsonArray.getJSONObject(k).toString(), NotificationsBean.class));
                        }
                        setAdapters(notificationsBeans);
                    }else {

                        HomeActivity.notificationCount=HomeActivity.notificationCount--;
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
                    Utils.showToast(this, data.get("message"));
                    break;
                default:

                    break;

            }
        }
    }

    private void setAdapters(ArrayList<NotificationsBean> questionList) {
        notificationsAdapter=new NotificationsAdapter(questionList,this,this);
        recycler.setAdapter(notificationsAdapter);
    }

    @Override
    public void CallLoader(NotificationsBean notificationsBean) {
        this.notificationsBean = notificationsBean;
         callloader(1);
    }

}
