package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.psgroup.psgroup.Models.OffersBean;
import in.psgroup.psgroup.OffersActivity;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Utility.Utils;

public class HomeOfferAdapter extends PagerAdapter {
    private TextView tv_text;
    private ImageView offer_image;
    private String TAG="OffersActivity";
    ArrayList<OffersBean> offersBeanArrayList=new ArrayList<>();
    Context context;
    private LayoutInflater layoutInflater;

    public HomeOfferAdapter( Context context,ArrayList<OffersBean> offersBeans) {
        this.offersBeanArrayList = offersBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return offersBeanArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        final OffersBean offersBean = offersBeanArrayList.get(position);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.offers_card, null);

        HomeOfferAdapter.PhoneCallListener phoneListener = new HomeOfferAdapter.PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);


        ImageView nextIcon, image;
        final TextView offer, statement, tv_readMore, tv_readLess;
        final LinearLayout linearLayout, ll_read;
        final Button btn_callUs;

        nextIcon = view.findViewById(R.id.see);
        image = view.findViewById(R.id.offer_image);

        offer = view.findViewById(R.id.tv_offers);
        statement = view.findViewById(R.id.upcoming);

        tv_readMore = view.findViewById(R.id.tv_readMore);
        tv_text = view.findViewById(R.id.tv_text);
        /*tv_readLess = view.findViewById(R.id.tv_readLess);*/
        btn_callUs = view.findViewById(R.id.btn_callUs);
        linearLayout = view.findViewById(R.id.layout);
        ll_read = view.findViewById(R.id.ll_read);
        offer_image = view.findViewById(R.id.offer_image);

//        ObjectAnimator animation = ObjectAnimator.ofInt(tv_blog_desc, "maxLines", 3);
//        animation.setDuration(0).start();


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);


        OffersBean offersBeans = offersBeanArrayList.get(position);

        offer.setText(offersBeans.getOffer_heading());
        statement.setText(offersBeans.getOffer_desc());
        Picasso.get().load(offersBeans.getOffer_image()).into(image);


        /*ll_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tv_readMore.getVisibility() == View.VISIBLE) {
                    tv_readMore.setVisibility(View.GONE);
                    tv_readLess.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    btn_callUs.setVisibility(View.VISIBLE);
                } else {
                    tv_readLess.setVisibility(View.GONE);
                    tv_readMore.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    btn_callUs.setVisibility(View.GONE);
                }

            }
        });*/

        tv_readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OffersBean ticketBean =offersBeanArrayList.get(0);
                Intent i=new Intent(context, OffersActivity.class);
                i.putExtra("position",position);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        /*tv_readLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_readLess.setVisibility(View.GONE);
                tv_readMore.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
            }
        });*/

        btn_callUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.checkPhonePermission(context)) {


                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:0377778888"));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(callIntent);

                } else {
                    Log.e(TAG, "Can't resolve app for ACTION_DIAL Intent.");
                }
            }
        });
        return view;

    }

    public class PhoneCallListener extends PhoneStateListener {
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
                    Intent i = context.getPackageManager()
                            .getLaunchIntentForPackage(context.getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);

                    isPhoneCalling = false;
                }

            }
        }
    }
}
