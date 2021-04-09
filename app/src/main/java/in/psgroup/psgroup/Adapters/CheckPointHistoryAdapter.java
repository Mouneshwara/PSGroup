package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.psgroup.psgroup.Models.CheckPointHistoryBean;
import in.psgroup.psgroup.R;


public class CheckPointHistoryAdapter extends RecyclerView.Adapter<CheckPointHistoryAdapter.MyViewHolder> {
    ArrayList<CheckPointHistoryBean> referencelist = new ArrayList<CheckPointHistoryBean>();
    Context context;

    public CheckPointHistoryAdapter(ArrayList<CheckPointHistoryBean> historyList, Context context) {
        this.referencelist = historyList;
        this.context = context;
    }
    @Override
    public CheckPointHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_check_point_history, parent, false);

        return new CheckPointHistoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckPointHistoryAdapter.MyViewHolder holder, int position) {
        final CheckPointHistoryBean historyBean = referencelist.get(position);

        /*holder.tv_pointsEarned.setText(historyBean.getPoints_earned());*/
/*if(historyBean.getPoints_earned().equals("Bank Transfer")) {
    holder.iv_historyImg.setImageDrawable(context.getResources().getDrawable(R.drawable.check_history_img));
    holder.ll_prop.setVisibility(View.GONE);
    holder.ll_refer.setVisibility(View.GONE);
}else{*/
if(historyBean.getType().equals("Redeem Point"))
{

    String notdate = historyBean.getDateofPoint();
    String date = dateset(notdate);


    holder.tv_when.setText(date);


    holder.iv_historyImg.setImageDrawable(context.getResources().getDrawable(R.drawable.check_history_img));
    holder.tv_pointsEarned.setText("Bank Transfer");




    holder.tv_points.setText('-'+historyBean.getPoints());
    holder.tv_points.setTextColor(context.getResources().getColor(R.color.redtext));
holder.ll_prop.setVisibility(View.GONE);
holder.ll_refer.setVisibility(View.GONE);
}else
{
    holder.iv_historyImg.setImageDrawable(context.getResources().getDrawable(R.drawable.points_img));
    holder.tv_pointsEarned.setText("Points Earned");
    //holder.tv_when.setText(historyBean.getDateofPoint());
    holder.tv_referredTo.setText(historyBean.getReferredCustomerName());
    holder.tv_propertyName.setText(historyBean.getProjectName());
    holder.tv_points.setText('+'+historyBean.getPoints());
    holder.tv_points.setTextColor(context.getResources().getColor(R.color.green));
    holder.ll_prop.setVisibility(View.VISIBLE);
    holder.ll_refer.setVisibility(View.VISIBLE);
    String notdate = historyBean.getDateofPoint();
    String date = dateset(notdate);


    holder.tv_when.setText(date);
}

    }

    private String dateset(String notdate) {
        Date timeform = null;
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        try {
            timeform = simpleDateFormat.parse(notdate);
            date = simpleDateFormat1.parse(notdate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        simpleDateFormat = new SimpleDateFormat("hh:mm a");
        simpleDateFormat1 = new SimpleDateFormat("MMM dd");
        String newtime = simpleDateFormat.format(timeform);
        String date1 = simpleDateFormat1.format(date);

        String value = date1+" at "+newtime;
        return value;


    }

    @Override
    public int getItemCount() {
        return referencelist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_pointsEarned, tv_referredTo, tv_propertyName, tv_when, tv_points,tv_firstName;
        ImageView iv_historyImg,iv_pointsImg;
        LinearLayout ll_prop,ll_refer;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            iv_historyImg = (ImageView)itemView.findViewById(R.id.iv_historyImg);
            tv_pointsEarned = (TextView) itemView.findViewById(R.id.tv_pointsEarned);
            tv_referredTo = (TextView) itemView.findViewById(R.id.tv_referredTo);
            tv_propertyName = (TextView) itemView.findViewById(R.id.tv_propertyName);
            tv_when = (TextView) itemView.findViewById(R.id.tv_when);
            tv_points = (TextView) itemView.findViewById(R.id.tv_points);
            tv_firstName = (TextView)itemView.findViewById(R.id.tv_firstName);
            ll_prop = (LinearLayout)itemView.findViewById(R.id.ll_prop);
            ll_refer = (LinearLayout)itemView.findViewById(R.id.ll_refer);


        }

        @Override
        public void onClick(View view) {

        }
    }
}

