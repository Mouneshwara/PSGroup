package in.psgroup.psgroup;

import android.app.Dialog;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;


import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import in.psgroup.psgroup.Fragments.HomeFragment;
import in.psgroup.psgroup.Models.UserSessionManager;
import in.psgroup.psgroup.NotificationsModule.NotificationsActivity;
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>, View.OnClickListener {
    Dialog dialog;
    View view;
    private DuoDrawerLayout duoDrawerLayout;
    private Toolbar mToolbar;
    private DuoDrawerToggle drawerToggle;
    private LinearLayout ll_properties,ll_happy_our,ll_profile;
    private RelativeLayout logout;
    private ImageView Headerimage, profile_pic,ic_notifica_unread,iv_notification;
    public static ImageView notificationDot;
    private TextView tvheader, user_name,tv_image_holder;
    private UserSessionManager session;
    private CircleImageView ic_image;
    private String AccessToken, RefreshToken, message,flag="";
    private int response_code = 0;
    private JSONObject responseJson;
    private in.psgroup.psgroup.Models.MyProperty myProperty;
    private String name,image;
    public static int notificationCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profile);
        session = new UserSessionManager(getApplicationContext());

        if(session.checkguest()){
            flag = "guest";
        }else
        {
            HashMap<String, String> user = session.getUserDetails();
            name= user.get(UserSessionManager.KEY_NAME);
            image= user.get(UserSessionManager.KEY_image);
        }
        initialze();
        initFragment();
        drawer();

    }


    private void initFragment() {
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment, "FRAGMENT").commit();

    }


    public void drawer() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerToggle = new DuoDrawerToggle(this, duoDrawerLayout, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerToggle.syncState();

    }

    private void initialze() {

        //Toolbar initializing
        Headerimage = (ImageView) findViewById(R.id.ic_groupicon);
//        ic_notifica_unread = (ImageView) findViewById(R.id.ic_notifica_unread);
        tvheader = (TextView) findViewById(R.id.headertext);
        duoDrawerLayout = (DuoDrawerLayout) findViewById(R.id.duoDrawerLayout);
        tv_image_holder= (TextView) findViewById(R.id.tv_image_holder);

        profile_pic = (ImageView) findViewById(R.id.ic_image);
        user_name = (TextView) findViewById(R.id.tv_name);

        ll_properties= (LinearLayout) findViewById(R.id.ll_properties);
        ll_happy_our= (LinearLayout) findViewById(R.id.happy_our);
        ll_profile= (LinearLayout) findViewById(R.id.ll_profile);
        iv_notification = (ImageView)findViewById(R.id.iv_notification);
        notificationDot = (ImageView)findViewById(R.id.notificationCount);
        ic_image = (CircleImageView)findViewById(R.id.ic_image);

        if(flag.equals("guest")){
            ll_happy_our.setVisibility(View.GONE);
            ll_properties.setVisibility(View.GONE);
            ic_notifica_unread.setVisibility(View.GONE);
            //ll_profile.setVisibility(View.GONE);

            tv_image_holder.setVisibility(View.VISIBLE);
            profile_pic.setVisibility(View.GONE);
            tv_image_holder.setText("G");
            user_name.setText("Guest");


        }
        else {
            user_name.setText(name);
            if(image==null||(!image.isEmpty())){
                Picasso.get()
                        .load(image)
                        .placeholder(R.drawable.thumbnail)
                        .resize(110,110)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE)
                        .into(ic_image);
            }
            else{
                tv_image_holder.setVisibility(View.VISIBLE);
                ic_image.setVisibility(View.GONE);
                /*String user_initial=String.valueOf(name.charAt(0));
                tv_image_holder.setText(user_initial);*/

            }
        }
      iv_notification.setOnClickListener(this);
    }

    public void humbergurCLick(View view) {
        switch (view.getId()) {
            case R.id.ll_profile:
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                break;
            case R.id.happy_our:
                startActivity(new Intent(HomeActivity.this, HappyOursActivity.class));
                break;
            /*case R.id.my_visits:
                startActivity(new Intent(HomeActivity.this,MyVisitActivity.class));
                break;*/
            case R.id.ll_properties:
                startActivity(new Intent(HomeActivity.this, MyProperties.class));
                break;
            case R.id.ll_help:
                startActivity(new Intent(HomeActivity.this, HelpActivity.class));
                break;
            case R.id.ll_projects:
                startActivity(new Intent(HomeActivity.this, ProjectsActivity.class));
                break;

            case R.id.rr_logout:
                session.logoutUser();
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                HomeActivity.this.finish();
                startActivity(i);
                break;
        }
    }


    @Override
    protected void onResume() {

        super.onResume();
        duoDrawerLayout.closeDrawer();

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

        } else
            super.onBackPressed();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.iv_notification:
               startActivity(new Intent(HomeActivity.this, NotificationsActivity.class));
               break;
       }
    }

    public static void showNotification(){
          if(notificationCount>0){
              notificationDot.setVisibility(View.VISIBLE);
          }else {
              notificationDot.setVisibility(View.GONE);
          }
    }
}
