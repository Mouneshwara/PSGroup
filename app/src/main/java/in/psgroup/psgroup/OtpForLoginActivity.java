package in.psgroup.psgroup;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

import in.psgroup.psgroup.Models.LoginBean;
import in.psgroup.psgroup.Models.OtpBean;
import in.psgroup.psgroup.Models.TotalPointsBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Models.VideoBean;
import in.psgroup.psgroup.Network.HelpLoader;
import in.psgroup.psgroup.Network.OtpLoader;
import in.psgroup.psgroup.Network.TotalPointsLoader;
import in.psgroup.psgroup.Utility.Utils;

import static in.psgroup.psgroup.Utility.Utils.showToast;

public class OtpForLoginActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    EditText otp1,otp2,otp3,otp4;
    TextView resendotp,didnt_gtotp,incorrect_otp;
    Button continueButton;
    LinearLayout lLayout;
    String userotp,responseOtp="";
    private LoginBean loginBean;
    ProgressDialog loading;
    private int id;
    PsGroupApplication myApplication;
    private String customerCode,access_token,otp;
    private int response_code = 0;
    private UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_for_login);
        sessionManager = new UserSessionManager(getApplicationContext());
        myApplication = (PsGroupApplication) this.getApplication();
        initialize();
        otpvalidate();
        try {
            loginBean = (LoginBean) getIntent().getExtras().getSerializable(Utils.KEY_DATA);
        }catch (NullPointerException e){
            loginBean = null;
        }
    }

    private void initialize() {
        otp1 = (EditText)findViewById(R.id.otp1);
        otp2 = (EditText)findViewById(R.id.otp2);
        otp3 = (EditText)findViewById(R.id.otp3);
        otp4 = (EditText)findViewById(R.id.otp4);
        resendotp = (TextView)findViewById(R.id.resendotp);
        continueButton = (Button)findViewById(R.id.continueButton);
        didnt_gtotp = (TextView)findViewById(R.id.didnt_gtotp);
        lLayout = (LinearLayout)findViewById(R.id.lLayout);
        incorrect_otp = (TextView)findViewById(R.id.incorrect_otp);
        continueButton.setOnClickListener(this);
    }

    public void otpvalidate() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    otp1.clearFocus();
                    otp2.requestFocus();
                    otp2.setCursorVisible(true);
                    incorrect_otp.setVisibility(View.GONE);
                } else {
                    otp1.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /* otp1.setEnabled(false);*/
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    otp2.clearFocus();
                    otp3.requestFocus();
                    otp3.setCursorVisible(true);
                    incorrect_otp.setVisibility(View.GONE);
                } else {
                    otp2.clearFocus();
                    otp1.setEnabled(true);
                    otp1.requestFocus();
                    otp1.setCursorVisible(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    //check if the right key was pressed
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (otp2.length() == 0) {
                            otp2.clearFocus();
                            otp1.setEnabled(true);
                            otp1.requestFocus();
                            otp1.setCursorVisible(true);
                        } else {
                            otp2.setText("");
                        }

                        return true;
                    }
                }
                return false;
            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /*otp2.setEnabled(false);*/
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    otp3.clearFocus();
                    otp4.requestFocus();
                    otp4.setCursorVisible(true);
                    incorrect_otp.setVisibility(View.GONE);
                } else {
                    otp3.clearFocus();
                    otp2.setEnabled(true);
                    otp2.requestFocus();
                    otp2.setCursorVisible(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    //check if the right key was pressed
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (otp3.length() == 0) {
                            otp3.clearFocus();
                            otp2.setEnabled(true);
                            otp2.requestFocus();
                            otp2.setCursorVisible(true);
                            incorrect_otp.setVisibility(View.GONE);
                        } else {
                            otp3.setText("");
                        }

                        return true;
                    }
                }
                return false;
            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /*otp3.setEnabled(false);*/
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    otp3.clearFocus();
                    otp4.requestFocus();
                    incorrect_otp.setVisibility(View.GONE);

                } else {
                    otp4.clearFocus();
                    otp3.setEnabled(true);
                    otp3.requestFocus();
                    otp3.setCursorVisible(true);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        otp4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    //check if the right key was pressed
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (otp4.length() == 0) {
                            otp4.clearFocus();
                            otp3.setEnabled(true);
                            otp3.requestFocus();
                            otp3.setCursorVisible(true);
                        } else {
                            otp4.setText("");
                        }

                        return true;
                    }
                }
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.continueButton:
                userotp = new String().concat(otp1.getText().toString().trim()).concat(otp2.getText().toString().trim().concat(otp3.getText().toString().trim().concat(otp4.getText().toString().trim())));
                if(loginBean!=null && !TextUtils.isEmpty(loginBean.getOTP())) {

                    if (userotp.equals(loginBean.getOTP())) {
                        callLoader(0);
                    } else {
                        incorrect_otp.setVisibility(View.VISIBLE);

                    }

                }
                break;
        }
    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        result = new OtpLoader(this,loginBean.getAccessToken(),userotp);
        Log.e("access_token",loginBean.getAccessToken());
        Log.e("otp",userotp);
        return result;
    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        getSupportLoaderManager().destroyLoader(loader.getId());
        if (data != null && !data.isEmpty()) {
            try {
                setData(data, id);
                getLoaderManager().destroyLoader(id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            showToast(this, "something went wrong");
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
        Gson gson = new Gson();
        Intent i;
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    JSONObject jsonObject = new JSONObject(data.get("data"));
                    JSONObject dataobject = new JSONObject(jsonObject.getString("data"));
                   /* Type type = new TypeToken<LoginBean>() {
                    }.getType();
                    OtpBean otpBean = gson.fromJson(data.get("data"),type);*/
                    sessionManager.createUserLoginSession( loginBean.getAccessToken(),dataobject.getString("fname"),dataobject.getString("email"),
                            dataobject.getString("mobile"),dataobject.getString("image"),dataobject.getString("loyaltycode"),dataobject.getString("customertype"));
                    i = new Intent(OtpForLoginActivity.this, HomeActivity.class);
                    startActivity(i);
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
