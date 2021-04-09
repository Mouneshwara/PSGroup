package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.psgroup.psgroup.BedRoomActivity;
import in.psgroup.psgroup.Models.ProjectSpecificationTwoList;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.ProjectSpecification;

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.MyViewHolder> {

    ArrayList<ProjectSpecificationTwoList> specifications = new ArrayList<>();
    Context context;

    public SpecificationAdapter(ArrayList<ProjectSpecificationTwoList> projectSpecificationTwoListArrayList, Context context) {
        this.specifications = projectSpecificationTwoListArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SpecificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.specification_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecificationAdapter.MyViewHolder holder, int position) {
        final ProjectSpecificationTwoList specification = specifications.get(position);

        Picasso.get().
                load(specification.getDetailName()).placeholder(R.drawable.thumbnail)
                .into(holder.ic_specImage);
        holder.tv_specName.setText(specification.getDetailTitle());
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ic_specImage;
        TextView tv_specName;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ic_specImage = itemView.findViewById(R.id.ic_spec);
            tv_specName = itemView.findViewById(R.id.tv_specName);

        }

        @Override
        public void onClick(View v) {
            int pos=this.getAdapterPosition();
            ProjectSpecificationTwoList specification = specifications.get(pos);

            Intent i=new Intent(context, BedRoomActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("title",specification.getDetailTitle() );
            bundle.putString("description",specification.getDetailDesc() );
            bundle.putString("url",specification.getDetailName() );
            bundle.putString("note",specification.getDetailNote() );
            i.putExtras(bundle);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    @Override
    public int getItemCount() {
        return specifications.size();
    }

}
