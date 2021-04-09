package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.OffersBean;
import in.psgroup.psgroup.Utility.Utils;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {
    private TextView tv_text;
    private ImageView offer_image;
    private String TAG="OffersActivity";
    ArrayList<OffersBean> offersBeanArrayList = new ArrayList<>();
    Context context;

    public OffersAdapter( Context context,ArrayList<OffersBean> offersBeans) {
        this.offersBeanArrayList = offersBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public OffersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offers_card, parent, false);

        OffersAdapter.PhoneCallListener phoneListener = new OffersAdapter.PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        return new MyViewHolder(itemView);
    }



    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView nextIcon,image;
        TextView offer,statement,tv_readMore,tv_readLess;
        private LinearLayout linearLayout,ll_read;
        private Button btn_callUs;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nextIcon = itemView.findViewById(R.id.see);
            image = itemView.findViewById(R.id.offer_image);

            offer = itemView.findViewById(R.id.tv_offers);
            statement = itemView.findViewById(R.id.upcoming);

            tv_readMore = itemView.findViewById(R.id.tv_readMore);
           /* tv_text = itemView.findViewById(R.id.tv_text);*/
            tv_readLess = itemView.findViewById(R.id.tv_readLess);
            btn_callUs = itemView.findViewById(R.id.btn_callUs);

          /*  tv_text.setOnClickListener(this);*/

            btn_callUs.setOnClickListener(this);
            /*linearLayout =itemView.findViewById(R.id.layout);*/
            ll_read =itemView.findViewById(R.id.ll_read);
            offer_image = itemView.findViewById(R.id.offer_image);

            /*statement.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    statement.getViewTreeObserver().removeOnPreDrawListener(this);

                    if(statement.getLineCount() > 2) {
                       btn_callUs.setVisibility(View.VISIBLE);
                    }

                    return true;
                }
            });*/

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_readMore:
                    tv_readMore.setVisibility(View.GONE);
                    tv_readLess.setVisibility(View.VISIBLE);
                    /*linearLayout.setVisibility(View.VISIBLE);*/
                   // offer_image.setVisibility(View.GONE);
                    break;
                case R.id.tv_readLess:
                    tv_readLess.setVisibility(View.GONE);
                    tv_readMore.setVisibility(View.VISIBLE);
                    /*linearLayout.setVisibility(View.GONE);*/
                    break;
                case R.id.btn_callUs:

                    if(Utils.checkPhonePermission(context)){
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:0377778888"));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(callIntent);
                    }
                    else {
                        Log.e(TAG, "Can't resolve app for ACTION_DIAL Intent.");
                    }

                    break;
            }

        }
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
                    Intent i = context.getPackageManager()
                            .getLaunchIntentForPackage(context.getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);

                    isPhoneCalling = false;
                }

            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        OffersBean offersBean = offersBeanArrayList.get(position);

        holder.offer.setText(offersBean.getOffer_heading());
        holder.statement.setText(offersBean.getOffer_desc());
        Picasso.get().load(offersBean.getOffer_image()).into(holder.image);


        holder.ll_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tv_readMore.getVisibility()==View.VISIBLE) {
                    holder.tv_readMore.setVisibility(View.GONE);
                    holder.tv_readLess.setVisibility(View.VISIBLE);
                    /*holder.linearLayout.setVisibility(View.VISIBLE);*/
                    holder.btn_callUs.setVisibility(View.VISIBLE);
                }else{
                    holder.tv_readLess.setVisibility(View.GONE);
                    holder.tv_readMore.setVisibility(View.VISIBLE);
               /* holder.linearLayout.setVisibility(View.GONE);*/
                    holder.btn_callUs.setVisibility(View.GONE);}

            }
            });



        /*Picasso.get().load(offersBean.getOffer_image()).placeholder(R.drawable.thumbnail).into(holder.image);*/
      /*  holder.image.set(R.drawable.thumbnail);*/

    }

    @Override
    public int getItemCount() {
        return offersBeanArrayList.size();
    }
}
