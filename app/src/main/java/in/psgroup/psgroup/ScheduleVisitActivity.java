package in.psgroup.psgroup;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import in.psgroup.psgroup.Adapters.HelpAdapter;
import in.psgroup.psgroup.Models.HelpBean;
import in.psgroup.psgroup.Models.ScheduleBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ScheduleVisitLoader;
import in.psgroup.psgroup.Utility.Utils;

public class ScheduleVisitActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    private ArrayList<ScheduleBean> dataModels;
//    private ListView listView;

    private ImageView back;

    /*private GridLayout gridLayout;*/
    private Calendar calendar;
    private CalendarView calendarView;
    private Button btn_schedule;
    private ProgressDialog loading;
    private String access_token, title, project_id,time,curDate,visit_date,visit_time,customerCode,visit_type;
    private int id;
    private CheckBox cb_site,cb_office;
    PsGroupApplication myApplication;
    private EditText message;
    private UserSessionManager sessionManager;
    private int response_code = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_next);
        myApplication = (PsGroupApplication) this.getApplication();
        sessionManager = new UserSessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetails();
        access_token = user.get(UserSessionManager.KEY_accessToken);
        customerCode= user.get(UserSessionManager.KEY_loyaltycode);
        setContentView(R.layout.activity_schedule_visit);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("projectName");
            project_id = extras.getString("projectid");
        }
        initialize();


    }

    private void initialize() {
        back = (ImageView) findViewById(R.id.ic_backLoyalty);
        /*gridLayout = (GridLayout) findViewById(R.id.grid_layout);*/
        calendar = Calendar.getInstance();
        calendarView = findViewById(R.id.calendarView);
        btn_schedule = findViewById(R.id.btn_Schedule);
        cb_site = (CheckBox) findViewById(R.id.cb_site);
        cb_office = (CheckBox) findViewById(R.id.cb_office);
        message= findViewById(R.id.message);

        btn_schedule.setOnClickListener(this);
        cb_office.setOnClickListener(this);
        cb_site.setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*final int count = gridLayout.getChildCount();


        for (int i = 0; i < count; i++) {
            final Button button = (Button) gridLayout.getChildAt(i);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!button.isSelected()) {
                        for (int j = 0; j < count; j++) {
                            gridLayout.getChildAt(j).setSelected(false);
                            ((Button) gridLayout.getChildAt(j)).setTextColor(getResources().getColor(R.color.sbiDesc));

                        }
                        button.setSelected(true);
                        button.setTextColor(getResources().getColor(R.color.white));
                        time=button.getText().toString();
                    }


                }
            });*/

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month,
                                                int dayOfMonth) {
                    String d = String.valueOf(dayOfMonth);
                    String y =String.valueOf(month);
                    String m =String.valueOf(year);

                    curDate = y+"-"+m+"-"+d;
                }
            });
        }


    /*private void property_status_selection() {
        final int childCount = gridLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final Button btn = (Button) gridLayout.getChildAt(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!btn.isSelected()) {
                        for (int j = 0; j < childCount; j++) {
                            gridLayout.getChildAt(j).setSelected(false);
                            ((Button) gridLayout.getChildAt(j)).setTextColor(getResources().getColor(R.color.sbiDesc));

                        }
                        btn.setSelected(true);
                        btn.setTextColor(getResources().getColor(R.color.white));
                        time=btn.getText().toString();
                    }

                }
            });
        }
    }*/


    public void onBackPressed() {
        super.onBackPressed();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Schedule:
                callloader(0);
                /*Intent k = new Intent(ScheduleVisitActivity.this, ScheduleNextActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("projectName", title);
                bundle.putString("projectid", project_id);
                bundle.putString("visit_date", curDate);
                bundle.putString("visit_time", time);
                k.putExtras(bundle);
                startActivity(k);*/
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

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        HashMap<String,String> input = new HashMap<>();
        input = new HashMap<>();
        input.put("CustomerCode",customerCode);
        input.put("projectId",project_id);
        input.put("projectName",title);
        input.put("visit_date",curDate);
        input.put("visit_time","12.30");
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
                finish();
            }
        });


    }
}
