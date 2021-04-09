package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
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

import in.psgroup.psgroup.Adapters.MyPropertyAdapter;
import in.psgroup.psgroup.FilterModule.FilterActivity;
import in.psgroup.psgroup.Models.MyProperty;
import in.psgroup.psgroup.Models.OffersBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.MyPropertiesLoader;
import in.psgroup.psgroup.Utility.Utils;

public class MyProperties extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String,String>> {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyPropertyAdapter myPropertyAdapter;
    private ArrayList<MyProperty> projectList=new ArrayList<>();
    private ImageView backPress;
    private SearchView search;
    private FloatingActionButton filter;
    private MyProperty myProperty;
    private SearchManager searchManager;
    private UserSessionManager session;
    private String access_token;
    private int response_code = 0;
    Bundle bundle;
    ProgressDialog loading;
    PsGroupApplication myApplication;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_properties);
        myApplication = (PsGroupApplication) this.getApplication();
        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        access_token=user.get(UserSessionManager.KEY_accessToken);
        callLoader(0);
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
        //search = findViewById(R.id.ic_projectSearch);
        /*filter = findViewById(R.id.filter);*/
        search = (SearchView)findViewById(R.id.search);
        search.setFocusable(true);
        searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);
        EditText searchEditText = (EditText) search.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.imageCount));
        searchEditText.setHintTextColor(getResources().getColor(R.color.imageCount));
        searchEditText.setBackgroundResource(R.color.White);
        ImageView searchClose = (ImageView) search.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setImageResource(R.drawable.ic_close);
        search.setMaxWidth(Integer.MAX_VALUE);

        recyclerView = findViewById(R.id.project_recycler);
        /*filter.setOnClickListener(this);*/

       // search.setOnClickListener(this);
        backPress.setOnClickListener(this);

    }

    private void initializeRecyclerView() {

    }
    private void callLoader(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }


    private  void  showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            /*case R.id.filter:
                startActivity(new Intent(MyProperties.this, FilterActivity.class));
                break;*/

            case R.id.ic_projectSearch:
                startActivity(new Intent(MyProperties.this,SearchActivity.class));
                break;
            case R.id.ic_backProject:
                onBackPressed();
                break;

        }


    }

    public void beginSearch(String query) {
        myPropertyAdapter.getFilter().filter(query);
    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        result = new MyPropertiesLoader(this,access_token);
        return result;
    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        if (data != null && !data.isEmpty()) {
            try {
                setData(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Utils.showToast(this,"something went wrong");
        }
    }

    private void setData(HashMap<String, String> data) throws JSONException{
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        projectList=new ArrayList<>();
        Type listType = new TypeToken<ArrayList<OffersBean>>() {}.getType();
        Gson gson = new Gson();
        if(response_code!=0)
        {
            switch (response_code)
            {
                case 200:
                    projectList.clear();
                    JSONObject jsonObject=new JSONObject(data.get("data"));
                    JSONObject jsonObject1= jsonObject.getJSONObject("data");
                    JSONArray jsonArray=jsonObject1.getJSONArray("myproperties");
                    for (int k = 0; k < jsonArray.length(); k++) {
                        projectList.add(gson.fromJson(jsonArray.getJSONObject(k).toString(), MyProperty.class));
                    }
                    setAdapter(projectList);
                    /*if(projectList.size()<=1)
                    {
                        myProperty=projectList.get(0);
                        Intent i=new Intent(this,LedgerActivity.class);
                        i.putExtra("my_property",myProperty);
                        startActivity(i);
                    }else
                    {
                        setAdapter(projectList);
                    }*/

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

    private void setAdapter(ArrayList<MyProperty> projectList) {
        layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        myPropertyAdapter = new MyPropertyAdapter(projectList,getApplicationContext());
        recyclerView.setAdapter(myPropertyAdapter);
    }



    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }
}
