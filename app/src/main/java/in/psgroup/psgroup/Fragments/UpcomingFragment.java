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

import in.psgroup.psgroup.Adapters.AllReferenceAdapter;
import in.psgroup.psgroup.Adapters.UpcomingAdapter;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.UpcomingBean;

public class UpcomingFragment extends Fragment {
    RecyclerView recycler;
    UpcomingAdapter upcomingAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<UpcomingBean> upcomingBeanArrayList = new ArrayList<UpcomingBean>();
    View view;
    UpcomingBean upcomingBean;

    public static UpcomingFragment newInstance() {

        Bundle args = new Bundle();

        UpcomingFragment fragment = new UpcomingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        view = inflater.inflate(R.layout.fragment_pastvisit, container, false);
        initView();
        layoutManager(view);
        return view;
    }

    private void initView() {
        recycler = (RecyclerView)view.findViewById(R.id.recycler);
    }

    private void layoutManager(View view) {
        layoutManager = new GridLayoutManager(view.getContext(), 1, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        upcomingBeanArrayList = new ArrayList<>();
        upcomingBeanArrayList.add(new UpcomingBean("", "Arham", "A-501,", "3 BHK ", "Topsia, Kolkata", "Nov 25, 2017", "Pending for approval"));
        upcomingBeanArrayList.add(new UpcomingBean("", "Arham", "A-501,", "3 BHK ", "Topsia, Kolkata", "Nov 25, 2017", "Pending for approval"));
        upcomingBeanArrayList.add(new UpcomingBean("", "Arham", "A-501,", "3 BHK ", "Topsia, Kolkata", "Nov 25, 2017", "Pending for approval"));
        upcomingBeanArrayList.add(new UpcomingBean("", "Arham", "A-501,", "3 BHK ", "Topsia, Kolkata", "Nov 25, 2017", "Pending for approval"));
        upcomingAdapter = new UpcomingAdapter(view.getContext(), upcomingBeanArrayList);
        recycler.setAdapter(upcomingAdapter);
    }
}
