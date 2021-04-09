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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.psgroup.psgroup.Models.GeneralEnquiryBean;
import in.psgroup.psgroup.ProjectsActivity;
import in.psgroup.psgroup.PropertyDetailsActivity;
import in.psgroup.psgroup.PsGroupApplication;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.ProjectBean;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder> implements Filterable {
    public static List<ProjectBean> mFilteredList = new ArrayList<>();
    ArrayList<ProjectBean> projectBeanArrayList=new ArrayList<>();
    ProjectsActivity projectsActivity;

    Context context;


    public ProjectAdapter(ArrayList<ProjectBean> projectBeanArrayList, Context context) {
        this.projectBeanArrayList = projectBeanArrayList;
        this.context = context;
        this.mFilteredList =projectBeanArrayList ;
    }

    @NonNull
    @Override
    public ProjectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.projects_card, parent, false);

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
            tv_rupee = itemView.findViewById(R.id.rupee);
            /*tv_toBeEarned = itemView.findViewById(R.id.tv_toBeEarned);*/
        }

        @Override
        public void onClick(View v) {

            int pos=this.getAdapterPosition();
            ProjectBean projectBean = mFilteredList.get(pos);
//            ProjectBean propertyBean=projectBeanArrayList.get(pos);
            Intent i=new Intent(context, PropertyDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("projectName",projectBean.getPost_title());
            bundle.putString("projectid",projectBean.getSk_post_id());
            i.setFlags(FLAG_ACTIVITY_NEW_TASK);
            i.putExtras(bundle);
            context.startActivity(i);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.MyViewHolder holder, int position) {


        ProjectBean projectBean = mFilteredList.get(position);
        holder.ic_project.setImageDrawable(context.getResources().getDrawable(R.drawable.apartment));
        holder.tv_projectName.setText(projectBean.getPost_title());
        holder.tv_type.setText(projectBean.getPost_content());
        String address =projectBean.getAddress()+","+projectBean.getCity_name();
        holder.tv_landMark.setText(address);
       /* holder.tv_toBeEarned.setText(projectBean.getPointstobe_earned());*/
        holder.tv_rupee.setText(projectBean.getStarting_price());
        Picasso.get().
                load(projectBean.getBannerImage()).placeholder(R.drawable.thumbnail)
                .into(holder.ic_project);
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
                } else {  List<ProjectBean> filteredList = new ArrayList<>();



                    for (ProjectBean item : projectBeanArrayList) {

                        if (item.getPost_title().toLowerCase().contains(charString)) {

                            filteredList.add(item);

                            if (item.getPost_title().toLowerCase().contains(charString)) {

                                filteredList.add(item);
                            }

                            if (item.getCity_name().toLowerCase().contains(charString)) {

                                filteredList.add(item);

                            }
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
                mFilteredList = (ArrayList<ProjectBean>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
