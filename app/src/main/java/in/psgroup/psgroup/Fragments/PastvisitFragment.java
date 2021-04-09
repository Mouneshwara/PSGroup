package in.psgroup.psgroup.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.psgroup.psgroup.Adapters.PastvisitAdapter;
import in.psgroup.psgroup.Adapters.UpcomingAdapter;
import in.psgroup.psgroup.Models.UpcomingBean;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.PastvisitBean;

public class PastvisitFragment extends Fragment {
    RecyclerView recycler;
    PastvisitAdapter pastvisitAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PastvisitBean> pastvisitBeanArrayList= new ArrayList<PastvisitBean>();
    View view;
    PastvisitBean pastvisitBean;

    public static PastvisitFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PastvisitFragment fragment = new PastvisitFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        view = inflater.inflate(R.layout.fragment_pastvisit,container,false);
        initView();
        layoutManager(view);
        return view;
    }

    private void layoutManager(final View view) {
        layoutManager = new GridLayoutManager(view.getContext(), 1, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        pastvisitBeanArrayList = new ArrayList<>();
        pastvisitBeanArrayList.add(new PastvisitBean("", "Arham","A-501, ","3 BHK ","Topsia, Kolkata","Nov 25, 2017","Visited at 4:30 pm","Apartment main door still needs to be fixed"));
        pastvisitBeanArrayList.add(new PastvisitBean("", "Arham","A-501, ","3 BHK ","Topsia, Kolkata","Nov 25, 2017","Visited at 4:30 pm","Apartment main door still needs to be fixed"));
        pastvisitBeanArrayList.add(new PastvisitBean("", "Arham","A-501, ","3 BHK ","Topsia, Kolkata","Nov 25, 2017","Visited at 4:30 pm","Apartment main door still needs to be fixed"));
        pastvisitBeanArrayList.add(new PastvisitBean("", "Arham","A-501, ","3 BHK ","Topsia, Kolkata","Nov 25, 2017","Visited at 4:30 pm","Apartment main door still needs to be fixed"));
        pastvisitAdapter = new PastvisitAdapter(view.getContext(), pastvisitBeanArrayList);
        recycler.setAdapter(pastvisitAdapter);
    }

    private void initView() {
        recycler=(RecyclerView)view.findViewById(R.id.recycler);

    }
}
