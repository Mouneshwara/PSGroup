package in.psgroup.psgroup.FilterModule.Adappters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import in.psgroup.psgroup.FilterModule.Interfaces.FilterInterface;
import in.psgroup.psgroup.FilterModule.Models.PropertyType;
import in.psgroup.psgroup.R;

public class FilterPropertyTypeAdapter extends RecyclerView.Adapter<FilterPropertyTypeAdapter.MyViewHolder> {
    Context context;
    int prevPos = -1;
    private List<PropertyType> arrayList;
    private FilterInterface mcallback;
    public FilterPropertyTypeAdapter(List<PropertyType> arrayList, Context context,FilterInterface mcallback) {
        this.arrayList = arrayList;
        this.context = context;
        this.mcallback = mcallback;
    }

    @Override
    public FilterPropertyTypeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_filter_propertytype, parent, false);

        FlexboxLayoutManager.LayoutParams flexboxLp =
                (FlexboxLayoutManager.LayoutParams) itemView.getLayoutParams();

        flexboxLp.setFlexGrow(1.0f);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterPropertyTypeAdapter.MyViewHolder holder, int position) {
        final PropertyType propert_type = arrayList.get(position);
        holder.button.setText(propert_type.getType());
        if (propert_type.isSlected()) {
            holder.button.setTextColor(context.getResources().getColor(R.color.white));
            holder.button.setSelected(true);
        } else {
            holder.button.setTextColor(context.getResources().getColor(R.color.sbiDesc));
            holder.button.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button button;

        public MyViewHolder(View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this);
            button = (Button) itemView.findViewById(R.id.propertyType);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            switch (view.getId()) {
                case R.id.propertyType:
                    if (prevPos >= 0) {
                        arrayList.get(prevPos).setSlected(false);
                        notifyItemChanged(prevPos);
                    }
                    arrayList.get(pos).setSlected(true);
                    prevPos = pos;
                    notifyItemChanged(prevPos);
                    mcallback.setPropertyType(arrayList.get(pos));
                    break;
            }
        }
    }

}
