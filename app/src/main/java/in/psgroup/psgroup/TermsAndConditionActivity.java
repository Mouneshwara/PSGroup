package in.psgroup.psgroup;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;

import java.util.HashMap;

import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ReferDetailLoader;
import in.psgroup.psgroup.Utility.Utils;

public class TermsAndConditionActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    private ImageView ic_back;
    private WebView tv_text;
    private CheckBox cb_checkbox;
    private Button btn_refer;
    private View view;
    private String title,project_id,access_token,customerCode,fName,lname,mobile,emailAddress,street,city,state,country,postalCode,
            dOb,alternateMobile,message;
    private ProgressDialog loading;
    private PsGroupApplication myApplication;
    private int response_code = 0,id;
    private UserSessionManager session;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);

        myApplication = (PsGroupApplication) this.getApplication();
        session = new UserSessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        access_token = user.get(UserSessionManager.KEY_accessToken);
        customerCode= user.get(UserSessionManager.KEY_loyaltycode);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            title =extras.getString("projectName");
            project_id =extras.getString("projectid");
            customerCode=extras.getString("CustomerCode");
            fName=extras.getString("fName");
            lname =extras.getString("lname");
            mobile =extras.getString("mobile");
            emailAddress=extras.getString("emailAddress");
            city=extras.getString("City");
            street=extras.getString("Street");
            state =extras.getString("State");
            country =extras.getString("Country");
            postalCode=extras.getString("postalCode");
            alternateMobile =extras.getString("alternateMobile");
            dOb =extras.getString("DOB");
        }
        initialize();
    }

    private void initialize() {
        view = new View(this);
        ic_back = (ImageView)findViewById(R.id.ic_back);
        tv_text = (WebView) findViewById(R.id.tv_text);
        tv_text.loadUrl("https://psgroup.in/file.html");
        cb_checkbox = (CheckBox)findViewById(R.id.cb_checkbox);
        btn_refer = (Button)findViewById(R.id.btn_refer);
        ic_back.setOnClickListener(this);
        btn_refer.setOnClickListener(this);
        cb_checkbox.setOnClickListener(this);

    }

    private void showAlertDialog(View view) {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_redeem_now, viewGroup, false);
        Button btn = (Button) dialogView.findViewById(R.id.btn_done);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                TermsAndConditionActivity.this.finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_back:
                this.onBackPressed();
                break;
            case R.id.btn_refer:
                callloader(0);
                break;

            case R.id.cb_checkbox:
                if(cb_checkbox.isChecked()) {
                    btn_refer.setEnabled(true);
                }else {
                    btn_refer.setEnabled(false);
                }
                break;

        }
    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        HashMap<String,String> input = new HashMap<>();
        input = new HashMap<>();
        input.put("CustomerCode",customerCode);
        input.put("fName",fName);
        input.put("lname",lname);
        input.put("mobile",mobile);
        input.put("emailAddress",emailAddress);
        input.put("Street",street);
        input.put("City",city);
        input.put("State",state);
        input.put("Country",country);
        input.put("postalCode",postalCode);
        input.put("ProjectName",title);
        input.put("alternateMobile",alternateMobile);
        input.put("DOB",dOb);
        input.put("project_id",project_id);
        result = new ReferDetailLoader(this,access_token,input);
        return result;
    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        getLoaderManager().destroyLoader(id);
        if (data != null && !data.isEmpty()) {
            try {
                setData(data,id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    private void callloader(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }
    }

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }

    private void setData(HashMap<String, String> data, int id)throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        Gson gson = new Gson();
        Intent i;
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    showAlertDialog(view);
                    break;
                case 400:
                    Toast.makeText(this,"You Cannot Create Referral Because Limit Has Been Exceeded.",Toast.LENGTH_LONG).show();
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
