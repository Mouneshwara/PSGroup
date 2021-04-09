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

import in.psgroup.psgroup.Adapters.PaidAdapter;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.PaidBean;

public class PaidFragment extends Fragment {
    RecyclerView recyclerView;
    PaidAdapter paidAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PaidBean> paidList = new ArrayList<PaidBean>();
    View view;
    PaidBean paidBean;

    public static PaidFragment newInstance() {

        Bundle args = new Bundle();

        PaidFragment fragment = new PaidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_paid,container,false);
        initView();
        layoutManager(view);
        return view;
    }

    private void layoutManager(View view) {
        layoutManager=new GridLayoutManager(view.getContext(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        paidList = new ArrayList<>();
        paidList.add(new PaidBean("Oct 25, 2017 ","Equinox","A-501, 3 BHK ","ledger","Roof Laying ","Dec 14, 2017","12:09 PM"));
        paidList.add(new PaidBean("Oct 25, 2017 ","Equinox","A-501, 3 BHK ","ledger","Roof Laying ","Dec 14, 2017","12:09 PM"));
        paidList.add(new PaidBean("Oct 25, 2017 ","Equinox","A-501, 3 BHK ","ledger","Roof Laying ","Dec 14, 2017","12:09 PM"));
        paidList.add(new PaidBean("Oct 25, 2017 ","Equinox","A-501, 3 BHK ","ledger","Roof Laying ","Dec 14, 2017","12:09 PM"));
        paidList.add(new PaidBean("Oct 25, 2017 ","Equinox","A-501, 3 BHK ","ledger","Roof Laying ","Dec 14, 2017","12:09 PM"));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        paidAdapter=new PaidAdapter(view.getContext(),paidList);
        recyclerView.setAdapter(paidAdapter);
    }

    private void initView() {
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);

    }
}
