package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.ExplanationOfClauseActivity;
import in.psgroup.psgroup.Models.GeneralEnquiryBean;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.WriteToUsActivity;

public class GeneralEnquiryAdapter extends RecyclerView.Adapter<GeneralEnquiryAdapter.MyViewHolder> {
    ArrayList<GeneralEnquiryBean> generalList = new ArrayList<>();
    Context context;

    public GeneralEnquiryAdapter(ArrayList<GeneralEnquiryBean> generalEnquiryList, Context context) {
        this.generalList = generalEnquiryList;
        this.context = context;
    }
    @Override
    public GeneralEnquiryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_general_enquiry, parent, false);

        return new GeneralEnquiryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralEnquiryAdapter.MyViewHolder holder, int position) {
        final GeneralEnquiryBean allReferenceBean = generalList.get(position);
        holder.tv_question.setText(allReferenceBean.getQuestion());
    }

    @Override
    public int getItemCount() {
        return generalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_question;
        LinearLayout lLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_question = (TextView) itemView.findViewById(R.id.tv_question);
            lLayout = (LinearLayout)itemView.findViewById(R.id.lLayout);

        }

        @Override
        public void onClick(View view) {

                int position=this.getAdapterPosition();
                GeneralEnquiryBean generalEnquiryList =generalList.get(position);
                if(generalEnquiryList.getAnswer()==null)
                {
                   /* Intent i=new Intent(context, WriteToUsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);*/
                }else
                {
                    Intent i=new Intent(context, ExplanationOfClauseActivity.class);
                    i.putExtra("question",generalEnquiryList.getQuestion());
                    i.putExtra("answer",generalEnquiryList.getAnswer());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);

                }


        }
    }
}
