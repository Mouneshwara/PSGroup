package in.psgroup.psgroup;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.signin.SignIn;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.Network.GuestLoader;
import in.psgroup.psgroup.Network.SignUpDetailsLoader;
import in.psgroup.psgroup.Network.SignUpLoader;
import in.psgroup.psgroup.Utility.Utils;

public class SignUpDetailsActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<HashMap<String, String>> {
    EditText full_name,phn_no,email,property_name,unit_no;
    TextView tv_choosefile,emailerror,phone_error,name_error;
    ImageView back;
    Button choose_button,btn_signup;
    PsGroupApplication myApplication;
    private static int RESULT_LOAD_IMAGE = 1, MY_PERMISSIONS = 1, SELECT_FILE = 1;
    private Bitmap bitmap;
    int dataSize;
    private UserSessionManager session;
    ProgressDialog loading;
    private int response_code = 0;
    private int id;
    String encoded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_details);
        session = new UserSessionManager(getApplicationContext());
        myApplication = (PsGroupApplication) this.getApplication();
        initialize();
    }

    private void initialize() {
        full_name = (EditText)findViewById(R.id.full_name);
        phn_no = (EditText)findViewById(R.id.phn_no);
        email = (EditText)findViewById(R.id.email);
        property_name = (EditText)findViewById(R.id.property_name);
        unit_no = (EditText)findViewById(R.id.unit_no);
        choose_button = (Button)findViewById(R.id.choose_button);
        btn_signup = (Button)findViewById(R.id.btn_signup);
        tv_choosefile = (TextView)findViewById(R.id.tv_choosefile);
        back = (ImageView)findViewById(R.id.back);
        emailerror =(TextView)findViewById(R.id.emailerror);
        phone_error =(TextView)findViewById(R.id.phone_error);
        name_error =(TextView)findViewById(R.id.name_error);
        back.setOnClickListener(this);
        btn_signup.setOnClickListener(this);
        choose_button.setOnClickListener(this);
    }


    private boolean valid() {


            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() && email.length() > 0) {
                emailerror.setVisibility(View.VISIBLE);
                email.requestFocus();

                return false;
            }
        if (full_name.length() <= 0) {
           name_error.setVisibility(View.VISIBLE);
            full_name.requestFocus();

            return false;
        }

        if (phn_no.length() <= 0) {
            phone_error.setVisibility(View.VISIBLE);
            phn_no.requestFocus();

            return false;
        } else {
            emailerror.setVisibility(View.GONE);
            name_error.setVisibility(View.GONE);
            phone_error.setVisibility(View.GONE);


            return true;
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signup:
                if(valid()) {
                    callLoader(0);
                }

                break;

            case R.id.choose_button:

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS);
                }
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS);
                }
                galleryIntent();
                break;

            case R.id.back:
                this.onBackPressed();
                break;
        }
    }

    private void showAlertDialog( ) {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_signup_alert, viewGroup, false);
        Button btn = (Button) dialogView.findViewById(R.id.btn_done);
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

                session.createguest(true);
              //  callLoader(1);
                Intent i = new Intent(SignUpDetailsActivity.this, LoginActivity.class);
                startActivity(i);
                alertDialog.dismiss();

            }
        });


//
//        final Dialog dialog = new Dialog(SignUpDetailsActivity.this);
//        dialog.setContentView(R.layout.layout_signup_alert);
//        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//
//        dialog.show();
//        Button btn_done = (Button) dialog.findViewById(R.id.btn_done);
//
//        //finally creating the alert dialog and displaying it
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.getWindow().setBackgroundDrawableResource(R.color.dialogbg);
//        dialog.show();
//
//        btn_done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ShowPopUp();
//                dialog.dismiss();
//
//            }
//        });
    }

    private void ShowPopUp() {


        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_signup_popup, viewGroup, false);
        Button btn = (Button) dialogView.findViewById(R.id.btn_done);
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

            }
        });
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
            byte[] byteArray = bytes .toByteArray();



            try {
                InputStream fileInputStream=getApplicationContext().getContentResolver().openInputStream(imageUri);

                 dataSize = fileInputStream.available();
                if(dataSize<30000){
                    tv_choosefile.setText("file size is less than 30kb");
                    tv_choosefile.setTextColor(getResources().getColor(R.color.red));
                }
                else if(dataSize>500000){
                    tv_choosefile.setText("file size is more than 500kb");
                    tv_choosefile.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    tv_choosefile.setText("One file selected");
                    encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(SignUpDetailsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
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
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {

        loading = ProgressDialog.show(this, "Signing Up.....", "wait....", false, false);
        Loader<HashMap<String, String>> result = null;
        switch (id) {

            case 0:
                HashMap<String, String> input = new HashMap<>();
                input.put("email", email.getText().toString());
                input.put("fullname", full_name.getText().toString());
                input.put("mobile", phn_no.getText().toString());
                input.put("property_name", property_name.getText().toString());
                input.put("unit_number", unit_no.getText().toString());
                input.put("image", encoded!= null ? encoded : "");
                result = new SignUpDetailsLoader(this, input);

                break;
            case 1:
                this.id=1;
                loading = ProgressDialog.show(this, "Guest Sign In.....", "wait....", false, false);
                result = new GuestLoader(this);
                break;

        }
        return result;


    }



    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        loading.cancel();
        getLoaderManager().destroyLoader(loader.getId());
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
                            showAlertDialog();
                            break;
                        case 1:
                            JSONObject jsonObject1 = new JSONObject(data.get("data"));
                            session.createUserLoginSession(jsonObject1.getString("access_token"),"Guest","","","","","");
                            session.createguest(true);
                            i = new Intent(SignUpDetailsActivity.this, HomeActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            // Add new Flag to start new Activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();
                            break;


                    }
                    break;
                case 203:
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
}
