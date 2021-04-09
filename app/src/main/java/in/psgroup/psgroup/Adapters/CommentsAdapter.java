package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.psgroup.psgroup.Models.AllReferenceBean;
import in.psgroup.psgroup.Models.CommentsBean;
import in.psgroup.psgroup.Models.TicketIdBean;
import in.psgroup.psgroup.R;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {

    ArrayList<CommentsBean> commentsBeans = new ArrayList<>();
    Context context;

    public CommentsAdapter(ArrayList<CommentsBean> commentList, Context context) {
        this.commentsBeans = commentList;
        this.context = context;
    }
    @Override
    public CommentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_comments, parent, false);

        return new CommentsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.MyViewHolder holder, int position) {
        final CommentsBean commentsBean = commentsBeans.get(position);
        holder.tv_r.setText(commentsBean.getName().substring(0,1));
        holder.tv_name.setText(commentsBean.getName());
        holder.tv_comment.setText(commentsBean.getComment());
        holder.tv_time.setText(commentsBean.getTime());
    }

    @Override
    public int getItemCount() {
        return commentsBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_r,tv_name, tv_comment, tv_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_r = (TextView) itemView.findViewById(R.id.tv_r);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_comment = (TextView) itemView.findViewById(R.id.tv_comment);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
