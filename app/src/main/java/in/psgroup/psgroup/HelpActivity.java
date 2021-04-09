package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import in.psgroup.psgroup.Adapters.HelpAdapter;
import in.psgroup.psgroup.Models.HelpBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.HelpLoader;
import in.psgroup.psgroup.Utility.Utils;

import static in.psgroup.psgroup.Utility.Utils.showToast;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    private TextView tv_viewTicket,tv_name,tv_hwCanHelp,tv_generalEnquiry,tv_documentation,tv_happyOurs,tv_homeLoan,tv_unitHandover,tv_taxation,tv_resale;
    private ImageView ic_back;
    private SearchView search;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<HelpBean> helpBeans;
    private HelpAdapter generalAdapter;
    private ScrollView scrollview;
    ProgressDialog loading;
    private SearchManager searchManager;
    private PsGroupApplication myApplication;
    private int id;
    String access_token= null,flag = "";
    private int response_code = 0;
    private UserSessionManager sessionManager;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        myApplication = (PsGroupApplication) this.getApplication();
        sessionManager = new UserSessionManager(this);
        name= sessionManager.getUserDetails().get(UserSessionManager.KEY_NAME);
        if (sessionManager.checkLogin()){
            Utils.showToast(this,"You are not logged in please Login once Again");
            this.finish();
        } else{
            access_token = sessionManager.getUserDetails().get(UserSessionManager.KEY_accessToken);
            initialize();

            if (search != null) {
                search.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
            }
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    if (query.length() != 0) {

                        beginSearch(query);

                        return false;
                    } else {
                        beginSearch("");
                    }
                    return false;
                }
            });

            callLoader(0);
        }

    }

    private void initialize() {
        ((ScrollView) findViewById(R.id.scrollview)).post(new Runnable() {
            public void run() {
                ((ScrollView) findViewById(R.id.scrollview)).fullScroll(View.FOCUS_UP);
            }
        });
        /*tv_viewTicket = (TextView)findViewById(R.id.tv_viewTicket);
        tv_viewTicket.setOnClickListener(this);*/
        ic_back = (ImageView)findViewById(R.id.ic_back);
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_name.setText("Hi "+name+",");
        tv_hwCanHelp = (TextView)findViewById(R.id.tv_hwCanHelp);
        search = (SearchView)findViewById(R.id.search);
        search.setFocusable(true);
        search.setOnClickListener(this);
        searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);

        recycler = (RecyclerView)findViewById(R.id.recycler);
        ic_back.setOnClickListener(this);
        initializeRecyclerView();


    }

    private void initializeRecyclerView() {
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            /*case R.id.tv_viewTicket:
                i= new Intent(HelpActivity.this,ViewTicketsActivity.class);
                startActivity(i);
                break;*/
            case R.id.ic_back:
                this.onBackPressed();
                break;

            case R.id.search:
                search.setIconified(false);
                search.clearFocus();
                break;

        }

    }

    public void beginSearch(String query) {
        generalAdapter.getFilter().filter(query);
    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        result = new HelpLoader(this,access_token);
        return result;
    }



    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        getSupportLoaderManager().destroyLoader(loader.getId());
        if (data != null && !data.isEmpty()) {
            try {
                setData(data, id);
                getLoaderManager().destroyLoader(id);
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

    private void callLoader(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().initLoader(id, null,this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }
    }

    private void setData(HashMap<String, String> data, int id) throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        Gson gson = new Gson();
        ArrayList<HelpBean> questionList = new ArrayList<>();
        Intent i;
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    Type type = new TypeToken<ArrayList<HelpBean>>(){
                    }.getType();
                    helpBeans = gson.fromJson(data.get("data"),type);
                    generalAdapter = new HelpAdapter(helpBeans,getApplicationContext());
                    recycler.setAdapter(generalAdapter);

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
