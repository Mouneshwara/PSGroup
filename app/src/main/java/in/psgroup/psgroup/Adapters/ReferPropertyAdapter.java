package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.psgroup.psgroup.Interfaces.ReferPropertyInterface;
import in.psgroup.psgroup.Models.CheckPointHistoryBean;
import in.psgroup.psgroup.Models.ProjectBean;
import in.psgroup.psgroup.Models.ReferPropertyBean;
import in.psgroup.psgroup.R;

public class ReferPropertyAdapter extends RecyclerView.Adapter<ReferPropertyAdapter.MyViewHolder> implements ReferPropertyInterface {

    private ArrayList<ProjectBean> referlist = new ArrayList<>();
    Context context;
    boolean isChecked =false;

    public ReferPropertyAdapter( ArrayList<ProjectBean> projectList, Context context) {
        this.referlist = projectList;
        this.context = context;
    }
    @Override
    public ReferPropertyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_refer_property, parent, false);

        return new ReferPropertyAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferPropertyAdapter.MyViewHolder holder, int position) {
        final ProjectBean referPropertyBean = referlist.get(position);
        holder.iv_img.setImageDrawable(context.getResources().getDrawable(R.drawable.apartment));
        holder.tv_propertyName.setText(referPropertyBean.getPost_title());
        holder.tv_type.setText(referPropertyBean.getPost_content());
        holder.tv_landmark.setText(referPropertyBean.getAddress()+","+referPropertyBean.getCity_name());
        Picasso.get().
                load(referPropertyBean.getBannerImage()).placeholder(R.drawable.thumbnail)
                .into(holder.iv_img);
        /*holder.tv_pointsEarned.setText(referPropertyBean.getPoints_to_earned());
        holder.tv_hiraNo.setText(referPropertyBean.getHira_no());
        holder.tv_webSite.setText(referPropertyBean.getWebsite());*/
        holder.tv_rupee.setText(referPropertyBean.getStarting_price());
        if(referPropertyBean.isChecked()){
            holder.cb_checkbox.setChecked(true);
            isChecked =true;
        }else {
            holder.cb_checkbox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return referlist.size();
    }

    @Override
    public boolean selectProperty() {
     return isChecked;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_propertyName, tv_type, tv_landmark, tv_pointsEarned, tv_hiraNo,tv_webSite,tv_rupee;
        ImageView iv_img;
        CheckBox cb_checkbox;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView)itemView.findViewById(R.id.iv_img);
            tv_propertyName = (TextView) itemView.findViewById(R.id.tv_propertyName);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_landmark = (TextView) itemView.findViewById(R.id.tv_landmark);
           /* tv_pointsEarned = (TextView) itemView.findViewById(R.id.tv_pointsEarned);
            tv_hiraNo = (TextView) itemView.findViewById(R.id.tv_hiraNo);
            tv_webSite = (TextView)itemView.findViewById(R.id.tv_webSite);*/
            tv_rupee = (TextView)itemView.findViewById(R.id.tv_rupee);
            cb_checkbox = (CheckBox) itemView.findViewById(R.id.cb_checkbox);
            itemView.setOnClickListener(this);
            cb_checkbox.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            uncheckAll();
            referlist.get(pos).setChecked(true);
            notifyItemChanged(pos);

            switch (view.getId()) {
                case R.id.cb_checkbox:
                uncheckAll();
                referlist.get(pos).setChecked(true);
                notifyItemChanged(pos);

                break;
            }
        }


    }

    private void uncheckAll(){
        for (ProjectBean ref: referlist
             ) {
            if(ref.isChecked()){
                referlist.get(referlist.indexOf(ref)).setChecked(false);
                notifyItemChanged(referlist.indexOf(ref));
            }
        }
    }
}
