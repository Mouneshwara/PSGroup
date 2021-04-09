package in.psgroup.psgroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ExplanationOfClauseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ic_back;
    private TextView tv_question,tv_answer,tv_yes,tv_no,tv_thankYou,tv_text,tv_feedBack,tv_writeUs;
    RelativeLayout rLayout,rThankYou,rWriteUs;
private String question,answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation_of_clause);
        if (getIntent().getExtras()!=null){
            question =getIntent().getExtras().getString("question");
            answer =getIntent().getExtras().getString("answer");
        }
        initialize();
    }

    private void initialize() {
        ic_back = (ImageView)findViewById(R.id.ic_back);
        tv_question = (TextView)findViewById(R.id.tv_question);
        tv_question.setText(question);
        tv_answer = (TextView)findViewById(R.id.tv_answer);
        tv_answer.setText(answer);
        rLayout = (RelativeLayout)findViewById(R.id.rLayout);
        tv_yes = (TextView)findViewById(R.id.tv_yes);
        tv_no = (TextView)findViewById(R.id.tv_no);
        rThankYou = (RelativeLayout)findViewById(R.id.rThankYou);
        tv_thankYou = (TextView)findViewById(R.id.tv_thankYou);
        tv_text = (TextView)findViewById(R.id.tv_text);
        rWriteUs = (RelativeLayout)findViewById(R.id.rWriteUs);
        tv_feedBack = (TextView)findViewById(R.id.tv_feedBack);
        tv_writeUs = (TextView)findViewById(R.id.tv_writeUs);
        ic_back.setOnClickListener(this);
        tv_yes.setOnClickListener(this);
        tv_no.setOnClickListener(this);
        tv_writeUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.ic_back:
                this.onBackPressed();
                break;
            case R.id.tv_yes:
                rThankYou.setVisibility(View.VISIBLE);
                rLayout.setVisibility(View.GONE);
                break;
            case R.id.tv_no:
                rWriteUs.setVisibility(View.VISIBLE);
                rLayout.setVisibility(View.GONE);
                break;
            case R.id.tv_writeUs:
                i = new Intent(ExplanationOfClauseActivity.this,WriteToUsActivity.class);
                startActivity(i);
                break;
        }
    }
}
