package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ResendOtpLoader;
import in.psgroup.psgroup.Utility.Utils;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    EditText new_pass,conf_pass;
    TextView goback,pass_error;
    Button reset;
    private String mobile;
    ProgressDialog loading;
    private int response_code = 0;
    private int id;
    PsGroupApplication myApplication;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        myApplication = (PsGroupApplication) this.getApplication();
        mobile= getIntent().getExtras().getString("mobile");
        initialize();
    }

    private void initialize() {
        new_pass = (EditText)findViewById(R.id.new_pass);
        conf_pass = (EditText)findViewById(R.id.conf_pass);
        goback = (TextView)findViewById(R.id.goback);
        pass_error= (TextView)findViewById(R.id.pass_error);
        reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(this);
        goback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.reset:
                if(new_pass.getText().toString().equals(conf_pass.getText().toString())) {
                    callLoader(0);

                    pass_error.setVisibility(View.GONE);
                }
                else{
                    pass_error.setVisibility(View.VISIBLE);
                }

           break;
            case R.id.goback:
                i = new Intent(ResetPasswordActivity.this,OtpActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Signing In.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        switch (id) {

            case 0:
                HashMap<String, String> input = new HashMap<>();
                input.put("otp", new_pass.getText().toString());
                input.put("mobile",mobile);
                result = new ResendOtpLoader(this, input);

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
            Utils.showToast(this,"something went wrong");
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
                            JSONObject jsonObject = new JSONObject(data.get("Data"));
                            if(jsonObject.getString("status").equals("success")) {
                                i = new Intent(ResetPasswordActivity.this,LoginActivity.class);
                                startActivity(i);

                            }else
                            {
                                Utils.showToast(this,jsonObject.getString("message"));
                                // i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                                // startActivity(i);
                            }
                            break;


                    }
                    break;
                case 203:
                    JSONObject jsonObject = new JSONObject(data.get("Data"));
                    if(jsonObject.getString("status").equals("success")) {


                    }else
                    {
                        Utils.showToast(this,jsonObject.getString("message"));

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
    private void callLoader(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }
    }
}
