package in.psgroup.psgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import in.psgroup.psgroup.Models.ScheduleBean;

public class TestingActivity extends AppCompatActivity {
    ArrayList<ScheduleBean> dataModels;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
         listView=(ListView)findViewById(R.id.listview);

        dataModels= new ArrayList<>();
        dataModels.add(new ScheduleBean("Today","24",null,false));
        dataModels.add(new ScheduleBean("Tomorrow","25",null,false));
        dataModels.add(new ScheduleBean("Friday","26",null,false));
        dataModels.add(new ScheduleBean("Saturday","27",null,false));
        dataModels.add(new ScheduleBean("Sunday","28",null,false));
        dataModels.add(new ScheduleBean("Monday","29",null,false));



//        listView.setAdapter(adapter);


    }
}
