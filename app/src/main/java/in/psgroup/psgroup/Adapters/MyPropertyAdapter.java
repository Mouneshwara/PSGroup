package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.psgroup.psgroup.LedgerActivity;
import in.psgroup.psgroup.Models.GeneralEnquiryBean;
import in.psgroup.psgroup.Models.MyProperty;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.ProjectBean;

public class MyPropertyAdapter extends RecyclerView.Adapter<MyPropertyAdapter.MyViewHolder> implements Filterable {
    public static List<MyProperty> mFilteredList = new ArrayList<>();
    ArrayList<MyProperty> projectBeanArrayList=new ArrayList<>();

    Context context;


    public MyPropertyAdapter(ArrayList<MyProperty> projectBeanArrayList, Context context) {
        this.projectBeanArrayList = projectBeanArrayList;
        this.context = context;
        this.mFilteredList =projectBeanArrayList ;
    }

    @NonNull
    @Override
    public MyPropertyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_projects_card, parent, false);

        return new MyViewHolder(itemView);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ic_project,ic_location;
        TextView tv_projectName,tv_type,tv_landMark,tv_rupee,tv_toBeEarned;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ic_project = itemView.findViewById(R.id.ic_project);

            tv_projectName = itemView.findViewById(R.id.tv_projectName);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_landMark = itemView.findViewById(R.id.tv_projectLandmark);
          //  tv_rupee = itemView.findViewById(R.id.rupee);
            /*tv_toBeEarned = itemView.findViewById(R.id.tv_toBeEarned);*/
        }

        @Override
        public void onClick(View v) {

            /*int pos=this.getAdapterPosition();
            MyProperty myProperty=projectBeanArrayList.get(pos);
          Intent i=new Intent(context, LedgerActivity.class);
           i.putExtra("my_property",myProperty);
            context.startActivity(i);*/
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyPropertyAdapter.MyViewHolder holder, int position) {


        MyProperty myProperty = mFilteredList.get(position);

        Picasso.get().load(myProperty.getBanner_image()).into(holder.ic_project);
        holder.tv_projectName.setText(myProperty.getProject_title());
        holder.tv_type.setText(myProperty.getApartment_no());
        holder.tv_landMark.setText(myProperty.getAddress());
//        holder.tv_rupee.setText("₹ " +myProperty.getPurchase_cost());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();

                if (charString.isEmpty()) {

                    mFilteredList = projectBeanArrayList;
                } else {

                    List<MyProperty> filteredList = new ArrayList<>();

                    for (MyProperty item : projectBeanArrayList) {

                        if (item.getProject_title().toLowerCase().contains(charString)) {

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
                mFilteredList = (ArrayList<MyProperty>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
