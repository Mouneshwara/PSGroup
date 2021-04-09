package in.psgroup.psgroup;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.psgroup.psgroup.Adapters.CommentsAdapter;
import in.psgroup.psgroup.Adapters.TicketIdAdapter;
import in.psgroup.psgroup.Models.CommentsBean;
import in.psgroup.psgroup.Models.TicketIdBean;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backPress,iv_send;
    RecyclerView recyclerView;
    private TextView letter;
    private EditText et_text;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CommentsBean> commentList;
    private CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        initView();
    }
    private void initView() {
        recyclerView=(RecyclerView)findViewById(R.id.commentRecycle);
        backPress = (ImageView) findViewById(R.id.ic_backVisit);
        iv_send = (ImageView)findViewById(R.id.iv_send);
        et_text = (EditText)findViewById(R.id.et_text);
        letter = (TextView)findViewById(R.id.letter);
        backPress.setOnClickListener(this);
        et_text.setOnClickListener(this);

        initializeRecyclerView();
        validate();

    }

    private void initializeRecyclerView() {
        layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        commentList = new ArrayList<>();
        commentList.add(new CommentsBean("Rohit Verma","Close to everything and walking distance from the metro station. ","23min ago"));
        commentList.add(new CommentsBean("Rohit Verma","Close to everything and walking distance from the metro station. ","23min ago"));
        commentList.add(new CommentsBean("Rohit Verma","Close to everything and walking distance from the metro station. ","23min ago"));
        commentList.add(new CommentsBean("Rohit Verma","Close to everything and walking distance from the metro station. ","23min ago"));
        commentsAdapter = new CommentsAdapter(commentList,getApplicationContext());
        recyclerView.setAdapter(commentsAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_backVisit:
                this.onBackPressed();
                break;
        }

    }
    private void validate() {
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_text.getText().toString().isEmpty()){
                    iv_send.setVisibility(View.GONE);
                }else{
                    iv_send.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        et_text.addTextChangedListener(tw);

    }
}
