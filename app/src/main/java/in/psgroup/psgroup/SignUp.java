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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.LoginLoader;
import in.psgroup.psgroup.Network.SignUpLoader;
import in.psgroup.psgroup.Utility.Utils;

public class SignUp extends AppCompatActivity implements View.OnClickListener , LoaderManager.LoaderCallbacks<HashMap<String, String>>{
    LinearLayout google_button,fb_button;
    EditText email,password,confirm_password;
    Button btn_signup;
    TextView login_here,emailerror,passworderror,confirm_pass_error;
    private String mpassword,mconfirm_password;
    ProgressDialog loading;
    private int response_code = 0;
    private int id;
    PsGroupApplication myApplication;
    UserSessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myApplication = (PsGroupApplication) this.getApplication();
        initialize();
        validate();
    }

    private void initialize() {

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        confirm_password = (EditText)findViewById(R.id.confirm_password);
        btn_signup = (Button)findViewById(R.id.btn_signup);
        login_here = (TextView)findViewById(R.id.login_here);
        emailerror =(TextView)findViewById(R.id.emailerror);
        passworderror =(TextView)findViewById(R.id.passworderror);
        confirm_pass_error =(TextView)findViewById(R.id.confirm_pass_error);
        login_here.setOnClickListener(this);
        btn_signup.setOnClickListener(this);
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
                if ((!(android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() || Patterns.PHONE.matcher(email.getText().toString()).matches()) && email.length() > 0)){
                    emailerror.setVisibility(View.VISIBLE);
                    email.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_stroke));
                } else {
                    emailerror.setVisibility(View.GONE);
                    email.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.edittextbackground));

                }


                if (password.length() < 6 && password.length() > 0) {
                    passworderror.setVisibility(View.VISIBLE);
                    password.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_stroke));


                } else {

                    passworderror.setVisibility(View.GONE);
                    password.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.edittextbackground));


                }
                if (confirm_password.length() < 6 && confirm_password.length() > 0) {
                    confirm_pass_error.setVisibility(View.VISIBLE);
                    confirm_password.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.error_stroke));


                } else {
                    confirm_password.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.edittextbackground));
                    confirm_pass_error.setVisibility(View.GONE);
                }

            }
        };

        email.addTextChangedListener(tw);
        password.addTextChangedListener(tw);
        confirm_password.addTextChangedListener(tw);
    }
    private boolean valid() {

        if (password.length() < 6 || confirm_password.length() < 6) {
            passworderror.setVisibility(View.VISIBLE);
            confirm_pass_error.setVisibility(View.VISIBLE);
            password.requestFocus();
            return false;
        } else {
            passworderror.setVisibility(View.GONE);
            confirm_pass_error.setVisibility(View.GONE);

            return true;
        }



    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.login_here:
                i = new Intent(SignUp.this,LoginActivity.class);
                startActivity(i);


                break;
            case R.id.btn_signup:


              if(valid()) {
                  mpassword = password.getText().toString();
                  mconfirm_password = confirm_password.getText().toString();
                  if (mconfirm_password.equals(mpassword)) {
                      callLoader(0);


                  } else {
                      Utils.showToast(this, "Password Didn't match, Try again!");
                  }

              }
                break;
        }
    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {

        loading = ProgressDialog.show(this, "Signing Up.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        switch (id) {

            case 0:
                HashMap<String, String> input = new HashMap<>();
                input.put("username", email.getText().toString());
                input.put("password", password.getText().toString());
                result = new SignUpLoader(this, input);

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
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    switch (id) {
                        case 0:
                            JSONObject jsonObject = new JSONObject(data.get("Data"));
                            if(jsonObject.getString("status").equals("success")) {
                                Utils.showToast(this,jsonObject.getString("message"));
//                                i = new Intent(SignUp.this, SignUpDetailsActivity.class);
//                                startActivity(i);
                            }else
                            {
                                Utils.showToast(this,jsonObject.getString("message"));
//                                i = new Intent(SignUp.this, LoginActivity.class);
//                                startActivity(i);
                            }
                            break;


                    }
                    break;
                case 203:
                    JSONObject jsonObject = new JSONObject(data.get("Data"));
                    if(jsonObject.getString("status").equals("success")) {
                        Utils.showToast(this,jsonObject.getString("message"));
//                        i = new Intent(SignUp.this, SignUpDetailsActivity.class);
//                        startActivity(i);
                    }else
                    {
                        Utils.showToast(this,jsonObject.getString("message"));
//                        i = new Intent(SignUp.this, SignUpDetailsActivity.class);
//                        startActivity(i);
                    }

                    break;
                case 400:
                    //Utils.showToast(this, data.get("message"));

                    i = new Intent(SignUp.this, SignUpDetailsActivity.class);
                    startActivity(i);
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
