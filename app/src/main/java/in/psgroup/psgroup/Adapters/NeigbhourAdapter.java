package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.NeigbhourBean;

public class NeigbhourAdapter extends RecyclerView.Adapter<NeigbhourAdapter.MyViewHolder> {

    ArrayList<NeigbhourBean> neigbhourBeanArrayList=new ArrayList<>();
    Context context;


    public NeigbhourAdapter(ArrayList<NeigbhourBean> neigbhourBeanArrayList, Context context) {
        this.neigbhourBeanArrayList = neigbhourBeanArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public NeigbhourAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.neighbours_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NeigbhourAdapter.MyViewHolder holder, int position) {

        NeigbhourBean neigbhourBean = neigbhourBeanArrayList.get(position);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inputdate=neigbhourBean.getEvent_date();
        Date date = null;
        try {
            date = inputFormat.parse(inputdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");
        String month=simpleDateFormat.format(date).toUpperCase();
        String day=new SimpleDateFormat("dd").format(date).toUpperCase();
        holder.tv_title.setText(neigbhourBean.getEvent_title());
        holder.tv_event.setText(neigbhourBean.getEvent_short_note());
        holder.tv_date.setText(day);
        holder.tv_month.setText(month);

        holder.tv_landmark.setText(neigbhourBean.getEvent_location());
        Picasso.get().
                load(neigbhourBean.getEvent_image()).
                placeholder(R.drawable.thumbnail).
                into(holder.pics);
    }

    @Override
    public int getItemCount() {
        return neigbhourBeanArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView pics,loc_icon;
        TextView tv_title,tv_event,tv_date,tv_month,tv_landmark;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_event = (TextView) itemView.findViewById(R.id.tv_events);
            tv_date = (TextView) itemView.findViewById(R.id.date_each);
            tv_month =  (TextView) itemView.findViewById(R.id.month);
            tv_landmark = (TextView) itemView.findViewById(R.id.event_landmark);

            pics = (ImageView) itemView.findViewById(R.id.iv_image);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
