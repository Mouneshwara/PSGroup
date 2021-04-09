package in.psgroup.psgroup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.Adapters.TicketIdAdapter;
import in.psgroup.psgroup.Adapters.ViewTicketAdapter;
import in.psgroup.psgroup.Models.TicketIdBean;
import in.psgroup.psgroup.Models.ViewTicketBean;

public class TicketIdActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ic_back;
    private TextView tv_tickerId;
    private RecyclerView recycler;
    private Button btn_callUs;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<TicketIdBean> idList;
    private TicketIdAdapter ticketAdapter;
    private int REQUEST_PHONE_CALL=100;
    private String TAG="TicketIdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_id);
        initialize();

        // add PhoneStateListener
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void initialize() {
        ic_back = (ImageView) findViewById(R.id.ic_back);
        tv_tickerId = (TextView) findViewById(R.id.tv_tickerId);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        btn_callUs = (Button) findViewById(R.id.btn_callUs);
        ic_back.setOnClickListener(this);
        btn_callUs.setOnClickListener(this);
        initializeRecyclerView();

    }

    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    isPhoneCalling = false;
                }

            }
        }
    }

    private void initializeRecyclerView() {
        layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        idList = new ArrayList<>();
        idList.add(new TicketIdBean("PS Group", "Ticket raised for fixing bathroom lights. Executives will get in touch with the customer.", "Dec 2, 2018"));
        idList.add(new TicketIdBean("PS Group", "Ticket raised for fixing bathroom lights. Executives will get in touch with the customer.", "Dec 2, 2018"));
        idList.add(new TicketIdBean("PS Group", "Ticket raised for fixing bathroom lights. Executives will get in touch with the customer.", "Dec 2, 2018"));
        idList.add(new TicketIdBean("PS Group", "Ticket raised for fixing bathroom lights. Executives will get in touch with the customer.", "Dec 2, 2018"));
        ticketAdapter = new TicketIdAdapter(idList, getApplicationContext());
        recycler.setAdapter(ticketAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                this.onBackPressed();
                break;

            case R.id.btn_callUs:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0377778888"));
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    if (callIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(callIntent);
                    } else {
                        Log.e(TAG, "Can't resolve app for ACTION_DIAL Intent.");
                    }
                }
                break;

        }
    }
}
