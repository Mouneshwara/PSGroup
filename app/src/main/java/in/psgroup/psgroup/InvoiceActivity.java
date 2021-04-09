package in.psgroup.psgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class InvoiceActivity extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        initView();

    }

    private void initView() {

        back = (ImageView) findViewById(R.id.ic_backInvoice);

        back.setOnClickListener(new View.OnClickListener() {
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
