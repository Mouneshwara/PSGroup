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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import in.psgroup.psgroup.Adapters.CheckPointHistoryAdapter;
import in.psgroup.psgroup.Adapters.ReferPropertyAdapter;
import in.psgroup.psgroup.Interfaces.ReferPropertyInterface;
import in.psgroup.psgroup.Models.CheckPointHistoryBean;
import in.psgroup.psgroup.Models.ProjectBean;
import in.psgroup.psgroup.Models.ReferPropertyBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ProjectsLoader;
import in.psgroup.psgroup.Utility.Utils;

public class ReferPropertyActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    private RecyclerView recyclerView;
    private ImageView ic_back;
    private Button btn_proceed;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ProjectBean> projectList = new ArrayList<>();
    private ArrayList<ReferPropertyBean> propertyList;
    private ReferPropertyAdapter referAdapter;
    private String access_token,location,property_type,property_status,unit_type,budget,title,project_id;
    UserSessionManager session;
    public static int selectedPosition = -1;
    ProgressDialog loading;
    PsGroupApplication myApplication;
    private HashMap<String, String> input = new HashMap<>();
    private int response_code = 0;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_property);
        myApplication = (PsGroupApplication) this.getApplication();
        session = new UserSessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        access_token = user.get(UserSessionManager.KEY_accessToken);
        initialize();
        callLoader(0);
    }

    private void initialize() {
        ic_back = (ImageView) findViewById(R.id.ic_back);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        btn_proceed = (Button) findViewById(R.id.btn_proceed);
        ic_back.setOnClickListener(this);
        btn_proceed.setOnClickListener(this);

    }

    private void initializeRecyclerView(ArrayList<ProjectBean> projectList) {
        layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        referAdapter = new ReferPropertyAdapter(projectList, getApplicationContext());
        recyclerView.setAdapter(referAdapter);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.ic_back:
                this.onBackPressed();
                break;
            case R.id.btn_proceed:
                if(referAdapter.selectProperty()) {
                    i = new Intent(ReferPropertyActivity.this, RefreeDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("projectName", title);
                    bundle.putString("projectid", project_id);
                    i.putExtras(bundle);
                    startActivity(i);
                }else {
                    Toast.makeText(this,"Please select Property",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void setButton(String name) {
        if (selectedPosition > -1) {
            btn_proceed.setEnabled(true);

        } else {
            btn_proceed.setEnabled(false);
        }
    }


    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {

        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;

        switch (id) {

            case 0:
                input = new HashMap<>();
                input.put("location",location!= null ? location : "");
                input.put("property_type",property_type!= null ? property_type : "");
                input.put("property_status",property_status!= null ? property_status : "");
                input.put("unit_type",unit_type!= null ? unit_type : "");
                input.put("budget",budget!= null ? budget : "");
                result = new ProjectsLoader(this,access_token,input);
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
            Utils.showToast(this, "something went wrong");
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
//                            JSONObject jsonObject = new JSONObject(data.get("data"));

                            if (data.get("projects") != null) {

                                JSONArray jsonarray = data.containsKey("projects") ? new JSONArray(data.get("projects")) : new JSONArray();
                                for (int k = 0; k < jsonarray.length(); k++) {
                                    projectList.add(gson.fromJson(jsonarray.getJSONObject(k).toString(), ProjectBean.class));
                                }
                                initializeRecyclerView(projectList);
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

}
