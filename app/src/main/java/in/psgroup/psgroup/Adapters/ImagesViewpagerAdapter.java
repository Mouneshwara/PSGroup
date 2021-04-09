package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.TouchImageView;

public class ImagesViewpagerAdapter extends PagerAdapter {
    Context mContext;
    String images[];
    int imagePos;
    LayoutInflater layoutInflater;

    public ImagesViewpagerAdapter(Context context, String[] images, int imagePos) {
        this.mContext = context;
        this.images = images;
        this.imagePos = imagePos;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {


        View itemView = layoutInflater.inflate(R.layout.single_zoom_image, container, false);
        TouchImageView imageView = (TouchImageView) itemView.findViewById(R.id.img);
        Picasso.get().load(images[i]).into(imageView);
//        Picasso.clearCache(Picasso.with(mContext));
       /* Picasso.with(mContext)
                .load(url+images[i])
                .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(imageView);*/
        container.addView(itemView);
//        imageView.setDrawingCacheEnabled(true);
//        this.bitmap=imageView.getDrawingCache();
        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        container.removeView((RelativeLayout) obj);
    }
}
