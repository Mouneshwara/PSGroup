package in.psgroup.psgroup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.psgroup.psgroup.LedgerActivity;
import in.psgroup.psgroup.Models.MyProperty;
import in.psgroup.psgroup.PaymentActivity;
import in.psgroup.psgroup.PropertyDetailsActivity;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.PropertyBean;

public class CardViewPagerAdapter extends PagerAdapter {

    ArrayList<MyProperty> topPropertyBeanArrayList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;


    public CardViewPagerAdapter(ArrayList<MyProperty> topPropertyBeanArrayList, Context context) {
        this.topPropertyBeanArrayList = topPropertyBeanArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return topPropertyBeanArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;



    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        ImageView ic_project,iv_end_point;
        TextView tv_projectName,tv_type,tv_projectLandmark,rupee;
        RelativeLayout card_project;
        final MyProperty myPropertyBean=topPropertyBeanArrayList.get(position);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.my_projects_card, null);


        ic_project=view.findViewById(R.id.ic_project);
        tv_projectName = (TextView)view.findViewById(R.id.tv_projectName);
        tv_type = (TextView)view.findViewById(R.id.tv_type);
        tv_projectLandmark = (TextView)view.findViewById(R.id.tv_projectLandmark);
        rupee = (TextView)view.findViewById(R.id.rupee);

        card_project= (RelativeLayout) view.findViewById(R.id.card_project);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        Picasso.get().load(myPropertyBean.getBanner_image()).into(ic_project);
        tv_projectName.setText(myPropertyBean.getProject_title());
        tv_type.setText(myPropertyBean.getApartment_no());
        tv_projectLandmark.setText(myPropertyBean.getAddress());
       /* rupee.setText("₹ "+myPropertyBean.getPurchase_cost());*/


       /* iv_end_point=view.findViewById(R.id.end_point);
        TextView tv_title = view.findViewById(R.id.advTitle);
        TextView tv_milestone=view.findViewById(R.id.milestone_status);
        TextView milestone_name=view.findViewById(R.id.milestone_name);
        milestone_name.setText(myPropertyBean.getMilestoneName());
        SeekBar seekBar=view.findViewById(R.id.seekbarpager);
        seekBar.setMax(myPropertyBean.getMax_milestone());
        seekBar.setProgress(myPropertyBean.getCurrent_milestone());
        tv_milestone.setText(String.valueOf(seekBar.getProgress())+"/"+String.valueOf(myPropertyBean.getMax_milestone()));
        if (seekBar.getProgress()==myPropertyBean.getMax_milestone() && myPropertyBean.getMax_milestone()!=0){
            iv_end_point.setVisibility(View.GONE);
        }else{
            iv_end_point.setVisibility(View.VISIBLE);
        }
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });*/

        /*card_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProperty myProperty=topPropertyBeanArrayList.get(position);
                Intent i=new Intent(context, LedgerActivity.class);
                i.putExtra("my_property",myProperty);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });*/

        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

}

