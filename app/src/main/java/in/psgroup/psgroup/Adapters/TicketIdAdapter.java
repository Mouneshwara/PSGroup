package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.Models.AllReferenceBean;
import in.psgroup.psgroup.Models.TicketIdBean;
import in.psgroup.psgroup.R;

public class TicketIdAdapter extends RecyclerView.Adapter<TicketIdAdapter.MyViewHolder>  {
    ArrayList<TicketIdBean> ticketIdBeans = new ArrayList<>();
    Context context;

    public TicketIdAdapter(ArrayList<TicketIdBean> ticketIdList, Context context) {
        this.ticketIdBeans = ticketIdList;
        this.context = context;
    }
    @Override
    public TicketIdAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_ticket_id, parent, false);

        return new TicketIdAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketIdAdapter.MyViewHolder holder, int position) {
        final TicketIdBean ticketIdBean = ticketIdBeans.get(position);
        holder.tv_ps.setText(ticketIdBean.getName().substring(0,2));
        holder.tv_psGroup.setText(ticketIdBean.getName());
        holder.tv_text.setText(ticketIdBean.getComment());
        holder.tv_date.setText(ticketIdBean.getDate());
    }

    @Override
    public int getItemCount() {
        return ticketIdBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_ps,tv_psGroup, tv_text, tv_date;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_ps = (TextView) itemView.findViewById(R.id.tv_ps);
            tv_psGroup = (TextView) itemView.findViewById(R.id.tv_psGroup);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
