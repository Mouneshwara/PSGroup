package in.psgroup.psgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResendOtpActivity extends AppCompatActivity {
    EditText otp1,otp2,otp3,otp4;
    Button resendButton;
    TextView incorrect_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resend_otp);
        initialize();
    }

    private void initialize() {
        otp1 = (EditText)findViewById(R.id.otp1);
        otp2 = (EditText)findViewById(R.id.otp2);
        otp3 = (EditText)findViewById(R.id.otp3);
        otp4 = (EditText)findViewById(R.id.otp4);
        resendButton = (Button)findViewById(R.id.resendButton);
        incorrect_otp = (TextView)findViewById(R.id.incorrect_otp);
    }
}
