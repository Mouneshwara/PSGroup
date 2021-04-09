package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import in.psgroup.psgroup.R;

public class PropertyPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private String Images[]; //z= {R.drawable.apartment,R.drawable.apartment,R.drawable.apartment,R.drawable.apartment,R.drawable.apartment,R.drawable.apartment};

    public PropertyPagerAdapter(Context context, String[] images) {
        this.context = context;
        Images = images;
    }

    @Override
    public int getCount() {
        if(Images!=null) {
            return Images.length;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.ImageView);

        Picasso.get().load(Images[position]).placeholder(R.drawable.thumbnail).into(imageView);
//        imageView.setImageResource(Images[position]);

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
