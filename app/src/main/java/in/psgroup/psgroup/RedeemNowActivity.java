package in.psgroup.psgroup;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import in.psgroup.psgroup.Models.ProfileBean;
import in.psgroup.psgroup.Models.RedeemNowBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.RedeemNowLoader;
import in.psgroup.psgroup.Network.RedeemPointsLoader;
import in.psgroup.psgroup.Utility.Utils;

public class RedeemNowActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    private ImageView iv_back;
    private TextView tv_pointsEarned,tv_points,accNoError,confirmAccNoError,tv_amtError,tv_pointError,tv_paymentError;
    private EditText et_bankName,et_accHolder,et_ifscCode,et_accRedeem,et_accNo,et_confirmAccNo,et_useraccNo;
    private Button btn_submit;
    private String payment_type[] = {"Select","EnCash","NTTTF"},Points, maccNo,mconfirmAccNo,autoFetch;
    private ArrayAdapter<String> spinnerpaymentArrayAdapter;
    private Spinner paymentspinner;
    private LinearLayout layout_accountno;
    PsGroupApplication myApplication;
    private UserSessionManager session;
    ProgressDialog loading;
    private ArrayList<RedeemNowBean> pointsList = new ArrayList<>();
    private String customerCode,access_token;
    private int id;
    private Double redeemPoints,userPoints;
    private int response_code = 0;
    ArrayList<RedeemNowBean> redeemNowBeanArrayList = new ArrayList<>();
    RedeemNowBean redeemNowBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_now);
        myApplication = (PsGroupApplication) this.getApplication();
        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        customerCode= user.get(UserSessionManager.KEY_loyaltycode);
        access_token=user.get(UserSessionManager.KEY_accessToken);
        if (getIntent().getExtras()!=null){
            Points =getIntent().getExtras().getString("points");
        }
        initialize();
        callLoader(0);
        validateAccNo();
    }

    private boolean validateAmount() {
        redeemPoints = Double.parseDouble(Points);
        if(et_accRedeem.getText().toString().isEmpty()||et_accRedeem.getText().toString()==null) {
            userPoints =0.0;

        }else{
            userPoints = Double.parseDouble(et_accRedeem.getText().toString());
        }

        if (userPoints>redeemPoints || userPoints==0) {
            tv_amtError.setVisibility(View.VISIBLE);
            et_accRedeem.requestFocus();
            return false;
        } else {
            tv_amtError.setVisibility(View.GONE);
            et_accRedeem.requestFocus();
            return true;

        }
    }

    private boolean validatePayment(){
        View selectedView = paymentspinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            TextView selectedTextView = (TextView) selectedView;
            if (selectedTextView.getText().equals("")) {
                tv_paymentError.setVisibility(View.VISIBLE);
                return false;
            }
            else {
                return true;
            }
        }else {
            tv_paymentError.setVisibility(View.GONE);
            return true;
        }

    }

    private void validateAccNo() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if ( et_accNo.length() > 0){
                    accNoError.setVisibility(View.GONE);
                }

                if ( et_confirmAccNo.length() > 0) {
                    confirmAccNoError.setVisibility(View.GONE);
                }

                if ( et_accRedeem.length() > 0){
                    tv_amtError.setVisibility(View.GONE);
                }

            }
        };
        et_accNo.addTextChangedListener(textWatcher);
        et_confirmAccNo.addTextChangedListener(textWatcher);
        et_accRedeem.addTextChangedListener(textWatcher);

    }

    private boolean valid() {
        if (et_accNo.length() < 6 || et_confirmAccNo.length() < 6) {
            accNoError.setVisibility(View.VISIBLE);
            confirmAccNoError.setVisibility(View.VISIBLE);
            et_accNo.requestFocus();
            return false;
        } else {
            accNoError.setVisibility(View.GONE);
            confirmAccNoError.setVisibility(View.GONE);

            return true;
        }

    }

    private void initialize() {
        layout_accountno=(LinearLayout)findViewById(R.id.layout_accountno);
        paymentspinner=(Spinner)findViewById(R.id.payment_type);
        iv_back = (ImageView)findViewById(R.id.iv_back);
        tv_pointsEarned = (TextView)findViewById(R.id.tv_pointsEarned);
        tv_points = (TextView)findViewById(R.id.tv_points);
        et_bankName = (EditText)findViewById(R.id.et_bankName);
        et_accHolder = (EditText)findViewById(R.id.et_accHolder);
        et_ifscCode = (EditText)findViewById(R.id.et_ifscCode);
        et_accRedeem = (EditText)findViewById(R.id.et_accRedeem);
        et_accNo = (EditText)findViewById(R.id.et_accNo);
        et_confirmAccNo = (EditText)findViewById(R.id.et_confirmAccNo);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        accNoError = (TextView)findViewById(R.id.accNoError);
        tv_amtError = (TextView)findViewById(R.id.tv_amtError);
        confirmAccNoError = (TextView)findViewById(R.id.confirmAccNoError);
        tv_pointError = (TextView)findViewById(R.id.tv_pointError);
        tv_paymentError = (TextView)findViewById(R.id.tv_paymentError);
        tv_points.setText(Points+" pts");
        iv_back.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        paymentAdapter();
    }

    private void paymentAdapter() {
        spinnerpaymentArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.layout_spinner, payment_type);
        spinnerpaymentArrayAdapter .setDropDownViewResource(R.layout.layout_spinner);
        paymentspinner.setAdapter(spinnerpaymentArrayAdapter);
    }

    private void showSuccessAlert(String title_message, String description) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_redeem_now, viewGroup, false);
        Button btn = (Button) dialogView.findViewById(R.id.btn_done);
        TextView tv_title_message = (TextView) dialogView.findViewById(R.id.title_message);
        TextView tv_description = (TextView) dialogView.findViewById(R.id.tv_success_text);
        tv_title_message.setText(title_message);
        tv_description.setText(description);
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
                RedeemNowActivity.this.finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                this.onBackPressed();
                break;
            case R.id.btn_submit:
                if(validateAmount() && validatePayment()) {
                    if (redeemNowBean.getAutofetch().equals(true)) {
                        callLoader(1);
                    } else {
                        if (valid()) {
                            maccNo = et_accNo.getText().toString();
                            mconfirmAccNo = et_confirmAccNo.getText().toString();
                            if (mconfirmAccNo.equals(maccNo)) {
                                callLoader(1);
                            } else {
                                //  confirmAccNoError.setVisibility(View.VISIBLE);
                            }

                        }
                    }
                }
                break;
        }
    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        this.id =id;
        Loader<HashMap<String, String>> result = null;
        if(id==0) {
            loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
            HashMap<String, String> input = new HashMap<>();
            result = new RedeemNowLoader(this, access_token);
        }else if(id==1){
            HashMap<String,String> input = new HashMap<>();
            input.put("CustomerCode",customerCode);
            input.put("autofetch",autoFetch);
            input.put("BankName",et_bankName.getText().toString());
            input.put("AccountHolderName",et_accHolder.getText().toString());
            input.put("IFSCCode",et_ifscCode.getText().toString());
            input.put("AccNo",et_accNo.getText().toString());
            input.put("RedemptionPoints",et_accRedeem.getText().toString());
            input.put("TypeOfRedemption",paymentspinner.getSelectedItem().toString());
            result = new RedeemPointsLoader(this, access_token,input);
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

    private void setData(HashMap<String, String> data, int id)throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        Gson gson = new Gson();
        Intent i;
        if (response_code != 0) {
            switch (response_code) {
                case 200://on success
                    switch (id) {
                        case 0:
                            if (data.get("data") != null) {
                                JSONObject jsonarray = new JSONObject(data.get("data"));
                                JSONObject datajson = jsonarray.getJSONObject("data");
                                for (int k = 0; k < datajson.length(); k++) {
                                    redeemNowBeanArrayList.add(gson.fromJson(datajson.toString(), RedeemNowBean.class));
                                }
                                setpoints(redeemNowBeanArrayList);
                            }
                            break;
                        case 1:
                            showSuccessAlert("Requested", getResources().getString(R.string.redeem_requested));
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

    private void setpoints(ArrayList<RedeemNowBean> redeemNowBeanArrayList) {
        redeemNowBean =redeemNowBeanArrayList.get(0);
        autoFetch = redeemNowBean.getAutofetch();
        if(redeemNowBean.getAutofetch().equals("true"))
        {
            et_accNo.setInputType(InputType.TYPE_CLASS_TEXT);
            et_bankName.setText(redeemNowBean.getBankName());
            et_accHolder.setText(redeemNowBean.getAccountHolderName());
            et_ifscCode.setText(redeemNowBean.getIfscCode());
            et_accNo.setText(redeemNowBean.getAccountNo());
            et_confirmAccNo.setText(redeemNowBean.getAccountNo());
           /* String accountnumber=redeemNowBean.getAccountNo();
            et_accNo.setText(maskNumber(accountnumber,"XXXXXXX####"));*/


        }else if(redeemNowBean.getAutofetch().equals("false")){
            et_bankName.setEnabled(true);
            et_accNo.setEnabled(true);
            et_ifscCode.setEnabled(true);
            et_accHolder.setEnabled(true);
            layout_accountno.setVisibility(View.VISIBLE);
        }


    }



    /*private static String maskNumber(String number, String mask) {
        int index = 7;
        StringBuilder masked = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                masked.append(number.charAt(index));
                index++;
            } else if (c == 'x') {
                masked.append(c);
                index++;
            } else {
                masked.append(c);
            }
        }
        return masked.toString();
    }*/



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
}
