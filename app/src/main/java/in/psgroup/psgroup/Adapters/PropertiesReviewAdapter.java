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

import in.psgroup.psgroup.R;


public class PropertiesReviewAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    TextView message,name,tv_place;
    ImageView header;

    private String  headers[];
    private String  messages[];
    private String  names[];
    private String place[];

    public PropertiesReviewAdapter(Context context, String  messages[], String names[], String place[]) {
        this.context = context;
        this.messages = messages;
        this.names = names;
        this.place = place;
    }


    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.review_card, null);
        header=(ImageView)view.findViewById(R.id.headder);
        message=(TextView)view.findViewById(R.id.message);
        name = (TextView)view.findViewById(R.id.name);
        tv_place = (TextView)view.findViewById(R.id.tv_place);

        message.setText(messages[position]);
        name.setText(names[position]);
        tv_place.setText(place[position]);

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
