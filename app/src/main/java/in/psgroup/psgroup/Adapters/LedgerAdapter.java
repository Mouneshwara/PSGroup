package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.LedgerActivity;
import in.psgroup.psgroup.Models.LedgerBean;
import in.psgroup.psgroup.Models.ViewTicketBean;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.TicketIdActivity;

public class LedgerAdapter extends RecyclerView.Adapter<LedgerAdapter.MyViewHolder> {

    ArrayList<LedgerBean> ledgerBeans = new ArrayList<>();
    Context context;

    public LedgerAdapter(ArrayList<LedgerBean> list, Context context) {
        this.ledgerBeans = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LedgerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_ledger, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LedgerAdapter.MyViewHolder holder, int position) {

        final LedgerBean ledgerBean = ledgerBeans.get(position);

        holder.tv_date.setText(ledgerBean.getDate());
        holder.tv_balance.setText(ledgerBean.getBalance());
        holder.tv_amount.setText(ledgerBean.getAmount());
        holder.tv_demand.setText(ledgerBean.getDemand());
        holder.tv_receipt.setText(ledgerBean.getReceipt());
        holder.tv_chkNo.setText(ledgerBean.getCheck_no());
        holder.tv_drawnOn.setText(ledgerBean.getDrawn_on());
        holder.amountTax.setText(ledgerBean.getAmount_tax());
        holder.tv_tax.setText(ledgerBean.getTax());
        holder.tv_EFTno.setText(ledgerBean.getEft_no());
        String balance = holder.tv_balance.getText().toString();
        String charcolor =balance.substring(balance.length() - 3);
        if(charcolor.equals("Cr.")){
            holder.tv_balance.setTextColor(context.getResources().getColor(R.color.lightgreen));
        }else{

        }

    }

    @Override
    public int getItemCount() {
        return ledgerBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_date, tv_balance, tv_amount, tv_demand, tv_receipt, tv_chkNo,tv_drawnOn,amountTax,tv_tax,tv_EFTno;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_balance = (TextView) itemView.findViewById(R.id.tv_balance);
            tv_amount = (TextView) itemView.findViewById(R.id.tv_amount);
            tv_demand = (TextView) itemView.findViewById(R.id.tv_demand);
            tv_receipt = (TextView) itemView.findViewById(R.id.tv_receipt);
            tv_chkNo = (TextView) itemView.findViewById(R.id.tv_chkNo);
            tv_drawnOn = (TextView)itemView.findViewById(R.id.tv_drawnOn);
            amountTax = (TextView)itemView.findViewById(R.id.amountTax);
            tv_tax = (TextView)itemView.findViewById(R.id.tv_tax);
            tv_EFTno = (TextView)itemView.findViewById(R.id.tv_EFTno);


        }

        @Override
        public void onClick(View view) {

        }
    }
}
