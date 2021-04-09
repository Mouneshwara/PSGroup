package in.psgroup.psgroup;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import in.psgroup.psgroup.Models.ApiClient;
import in.psgroup.psgroup.Models.APIUrls;
import in.psgroup.psgroup.Models.LoginBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.LoginLoader;
import in.psgroup.psgroup.Utility.Utils;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>>{

    UserSessionManager session;
    JSONObject response,profile_pic;
    ProgressDialog loading;
    private JSONObject responseJson;
    private String status,access_token,tv_name,email,tv_userType,tv_mobile,profile,password,password_pattern = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,16}$";;
    private int response_code = 0;
    private int id;
    PsGroupApplication myApplication;
    Button loginBtn;
    EditText et_email,et_password;
    TextView tv_forgot,tv_emailerror,tv_password_error,signup_here,login_as_guest,forgotPassword;
    LinearLayout google_button,fb_button;
    View rootview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //keyHash
        session = new UserSessionManager(getApplicationContext());
        myApplication = (PsGroupApplication) this.getApplication();
        rootview =getWindow().getDecorView().getRootView();
        //initializing
        initView();

        validate();

    }
    private void validate() {
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(!et_email.isFocused()&&!et_email.getText().toString().isEmpty()){
                    if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches() || Patterns.PHONE.matcher(et_email.getText().toString()).matches()) && et_email.length() > 0) {
                        tv_emailerror.setVisibility(View.VISIBLE);
                    }

                }
                else {
                    tv_emailerror.setVisibility(View.GONE);

                }

                /*if(!et_password.isFocused()&&!et_password.getText().toString().isEmpty()) {
                    if (et_password.length() < 8 && et_password.length() > 0) {
                        tv_password_error.setVisibility(View.VISIBLE);

                    }
                }
                else {
                    tv_password_error.setVisibility(View.GONE);

                }*/
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        et_email.addTextChangedListener(tw);
        /*et_password.addTextChangedListener(tw);*/
    }

    private boolean valid() {
        this.email = et_email.getText().toString();
        /*this.password = et_password.getText().toString();*/
        if (et_email.length() < 10) {
//            btn_getotp.setEnabled(true);
            tv_emailerror.setVisibility(View.VISIBLE);
            et_email.requestFocus();

            return false;
        }
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches() && et_email.length() > 0) {
                tv_emailerror.setVisibility(View.VISIBLE);
                et_email.requestFocus();


            }return false;
        }
        /*if (et_password.length() <= 0) {
            tv_password_error.setVisibility(View.VISIBLE);
            et_password.requestFocus();

            return false;
        }*/ else {
            tv_emailerror.setVisibility(View.GONE);
            tv_password_error.setVisibility(View.GONE);
            return true;
        }
    }

    private void initView() {
        //loginButton initializing
        loginBtn=(Button)findViewById(R.id.loginButton);

        //editText initialize
        et_email=(EditText)findViewById(R.id.email);
        et_email.setSingleLine(true);
       /* et_password=(EditText)findViewById(R.id.password);*/

        //textView
        /*tv_forgot=(TextView)findViewById(R.id.forgotPassword);*/
        tv_emailerror = (TextView) findViewById(R.id.emailerror) ;
        tv_password_error = (TextView) findViewById(R.id.passworderror);
        signup_here = (TextView) findViewById(R.id.signup_here);
        login_as_guest = (TextView)findViewById(R.id.login_as_guest);

        /*forgotPassword = (TextView)findViewById(R.id.forgotPassword);*/

        signup_here.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        /*tv_forgot.setOnClickListener(this);*/

      }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.loginButton:
                if(valid()){
                callLoader(0);

            }
                break;

           /* case R.id.forgotPassword:
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                break;*/

            case R.id.signup_here:
                startActivity(new Intent(LoginActivity.this,SignUpDetailsActivity.class));
                    break;
        }
    }


    private void showAlert(String alert_message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(
                this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Log In");

        // Setting Dialog Message
        alertDialog.setMessage(alert_message);

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // Write your code here to execute after dialog
                        // closed
                        dialog.cancel();
                    }
                });

        // Showing Alert Message
        alertDialog.show();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }




    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int i, Bundle bundle) {

        loading = ProgressDialog.show(this, "Signing In.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        switch (id) {

            case 0:
                HashMap<String, String> input = new HashMap<>();
                input.put("username", et_email.getText().toString());
                /*input.put("password", et_password.getText().toString());*/
                result = new LoginLoader(this, input);

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
        Intent i;
        Gson gson = new Gson();
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    switch (id) {
                        case 0:
                            JSONObject jsonObject = new JSONObject(data.get("Data"));
                            Type type = new TypeToken<LoginBean>() {
                            }.getType();
                            LoginBean loginBean = gson.fromJson(data.get("Data"),type);
//                            session.createUserLoginSession(loginBean.getAccessToken(), "","","",
//                                    "","","");
                            session.createUserLoginSession(loginBean.getAccessToken(),"","","",
                                    "",loginBean.getSfdcId(),"");
                            session.createguest(false);
                            i = new Intent(LoginActivity.this, OtpForLoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            // Add new Flag to start new Activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra(Utils.KEY_DATA,loginBean);
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
