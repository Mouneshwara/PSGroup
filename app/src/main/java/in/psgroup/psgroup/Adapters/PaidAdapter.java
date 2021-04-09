package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.InvoiceActivity;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.PaidBean;

/**
 * Created by codebele-07 on 6/4/18.
 */

public class PaidAdapter extends RecyclerView.Adapter<PaidAdapter.MyViewHolder> {
    ArrayList<PaidBean> paidArrayList =new ArrayList<PaidBean>();
    Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView milestoneDate,apartment,doornumber,milestone,tv_paidOn,tv_paidtime;
        public Button btn_ledger;


        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            milestoneDate=(TextView)view.findViewById(R.id.milestoneDate);
            apartment=(TextView)view.findViewById(R.id.apartment);
            doornumber=(TextView)view.findViewById(R.id.doornumber);
            btn_ledger=(Button) view.findViewById(R.id.btn_ledger);
            milestone=(TextView)view.findViewById(R.id.milestone);
            tv_paidOn=(TextView)view.findViewById(R.id.tv_paidOn);
            tv_paidtime =  (TextView)view.findViewById(R.id.tv_paidtime);

        }

        @Override
        public void onClick(View v) {
            int position=this.getAdapterPosition();
            PaidBean paidBean=paidArrayList.get(position);

            switch (v.getId()){
                case R.id.btn_invoice:

                    Intent i = new Intent(mContext, InvoiceActivity.class);
                    mContext.startActivity(i);
                    break;
            }

        }
    }

    public PaidAdapter(Context mContext, ArrayList<PaidBean> paidArrayList) {
        this.paidArrayList=paidArrayList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paid_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PaidBean paidBean=paidArrayList.get(position);
        // holder.tvdesc.setText(transaction.getDescription().toString())
        holder.milestoneDate.setText(paidBean.getMilestone_date());
        holder.apartment.setText(paidBean.getAppartment());
        holder.doornumber.setText(paidBean.getBhk());
        holder.btn_ledger.setText(paidBean.getBtn_ledger());
        holder.milestone.setText(paidBean.getMilestone());
        holder.tv_paidOn.setText(paidBean.getPaid_on());
        holder.tv_paidtime.setText(paidBean.getPaid_time());

    }

    @Override
    public int getItemCount() {
        return paidArrayList.size();
    }

}
