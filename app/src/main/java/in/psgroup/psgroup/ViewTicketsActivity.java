package in.psgroup.psgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import in.psgroup.psgroup.Adapters.ViewTicketAdapter;
import in.psgroup.psgroup.Models.ViewTicketBean;

public class ViewTicketsActivity extends AppCompatActivity implements View.OnClickListener {
    private  RecyclerView recyclerview;
    private ImageView  ic_backVisit;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ViewTicketBean> list;
    private ViewTicketAdapter ticketAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tickets);
        initialize();
    }

    private void initialize() {
        recyclerview = (RecyclerView)findViewById(R.id.recyclerview);
        ic_backVisit = (ImageView)findViewById(R.id.ic_backVisit);
        ic_backVisit.setOnClickListener(this);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        list.add(new ViewTicketBean("Fixing Bathroom Lights","10th Nov, 2018","1820AGDFJ"," Ticket Raised"," Bathroom","Lights"));
        list.add(new ViewTicketBean("Fixing Bathroom Lights","10th Nov, 2018","1820AGDFJ"," Ticket Raised"," Bathroom","Lights"));
        list.add(new ViewTicketBean("Fixing Bathroom Lights","10th Nov, 2018","1820AGDFJ"," Ticket Raised"," Bathroom","Lights"));
        list.add(new ViewTicketBean("Fixing Bathroom Lights","10th Nov, 2018","1820AGDFJ"," Ticket Raised"," Bathroom","Lights"));
        ticketAdapter = new ViewTicketAdapter(list,getApplicationContext());
        recyclerview.setAdapter(ticketAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_backVisit:
                onBackPressed();
                break;
        }
    }
}
