package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
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
import in.psgroup.psgroup.Models.Gallery;
import in.psgroup.psgroup.Models.Highlight;
import in.psgroup.psgroup.R;

/**
 * Created by Codebele on 13-Feb-19.
 */
public class HighlightsAdapter extends RecyclerView.Adapter<HighlightsAdapter.MyViewHolder> {

    ArrayList<Highlight> highlightArrayList = new ArrayList<>();
    Context context;

    public HighlightsAdapter(ArrayList<Highlight> highlightArrayList, Context context) {
        this.highlightArrayList = highlightArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HighlightsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.highlights_card, parent, false);

        return new HighlightsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightsAdapter.MyViewHolder holder, int position) {
        final Highlight highlight = highlightArrayList.get(position);

        holder.tv_title.setText(highlight.getName());
        holder.tv_description.setText(highlight.getAmenities());



    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_title,tv_description;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tv_description = itemView.findViewById(R.id.tv_description);
            tv_title = itemView.findViewById(R.id.tv_title);


        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public int getItemCount() {
        return highlightArrayList.size();
    }

}
