package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.NotificationBean;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    ArrayList<NotificationBean> notificationBeanArrayList=new ArrayList<>();
    Context context;

    public NotificationAdapter(ArrayList<NotificationBean> notificationBeanArrayList, Context context) {
        this.notificationBeanArrayList = notificationBeanArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NotificationBean notificationBean=notificationBeanArrayList.get(position);
       holder.notify_icon.setImageResource(R.drawable.notify_project);
       holder.notification.setText(notificationBean.getNotification_desc());
       holder.time.setText(notificationBean.getNotification_time());
    }

    @Override
    public int getItemCount() {
        return notificationBeanArrayList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView notify_icon;
        TextView notification,time;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            notify_icon=(ImageView)itemView.findViewById(R.id.ic_notify);
            notification=(TextView)itemView.findViewById(R.id.tv_notifymessage);
            time=(TextView)itemView.findViewById(R.id.notification_time);

        }


        @Override
        public void onClick(View v) {

            int pos=this.getAdapterPosition();
//            NotificationBean notificationBean=NotificationBean.get(pos);
//            Intent i=new Intent(context, .class);
//            i.putExtra("apartment_parcel",notificationBean);
//            context.startActivity(i);
        }
    }
}
