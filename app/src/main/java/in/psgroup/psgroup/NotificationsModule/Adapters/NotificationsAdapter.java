package in.psgroup.psgroup.NotificationsModule.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.CheckPointHistoryActivity;
import in.psgroup.psgroup.NotificationsModule.Interface.NotificationInterface;
import in.psgroup.psgroup.NotificationsModule.Models.NotificationsBean;
import in.psgroup.psgroup.ProjectsActivity;
import in.psgroup.psgroup.PropertyDetailsActivity;
import in.psgroup.psgroup.R;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolder> {

    ArrayList<NotificationsBean> notificationList = new ArrayList<>();
    Context context;
    private int lastPosition=-1;
    private NotificationInterface mcallBack;

    public NotificationsAdapter(ArrayList<NotificationsBean> notificationsBean, Context context,NotificationInterface mcallBack) {
        this.notificationList = notificationsBean;
        this.context = context;
        this.mcallBack = mcallBack;
    }

    @Override
    public NotificationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_card, parent, false);

        return new NotificationsAdapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.MyViewHolder holder, int position) {
        final NotificationsBean notificationsBean = notificationList.get(position);
        /*if(notificationsBean.getNotificationType().equalsIgnoreCase("Payment")){
            holder.ic_notify.setImageDrawable(context.getResources().getDrawable(R.drawable.notification_payment));
        }else if(notificationsBean.getNotificationType().equalsIgnoreCase("Visit")){
            holder.ic_notify.setImageDrawable(context.getResources().getDrawable(R.drawable.notification_visit));
        }else if(notificationsBean.getNotificationType().equalsIgnoreCase("Points")){
            holder.ic_notify.setImageDrawable(context.getResources().getDrawable(R.drawable.notification_points));
        }else if(notificationsBean.getNotificationType().equalsIgnoreCase("Projects")){
            holder.ic_notify.setImageDrawable(context.getResources().getDrawable(R.drawable.notification_project));
        }else if(notificationsBean.getNotificationType().equalsIgnoreCase("Milestone")){
            holder.ic_notify.setImageDrawable(context.getResources().getDrawable(R.drawable.notification_milestone));
        }*/
        holder.ic_notify.setImageDrawable(context.getResources().getDrawable(R.drawable.notification_payment));
        holder.tv_notifymessage.setText(Html.fromHtml("By <b>"+notificationsBean.getNotificationDesc()));
        holder.notification_time.setText(notificationsBean.getDuration());
        /*setAnimation(holder.itemView, position);*/
        if (notificationsBean.getReadStatus().equalsIgnoreCase("yes")){
            holder.lLayout.setBackgroundColor(context.getResources().getColor(R.color.orange));
        }else {
            holder.lLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

    }

    /*private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }*/

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_notifymessage, notification_time;
        ImageView ic_notify;
        LinearLayout lLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ic_notify = (ImageView)itemView.findViewById(R.id.ic_notify);
            tv_notifymessage = (TextView) itemView.findViewById(R.id.tv_notifymessage);
            notification_time = (TextView) itemView.findViewById(R.id.notification_time);
            lLayout = (LinearLayout)itemView.findViewById(R.id.lLayout);
            lLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            NotificationsBean notificationsBean = notificationList.get(position);
            Intent i;
            switch (view.getId()){
                case R.id.lLayout:
                    if(notificationList.get(position).getReadStatus().equalsIgnoreCase("no")) {
                        notificationList.get(position).setReadStatus("yes");
                        notifyItemChanged(position);
                        mcallBack.CallLoader(notificationsBean);
                    }
                    if(notificationsBean.getNotificationType().equalsIgnoreCase("project")){
                        i = new Intent(context, ProjectsActivity.class);
                        context.startActivity(i);
                    }else if(notificationsBean.getNotificationType().equalsIgnoreCase("points_added")){
                        i = new Intent(context, CheckPointHistoryActivity.class);
                        context.startActivity(i);
                    }
                    break;
            }
        }
    }
}
