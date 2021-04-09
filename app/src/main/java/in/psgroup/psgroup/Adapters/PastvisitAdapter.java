package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.CommentsActivity;
import in.psgroup.psgroup.ForgotPasswordActivity;
import in.psgroup.psgroup.LoginActivity;
import in.psgroup.psgroup.Models.ViewTicketBean;
import in.psgroup.psgroup.MyVisitActivity;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.PastvisitBean;
import in.psgroup.psgroup.TicketIdActivity;

/**
 * Created by codebele-07 on 6/4/18.
 */

public class PastvisitAdapter extends RecyclerView.Adapter<PastvisitAdapter.MyViewHolder> {
    ArrayList<PastvisitBean> pastvisitBeanArrayList =new ArrayList<PastvisitBean>();
    Context mContext;



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_apartmentName,tv_plotNo,tv_bhk,visit_Loc,visit_date,visit_status,tv_comments,tv_letter,tv_comment;
        public ImageView ic_property;
        public LinearLayout lLayout;


        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tv_apartmentName=(TextView)view.findViewById(R.id.tv_apartmentName);
            tv_plotNo=(TextView)view.findViewById(R.id.tv_plotNo);
            tv_bhk=(TextView)view.findViewById(R.id.tv_bhk);
            visit_Loc=(TextView)view.findViewById(R.id.visit_Loc);
            visit_date=(TextView)view.findViewById(R.id.visit_date);
            ic_property = (ImageView)view.findViewById(R.id.ic_property);
            visit_status=(TextView)view.findViewById(R.id.visit_status);
            tv_comments = (TextView)view.findViewById(R.id.tv_comments);
            tv_letter = (TextView)view.findViewById(R.id.tv_letter);
            tv_comment = (TextView)view.findViewById(R.id.tv_comment);
            lLayout = (LinearLayout)view.findViewById(R.id.lLayout);

            tv_comments.setOnClickListener(this);




        }

        @Override
        public void onClick(View v) {
            int position=this.getAdapterPosition();
            PastvisitBean ticketBean =pastvisitBeanArrayList.get(position);
            Intent i=new Intent(mContext, CommentsActivity.class);
            i.putExtra("",pastvisitBeanArrayList);
            mContext.startActivity(i);
            }


        }


    public PastvisitAdapter(Context mContext, ArrayList<PastvisitBean> pastvisitBeanArrayList) {
        this.pastvisitBeanArrayList=pastvisitBeanArrayList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pastvisit_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PastvisitBean pastvisitBean=pastvisitBeanArrayList.get(position);
        holder.ic_property.setImageDrawable(mContext.getResources().getDrawable(R.drawable.apartment));
        holder.tv_apartmentName.setText(pastvisitBean.getAppartment_name());
        holder.tv_plotNo.setText(pastvisitBean.getPlot_no());
        holder.tv_bhk.setText(pastvisitBean.getBhk());
        holder.visit_Loc.setText(pastvisitBean.getLandmark());
        holder.visit_date.setText(pastvisitBean.getDate());
        holder.visit_status.setText(pastvisitBean.getStatus());
        holder.tv_comment.setText(pastvisitBean.getComments());

    }

    @Override
    public int getItemCount() {
        return pastvisitBeanArrayList.size();
    }

}
