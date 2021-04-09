package in.psgroup.psgroup.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.psgroup.psgroup.Adapters.AllReferenceAdapter;
import in.psgroup.psgroup.Adapters.ProjectWiseAdapter;
import in.psgroup.psgroup.Models.AllReferenceBean;
import in.psgroup.psgroup.Models.ProjectWiseBean;
import in.psgroup.psgroup.Models.ReferralHistoryBean;
import in.psgroup.psgroup.R;

public class ProjectWiseReferenceFragment extends Fragment {
    RecyclerView recyclerView;
    ProjectWiseAdapter projectWiseAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ProjectWiseBean> projectWiseBeans= new ArrayList<ProjectWiseBean>();
    ArrayList<AllReferenceBean> list1,list2,list3,list4;
    View view;
    ProjectWiseBean projectWiseBean;
    ArrayList<ReferralHistoryBean> referralHistoryBeanArrayList = new ArrayList<>();
    HashMap<String, List<ReferralHistoryBean>> projectname = new HashMap<>();

    public static ProjectWiseReferenceFragment newInstance() {

        Bundle args = new Bundle();

        ProjectWiseReferenceFragment fragment = new ProjectWiseReferenceFragment();
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


        for(ReferralHistoryBean p : referralHistoryBeanArrayList){
            if(!projectname.containsKey(p.getProjectName()))
            { projectname.put(p.getProjectName(), new ArrayList<ReferralHistoryBean>()); }
            projectname.get(p.getProjectName()).add(p);
        }
        System.out.println("Person grouped by cities : " + projectname);
        Gson gson = new Gson();
        String jsonString = gson.toJson(projectname);
        System.out.println("jsonString : " + jsonString);


        view = inflater.inflate(R.layout.fragment_projectwise_reference,container,false);
        initView();
        layoutManager(view);
        return view;
    }

    private void layoutManager(View view) {
        layoutManager=new GridLayoutManager(view.getContext(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

              recyclerView.setItemAnimator(new DefaultItemAnimator());
        projectWiseAdapter=new ProjectWiseAdapter(projectname,view.getContext());
        recyclerView.setAdapter(projectWiseAdapter);
    }

    private void initView() {
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);

    }
}
