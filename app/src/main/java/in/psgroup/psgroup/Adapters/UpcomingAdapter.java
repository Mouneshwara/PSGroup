package in.psgroup.psgroup.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.psgroup.psgroup.R;
import in.psgroup.psgroup.ScheduleVisitActivity;
import in.psgroup.psgroup.Models.UpcomingBean;

/**
 * Created by codebele-07 on 6/4/18.
 */

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MyViewHolder> {
    ArrayList<UpcomingBean> upcomingBeanArrayList = new ArrayList<UpcomingBean>();
    Context mContext;
    View view;
    Button btn_cancel, btn_schedule,btn_cncl,done;
    public final int CATEGORY_ID = 0;
    Dialog dialog;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView upComingName, tv_plotNo, tv_bhk, tv_landMark, visit_date,visit_status;
        private ImageView ic_property;


        public MyViewHolder(View view) {
            super(view);

            upComingName = (TextView) view.findViewById(R.id.upComingName);
            tv_plotNo = (TextView) view.findViewById(R.id.tv_plotNo);
            tv_bhk = (TextView) view.findViewById(R.id.tv_bhk);
            tv_landMark = (TextView) view.findViewById(R.id.tv_landMark);
            visit_date = (TextView) view.findViewById(R.id.visit_date);
            visit_status = (TextView)view.findViewById(R.id.visit_status);
            ic_property = (ImageView) view.findViewById(R.id.ic_property);

            btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
            btn_schedule = (Button) view.findViewById(R.id.btn_reSchedule);

            btn_schedule.setOnClickListener(this);
            btn_cancel.setOnClickListener(this);
            view.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            UpcomingBean upcomingBean = upcomingBeanArrayList.get(position);

            switch (v.getId()) {
                case R.id.btn_cancel:
                    showDialog(CATEGORY_ID);
                    bottomDialog();


                    break;
                case R.id.btn_reSchedule:
                    Intent i = new Intent(mContext, ScheduleVisitActivity.class);
                    mContext.startActivity(i);
                    break;
            }

        }

    }

    public UpcomingAdapter(Context mContext, ArrayList<UpcomingBean> upcomingBeanArrayList) {
        this.upcomingBeanArrayList = upcomingBeanArrayList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upcomingvist_card, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UpcomingBean upcomingBean = upcomingBeanArrayList.get(position);
        holder.ic_property.setImageDrawable(mContext.getResources().getDrawable(R.drawable.apartment));
        holder.upComingName.setText(upcomingBean.getAppartment_name());
        holder.tv_plotNo.setText(upcomingBean.getPlot_no());
        holder.tv_bhk.setText(upcomingBean.getBhk());
        holder.tv_landMark.setText(upcomingBean.getLandmark());
        holder.visit_date.setText(upcomingBean.getDate());
        holder.visit_status.setText(upcomingBean.getStatus());

    }

    @Override
    public int getItemCount() {
        return upcomingBeanArrayList.size();
    }

    private void bottomDialog() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext);
        View sheetView = mBottomSheetDialog.getLayoutInflater().inflate(R.layout.fragment_cancel, null);
        mBottomSheetDialog.setContentView(sheetView);

        layoutParams.copyFrom(mBottomSheetDialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        btn_cncl = (Button) sheetView.findViewById(R.id.btn_cncl);

        mBottomSheetDialog.show();

        btn_cncl.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            mBottomSheetDialog.dismiss();
                                            showDialog(CATEGORY_ID);

                                        }
                                    }
        );

    }

        public Dialog showDialog(int category_id) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

            Dialog returnDialog = null;
            switch (category_id) {
                case CATEGORY_ID:

                    dialog = new Dialog(mContext);
                    dialog.setCancelable(true);
                    dialog.setCanceledOnTouchOutside(false);

                    layoutParams.copyFrom(dialog.getWindow().getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

                    view = LayoutInflater.from(mContext).inflate(R.layout.canclevisit_card, null);
                    dialog.setContentView(view);

                    done = (Button) view.findViewById(R.id.cancelDone);

                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.getWindow().setBackgroundDrawableResource(R.color.dialogbg);
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.show();
                    returnDialog = dialog;

                    break;

            }
            return returnDialog;
        }





}
