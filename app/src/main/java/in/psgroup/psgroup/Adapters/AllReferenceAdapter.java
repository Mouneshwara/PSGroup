package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.psgroup.psgroup.Models.AllReferenceBean;
import in.psgroup.psgroup.Models.ReferralHistoryBean;
import in.psgroup.psgroup.Models.ViewTicketBean;
import in.psgroup.psgroup.R;

public class AllReferenceAdapter extends RecyclerView.Adapter<AllReferenceAdapter.MyViewHolder> {
    ArrayList<ReferralHistoryBean> referralHistoryBeanArrayList = new ArrayList<>();
    Context context;

    public AllReferenceAdapter(ArrayList<ReferralHistoryBean> referralHistoryBeanArrayList, Context context) {
        this.referralHistoryBeanArrayList = referralHistoryBeanArrayList;
        this.context = context;
    }
    @Override
    public AllReferenceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_all_reference, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllReferenceAdapter.MyViewHolder holder, int position) {


        final ReferralHistoryBean referralHistoryBean = referralHistoryBeanArrayList.get(position);
        holder.tv_name.setText("Referred to "+referralHistoryBean.getFirstName()+" "+referralHistoryBean.getLastName());
        holder.tv_date.setText(referralHistoryBean.getReferralDate());
        holder.tv_status.setText(referralHistoryBean.getStatus());
        holder.tv_refferedProperty.setText(referralHistoryBean.getProjectName());
        String point = String.valueOf(referralHistoryBean.getPointsEarned().charAt(0));
        if(!point.equals("P")) {
            holder.tv_points.setText(referralHistoryBean.getPointsEarned());
        }else{
            holder.tv_points.setText(referralHistoryBean.getPointsEarned());
            holder.tv_points.setTextColor(context.getResources().getColor(R.color.redtext));

        }
    }

    @Override
    public int getItemCount() {
        return referralHistoryBeanArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name, tv_date, tv_points, tv_place, tv_refferedProperty, tv_placeName,tv_status;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_points = (TextView) itemView.findViewById(R.id.tv_points);
            tv_refferedProperty = (TextView) itemView.findViewById(R.id.tv_refferedProperty);
            tv_status = (TextView)itemView.findViewById(R.id.tv_status);


        }

        @Override
        public void onClick(View view) {

        }
    }
}
