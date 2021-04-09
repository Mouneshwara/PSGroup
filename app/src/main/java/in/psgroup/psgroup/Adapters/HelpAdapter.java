package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.psgroup.psgroup.ExplanationOfClauseActivity;
import in.psgroup.psgroup.GeneralEnquiryActivity;
import in.psgroup.psgroup.Models.GeneralEnquiryBean;
import in.psgroup.psgroup.Models.HelpBean;
import in.psgroup.psgroup.R;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.MyViewHolder> implements Filterable {
    public static List<HelpBean> mFilteredList = new ArrayList<>();
    ArrayList<HelpBean> generalList = new ArrayList<>();
    Context context;


    public HelpAdapter(ArrayList<HelpBean> generalEnquiryList, Context context) {
        this.generalList = generalEnquiryList;
        this.context = context;
        this.mFilteredList =generalEnquiryList ;
    }
    @Override
    public HelpAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_help, parent, false);

        return new HelpAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpAdapter.MyViewHolder holder, int position) {
        // GeneralEnquiryBean allReferenceBean = generalList.get(position);
        HelpBean allReferenceBean = mFilteredList.get(position);
        holder.tv_help.setText(allReferenceBean.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
        //generalList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_help;
        LinearLayout lLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_help = (TextView) itemView.findViewById(R.id.tv_help);
            lLayout = (LinearLayout)itemView.findViewById(R.id.lLayout);

        }

        @Override
        public void onClick(View view) {

            int position=this.getAdapterPosition();
            HelpBean helpBean =generalList.get(position);
            Intent i=new Intent(context, GeneralEnquiryActivity.class);
            i.putExtra("help",helpBean.getCategoryId());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);


        }


    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();

                if (charString.isEmpty()) {

                    mFilteredList = generalList;
                } else {

                    List<HelpBean> filteredList = new ArrayList<>();

                    for (HelpBean item : generalList) {

                        if (item.getCategoryName().toLowerCase().contains(charString)) {

                            filteredList.add(item);
                        }
                    }

                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredList = (ArrayList<HelpBean>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
