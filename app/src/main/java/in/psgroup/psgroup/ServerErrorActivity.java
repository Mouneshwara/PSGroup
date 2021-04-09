package in.psgroup.psgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ServerErrorActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ic_back;
    private Button brn_retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_error);
        initialize();
    }

    private void initialize() {
        ic_back = (ImageView)findViewById(R.id.ic_back);
        brn_retry = (Button)findViewById(R.id.brn_retry);
        ic_back.setOnClickListener(this);
        brn_retry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_back:
                this.onBackPressed();
                break;

            case R.id.brn_retry:
                this.onBackPressed();
                break;
        }
    }
}
