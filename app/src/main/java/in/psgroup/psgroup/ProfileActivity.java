package in.psgroup.psgroup;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import in.psgroup.psgroup.Models.ProfileBean;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.EditPicLoader;
import in.psgroup.psgroup.Network.EditProfileLoader;
import in.psgroup.psgroup.Network.ProfileLoader;
import in.psgroup.psgroup.Utility.Utils;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {

    private ImageView backPress, iv_editProfile,iv_profile;
    private TextView  tv_fullName, tv_edit, tv_identityProof, tv_residentialStatus, tv_localContact, tv_uploadForm,tv_initial,paaPortError,nameError
            ,mobNoError,addressError,dobError,dobAnvsryError;
    private EditText fathers_name, et_address, et_city,et_editAdhaarCard,et_editTextDl,et_editRationCard, et_pin,et_editPassport, et_state,et_editText, et_district, et_country, et_policeStation, et_postOffice, et_pan_no, et_adhaarNo, et_gst, et_mobNo, et_phoneNum1, et_phoneNum2, et_email, et_dob, et_dateOfAnniversary, et_passportNo, et_holdersName, et_name, et_mob_No, et_Address, et_correspondenceAddress, et_City2, et_Pin2, et_State2, et_District2, et_Country2;
    private UserSessionManager session;
    private CircleImageView profile;
    private CheckBox cb_service, cb_professional, cb_business, cb_housewife, cb_others, service, cb_votersId, cb_passport, cb_drivingLicense, cb_rationCard, cb_adhaarCard, cb_indian, cb_nri;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Integer dateid;
    private HashMap<String, String> user;
    private String userChoosenTask,c_ResidentialStatus,c_AddressProofDocumentNumber,c_Occupation,c_AddressProofDocumentName;
    private LinearLayout nri_layout,lNri;
    private TextInputLayout fathers_input;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1, MY_PERMISSIONS = 1;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private ProgressDialog loading;
    private String accesss_token;
    private int response_code = 0;
    ProfileBean profileBean;
    private int id;
    ArrayList<ProfileBean> profileBeans = new ArrayList<>();
    PsGroupApplication myApplication;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        session = new UserSessionManager(getApplicationContext());
        accesss_token = session.getUserDetails().get(UserSessionManager.KEY_accessToken);
        myApplication = (PsGroupApplication) this.getApplication();
        initView();
        datePickerInit();
        callloader(0);
        validation();

    }

    private void validation() {
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if ( et_passportNo.length() > 0){
                    paaPortError.setVisibility(View.GONE);
                }

                if ( et_name.length() >0){
                    nameError.setVisibility(View.GONE);
                }

                if ( et_mob_No.length() > 0){
                    mobNoError.setVisibility(View.GONE);
                }

                if ( et_Address.length() >0){
                    addressError.setVisibility(View.GONE);
                }

                if(et_dob.length()>0){
                    dobError.setVisibility(View.GONE);
                }

                if(et_dateOfAnniversary.length()>0){
                    dobAnvsryError.setVisibility(View.GONE);
                }

            }
        };
        et_passportNo.addTextChangedListener(tw);
        et_name.addTextChangedListener(tw);
        et_mob_No.addTextChangedListener(tw);
        et_Address.addTextChangedListener(tw);
        et_dob.addTextChangedListener(tw);
        et_dateOfAnniversary.addTextChangedListener(tw);
    }

    private void callloader(int id) {
        if (myApplication.checkNetwork()) {
            getLoaderManager().destroyLoader(id);
            getLoaderManager().initLoader(id, null, this).forceLoad();
        } else {
            Utils.showNoInternrt(this);
        }
    }

    private void initView() {
        tv_initial =  findViewById(R.id.tv_initial);
        tv_fullName = (TextView) findViewById(R.id.tv_fullName);
        backPress = (ImageView) findViewById(R.id.ic_back);
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        profile = (CircleImageView) findViewById(R.id.profile);
        profile.setVisibility(View.VISIBLE);
        iv_editProfile = (ImageView) findViewById(R.id.iv_editProfile);
        fathers_name = (EditText) findViewById(R.id.fathers_name);
        et_address = (EditText) findViewById(R.id.et_address);
        et_address = (EditText) findViewById(R.id.et_address);
        et_city = (EditText) findViewById(R.id.et_city);
        et_pin = (EditText) findViewById(R.id.et_pin);
        et_state = (EditText) findViewById(R.id.et_state);
        et_district = (EditText) findViewById(R.id.et_district);
        et_country = (EditText) findViewById(R.id.et_country);
        et_policeStation = (EditText) findViewById(R.id.et_policeStation);
        et_postOffice = (EditText) findViewById(R.id.et_postOffice);
        et_pan_no = (EditText) findViewById(R.id.et_pan_no);
        et_adhaarNo = (EditText) findViewById(R.id.et_adhaarNo);
        et_gst = (EditText) findViewById(R.id.et_gst);
        et_mobNo = (EditText) findViewById(R.id.et_mobNo);
        et_phoneNum1 = (EditText) findViewById(R.id.et_phoneNum1);
        et_phoneNum2 = (EditText) findViewById(R.id.et_phoneNum2);
        et_email = (EditText) findViewById(R.id.et_email);
        et_dob = (EditText) findViewById(R.id.et_dob);
        et_dateOfAnniversary = (EditText) findViewById(R.id.et_dateOfAnniversary);
        cb_service = (CheckBox) findViewById(R.id.cb_service);
        cb_professional = (CheckBox) findViewById(R.id.cb_professional);
        cb_business = (CheckBox) findViewById(R.id.cb_business);
        cb_housewife = (CheckBox) findViewById(R.id.cb_housewife);
        cb_others = (CheckBox) findViewById(R.id.cb_others);
        tv_identityProof = (TextView) findViewById(R.id.tv_identityProof);
        service = (CheckBox) findViewById(R.id.service);
        cb_votersId = (CheckBox) findViewById(R.id.cb_votersId);
        cb_passport = (CheckBox) findViewById(R.id.cb_passport);
        cb_drivingLicense = (CheckBox) findViewById(R.id.cb_drivingLicense);
        cb_rationCard = (CheckBox) findViewById(R.id.cb_rationCard);
        cb_adhaarCard = (CheckBox) findViewById(R.id.cb_adhaarCard);
        tv_residentialStatus = (TextView) findViewById(R.id.tv_residentialStatus);
        cb_indian = (CheckBox) findViewById(R.id.cb_indian);
        cb_nri = (CheckBox) findViewById(R.id.cb_nri);
        et_passportNo = (EditText) findViewById(R.id.et_passportNo);
        et_holdersName = (EditText) findViewById(R.id.et_holdersName);
        tv_localContact = (TextView) findViewById(R.id.tv_localContact);
        et_name = (EditText) findViewById(R.id.et_name);
        et_mob_No = (EditText) findViewById(R.id.et_mob_No);
        et_Address = (EditText) findViewById(R.id.et_Address);
        et_correspondenceAddress = (EditText) findViewById(R.id.et_correspondenceAddress);
        et_City2 = (EditText) findViewById(R.id.et_City2);
        et_Pin2 = (EditText) findViewById(R.id.et_Pin2);
        et_State2 = (EditText) findViewById(R.id.et_State2);
        et_District2 = (EditText) findViewById(R.id.et_District2);
        et_Country2 = (EditText) findViewById(R.id.et_Country2);
        tv_uploadForm = (TextView) findViewById(R.id.tv_uploadForm);
        nri_layout = (LinearLayout) findViewById(R.id.nri_layout);
        fathers_input = (TextInputLayout) findViewById(R.id.fathers_input);
        et_editText = (EditText)findViewById(R.id.et_editText);
        et_editPassport = (EditText)findViewById(R.id.et_editPassport);
        et_editTextDl = (EditText)findViewById(R.id.et_editTextDl);
        et_editRationCard = (EditText)findViewById(R.id.et_editRationCard);
        et_editAdhaarCard = (EditText)findViewById(R.id.et_editAdhaarCard);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        lNri = (LinearLayout)findViewById(R.id.lNri);
        iv_profile = (ImageView)findViewById(R.id.profile);
        paaPortError = (TextView)findViewById(R.id.paaPortError);
        nameError = (TextView)findViewById(R.id.nameError);
        mobNoError = (TextView)findViewById(R.id.mobNoError);
        addressError = (TextView)findViewById(R.id.addressError);
        dobError = (TextView)findViewById(R.id.dobError);
        dobAnvsryError = (TextView)findViewById(R.id.dobAnvsryError);
        tv_edit.setText("EDIT");
        tv_edit.setOnClickListener(this);
        cb_service.setOnCheckedChangeListener(this);
        cb_professional.setOnCheckedChangeListener(this);
        cb_business.setOnCheckedChangeListener(this);
        cb_housewife.setOnCheckedChangeListener(this);
        cb_others.setOnCheckedChangeListener(this);
        cb_service.setOnClickListener(this);
        cb_professional.setOnClickListener(this);
        cb_business.setOnClickListener(this);
        cb_housewife.setOnClickListener(this);
        cb_others.setOnClickListener(this);
        cb_indian.setOnClickListener(this);
        cb_nri.setOnClickListener(this);
        et_dob.setOnClickListener(this);
        et_dateOfAnniversary.setOnClickListener(this);
        backPress.setOnClickListener(this);

        iv_editProfile.setOnClickListener(this);

        cb_votersId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_editText.setVisibility(View.VISIBLE);
                    et_editText.setHint("Please Enter Voter Id");
                    et_editText.requestFocus();
                    cb_votersId.setChecked(true);
                    cb_passport.setChecked(false);
                    cb_drivingLicense.setChecked(false);
                    cb_rationCard.setChecked(false);
                    cb_adhaarCard.setChecked(false);
                    c_AddressProofDocumentName = "Voter Id";
                    c_AddressProofDocumentNumber = et_editText.getText().toString();
                    et_editText.getText().clear();
                } else {
                    et_editText.setVisibility(View.GONE);
                }
            }
        });

        cb_passport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_editPassport.setVisibility(View.VISIBLE);
                    et_editPassport.setHint("Please Enter Passport number");
                    et_editPassport.requestFocus();
                    cb_votersId.setChecked(false);
                    cb_passport.setChecked(true);
                    cb_drivingLicense.setChecked(false);
                    cb_rationCard.setChecked(false);
                    cb_adhaarCard.setChecked(false);
                    c_AddressProofDocumentName = "Passport";
                    c_AddressProofDocumentNumber = et_editText.getText().toString();
                    et_editPassport.getText().clear();
                } else {
                    et_editPassport.setVisibility(View.GONE);
                }
            }
        });

        cb_drivingLicense.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_editTextDl.setVisibility(View.VISIBLE);
                    et_editTextDl.setHint("Please Enter Driving License number");
                    et_editTextDl.requestFocus();
                    cb_votersId.setChecked(false);
                    cb_passport.setChecked(false);
                    cb_drivingLicense.setChecked(true);
                    cb_rationCard.setChecked(false);
                    cb_adhaarCard.setChecked(false);
                    c_AddressProofDocumentName = "Driving License";
                    c_AddressProofDocumentNumber = et_editText.getText().toString();
                    et_editTextDl.getText().clear();
                } else {
                    et_editTextDl.setVisibility(View.GONE);
                }
            }
        });

        cb_rationCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_editRationCard.setVisibility(View.VISIBLE);
                    et_editRationCard.setHint("Please Enter Ration Card number");
                    et_editRationCard.requestFocus();
                    cb_votersId.setChecked(false);
                    cb_passport.setChecked(false);
                    cb_drivingLicense.setChecked(false);
                    cb_rationCard.setChecked(true);
                    cb_adhaarCard.setChecked(false);
                    c_AddressProofDocumentName = "Ration Card";
                    c_AddressProofDocumentNumber = et_editText.getText().toString();
                    et_editRationCard.getText().clear();
                } else {
                    et_editRationCard.setVisibility(View.GONE);
                }
            }
        });

        cb_adhaarCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_editAdhaarCard.setVisibility(View.VISIBLE);
                    et_editAdhaarCard.setHint("Please Enter Adhaar Number");
                    et_editAdhaarCard.requestFocus();
                    cb_votersId.setChecked(false);
                    cb_passport.setChecked(false);
                    cb_drivingLicense.setChecked(false);
                    cb_rationCard.setChecked(false);
                    cb_adhaarCard.setChecked(true);
                    c_AddressProofDocumentName = "Adhaar Card";
                    c_AddressProofDocumentNumber = et_editText.getText().toString();
                    et_editAdhaarCard.getText().clear();
                } else {
                    et_editAdhaarCard.setVisibility(View.GONE);
                }
            }
        });


        // fathers_input.setHint(Html.fromHtml("Father’s/Husband’s Name" + "<font color=red>" + "*" + "</font>"));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        et_dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dateid=0;
                    fromDatePickerDialog.show();
                }
            }
        });
        et_dateOfAnniversary.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dateid=1;
                    fromDatePickerDialog.show();
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_edit:
                if (tv_edit.getText() .equals("EDIT") ) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                    tv_edit.setText("REQUEST EDIT");
                    fathers_name.setEnabled(true);
                    et_address.setEnabled(true);
                    et_mobNo.setEnabled(true);
                    et_mob_No.setEnabled(true);
                    fathers_name.setEnabled(true);
                    et_address.setEnabled(true);
                    et_city.setEnabled(true);
                    et_pin.setEnabled(true);
                    et_state.setEnabled(true);
                    et_district.setEnabled(true);
                    et_country.setEnabled(true);
                    et_policeStation.setEnabled(true);
                    et_postOffice.setEnabled(true);
                    et_pan_no.setEnabled(true);
                    et_adhaarNo.setEnabled(true);
                    et_gst.setEnabled(true);
                    et_mobNo.setEnabled(true);
                    et_phoneNum1.setEnabled(true);
                    et_phoneNum2.setEnabled(true);
                    et_email.setEnabled(true);
                    et_dob.setEnabled(true);
                    et_dateOfAnniversary.setEnabled(true);
                    et_passportNo.setEnabled(true);
                    et_holdersName.setEnabled(true);
                    et_name.setEnabled(true);
                    et_mob_No.setEnabled(true);
                    et_Address.setEnabled(true);
                    et_correspondenceAddress.setEnabled(true);
                    et_City2.setEnabled(true);
                    et_Pin2.setEnabled(true);
                    et_State2.setEnabled(true);
                    et_District2.setEnabled(true);
                    et_Country2.setEnabled(true);
                    et_editText.setEnabled(true);
                    et_editAdhaarCard.setEnabled(true);
                    et_editPassport.setEnabled(true);
                    et_editTextDl.setEnabled(true);
                    et_editRationCard.setEnabled(true);

                    cb_service.setEnabled(true);
                    cb_professional.setEnabled(true);
                    cb_business.setEnabled(true);
                    cb_housewife.setEnabled(true);
                    cb_others.setEnabled(true);
                    service.setEnabled(true);
                    cb_votersId.setEnabled(true);
                    cb_passport.setEnabled(true);
                    cb_drivingLicense.setEnabled(true);
                    cb_rationCard.setEnabled(true);
                    cb_adhaarCard.setEnabled(true);
                    cb_indian.setEnabled(true);
                    cb_nri.setEnabled(true);


                    //tv_edit.setText("EDIT");
                    fathers_name.setEnabled(true);
                    et_address.setEnabled(true);
                    et_city.setEnabled(true);
                    et_pin.setEnabled(true);
                    et_state.setEnabled(true);
                    et_district.setEnabled(true);
                    et_country.setEnabled(true);
                    et_policeStation.setEnabled(true);
                    et_postOffice.setEnabled(true);
                    et_pan_no.setEnabled(true);
                    et_adhaarNo.setEnabled(true);
                    et_gst.setEnabled(true);
                    et_mobNo.setEnabled(true);
                    et_phoneNum1.setEnabled(true);
                    et_phoneNum2.setEnabled(true);
                    et_email.setEnabled(true);
                    et_dob.setEnabled(true);
                    et_dateOfAnniversary.setEnabled(true);
                    et_passportNo.setEnabled(true);
                    et_holdersName.setEnabled(true);
                    et_name.setEnabled(true);
                    et_mob_No.setEnabled(true);
                    et_Address.setEnabled(true);
                    et_correspondenceAddress.setEnabled(true);
                    et_City2.setEnabled(true);
                    et_Pin2.setEnabled(true);
                    et_State2.setEnabled(true);
                    et_District2.setEnabled(true);
                    et_Country2.setEnabled(true);
                    cb_service.setEnabled(true);
                    cb_professional.setEnabled(true);
                    cb_business.setEnabled(true);
                    cb_housewife.setEnabled(true);
                    cb_others.setEnabled(true);
                    service.setEnabled(true);
                    cb_votersId.setEnabled(true);
                    cb_passport.setEnabled(true);
                    cb_drivingLicense.setEnabled(true);
                    cb_rationCard.setEnabled(true);
                    cb_adhaarCard.setEnabled(true);
                    cb_indian.setEnabled(true);
                    cb_nri.setEnabled(true);


                  /*  et_address.setBackgroundResource(android.R.drawable.edit_text);
                    et_mobNo.setBackgroundResource(android.R.drawable.edit_text);
                    et_phoneNum1.setBackgroundResource(android.R.drawable.edit_text);
                    et_phoneNum2.setBackgroundResource(android.R.drawable.edit_text);*/

                } else {

                    if(passPortvalidation()) {
                        tv_edit.setText("EDIT");
                        callloader(1);
                    }


                }

                break;
            case R.id.ic_back:
                this.onBackPressed();
                break;

            case R.id.cb_service:
                cb_service.setChecked(true);
                cb_professional.setChecked(false);
                cb_business.setChecked(false);
                cb_housewife.setChecked(false);
                cb_others.setChecked(false);
                c_Occupation = cb_service.getText().toString();
                break;

            case R.id.cb_professional:
                cb_professional.setChecked(true);
                cb_service.setChecked(false);
                cb_business.setChecked(false);
                cb_housewife.setChecked(false);
                cb_others.setChecked(false);
                c_Occupation = cb_professional.getText().toString();
                break;

            case R.id.cb_business:
                cb_business.setChecked(true);
                cb_service.setChecked(false);
                cb_professional.setChecked(false);
                cb_housewife.setChecked(false);
                cb_others.setChecked(false);
                c_Occupation = cb_business.getText().toString();
                break;

            case R.id.cb_housewife:
                cb_housewife.setChecked(true);
                cb_service.setChecked(false);
                cb_professional.setChecked(false);
                cb_business.setChecked(false);
                cb_others.setChecked(false);
                c_Occupation = cb_housewife.getText().toString();
                break;

            case R.id.cb_others:
                cb_others.setChecked(true);
                cb_service.setChecked(false);
                cb_professional.setChecked(false);
                cb_business.setChecked(false);
                cb_housewife.setChecked(false);
                c_Occupation = cb_others.getText().toString();
                break;

            case R.id.cb_indian:
                cb_indian.setChecked(true);
                cb_nri.setChecked(false);
                lNri.setVisibility(View.GONE);
                c_ResidentialStatus = cb_indian.getText().toString();
                //nri_layout.setVisibility(View.GONE);
                break;

            case R.id.cb_nri:
                cb_nri.setChecked(true);
                cb_indian.setChecked(false);
                lNri.setVisibility(View.VISIBLE);
                et_passportNo.requestFocus();
                c_ResidentialStatus = cb_nri.getText().toString();
                // nri_layout.setVisibility(View.VISIBLE);
                break;

            case R.id.et_dob:
                dateid=0;
                fromDatePickerDialog.show();
                break;

            case R.id.et_dateOfAnniversary:
                dateid=1;
                fromDatePickerDialog.show();
                break;
            case R.id.iv_editProfile:
                if (ContextCompat.checkSelfPermission(ProfileActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{android.Manifest.permission.SEND_SMS, android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS);
                }
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS);
                }
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS);
                }
                selectImage(view);

                break;

        }

    }

    private boolean passPortvalidation() {

        if(cb_indian.isChecked()){
            if(et_dob.length()<=4){
                dobError.setVisibility(View.VISIBLE);
                et_dob.requestFocus();
                return false;
            }if(et_dateOfAnniversary.length()<=4){
                dobAnvsryError.setVisibility(View.VISIBLE);
                et_dateOfAnniversary.requestFocus();
                return false;
            }else {
                dobError.setVisibility(View.GONE);
                dobAnvsryError.setVisibility(View.GONE);
                return true;
            }
        }
        if(cb_nri.isChecked()) {
            if (et_passportNo.length() <= 4) {
                paaPortError.setVisibility(View.VISIBLE);
                et_passportNo.requestFocus();
                return false;
            }
            if (et_name.length() <= 4) {
                nameError.setVisibility(View.VISIBLE);
                et_name.requestFocus();
                return false;
            }if (!(Patterns.PHONE.matcher(et_mob_No.getText().toString()).matches())) {
                mobNoError.setVisibility(View.VISIBLE);
                et_mob_No.requestFocus();
                return false;
            }if (et_Address.length() <= 4) {
                addressError.setVisibility(View.VISIBLE);
                et_Address.requestFocus();
                return false;
            } else {
                nameError.setVisibility(View.GONE);
                paaPortError.setVisibility(View.GONE);
                mobNoError.setVisibility(View.GONE);
                addressError.setVisibility(View.GONE);
                return true;
            }

        }else {
            return true;
        }
    }

    private void  showAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_edit_phn_no, null);


        Button done = (Button) view.findViewById(R.id.btn_done);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog.setView(view);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {

            case R.id.cb_votersId:

                break;
            case R.id.cb_passport:


                break;
            case R.id.cb_drivingLicense:


                break;
            case R.id.cb_rationCard:


                break;
            case R.id.cb_adhaarCard:

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
                if(dateid==0) {
                    et_dob.setText(dateFormatter.format(newDate.getTime()));
                }
                else{et_dateOfAnniversary.setText(dateFormatter.format(newDate.getTime()));
                }
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
    }

    private void selectImage(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.edit_pic_card, viewGroup, false);
        TextView chose_pic = (TextView) dialogView.findViewById(R.id.chose_pic);
        TextView take_pic = (TextView) dialogView.findViewById(R.id.take_pic);
        TextView remove_pic = (TextView) dialogView.findViewById(R.id.remove_pic);
        ImageView close =(ImageView)dialogView.findViewById(R.id.close) ;
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
        chose_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryIntent();
                alertDialog.dismiss();

            }
        });
        take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraIntent();
                alertDialog.dismiss();

            }
        });
        remove_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap =null;
                callloader(2);
                alertDialog.dismiss();

            }
        });



    }


    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }

        }
    }
    private void onSelectFromGalleryResult(Intent data) {

        try {
            final Uri imageUri = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            bitmap = BitmapFactory.decodeStream(imageStream);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
           // profile.setImageBitmap(bitmap);
            callloader(2);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }



    }


    private void onCaptureImageResult(Intent data) {
        bitmap = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
       // profile.setImageBitmap(bitmap);
        callloader(2);


    }

    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {
        Loader<HashMap<String, String>> result = null;
        this.id =id;
        if(id==0) {
            loading = ProgressDialog.show(this, "Loading.....", "wait....", false, false);
            result = new ProfileLoader(this, accesss_token);

        }else if(id==1){
                HashMap<String,String> input = new HashMap<>();
                input.put("C_FatherOrHusbandName",fathers_name.getText().toString());
                input.put("C_PermanentAddress",et_address.getText().toString());
            input.put("C_City",et_city.getText().toString());
            input.put("C_Pin",et_pin.getText().toString());
            input.put("C_State",et_state.getText().toString());
            input.put("C_Country",et_country.getText().toString());
            input.put("C_District",et_district.getText().toString());
            input.put("C_PoliceStation",et_policeStation.getText().toString());
            input.put("C_PostOffice",et_postOffice.getText().toString());
            input.put("C_PanNumber",et_pan_no.getText().toString());
            input.put("C_AdhaarNumber",et_adhaarNo.getText().toString());
            input.put("C_GSTIN",et_gst.getText().toString());
            input.put("C_DateOfBirth",et_dob.getText().toString());
            input.put("C_DateOfAnniversary",et_dateOfAnniversary.getText().toString());
            input.put("C_Occupation",c_Occupation);
            input.put("C_AddressProofDocumentName",c_AddressProofDocumentName);
            input.put("C_AddressProofDocumentNumber",c_AddressProofDocumentNumber);
            input.put("C_ResidentialStatus",c_ResidentialStatus);
            input.put("NRI_PassportNumber",et_passportNo.getText().toString());
            input.put("NRI_PowerOfAttorneyHolderName",et_holdersName.getText().toString());
            input.put("NRI_LocalContactName",et_name.getText().toString());
            input.put("NRI_LocalContactMobile",et_mob_No.getText().toString());
            input.put("NRI_LocalContactAddress",et_Address.getText().toString());
            input.put("NRI_LocalContactCorrespondenceAddress",et_correspondenceAddress.getText().toString());
            input.put("NRI_LocalContactCity",et_City2.getText().toString());
            input.put("NRI_LocalContactPin",et_Pin2.getText().toString());
            input.put("NRI_LocalContactState",et_State2.getText().toString());
            input.put("NRI_LocalContactDistrict",et_District2.getText().toString());
            input.put("NRI_LocalContactCountry",et_Country2.getText().toString());

                result = new EditProfileLoader(this, accesss_token,input);
            showAlertDialog();
        }
        else if(id==2) {
            String encoded ="";
            if(!(bitmap ==null)) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                 encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                 progressBar.setVisibility(View.VISIBLE);
                 progressBar.requestFocus();
            }else{
                encoded = "";
                profile.setCircleBackgroundColor(getResources().getColor(R.color.White));
                progressBar.setVisibility(View.VISIBLE);
                progressBar.requestFocus();
            }
            progressBar.setVisibility(View.VISIBLE);
            HashMap<String,String> input = new HashMap<>();
            input.put("profile_pic",encoded);
            result = new EditPicLoader(this, accesss_token,input);
        }

        return result;

    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        getLoaderManager().destroyLoader(id);

        if(id==2){
            progressBar.setVisibility(View.GONE);
            if(!(bitmap ==null)) {
                Toast.makeText(this, "profile pic has been updated.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "profile pic has been removed.", Toast.LENGTH_SHORT).show();
            }
        }
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

    private void setData(HashMap<String, String> data,int id) throws JSONException {
        response_code = Integer.parseInt(data.get(getString(R.string.response_code)));
        profileBeans = new ArrayList<>();
        Type listType = new TypeToken<ProfileBean>() {
        }.getType();
        Gson gson = new Gson();
        if (response_code != 0) {
            switch (response_code) {
                        case 200:
                            switch (id) {
                                case 0:
                                profileBean = gson.fromJson(data.get("data"), listType);
                                setResultData(profileBean);
                                break;
                                case 1:

                                break;
                                case 2:
                                    JSONObject object = new JSONObject(data.get("data"));
                                    String msg = object.getString("message");
                                    if (msg.equals("profile pic has been updated.")) {
                                        profile.setImageBitmap(bitmap);
                                        String profile_image = object.getString("profile_pic");
                                        session.updateProfilePic(profile_image);
                                        if(!TextUtils.isEmpty(object.getString("data"))){
                                            Picasso.get().load(object.getString("data"))
                                                    .placeholder(R.drawable.thumbnail)
                                                    .resize(110,110)
                                                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                                                    .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
                                                    .into(profile);
                                            tv_initial.setVisibility(View.INVISIBLE);
                                            profile.setVisibility(View.VISIBLE);
                                        }
                                    } else {
                                        session.updateProfilePic("");
                                        profile.setVisibility(View.INVISIBLE);
                                        tv_initial.setText(profileBean.getFname().substring(0,1).toUpperCase());
                                        tv_initial.setVisibility(View.VISIBLE);
                                    }
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

    private void setValue() {
        Picasso.get().load(user.get(UserSessionManager.KEY_ProfileImage)).placeholder(R.drawable.thumbnail).into(profile);
    }

    private void setResultData(ProfileBean profileBean) {
//        Picasso.get().load(session.getUserDetails().get(UserSessionManager.KEY_image)).into(profile);
        if(!TextUtils.isEmpty(profileBean.getImage())){
            Picasso.get().load(profileBean.getImage())
                    .placeholder(R.drawable.thumbnail)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
                    .resize(110,110)
                    .into(profile);
            tv_initial.setVisibility(View.INVISIBLE);
            profile.setVisibility(View.VISIBLE);
        }else{
            tv_initial.setText(profileBean.getFname().substring(0,1).toUpperCase());
            tv_initial.setVisibility(View.VISIBLE);
            profile.setVisibility(View.INVISIBLE);
        }
        if(!TextUtils.isEmpty(profileBean.getFname())) {
            tv_fullName.setText(profileBean.getFname());
        } else {
            tv_fullName.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCFatherOrHusbandName())) {
            fathers_name.setText(profileBean.getCFatherOrHusbandName());
        } else {
            fathers_name.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCPermanentAddress())) {
            et_address.setText(profileBean.getCPermanentAddress());
        } else {
            et_address.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCCity())) {
            et_city.setText(profileBean.getCCity());
        } else {
            et_city.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCPin())) {
            et_pin.setText(profileBean.getCPin());
        } else {
            et_pin.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCState())) {
            et_state.setText(profileBean.getCState());
        } else {
            et_state.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCDistrict())) {
            et_district.setText(profileBean.getCDistrict());
        } else {
            et_district.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCCountry())) {
            et_country.setText(profileBean.getCCountry());
        } else {
            et_country.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCPoliceStation())) {
            et_policeStation.setText(profileBean.getCPoliceStation());
        } else {
            et_policeStation.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCPostOffice())) {
            et_postOffice.setText(profileBean.getCPostOffice());
        } else {
            et_postOffice.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCPanNumber())) {
            et_pan_no.setText(profileBean.getCPanNumber());
        } else {
            et_pan_no.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCAdhaarNumber())) {
            et_adhaarNo.setText(profileBean.getCAdhaarNumber());
        } else {
            et_adhaarNo.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCGSTIN())) {
            et_gst.setText(profileBean.getCGSTIN());
        } else {
            et_gst.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getMobile())) {
            et_mobNo.setText(profileBean.getMobile());
        } else {
            et_mobNo.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getEmail())) {
            et_email.setText(profileBean.getEmail());
        } else {
            et_email.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCDateOfBirth())) {
            et_dob.setText(profileBean.getCDateOfBirth());
        } else {
            et_dob.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCDateOfAnniversary())) {
            et_dateOfAnniversary.setText(profileBean.getCDateOfAnniversary());
        } else {
            et_dateOfAnniversary.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRIPowerOfAttorneyHolderName())) {
            et_holdersName.setText(profileBean.getNRIPowerOfAttorneyHolderName());
        } else {
            et_holdersName.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRILocalContactName())) {
            et_name.setText(profileBean.getNRILocalContactName());
        } else {
            et_name.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRILocalContactMobile())) {
            et_mob_No.setText(profileBean.getNRILocalContactMobile());
        } else {
            et_mob_No.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRILocalContactAddress())) {
            et_Address.setText(profileBean.getNRILocalContactAddress());
        } else {
            et_Address.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRILocalContactCorrespondenceAddress())) {
            et_correspondenceAddress.setText(profileBean.getNRILocalContactCorrespondenceAddress());
        } else {
            et_correspondenceAddress.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRILocalContactCity())) {
            et_City2.setText(profileBean.getNRILocalContactCity());
        } else {
            et_City2.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRILocalContactPin())) {
            et_Pin2.setText(profileBean.getNRILocalContactPin());
        } else {
            et_Pin2.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRILocalContactState())) {
            et_State2.setText(profileBean.getNRILocalContactState());
        } else {
            et_State2.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRILocalContactDistrict())) {
            et_District2.setText(profileBean.getNRILocalContactDistrict());
        } else {
            et_District2.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getNRILocalContactCountry())) {
            et_Country2.setText(profileBean.getNRILocalContactCountry());
        } else {
            et_Country2.setText("");
        }

        /*if(!TextUtils.isEmpty(profileBean.getCOccupation())) {
            cb_service.setText(profileBean.getCOccupation());
        } else {
            cb_service.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCOccupation())) {
            cb_professional.setText(profileBean.getCOccupation());
        } else {
            cb_professional.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCOccupation())) {
            cb_business.setText(profileBean.getCOccupation());
        } else {
            cb_business.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCOccupation())) {
            cb_housewife.setText(profileBean.getCOccupation());
        } else {
            cb_housewife.setText("");
        }

        if(!TextUtils.isEmpty(profileBean.getCOccupation())) {
            cb_others.setText(profileBean.getCOccupation());
        } else {
            cb_others.setText("");
        }*/

        if(!TextUtils.isEmpty(profileBean.getNRIPassportNumber())) {
            et_passportNo.setText(profileBean.getNRIPassportNumber());
        } else {
            et_passportNo.setText("");
        }

    }
}
