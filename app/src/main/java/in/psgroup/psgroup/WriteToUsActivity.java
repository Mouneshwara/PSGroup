package in.psgroup.psgroup;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import in.psgroup.psgroup.Adapters.CapturedImage_Adapter;
import in.psgroup.psgroup.Adapters.IssueSpinnerAdapter;
import in.psgroup.psgroup.Models.Bitmap_Image;
import in.psgroup.psgroup.Models.IssueBean;
import in.psgroup.psgroup.Utility.Utils;

import static in.psgroup.psgroup.Utility.Utils.STRING_REQUESTCODE;

public class WriteToUsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    String[] issues;
    private Spinner issueSpinner;
    private ImageView ic_back,add_photos;
    private IssueSpinnerAdapter issueSpinnerAdapter;
    RecyclerView.LayoutManager layoutManager;
    private Button btn_submit;
    private TextView tv_issue,tv_detailIssue,tv_explainIssue,tv_uploadPic;
    private ArrayList<IssueBean> issueList= new ArrayList<>();
    ArrayList<Bitmap_Image> bitmap_imageList = new ArrayList<>();
    ArrayList<Bitmap_Image> multiselect_list = new ArrayList<>();
    private CapturedImage_Adapter capturedImage_adapter;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1, MY_PERMISSIONS = 1;
    private Bitmap bitmap,thumbnail;
    private RecyclerView recyclerView;
    private String userChoosenTask;
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_to_us);
        initialize();
        initList();
        setSpinnerAdapter();

        bitmap_imageList.clear();
        capturedImage_adapter = new CapturedImage_Adapter(this, bitmap_imageList, multiselect_list);
        layoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view); // item position
                int spanCount = 2;
                int spacing = 10;//spacing between views in grid

                if (position >= 0) {
                    int column = position % spanCount; // item column

                    outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                    if (position < spanCount) { // top edge
                        outRect.top = spacing;
                    }
                    outRect.bottom = spacing; // item bottom
                } else {
                    outRect.left = 0;
                    outRect.right = 0;
                    outRect.top = 0;
                    outRect.bottom = 0;
                }
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(capturedImage_adapter);
    }

    private void initialize() {
        issueSpinner = (Spinner)findViewById(R.id.issueSpinner);
        ic_back = (ImageView)findViewById(R.id.ic_back);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        tv_issue = (TextView)findViewById(R.id.tv_issue);
        tv_detailIssue = (TextView)findViewById(R.id.tv_detailIssue);
        tv_explainIssue = (TextView)findViewById(R.id.tv_explainIssue);
        tv_uploadPic = (TextView)findViewById(R.id.tv_uploadPic);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        add_photos =(ImageView)findViewById(R.id.add_photos);
        ic_back.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        add_photos.setOnClickListener(this);

    }

    private void initList(){
        issueList.add(new IssueBean("0","Select"));
        issueList.add(new IssueBean("1","Issue1"));
        issueList.add(new IssueBean("2","Project list"));
        issueList.add(new IssueBean("3","Unit number"));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setSpinnerAdapter(){
        issueSpinnerAdapter=new IssueSpinnerAdapter(this,R.layout.layout_spinner,issueList);
        issueSpinner.setAdapter(issueSpinnerAdapter);
        issueSpinner.setOnItemSelectedListener(this);
    }

    private void showAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_ticket_raised_alert, viewGroup, false);
        Button btn_done = (Button) dialogView.findViewById(R.id.btn_done);
        /*  android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.MyAlertDialogStyle);*/
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.dialogbg);
        alertDialog.show();
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WriteToUsActivity.this,HelpActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                alertDialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_back:
            this.onBackPressed();
            break;
            case R.id.btn_submit:
                showAlertDialog(view);
                break;

            case R.id.add_photos:
               /* EditphotoFragment editphotoFragment=new EditphotoFragment();
                editphotoFragment.show(getSupportFragmentManager(),editphotoFragment.getTag());*/
                if (ContextCompat.checkSelfPermission(WriteToUsActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(WriteToUsActivity.this, new String[]{android.Manifest.permission.SEND_SMS, android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS);
                }
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS);
                }
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS);
                }
                selectImage();
                break;
        }

    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(WriteToUsActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utils.checkPermission(WriteToUsActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
            else if (requestCode==STRING_REQUESTCODE){

               int position=data.getExtras().getInt("position");
                bitmap_imageList.remove(position);
                capturedImage_adapter.notifyItemRemoved(position);

            }
        }
    }

    private void onSelectFromGalleryResult(Intent data) {

        try {
            final Uri imageUri = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            bitmap = BitmapFactory.decodeStream(imageStream);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            bitmap_imageList.add(new Bitmap_Image(bitmap));
            capturedImage_adapter.notifyDataSetChanged();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(WriteToUsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }



    }


    private void onCaptureImageResult(Intent data) {
        bitmap = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        bitmap_imageList.add(new Bitmap_Image(bitmap));
        capturedImage_adapter.notifyDataSetChanged();

    }


}
