package in.psgroup.psgroup.Adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.psgroup.psgroup.Models.BlogBean;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.WebViewActivity;

public class HomeBlogerAdapter extends PagerAdapter {

    ArrayList<BlogBean> blogBeanArrayList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public HomeBlogerAdapter(ArrayList<BlogBean> blogBeanArrayList, Context context) {
        this.blogBeanArrayList = blogBeanArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return blogBeanArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        final BlogBean blogBean = blogBeanArrayList.get(position);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.layout_home_blog_card, null);

        ImageView iv_blog_image;
        TextView tv_blog_title,tv_blog_by,tv_blog_desc,blog_read_more;

        tv_blog_title = (TextView)view.findViewById(R.id.tv_blog_title);
        tv_blog_by = (TextView) view.findViewById(R.id.tv_blog_by);
        tv_blog_desc = (TextView) view.findViewById(R.id.tv_blog_desc);
        blog_read_more =  (TextView) view.findViewById(R.id.blog_read_more);
        iv_blog_image = (ImageView) view.findViewById(R.id.iv_blog_image);

//        ObjectAnimator animation = ObjectAnimator.ofInt(tv_blog_desc, "maxLines", 3);
//        animation.setDuration(0).start();


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);





        tv_blog_title.setText(blogBean.getPost_title());
        tv_blog_by.setText(Html.fromHtml("By <b>"+blogBean.getPost_author()+"</b> "+blogBean.getPost_date()));
        tv_blog_desc.setText(blogBean.getPost_content());
        Picasso.get().
                load(blogBean.getPost_file_link()).placeholder(R.drawable.thumbnail)
                .into(iv_blog_image);

        blog_read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, WebViewActivity.class);
                i.putExtra("title",blogBean.getPost_title());
                i.putExtra("url", blogBean.getBlogUrl());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }


    /*@NonNull
    @Override
    public HomeBlogerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_home_blog_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeBlogerAdapter.MyViewHolder holder, int position) {

        BlogBean blogBean = blogBeanArrayList.get(position);
        holder.tv_blog_title.setText(blogBean.getPost_title());
        holder.tv_blog_by.setText(Html.fromHtml("By <b>"+blogBean.getPost_author()+"</b> "+blogBean.getPost_date()));
        holder.tv_blog_desc.setText(blogBean.getPost_content());
        Picasso.get().
                load(blogBean.getPost_file_link()).placeholder(R.drawable.thumbnail)
                .into(holder.iv_blog_image);




    }*/

   /* @Override
    public int getItemCount() {
        return blogBeanArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView iv_blog_image;
        TextView tv_blog_title,tv_blog_by,tv_blog_desc,blog_read_more;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_blog_title = (TextView) itemView.findViewById(R.id.tv_blog_title);
            tv_blog_by = (TextView) itemView.findViewById(R.id.tv_blog_by);
            tv_blog_desc = (TextView) itemView.findViewById(R.id.tv_blog_desc);
            blog_read_more =  (TextView) itemView.findViewById(R.id.blog_read_more);
            iv_blog_image = (ImageView) itemView.findViewById(R.id.iv_blog_image);
            blog_read_more.setOnClickListener(this);
          *//*  tv_blog_desc.setMaxLines(3);*//*

           *//* tv_blog_desc.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if(expandable) {
                        expandable = false;

                        if (tv_blog_desc.getLineCount() > 7) {
                            blog_read_more.setVisibility(View.VISIBLE);
                            ObjectAnimator animation = ObjectAnimator.ofInt(tv_blog_desc, "maxLines", 7);
                            animation.setDuration(0).start();
                        }
                    }



                }
            });
*//*


        }*/


}
