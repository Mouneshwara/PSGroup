package in.psgroup.psgroup.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import in.psgroup.psgroup.DeleteImageActivity;
import in.psgroup.psgroup.ExplanationOfClauseActivity;
import in.psgroup.psgroup.Models.Bitmap_Image;
import in.psgroup.psgroup.Models.GeneralEnquiryBean;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.WriteToUsActivity;

import static in.psgroup.psgroup.Utility.Utils.STRING_REQUESTCODE;


/**
 * Created by CodeBele-PC on 31-08-2017.
 */

public class CapturedImage_Adapter extends RecyclerView.Adapter<CapturedImage_Adapter.MyViewHolder> {

    public List<Bitmap_Image> bitmap_imageList = new ArrayList<>();
    public List<Bitmap_Image> selected_subitemsList = new ArrayList<>();
    Bitmap image_send;

    Context mContext;

    public CapturedImage_Adapter(Context context, List<Bitmap_Image> subitemsList, List<Bitmap_Image> selectedList) {
        this.bitmap_imageList = subitemsList;
        this.selected_subitemsList = selectedList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_image_singlecard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bitmap_Image si = bitmap_imageList.get(position);
        holder.image.setImageBitmap(si.getBitmap());
        //selected_itemsList.contains(mFilteredList.get(position))
    }

    @Override
    public int getItemCount() {
        return bitmap_imageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout ll_listitem;
        public ImageView image, remove_photo;

        public MyViewHolder(View view) {
            super(view);
//            view.setOnClickListener(this);
            ll_listitem=(LinearLayout)  view.findViewById(R.id.ll_listitem);
            image = (ImageView) view.findViewById(R.id.img);
            remove_photo = (ImageView) view.findViewById(R.id.remove);
            remove_photo.setOnClickListener(this);
            ll_listitem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = this.getAdapterPosition();
            switch (view.getId()) {
                case R.id.remove:
                    Bitmap_Image image = bitmap_imageList.get(pos);
                    bitmap_imageList.remove(image);
                    notifyItemRemoved(pos);
                    break;
                case R.id.ll_listitem:
                    Bitmap_Image subitemsList =bitmap_imageList.get(pos);
                    Intent i = new Intent(mContext, DeleteImageActivity.class);
                    image_send = subitemsList.getBitmap();

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image_send.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    Activity origin = (Activity)mContext;
                    i.putExtra("position",pos);
                    i.putExtra("picture",byteArray);
                    origin.startActivityForResult(i,STRING_REQUESTCODE);



                    break;
            }
        }
    }





}
