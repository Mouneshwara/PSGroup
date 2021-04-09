package in.psgroup.psgroup;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CompanyProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ic_back;
    private TextView tv_edit;
    private EditText gst,company,income_no,registration_no,registeredAddress,policeStation,city,pin,state,dist,country,phnNum1,phnNum2,
            mobNum,fax,correspondenceAdd,city2,pin2,state2,country2,phnNo1,phnN02,mobNum2,fax2,representedBy,nationality,fatherName,
            designation,permanentAdd,city3,pin3,state3,dist2,country3,contactNum,email;
    private CheckBox cb_proprietorship,cb_partnership,cb_huf,cb_pvtLtd,trade_liscence,panCard,voterId,passport,adharCard,drivingLiscence,
            partnershipDeed,partnerResolutionCopy,cb_hufPanCard,cb_hufTradeLiscence,cb_hufDeed,cb_hufReptPanCard,cb_hufAddress,pvtPanCard,
            cb_incorpCertificate,cb_pvtArticle,cb_llpAgreememt,cb_boardResolCopy,cb_authorizedsign,partnerPanCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);
        initialize();
    }

    private void initialize() {
        ic_back = (ImageView)findViewById(R.id.ic_back);
        tv_edit = (TextView)findViewById(R.id.tv_edit);
        gst = (EditText)findViewById(R.id.gst);
        company = (EditText)findViewById(R.id.company);
        cb_proprietorship= (CheckBox)findViewById(R.id.cb_proprietorship);
        cb_partnership = (CheckBox)findViewById(R.id.cb_partnership);
        cb_huf = (CheckBox)findViewById(R.id.cb_huf);
        cb_pvtLtd = (CheckBox)findViewById(R.id.cb_pvtLtd);
        income_no = (EditText)findViewById(R.id.income_no);
        registration_no = (EditText)findViewById(R.id.registration_no);
        registeredAddress = (EditText)findViewById(R.id.registeredAddress);
        policeStation = (EditText)findViewById(R.id.policeStation);
        city = (EditText)findViewById(R.id.city);
        pin = (EditText)findViewById(R.id.pin);
        state = (EditText)findViewById(R.id.state);
        dist = (EditText)findViewById(R.id.dist);
        country = (EditText)findViewById(R.id.country);
        phnNum1 = (EditText)findViewById(R.id.phnNum1);
        phnNum2 = (EditText)findViewById(R.id.phnNum2);
        mobNum = (EditText)findViewById(R.id.mobNum);
        fax = (EditText)findViewById(R.id.fax);
        correspondenceAdd = (EditText)findViewById(R.id.correspondenceAdd);
        city2 = (EditText)findViewById(R.id.city2);
        pin2 = (EditText)findViewById(R.id.pin2);
        state2 = (EditText)findViewById(R.id.state2);
        country2 = (EditText)findViewById(R.id.country2);
        phnNo1 = (EditText)findViewById(R.id.phnNo1);
        phnN02 = (EditText)findViewById(R.id.phnN02);
        mobNum2 = (EditText)findViewById(R.id.mobNum2);
        fax2 = (EditText)findViewById(R.id.fax2);
        representedBy = (EditText)findViewById(R.id.representedBy);
        nationality = (EditText)findViewById(R.id.nationality);
        fatherName = (EditText)findViewById(R.id.fatherName);
        designation = (EditText)findViewById(R.id.designation);
        permanentAdd = (EditText)findViewById(R.id.permanentAdd);
        city3 = (EditText)findViewById(R.id.city3);
        pin3 = (EditText)findViewById(R.id.pin3);
        state3 = (EditText)findViewById(R.id.state3);
        dist2 = (EditText)findViewById(R.id.dist2);
        country3 = (EditText)findViewById(R.id.country3);
        contactNum = (EditText)findViewById(R.id.contactNum);
        email = (EditText)findViewById(R.id.email);
        trade_liscence = (CheckBox)findViewById(R.id.trade_liscence);
        panCard = (CheckBox)findViewById(R.id.panCard);
        voterId = (CheckBox)findViewById(R.id.voterId);
        passport = (CheckBox)findViewById(R.id.passport);
        adharCard = (CheckBox)findViewById(R.id.adharCard);
        drivingLiscence = (CheckBox)findViewById(R.id.drivingLiscence);
        partnershipDeed = (CheckBox)findViewById(R.id.partnershipDeed);
        partnerPanCard = (CheckBox)findViewById(R.id.partnerPanCard);
        partnerResolutionCopy = (CheckBox)findViewById(R.id.partnerResolutionCopy);
        cb_hufPanCard = (CheckBox)findViewById(R.id.cb_hufPanCard);
        cb_hufTradeLiscence = (CheckBox)findViewById(R.id.cb_hufTradeLiscence);
        cb_hufDeed = (CheckBox)findViewById(R.id.cb_hufDeed);
        cb_hufReptPanCard = (CheckBox)findViewById(R.id.cb_hufReptPanCard);
        cb_hufAddress = (CheckBox)findViewById(R.id.cb_hufAddress);
        pvtPanCard = (CheckBox)findViewById(R.id.pvtPanCard);
        cb_incorpCertificate = (CheckBox)findViewById(R.id.cb_incorpCertificate);
        cb_pvtArticle = (CheckBox)findViewById(R.id.cb_pvtArticle);
        cb_llpAgreememt = (CheckBox)findViewById(R.id.cb_llpAgreememt);
        cb_boardResolCopy = (CheckBox)findViewById(R.id.cb_boardResolCopy);
        cb_authorizedsign = (CheckBox)findViewById(R.id.cb_authorizedsign);
        ic_back.setOnClickListener(this);
        tv_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_back:
                this.onBackPressed();
                break;

            case R.id.tv_edit:
                if (tv_edit.getText().equals("EDIT")) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                    tv_edit.setText("REQUEST EDIT");
                    gst.setEnabled(true);
                    company.setEnabled(true);
                    registeredAddress.setEnabled(true);
                    correspondenceAdd.setEnabled(true);
                    permanentAdd.setEnabled(true);

                }else {
                    showAlertDialog("Requested!",getResources().getString(R.string.edit_phnno));
                    tv_edit.setText("EDIT");
                    break;

                }
        }
    }

    private void showAlertDialog(String title_message, String description) {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_edit_phn_no, viewGroup, false);
        Button btn = (Button) dialogView.findViewById(R.id.btn_done);
        TextView tv_title_message = (TextView) dialogView.findViewById(R.id.tv_requested);
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
                recreate();
            }
        });
    }
}
