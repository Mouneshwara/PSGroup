package in.psgroup.psgroup;

import android.content.res.ColorStateList;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private String issueType[],mop;
    ImageView backPress,down;
    TextView tv_city;
    Spinner spinner;
    private ArrayAdapter<String> spinnermopArrayAdapter;
    LinearLayout ll_spinner,ll_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initializemopSpinner();
        setmopAdapter();
    }

    private void setmopAdapter() {

        spinnermopArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, issueType);
        spinnermopArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        ViewCompat.setBackgroundTintList(spinner, ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        spinner.setAdapter(spinnermopArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                mop = issueType[i];
                showToast(mop);
                tv_city.setText(mop);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initializemopSpinner() {

        issueType = new String[]{"All","Kolkata","Madhyamarg","Chennai",};
    }

    private void initView() {

        backPress = findViewById(R.id.ic_backSearch);
        down = findViewById(R.id.ic_drop);

        tv_city = (TextView) findViewById(R.id.tv_dropText);

        spinner = findViewById(R.id.search_spinner);

//        ll_spinner = findViewById(R.id.ll_spinner);
        ll_search = findViewById(R.id.ll_search);

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

      /*  down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_search.setVisibility(View.INVISIBLE);
                ll_spinner.setVisibility(View.VISIBLE);
            }
        });*/
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // user selected nothing...

//                ll_spinner.setVisibility(View.VISIBLE);
//                ll_search.setVisibility(View.INVISIBLE);
//                ll_spinner.setVisibility(View.INVISIBLE);
                    spinner.performClick();

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
