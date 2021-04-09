package in.psgroup.psgroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.Adapters.LedgerAdapter;
import in.psgroup.psgroup.Models.LedgerBean;
import in.psgroup.psgroup.Models.MyProperty;

public class LedgerActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ic_back;
    private RecyclerView recycler;
    private TextView propertyName,ledgerText,projectName,applicantName,unitNo,tv_dueOn,tv_recievedOn,balanceOn;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<LedgerBean> list;
    private LedgerAdapter ledgerAdapter;
    private MyProperty myProperties;
    private ArrayList<MyProperties> mypropertieslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger);
        myProperties = getIntent().getParcelableExtra("my_property");
        initialize();
    }

    private void initialize() {
        ic_back = (ImageView)findViewById(R.id.ic_back);
        propertyName = (TextView)findViewById(R.id.propertyName);
        ledgerText = (TextView)findViewById(R.id.ledgerText);
        projectName = (TextView)findViewById(R.id.projectName);
        applicantName = (TextView)findViewById(R.id.applicantName);
        unitNo = (TextView)findViewById(R.id.unitNo);
        tv_dueOn = (TextView)findViewById(R.id.tv_dueOn);
        tv_recievedOn = (TextView)findViewById(R.id.tv_recievedOn);
        balanceOn = (TextView)findViewById(R.id.balanceOn);
        recycler = (RecyclerView)findViewById(R.id.recycler);
        ic_back.setOnClickListener(this);
        propertyName.setText(myProperties.getProject_title()+":");
        projectName.setText(myProperties.getProject_title());
        unitNo.setText(myProperties.getApartment_no());
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        layoutManager = new GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        list.add(new LedgerBean("18th Oct 2016","936393.00 Dr.","9048.00 Dr.","  On Booking","REC0129/05783/16-17","066127"," AXIS BANK","(896070.00 + Tax: ","40323.00)","AXIMB181595431244"));
        list.add(new LedgerBean("18th Oct 2016","936393.00 Dr.","9048.00 Cr.","  On Booking","REC0129/05783/16-17","066127"," AXIS BANK","(896070.00 + Tax: ","40323.00)","AXIMB181595431244"));
        list.add(new LedgerBean("18th Oct 2016","936393.00 Dr.","9048.00 Cr.","  On Booking","REC0129/05783/16-17","066127"," AXIS BANK","(896070.00 + Tax: ","40323.00)","AXIMB181595431244"));
        list.add(new LedgerBean("18th Oct 2016","936393.00 Dr.","9048.00 Dr.","  On Booking","REC0129/05783/16-17","066127"," AXIS BANK","(896070.00 + Tax: ","40323.00)","AXIMB181595431244"));
        list.add(new LedgerBean("18th Oct 2016","936393.00 Dr.","9048.00 Cr.","  On Booking","REC0129/05783/16-17","066127"," AXIS BANK","(896070.00 + Tax: ","40323.00)","AXIMB181595431244"));
        ledgerAdapter = new LedgerAdapter(list,getApplicationContext());
        recycler.setAdapter(ledgerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_back:
                super.onBackPressed();
                Intent i = new Intent(LedgerActivity.this, HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;
        }
    }
}
