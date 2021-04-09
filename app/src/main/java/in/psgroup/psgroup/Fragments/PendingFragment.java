package in.psgroup.psgroup.Fragments;

import android.app.ActionBar;
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
import android.widget.Button;

import java.util.ArrayList;

import in.psgroup.psgroup.Adapters.AllReferenceAdapter;
import in.psgroup.psgroup.Adapters.PendingAdapter;
import in.psgroup.psgroup.Models.AllReferenceBean;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.PendingBean;

public class PendingFragment extends Fragment {
    RecyclerView recyclerView;
    PendingAdapter pendingAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PendingBean> pendingList = new ArrayList<PendingBean>();
    View view;
    PendingBean pendingBean;

    public static PendingFragment newInstance() {

        Bundle args = new Bundle();

        PendingFragment fragment = new PendingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_pending,container,false);
        initView();
        layoutManager(view);
        return view;
    }

    private void layoutManager(View view) {
        layoutManager=new GridLayoutManager(view.getContext(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        pendingList = new ArrayList<>();
        pendingList.add(new PendingBean("Arham","A-501, 3 BHK ","invoice","₹ 1,20,000","92 days","₹ 15,000 ","Oct 25, 2017 "));
        pendingList.add(new PendingBean("Arham","A-501, 3 BHK ","invoice","₹ 1,20,000","92 days","₹ 15,000 ","Oct 25, 2017 "));
        pendingList.add(new PendingBean("Arham","A-501, 3 BHK ","invoice","₹ 1,20,000","92 days","₹ 15,000 ","Oct 25, 2017 "));
        pendingList.add(new PendingBean("Arham","A-501, 3 BHK ","invoice","₹ 1,20,000","92 days","₹ 15,000 ","Oct 25, 2017 "));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        pendingAdapter=new PendingAdapter(view.getContext(),pendingList);
        recyclerView.setAdapter(pendingAdapter);
    }

    private void initView() {
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);

    }
}
