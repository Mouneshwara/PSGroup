package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import in.psgroup.psgroup.R;

public class PagerAdapter extends android.support.v4.view.PagerAdapter{

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] images ;

    public PagerAdapter(Context context, int[] images) {
        this.images =images;
        this.context = context;
    }

    public PagerAdapter() {
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pager_custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.pagerImageView);
        imageView.setImageResource(images[position]);
//        Picasso.get().load(images[position]).placeholder(R.drawable.thumbnail).into(imageView);


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
