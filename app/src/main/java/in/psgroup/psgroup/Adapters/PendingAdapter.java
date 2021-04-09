package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.PendingBean;

/**
 * Created by codebele-07 on 6/4/18.
 */

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.MyViewHolder> {
    ArrayList<PendingBean> pendingBeanArrayList =new ArrayList<PendingBean>();
    Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView apartment,doornumber,tv_amountDue,tv_pendingDays,tv_interest,tv_milestone;
        public Button btn_invoice;


        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            apartment=(TextView)view.findViewById(R.id.apartment);
            doornumber=(TextView)view.findViewById(R.id.doornumber);
            btn_invoice = (Button)view.findViewById(R.id.btn_invoice);
            tv_amountDue=(TextView)view.findViewById(R.id.tv_amountDue);
            tv_pendingDays=(TextView)view.findViewById(R.id.tv_pendingDays);
            tv_interest = (TextView)view.findViewById(R.id.tv_interest);
            tv_milestone = (TextView)view.findViewById(R.id.tv_milestone);



        }

        @Override
        public void onClick(View v) {
            int position=this.getAdapterPosition();
            PendingBean pendingBean=pendingBeanArrayList.get(position);


        }
    }

    public PendingAdapter(Context mContext, ArrayList<PendingBean> pendingBeanArrayList) {
        this.pendingBeanArrayList=pendingBeanArrayList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PendingBean pendingBean=pendingBeanArrayList.get(position);
        // holder.tvdesc.setText(transaction.getDescription().toString())
        holder.apartment.setText(pendingBean.getAppartment());
        holder.doornumber.setText(pendingBean.getBhk());
        holder.btn_invoice.setText(pendingBean.getInvoice_btn());
        holder.tv_amountDue.setText(pendingBean.getAmount_due());
        holder.tv_pendingDays.setText(pendingBean.getPending_days());
        holder.tv_interest.setText(pendingBean.getInterest_charged());
        holder.tv_milestone.setText(pendingBean.getMilestone());

    }

    @Override
    public int getItemCount() {
        return pendingBeanArrayList.size();
    }

}
