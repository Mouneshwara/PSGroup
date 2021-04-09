package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import in.psgroup.psgroup.Fragments.HomeFragment;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.GuestLoader;
import in.psgroup.psgroup.Network.LoginLoader;
import in.psgroup.psgroup.Utility.Utils;

public class GetStartedActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    TextView login_here, login_as_guest;
    UserSessionManager session;
    PsGroupApplication myApplication;
    private int id;
    ProgressDialog loading;
    private int response_code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        session = new UserSessionManager(getApplicationContext());
        myApplication = (PsGroupApplication) this.getApplication();;
        login_here = (TextView) findViewById(R.id.login_here);

        login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStartedActivity.this, LoginActivity.class));
                GetStartedActivity.this.finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_as_guest:
                session.createguest(true);
                callApi(0);
//                Intent i = new Intent(GetStartedActivity.this,HomeActivity.class);
//            startActivity(i);


                break;
        }
    }

    private void callApi(int i) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }
    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Guest Sign In.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        result = new GuestLoader(this);
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

    private void setData(HashMap<String, String> data, int id) throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        Intent i;
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    switch (id) {
                        case 0:
                            JSONObject jsonObject = new JSONObject(data.get("data"));

                            session.createUserLoginSession(jsonObject.getString("access_token"),"Guest","","","","","");

                            session.createguest(true);
                            i = new Intent(GetStartedActivity.this, HomeActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            // Add new Flag to start new Activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();
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
