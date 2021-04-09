package in.psgroup.psgroup.FilterModule;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import in.psgroup.psgroup.FilterModule.Adappters.FilterPropertyLocationAdapter;
import in.psgroup.psgroup.FilterModule.Adappters.FilterPropertyPriceAdapter;
import in.psgroup.psgroup.FilterModule.Adappters.FilterPropertyStatusAdapter;
import in.psgroup.psgroup.FilterModule.Adappters.FilterPropertyTypeAdapter;
import in.psgroup.psgroup.FilterModule.Interfaces.FilterInterface;
import in.psgroup.psgroup.FilterModule.Models.FilterBean;
import in.psgroup.psgroup.FilterModule.Models.Location;
import in.psgroup.psgroup.FilterModule.Models.Price;
import in.psgroup.psgroup.FilterModule.Models.PriceInterval;
import in.psgroup.psgroup.FilterModule.Models.PropertyStatus;
import in.psgroup.psgroup.FilterModule.Models.PropertyType;
import in.psgroup.psgroup.Models.ProjectBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.FilterLoader;
import in.psgroup.psgroup.PsGroupApplication;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Utility.Utils;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener, FilterInterface, LoaderManager.LoaderCallbacks<HashMap<String, String>>, CompoundButton.OnCheckedChangeListener {
    Button btn_apply, btn_close, btn_location;
    View view;
    GridLayout gl_bhk;
    RecyclerView rv_proprty_type, rv_location, rv_property_price, rv_property_status;
    String location_name, propety_type, property_price, property_status, property_bhk;
    ArrayList<String> location = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<String> price = new ArrayList<String>();
    ArrayList<String> status = new ArrayList<String>();
    ArrayList<String> bhk = new ArrayList<String>();
    ArrayList<ProjectBean> projectList = new ArrayList<>();
    String access_token, customerCode;
    FilterBean filterbean;
    Price priceBean;
    private PriceInterval priceInterval;
    private PropertyStatus propertyStatus;
    private PropertyType propertyType;
    private Location propertylocation;
    private ProgressDialog loading;
    private int id;
    private int response_code = 0;
    private PsGroupApplication myApplication;
    private UserSessionManager sessionManager;
    private ImageView ic_backFilter;
    private String priceinterval;
    private ArrayList<String> unit_types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        myApplication = (PsGroupApplication) this.getApplication();
        sessionManager = new UserSessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetails();
        access_token = user.get(UserSessionManager.KEY_accessToken);
        view = new View(this);
        // Intent intent = this.getIntent();
        //   projectList = intent.getExtras().getParcelableArrayList("projectlist");

        initView();
        callApi(0);
//        bhk_selection();
    }

    private void initView() {
        rv_location = (RecyclerView) findViewById(R.id.rv_location);
        rv_proprty_type = (RecyclerView) findViewById(R.id.rv_proprty_type);
        rv_property_status = (RecyclerView) findViewById(R.id.rv_property_status);
        rv_property_price = (RecyclerView) findViewById(R.id.rv_property_price);
        gl_bhk = (GridLayout) findViewById(R.id.gl_bhk);
        ic_backFilter = (ImageView) findViewById(R.id.ic_backFilter);
        btn_close = (Button) findViewById(R.id.btn_close);
        btn_apply = (Button) findViewById(R.id.btn_apply);
        btn_location = (Button) findViewById(R.id.btn_location);
        ic_backFilter.setOnClickListener(this);
        btn_apply.setOnClickListener(this);
        btn_close.setOnClickListener(this);

    }

    private void bhk_selection() {
        final int childCount = gl_bhk.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final CheckBox checkBox = (CheckBox) gl_bhk.getChildAt(i);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                        bhk.add(buttonView.getText().toString());
                    else
                        bhk.remove(buttonView.getText().toString());
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_backFilter:
                this.onBackPressed();
                break;
            case R.id.btn_close:
                this.onBackPressed();
                break;

            case R.id.btn_apply:
                Intent i = new Intent();
                if(propertylocation!=null)
                    i.putExtra("location_name", propertylocation.getLocationId());
                else
                    i.putExtra("location_name", "");
                if(propertyType!=null)
                    i.putExtra("property_type", propertyType.getType());
                else
                    i.putExtra("property_type", "");
                if(priceInterval!=null)
                    i.putExtra("budget", String.valueOf(priceInterval.getValue()));
                if(propertyStatus!=null)
                    i.putExtra("property_status",propertyStatus.getStatus());
                if(bhk!=null)
                    i.putExtra("property_bhk", TextUtils.join(",", bhk));
                //   i.putParcelableArrayListExtra("projectlist",projectList);
                setResult(RESULT_OK, i);
                finish();
                break;

        }


    }

    private void callApi(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showAlert(this,getResources().getString(R.string.no_internet_message));
        }
    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        HashMap<String, String> input = new HashMap<>();
        input.put("customerCode", customerCode);
        result = new FilterLoader(this, access_token, input);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
            Utils.showToast(this, "something went wrong");
        }
    }


    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setData(HashMap<String, String> data, int id) throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));

        Gson gson = new Gson();
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success

                    switch (id) {
                        case 0:

                            if (data.get("data") != null) {
                                Type type = new TypeToken<FilterBean>() {
                                }.getType();
                                filterbean = gson.fromJson(data.get(getResources().getString(R.string.data)), type);
                                setGridData(filterbean);
                            }
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setGridData(FilterBean filterbean) {
        setFlexLayout(rv_location);
        FilterPropertyLocationAdapter locationAdapter = new FilterPropertyLocationAdapter(filterbean.getLocation(), this,this);
        rv_location.setAdapter(locationAdapter);

        setFlexLayout(rv_proprty_type);
        FilterPropertyTypeAdapter adapter = new FilterPropertyTypeAdapter(filterbean.getPropertyType(), this,this);
        rv_proprty_type.setAdapter(adapter);

        setFlexLayout(rv_property_price);
        FilterPropertyPriceAdapter propertyPriceAdapter = new FilterPropertyPriceAdapter(filterbean.getPriceInterval(), this,this);
        rv_property_price.setAdapter(propertyPriceAdapter);

        setFlexLayout(rv_property_status);
        FilterPropertyStatusAdapter propertyStatusAdapter = new FilterPropertyStatusAdapter(filterbean.getPropertyStatus(), this,this);
        rv_property_status.setAdapter(propertyStatusAdapter);

        for (int i = 0; i < filterbean.getUnitType().size(); i++) {

            LayoutInflater inflater;
            CheckBox cb_check;
            inflater = LayoutInflater.from(this);
            View inflatedLayout = inflater.inflate(R.layout.layout_filter_unit_type, null);
            cb_check = inflatedLayout.findViewById(R.id.cb_check);
            cb_check.setText(filterbean.getUnitType().get(i));
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(5, 5, 5, 5);
            inflatedLayout.setLayoutParams(params);
            /*kolkata.setText(filterbean.getLocation().get(i).getlocationName());*/
            gl_bhk.addView(cb_check);
            cb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                        bhk.add(compoundButton.getText().toString());
                    else
                        bhk.remove(compoundButton.getText().toString());
                }
            });
        }

    }

    private void setFlexLayout(RecyclerView rv) {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        layoutManager.setAlignItems(AlignItems.CENTER);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        rv.setLayoutManager(layoutManager);
    }

    @Override
    public void setPropertyLocation(Location location) {
          this.propertylocation = location;
    }

    @Override
    public void setPriceInterval(PriceInterval priceInterval) {
         this.priceInterval=priceInterval;
    }

    @Override
    public void setPropertyStatus(PropertyStatus propertyStatus) {
          this.propertyStatus = propertyStatus;
    }

    @Override
    public void setPropertyType(PropertyType propertyType) {
          this.propertyType = propertyType;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}


