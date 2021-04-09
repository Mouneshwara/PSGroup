package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.psgroup.psgroup.Models.ProjectWiseBean;
import in.psgroup.psgroup.Models.ReferralHistoryBean;
import in.psgroup.psgroup.R;

public class ProjectWiseAdapter extends RecyclerView.Adapter<ProjectWiseAdapter.MyViewHolder> {
    HashMap<String, List<ReferralHistoryBean> >projectname;
    Context context;
    Object[] array;

    public ProjectWiseAdapter(HashMap<String, List<ReferralHistoryBean> >projectname, Context context) {
        this.projectname = projectname;
        this.context = context;
    }
    @Override
    public ProjectWiseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_project_wise_reference, parent, false);

        array= projectname.keySet().toArray();
        return new ProjectWiseAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectWiseAdapter.MyViewHolder holder, int position) {




        GridLayoutManager layoutManager=new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.tv_arham.setText(array[position].toString());
        ArrayList<ReferralHistoryBean> referralHistoryBeanArrayList1 = new ArrayList<>();
        referralHistoryBeanArrayList1= (ArrayList<ReferralHistoryBean>) projectname.get(array[position].toString());
        holder.tv_number.setText(" ("+String.valueOf(referralHistoryBeanArrayList1.size())+") ");
       AllReferenceAdapter adapter = new AllReferenceAdapter(referralHistoryBeanArrayList1,context);
       holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        holder.rlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( holder.iv_down.getVisibility()==View.VISIBLE) {
                    holder.iv_down.setVisibility(View.GONE);
                    holder.iv_up.setVisibility(View.VISIBLE);
                    holder.recyclerView.setVisibility(View.VISIBLE);

                }else{
                    holder.iv_up.setVisibility(View.GONE);
                    holder.recyclerView.setVisibility(View.GONE);
                    holder.iv_down.setVisibility(View.VISIBLE);


                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return projectname.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView tv_arham, tv_number;
        ImageView iv_down,iv_up;
        RecyclerView recyclerView;
        CardView all_ref_card;
        RelativeLayout rlLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_arham = (TextView) itemView.findViewById(R.id.tv_arham);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            iv_down = (ImageView)itemView.findViewById(R.id.iv_down);
            recyclerView = (RecyclerView)itemView.findViewById(R.id.recycler);
            iv_up = (ImageView)itemView.findViewById(R.id.iv_up);
            all_ref_card = (CardView)itemView.findViewById(R.id.all_ref_card);
            rlLayout= (RelativeLayout)itemView.findViewById(R.id.rlLayout);

        }

    }
}
