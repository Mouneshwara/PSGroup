package in.psgroup.psgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import in.psgroup.psgroup.Models.ProjectSpecification;

public class BedRoomActivity extends AppCompatActivity {
//oncreate


    private ProjectSpecification specification;
    private ImageView ic_back,room_image;
    private TextView tv_title,tv_desc;
    private String title,description,url,note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_room);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            //specification =extras.getParcelable("room_parcel");
             title = extras.getString("title");
            description = extras.getString("description");
            url = extras.getString("url");
            note = extras.getString("note");

        }
        initView();
        initValues();

    }

    private void initValues() {
        tv_title.setText(title);
        Picasso.get().load(url).into(room_image);
        tv_desc.setText(Html.fromHtml(description));

    }

    private void initView() {
        room_image =(ImageView) findViewById(R.id.room_image);
        tv_title = (TextView) findViewById(R.id.tv_titleRoom);
        tv_desc =(TextView) findViewById(R.id.tv_desc);
        ic_back = (ImageView)findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void onBackPressed() {
        super.onBackPressed();
    }

    private  void  showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
