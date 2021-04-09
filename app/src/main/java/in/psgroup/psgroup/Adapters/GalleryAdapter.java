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
import in.psgroup.psgroup.GalleryViewActivity;
import in.psgroup.psgroup.Models.Gallery;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.ProjectSpecification;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    ArrayList<Gallery> gallery = new ArrayList<Gallery>();
    Context context;

    public GalleryAdapter(ArrayList<Gallery> gallery, Context context) {
        this.gallery = gallery;
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.MyViewHolder holder, int position) {
        final Gallery gallerybean = gallery.get(position);

        Picasso.get().
                load(gallerybean.getImage()).placeholder(R.drawable.thumbnail)
                .into(holder.ic_specImage);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ic_specImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ic_specImage = itemView.findViewById(R.id.ic_spec);


        }

        @Override
        public void onClick(View v) {
            int pos=this.getAdapterPosition();
           // Gallery gallerybean = gallery.get(pos);

         //   i.putExtra("room_parcel",gallerybean);

            String a= "";
            for(int k=gallery.size()-1;k>=0;k--) {


                Gallery gallerybean = gallery.get(k);
                a = gallerybean.getImage()+","+ a;



            }


            String[] array= a.split(",");
            Intent i=new Intent(context, GalleryViewActivity.class);
            i.putExtra("Images", array);
            i.putExtra("position", pos);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);



        }
    }

    @Override
    public int getItemCount() {
        return gallery.size();
    }

}
