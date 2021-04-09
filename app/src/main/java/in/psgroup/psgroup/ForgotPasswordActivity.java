package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ForgotPasswordLoader;
import in.psgroup.psgroup.Network.SignUpLoader;
import in.psgroup.psgroup.Utility.Utils;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
     EditText et_userid,mob_no;
     Button btn_continue;
     TextView goback,phone_error;
     private String otp,user_id,mobile_no;
    ProgressDialog loading;
    private int response_code = 0;
    private int id;
    PsGroupApplication myApplication;
    UserSessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        myApplication = (PsGroupApplication) this.getApplication();
        initView();
    }

    private void initView() {
        et_userid = (EditText) findViewById(R.id.userId);
        et_userid.setSingleLine(true);

        btn_continue = (Button) findViewById(R.id.continueButton);
        goback = (TextView)findViewById(R.id.goback);
        phone_error= (TextView)findViewById(R.id.phone_error);
        mob_no = (EditText)findViewById(R.id.mob_no);
        goback.setOnClickListener(this);
        btn_continue.setOnClickListener(this);

    }

    private void validate() {
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ((!(android.util.Patterns.EMAIL_ADDRESS.matcher(et_userid.getText().toString()).matches()))){
                   // tv_emailerror.setVisibility(View.VISIBLE);
                    et_userid.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_stroke));
                }
                else {
                  //  tv_emailerror.setVisibility(View.GONE);
                  //  et_userid.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.edittextbackground ));

                }

                if (( !Patterns.PHONE.matcher(mob_no.getText().toString()).matches()) &&( mob_no.length() > 0)){
                    // tv_emailerror.setVisibility(View.VISIBLE);
                    et_userid.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_stroke));
                }
                else {
                    //  tv_emailerror.setVisibility(View.GONE);
                    //  et_userid.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.edittextbackground ));

                }
            }
        };
        et_userid.addTextChangedListener(tw);
        mob_no.addTextChangedListener(tw);
    }

    private boolean valid() {
        this.user_id = et_userid.getText().toString();
        this.mobile_no = mob_no.getText().toString();
       /* if (et_userid.length() < 10) {
//            btn_getotp.setEnabled(true);
           // tv_emailerror.setVisibility(View.VISIBLE);
            et_userid.requestFocus();

            return false;
        }*/
      /*  if (android.util.Patterns.EMAIL_ADDRESS.matcher(et_userid.getText().toString()).matches()) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_userid.getText().toString()).matches() && et_userid.length() > 0) {
                //tv_emailerror.setVisibility(View.VISIBLE);
                et_userid.requestFocus();

                return false;
            }
        }*/

        if (!(Patterns.PHONE.matcher(mob_no.getText().toString()).matches())) {

            phone_error.setVisibility(View.VISIBLE);
            return false;
        } else {
            phone_error.setVisibility(View.GONE);

            return true;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goback:
               onBackPressed();
               break;
            case R.id.continueButton:
                if(valid()) {
                    callLoader(0);
                }

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
            //    input.put("username", et_userid.getText().toString());
                input.put("mobile", mob_no.getText().toString());
                result = new ForgotPasswordLoader(this, input);

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
                                otp =jsonObject.getString("otp");
                                i = new Intent(ForgotPasswordActivity.this,OtpActivity.class);
                                Bundle extra = new Bundle();
                                extra.putString("otp",otp);
                                extra.putString("mobile",mob_no.getText().toString());
                                i.putExtras(extra);
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
                        otp =jsonObject.getString("otp");
                        i = new Intent(ForgotPasswordActivity.this,OtpActivity.class);
                        Bundle extra = new Bundle();
                        extra.putString("otp",otp);
                        extra.putString("mobile",mob_no.getText().toString());
                        i.putExtras(extra);
                        startActivity(i);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
