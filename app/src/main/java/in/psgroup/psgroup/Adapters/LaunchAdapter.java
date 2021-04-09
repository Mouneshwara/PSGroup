package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.psgroup.psgroup.Models.BlogBean;
import in.psgroup.psgroup.Models.LaunchBean;
import in.psgroup.psgroup.R;

public class LaunchAdapter extends PagerAdapter {
    ArrayList<LaunchBean> launchBeanArrayList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    TextView header,message;

//    private Integer [] images = {R.drawable.apartment, R.drawable.apartment,R.drawable.apartment};
//    private String [] headers;
//    private String [] messages;

    public LaunchAdapter( ArrayList<LaunchBean> launchBeanArrayList,Context context) {
        this.context = context;
        this.launchBeanArrayList = launchBeanArrayList;

    }

    @Override
    public int getCount() {
        return launchBeanArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
final LaunchBean launchBean=launchBeanArrayList.get(position);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_home_launch_card, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.launch_image);
        header=(TextView)view.findViewById(R.id.tv_tittle);
        message=(TextView)view.findViewById(R.id.tv_description);

        message.setText(launchBean.getLaunch_description());
        header.setText(launchBean.getLaunch_name());
//        imageView.setImageResource(images[position]);
        Picasso.get().
                load(launchBean.getImage()).placeholder(R.drawable.thumbnail)
                .into(imageView);


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

