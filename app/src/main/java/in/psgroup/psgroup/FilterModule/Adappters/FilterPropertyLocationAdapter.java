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
import in.psgroup.psgroup.FilterModule.Models.Location;
import in.psgroup.psgroup.R;

public class FilterPropertyLocationAdapter extends RecyclerView.Adapter<FilterPropertyLocationAdapter.MyViewHolder> {
    Context context;
    int prevPos = -1;
    private List<Location> arrayList;
    private FilterInterface mcallback;
    public FilterPropertyLocationAdapter(List<Location> arrayList, Context context,FilterInterface mcallback) {
        this.arrayList = arrayList;
        this.context = context;
        this.mcallback = mcallback;
    }

    @Override
    public FilterPropertyLocationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_filter_propertytype, parent, false);

        FlexboxLayoutManager.LayoutParams flexboxLp =
                (FlexboxLayoutManager.LayoutParams) itemView.getLayoutParams();

        flexboxLp.setFlexGrow(1.0f);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterPropertyLocationAdapter.MyViewHolder holder, int position) {
        final Location location = arrayList.get(position);
        holder.button.setText(location.getLocationName());
        if (location.isSelected()) {
            holder.button.setTextColor(context.getResources().getColor(R.color.white));
            holder.button.setSelected(true);
        } else {
            //sbiDesc
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
                        arrayList.get(prevPos).setSelected(false);
                        notifyItemChanged(prevPos);
                    }
                    arrayList.get(pos).setSelected(true);
                    prevPos = pos;
                    mcallback.setPropertyLocation(arrayList.get(pos));
                    notifyItemChanged(prevPos);
                    break;
            }
        }
    }

}
