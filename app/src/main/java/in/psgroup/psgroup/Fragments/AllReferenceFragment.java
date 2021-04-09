package in.psgroup.psgroup.Fragments;

import android.content.Context;
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
import in.psgroup.psgroup.Models.AllReferenceBean;
import in.psgroup.psgroup.Models.ReferralHistoryBean;
import in.psgroup.psgroup.Models.UpcomingBean;
import in.psgroup.psgroup.R;

public class AllReferenceFragment extends Fragment {
    RecyclerView recyclerView;
    AllReferenceAdapter allReferenceAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<AllReferenceBean> allReferenceBeanArrayList= new ArrayList<AllReferenceBean>();
    View view;
    AllReferenceBean allReferenceBean;

    ArrayList<ReferralHistoryBean> referralHistoryBeanArrayList = new ArrayList<>();

    public static AllReferenceFragment newInstance() {



        Bundle args = new Bundle();

        AllReferenceFragment fragment = new AllReferenceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            referralHistoryBeanArrayList = bundle.getParcelableArrayList("referralHistoryBeanArrayList");
        }
        view = inflater.inflate(R.layout.fragment_all_reference,container,false);
        initView();
        layoutManager(view);
        return view;
    }

    private void layoutManager(View view) {
        layoutManager=new GridLayoutManager(view.getContext(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        allReferenceAdapter=new AllReferenceAdapter(referralHistoryBeanArrayList,view.getContext());
        recyclerView.setAdapter(allReferenceAdapter);
    }

    private void initView() {
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);

    }
}
