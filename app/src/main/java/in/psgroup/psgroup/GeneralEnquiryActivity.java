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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import in.psgroup.psgroup.Adapters.GeneralEnquiryAdapter;
import in.psgroup.psgroup.Adapters.OffersAdapter;
import in.psgroup.psgroup.Adapters.ReferPropertyAdapter;
import in.psgroup.psgroup.Models.GeneralEnquiryBean;
import in.psgroup.psgroup.Models.HelpBean;
import in.psgroup.psgroup.Models.OffersBean;
import in.psgroup.psgroup.Models.ReferPropertyBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.GeneralEnquiryLoader;
import in.psgroup.psgroup.Network.OfferLoader;
import in.psgroup.psgroup.Utility.Utils;

public class GeneralEnquiryActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    private RecyclerView recycler;
    private ImageView ic_back;
    private int id;
    private ProgressDialog loading;
    private UserSessionManager session;
    private PsGroupApplication myApplication;
    private String accesss_token;
    private int response_code = 0;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<GeneralEnquiryBean> questionList;
    private GeneralEnquiryAdapter generalAdapter;
    private String CategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_enquiry);
        session = new UserSessionManager(getApplicationContext());
        accesss_token=session.getUserDetails().get(UserSessionManager.KEY_accessToken);
        myApplication = (PsGroupApplication) this.getApplication();
        if (getIntent().getExtras()!=null){
            CategoryId =getIntent().getExtras().getString("help");
        }

        initialize();
        callloader(0);
    }

    private void callloader(int i) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }
    }

    private void initialize() {
        ic_back = (ImageView)findViewById(R.id.ic_back);
        recycler = (RecyclerView)findViewById(R.id.recycler);
        ic_back.setOnClickListener(this);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(layoutManager);
        questionList = new ArrayList<>();
//        questionList.add(new GeneralEnquiryBean("What is the process of registration and when does\n" +
//                "registration take place?"));
//        questionList.add(new GeneralEnquiryBean("What is the process of registration and when does\n" +
//                "registration take place?"));
//        questionList.add(new GeneralEnquiryBean("What is the process of registration and when does\n" +
//                "registration take place?"));
//        questionList.add(new GeneralEnquiryBean("What is the process of registration and when does\n" +
//                "registration take place?"));
//        generalAdapter = new GeneralEnquiryAdapter(questionList,getApplicationContext());
        recycler.setAdapter(generalAdapter);
    }

    @Override
    public void onClick(View view) {
        Intent i;
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
        result = new GeneralEnquiryLoader(this,accesss_token,CategoryId);
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
        questionList=new ArrayList<>();
        Type listType = new TypeToken<ArrayList<OffersBean>>() {}.getType();
        Gson gson = new Gson();
        if(response_code!=0)
        {
            switch (response_code)
            {
                case 200:
                    questionList.clear();
                    JSONObject jsonObject=new JSONObject(data.get("data"));
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for (int k = 0; k < jsonArray.length(); k++) {
                        questionList.add(gson.fromJson(jsonArray.getJSONObject(k).toString(), GeneralEnquiryBean.class));
                    }
                    setAdapters(questionList);
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

    private void setAdapters(ArrayList<GeneralEnquiryBean> questionList) {
        generalAdapter=new GeneralEnquiryAdapter(questionList,this);
        recycler.setAdapter(generalAdapter);
    }

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }
}
