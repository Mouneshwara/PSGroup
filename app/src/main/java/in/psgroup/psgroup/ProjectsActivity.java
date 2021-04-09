package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.util.CollectionUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

import in.psgroup.psgroup.Adapters.ProjectAdapter;
import in.psgroup.psgroup.FilterModule.FilterActivity;
import in.psgroup.psgroup.FilterModule.Models.FilterBean;
import in.psgroup.psgroup.FilterModule.Models.PriceInterval;
import in.psgroup.psgroup.Models.ProjectBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ProjectsLoader;
import in.psgroup.psgroup.Utility.Utils;

import static in.psgroup.psgroup.Utility.Utils.STRING_REQUESTCODE;

public class ProjectsActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>>, Filterable {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProjectAdapter projectAdapter;
    private ArrayList<ProjectBean> projectList = new ArrayList<>();
    private ArrayList<ProjectBean> mFilteredList = new ArrayList<>();

    private ArrayList<ProjectBean> filteredList = new ArrayList<>();
    private ImageView backPress;
    private FloatingActionButton filter;
    private ProjectBean projectBean;
    private SearchView search;
    private SearchManager searchManager;
    private HashMap<String, String> input = new HashMap<>();
    ProgressDialog loading;
    PsGroupApplication myApplication;
    private int response_code = 0;
    private int id;
    public String location_name, key, access_token, propety_type, property_price, property_bhk,location,property_type,property_status,unit_type,budget;
    UserSessionManager session;
    PriceInterval priceInterval;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        view =getWindow().getDecorView().getRootView();
        context =getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        myApplication = (PsGroupApplication) this.getApplication();
        session = new UserSessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        access_token = user.get(UserSessionManager.KEY_accessToken);//accessToken

        initView();
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
    }

    private void initView() {

        backPress = findViewById(R.id.ic_backProject);
        filter = findViewById(R.id.filter);

        recyclerView = findViewById(R.id.project_recycler);
        filter.setOnClickListener(this);

        backPress.setOnClickListener(this);
        callLoader(0);

        search = (SearchView) findViewById(R.id.search);
        search.setFocusable(true);
        searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);
        EditText searchEditText = (EditText) search.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.imageCount));
        searchEditText.setHintTextColor(getResources().getColor(R.color.imageCount));
        searchEditText.setBackgroundResource(R.color.White);
        ImageView searchClose = (ImageView) search.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setImageResource(R.drawable.ic_close);
        search.setMaxWidth(Integer.MAX_VALUE);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        projectAdapter = new ProjectAdapter(projectList, getApplicationContext());
        recyclerView.setAdapter(projectAdapter);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filter:
                Intent i = new Intent(this, FilterActivity.class);
                // i.putParcelableArrayListExtra("projectlist",projectList);
                startActivityForResult(i, STRING_REQUESTCODE);
                break;

            case R.id.ic_projectSearch:
                startActivity(new Intent(ProjectsActivity.this, SearchActivity.class));
                break;
            case R.id.ic_backProject:
                onBackPressed();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case STRING_REQUESTCODE:
                    location=null;property_type=null;property_status=null;budget=null;property_bhk=null;
                    if (data.hasExtra("location_name")) {
                        if(!TextUtils.isEmpty(data.getStringExtra("location_name"))) {
                            location = data.getStringExtra("location_name");
                            Log.d("TAG", "location_name:" + location);
                        }
                    }
                    if (data.hasExtra("property_type")) {
                        if(!TextUtils.isEmpty(data.getStringExtra("property_type")) && data.getStringExtra("property_type")!=null) {
                            property_type = data.getStringExtra("property_type");
                            Log.d("TAG", "property_type:" + property_type);
                            if(property_type.equalsIgnoreCase("All")){
                                property_type="";
                            }
                        }

                    }
                  if (data.hasExtra("property_status")) {
                      property_status = data.getStringExtra("property_status");
                            Log.d("TAG","property_status:"+property_status);
                        }

                    if (data.hasExtra("property_bhk")) {
                        property_bhk = data.getStringExtra("property_bhk");
                        Log.d("TAG","property_bhk:"+property_bhk);
                    }
                    if (data.hasExtra("budget")) {
                        if(!TextUtils.isEmpty(data.getStringExtra("budget"))) {
                            budget = data.getStringExtra("budget");
                            Log.d("TAG","budget:"+budget);
                        }
                    }
                    callLoader(0);
                    break;
            }
        } else {

        }
    }

    public void beginSearch(String query) {
        projectAdapter.getFilter().filter(query);
    }

    public void startSearch(String query) {
        getFilter().filter(query);
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
                input.put("unit_type",property_bhk!= null ? property_bhk : "");
                input.put("budget",budget!= null ? budget : "");
                result = new ProjectsLoader(this,access_token,input);
                break;

            case 1:
                result = new ProjectsLoader(this, access_token,input);

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
                    if (data.get("projects") != null) {
                        projectList.clear();
                        Type type = new TypeToken<ArrayList<ProjectBean>>() {
                        }.getType();
                        ArrayList<ProjectBean> List  = gson.fromJson(data.get("projects"),type);
                        projectList.addAll(List);
                        projectAdapter.notifyDataSetChanged();
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();

                if (charString.isEmpty()) {

                    mFilteredList = projectList;
                } else {
                    ArrayList<ProjectBean> filteredList = new ArrayList<>();


                    for (ProjectBean item : projectList) {

                        if (item.getPost_title().toLowerCase().contains(charString)) {

                            filteredList.add(item);

                            if(key.equals("location")){
                                if (item.getCity_name().toLowerCase().contains(charString)) {

                                    filteredList.add(item);

                                }

                            }else if(key.equals("propety_type")){

                                if (item.getPost_title().toLowerCase().contains(charString)) {

                                    filteredList.add(item);
                                }
                            }

                        }
                    }

                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;

            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredList = (ArrayList<ProjectBean>) results.values;
//                initializeRecyclerView(projectList);

            }
        };


    }
}
