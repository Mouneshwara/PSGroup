package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.ExplanationOfClauseActivity;
import in.psgroup.psgroup.GeneralEnquiryActivity;
import in.psgroup.psgroup.Models.GeneralEnquiryBean;
import in.psgroup.psgroup.Models.ViewTicketBean;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.TicketIdActivity;

public class ViewTicketAdapter extends RecyclerView.Adapter<ViewTicketAdapter.MyViewHolder> {

    ArrayList<ViewTicketBean> ticketList = new ArrayList<>();
    Context context;

    public ViewTicketAdapter(ArrayList<ViewTicketBean> ticketList, Context context) {
        this.ticketList = ticketList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewTicketAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_view_tickets, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewTicketAdapter.MyViewHolder holder, int position) {

        final ViewTicketBean ticketBean = ticketList.get(position);
        holder.tv_progressText.setText(ticketBean.getProgress_status());
        holder.tv_ticketNo.setText(ticketBean.getTicket_id());
        holder.tv_category.setText(ticketBean.getCategory());
        holder.tv_status.setText(ticketBean.getStatus());
        holder.tv_subCategory.setText(ticketBean.getSub_category());
        holder.tv_date.setText(ticketBean.getDate());

    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_progressText, tv_date, tv_ticketNo, tv_category, tv_status, tv_subCategory;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_progressText = (TextView) itemView.findViewById(R.id.tv_progressText);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_ticketNo = (TextView) itemView.findViewById(R.id.tv_ticketNo);
            tv_category = (TextView) itemView.findViewById(R.id.tv_category);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_subCategory = (TextView) itemView.findViewById(R.id.tv_subCategory);


        }

        @Override
        public void onClick(View view) {
            int position=this.getAdapterPosition();
            ViewTicketBean ticketBean =ticketList.get(position);
            Intent i=new Intent(context, TicketIdActivity.class);
            i.putExtra("",ticketList);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }
    }
}
