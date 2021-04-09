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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import in.psgroup.psgroup.Models.HelpBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ScheduleVisitLoader;
import in.psgroup.psgroup.Utility.Utils;

public class ScheduleNextActivity extends AppCompatActivity  implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    private Button btn_schedule;
    private CheckBox cb_site, cb_office;
    private ImageView ic_back;
    private EditText message;
    private ProgressDialog loading;
    private String access_token,title,project_id,visit_date,visit_time,customerCode,visit_type,visit_msg;
    private int id;
    PsGroupApplication myApplication;
    UserSessionManager session;
    private int response_code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_next);
        myApplication = (PsGroupApplication) this.getApplication();
        session = new UserSessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        access_token = user.get(UserSessionManager.KEY_accessToken);
        customerCode= user.get(UserSessionManager.KEY_loyaltycode);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            title =extras.getString("projectName");
            project_id =extras.getString("projectid");
            visit_date=extras.getString("visit_date");
            visit_time=extras.getString("visit_time");
        }
        initialize();



    }

    private void initialize() {
        cb_site = (CheckBox) findViewById(R.id.cb_site);
        cb_office = (CheckBox) findViewById(R.id.cb_office);
        btn_schedule =findViewById(R.id.btn_Schedule);
        ic_back = findViewById(R.id.ic_back);
        message= findViewById(R.id.message);
        ic_back.setOnClickListener(this);
        btn_schedule.setOnClickListener(this);
        cb_office.setOnClickListener(this);
        cb_site.setOnClickListener(this);
     //   visit_type =cb_site.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ic_back:
                this.onBackPressed();
                break;

            case R.id.btn_Schedule:
                callloader(0);
                break;

            case R.id.cb_office:
                cb_office.setChecked(true);
                cb_office.setTextColor(getResources().getColor(R.color.black));
                cb_site.setTextColor(getResources().getColor(R.color.signup));
                cb_site.setChecked(false);
                visit_type ="office";
                break;

            case R.id.cb_site:
                cb_site.setChecked(true);
                cb_site.setTextColor(getResources().getColor(R.color.black));
                cb_office.setTextColor(getResources().getColor(R.color.signup));
                cb_office.setChecked(false);
                visit_type ="site";
                break;
        }
    }

    private void  showAlertDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.scheduled_card, viewGroup, false);
        Button btn = (Button) dialogView.findViewById(R.id.btn_done);
        TextView tv_title_message = (TextView) dialogView.findViewById(R.id.title_message);
        TextView tv_description = (TextView) dialogView.findViewById(R.id.tv_success_text);
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
                ScheduleNextActivity.this.finish();
            }
        });


    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        HashMap<String,String> input = new HashMap<>();
        input = new HashMap<>();
        input.put("CustomerCode",customerCode);
        input.put("projectId",project_id);
        input.put("projectName",title);
        input.put("visit_date",visit_date);
        input.put("visit_time",visit_time);
        input.put("visit_type",visit_type);
        input.put("message",message.getText().toString());
        result = new ScheduleVisitLoader(this,access_token,input);
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

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }

    private void callloader(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }
    }

    private void setData(HashMap<String, String> data, int id)throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        Gson gson = new Gson();
        Intent i;
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    showAlertDialog();
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
