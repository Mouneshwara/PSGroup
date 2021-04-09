package in.psgroup.psgroup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.psgroup.psgroup.Models.Bitmap_Image;
import in.psgroup.psgroup.Utility.TouchImageView;


public class DeleteImageActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public List<Bitmap_Image> bitmap_imageList = new ArrayList<>();
    TouchImageView plan;
    Bitmap bmp;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_image);



        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");
        position=getIntent().getExtras().getInt("position");


         bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
           getSupportActionBar().setTitle("");

        }

        plan = (TouchImageView)findViewById(R.id.plan_image);

        plan.setImageBitmap(bmp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.delete:
                Intent i = new Intent();
                i.putExtra("position", position);
                setResult(RESULT_OK, i);
                finish();
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
