package in.psgroup.psgroup;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.ReferDetailLoader;

public class RefreeDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ic_back;
    private EditText tv_name,tv_mobNo,tv_email,et_dob,et_fname,et_lname,et_street,et_city,et_state,et_country,et_postal,tv_altmobNo;
    private Button btn_proceed;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView phnError,emailError,nameError,lnameError,dobError;
    private String title,project_id,access_token,customerCode,fName,lname,mobile,emailAddress,Street,City,State,Country,postalCode,
            DOB,alternateMobile,message,email,mobile_no;
    private ProgressDialog loading;
    private UserSessionManager session;
    PsGroupApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refree_details);

        myApplication = (PsGroupApplication) this.getApplication();
        session = new UserSessionManager(this);
        HashMap<String, String> user = session.getUserDetails();
        access_token = user.get(UserSessionManager.KEY_accessToken);
        customerCode= user.get(UserSessionManager.KEY_loyaltycode);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            title =extras.getString("projectName");
            project_id =extras.getString("projectid");
        }
        initialize();
        datePickerInit();
        validate();
    }

    private void initialize() {
        ic_back = (ImageView)findViewById(R.id.ic_back);
        tv_name = (EditText)findViewById(R.id.tv_name);
        tv_mobNo = (EditText)findViewById(R.id.tv_mobNo);
        tv_email = (EditText)findViewById(R.id.tv_email);
        et_dob = (EditText)findViewById(R.id.et_dob);
        btn_proceed = (Button)findViewById(R.id.btn_proceed);
        et_fname = (EditText)findViewById(R.id.et_fname);
        et_lname = (EditText)findViewById(R.id.et_lname);
        et_street = (EditText)findViewById(R.id.et_street);
        et_city = (EditText)findViewById(R.id.et_city);
        et_state = (EditText)findViewById(R.id.et_state);
        et_country = (EditText)findViewById(R.id.et_country);
        et_postal = (EditText)findViewById(R.id.et_postal);
        tv_altmobNo = (EditText)findViewById(R.id.tv_altmobNo);
        phnError = (TextView)findViewById(R.id.phnError);
        emailError = (TextView)findViewById(R.id.emailError);
        nameError = (TextView)findViewById(R.id.nameError);
        lnameError = (TextView)findViewById(R.id.lnameError);
        dobError = (TextView)findViewById(R.id.dobError);
        et_dob.setOnClickListener(this);
        ic_back.setOnClickListener(this);
        btn_proceed.setOnClickListener(this);
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
                if (( !Patterns.PHONE.matcher(tv_mobNo.getText().toString()).matches()) &&( tv_mobNo.length() > 0)){
                    phnError.setVisibility(View.VISIBLE);
                }
                else {
                    phnError.setVisibility(View.GONE);
                }

                if(!tv_email.isFocused()&&!tv_email.getText().toString().isEmpty()) {
                    if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(tv_email.getText().toString()).matches() || Patterns.PHONE.matcher(tv_email.getText().toString()).matches()) && tv_email.length() > 0) {
                        emailError.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    emailError.setVisibility(View.GONE);
                }

                if ( et_fname.length() > 0){
                    nameError.setVisibility(View.GONE);
                }

                if ( et_lname.length() > 0){
                    lnameError.setVisibility(View.GONE);
                }

                if ( et_dob.length() > 0){
                    dobError.setVisibility(View.GONE);
                }
            }
        };
        tv_mobNo.addTextChangedListener(tw);
        tv_email.addTextChangedListener(tw);
        et_fname.addTextChangedListener(tw);
        et_lname.addTextChangedListener(tw);
        et_dob.addTextChangedListener(tw);

    }

    private boolean valid() {
        this.mobile_no = tv_mobNo.getText().toString();
        this.email = tv_email.getText().toString();
        if (et_fname.length() < 2) {
            nameError.setVisibility(View.VISIBLE);
            et_fname.requestFocus();

            return false;
        }if (et_lname.length() < 2) {
            lnameError.setVisibility(View.VISIBLE);
            et_lname.requestFocus();

            return false;
        }
        if (!(Patterns.PHONE.matcher(tv_mobNo.getText().toString()).matches())) {
            tv_mobNo.requestFocus();
            phnError.setVisibility(View.VISIBLE);
            return false;
        }
        if (tv_email.length() < 10) {
//            btn_getotp.setEnabled(true);
            emailError.setVisibility(View.VISIBLE);
            tv_email.requestFocus();

            return false;
        }
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(tv_email.getText().toString()).matches()) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(tv_email.getText().toString()).matches() && tv_email.length() > 0) {
                emailError.setVisibility(View.VISIBLE);
                tv_email.requestFocus();

                return false;
            }
        }if (et_dob.length() < 2) {
            dobError.setVisibility(View.VISIBLE);
            et_dob.requestFocus();

            return false;
        }else {
            emailError.setVisibility(View.GONE);
            phnError.setVisibility(View.GONE);
            nameError.setVisibility(View.GONE);
            lnameError.setVisibility(View.GONE);
            dobError.setVisibility(View.GONE);

            return true;
        }
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.ic_back:
                this.onBackPressed();
                break;
            case R.id.btn_proceed:
                if(valid()){
                i = new Intent(RefreeDetailsActivity.this,TermsAndConditionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CustomerCode", customerCode);
                bundle.putString("fName", et_fname.getText().toString());
                bundle.putString("lname", et_lname.getText().toString());
                bundle.putString("mobile", tv_mobNo.getText().toString());
                bundle.putString("emailAddress", tv_email.getText().toString());
                bundle.putString("Street", et_street.getText().toString());
                bundle.putString("City", et_city.getText().toString());
                bundle.putString("State", et_state.getText().toString());
                bundle.putString("Country", et_country.getText().toString());
                bundle.putString("postalCode", et_postal.getText().toString());
                bundle.putString("ProjectName", title);
                bundle.putString("alternateMobile", tv_altmobNo.getText().toString());
                bundle.putString("DOB", et_dob.getText().toString());
                bundle.putString("project_id", project_id);
                i.putExtras(bundle);
                startActivity(i);
                }
                break;

            case R.id.et_dob:
                fromDatePickerDialog.show();
                break;
        }
    }


    private void datePickerInit(){
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                view.setMaxDate(System.currentTimeMillis() - 1000);
                newDate.set(year, monthOfYear, dayOfMonth);

                    et_dob.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
    }

}
