package in.psgroup.psgroup.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.psgroup.psgroup.Adapters.MyPropertyAdapter;
import in.psgroup.psgroup.Models.MyProperty;
import in.psgroup.psgroup.PsGroupApplication;
import in.psgroup.psgroup.R;
import in.psgroup.psgroup.Models.HighLightBean;
import in.psgroup.psgroup.Models.ProjectSpecification;
import in.psgroup.psgroup.Models.PropertyBean;

public class MyPropertyFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyPropertyAdapter propertyAdapter;
    private ArrayList<MyPropertyFragment> propertyBeans = new ArrayList<>();
    private ImageView drawerBtn;
    private String sitePlanList;//= new ArrayList<String>();
    private String fragment_data;

    private in.psgroup.psgroup.Models.MyProperty myProperty;
    private ArrayList<MyProperty> myProperties = new ArrayList<MyProperty>();


    public MyPropertyFragment() {

    }

    public static MyPropertyFragment newInstance() {

        Bundle args = new Bundle();

        MyPropertyFragment fragment = new MyPropertyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myproperty, container, false);

        final PsGroupApplication myApplication = (PsGroupApplication) getActivity().getApplication();
        fragment_data = myApplication.getJson_data();
        Log.d("TAG", "recieved_json_in_property:" + fragment_data);

        initList();
        //initializing
        initView(view);
        return view;
    }

    private void initList() {
        try {
            if(fragment_data!=null){
            JSONObject jsonObject = new JSONObject(fragment_data);
            JSONArray properties = jsonObject.getJSONArray("myproperties");
            for (int i = 0; i < properties.length(); i++) {
                ArrayList<ProjectSpecification> projectSpecificationList = new ArrayList<ProjectSpecification>();
                ArrayList<String> floorPlanList = new ArrayList<>();
                ArrayList<String> overViewList = new ArrayList<String>();
                ArrayList<HighLightBean> highLightArray = new ArrayList<HighLightBean>();
                JSONObject object = properties.getJSONObject(i);
                projectSpecificationList.clear();
                floorPlanList.clear();
                overViewList.clear();
                highLightArray.clear();
                //accessing myproperty elemets
                String project_title = object.getString("project_title");
                Log.d("Tag", "mypropertyFragment" + project_title);
                String apartment_type = object.getString("apartment_type");
                String post_id = object.getString("post_id");
                String post_title = object.getString("post_title");
                Double purchase_cost = object.getDouble("purchase_cost");
                String apartment_no = object.getString("apartment_no");
                String post_file_link = object.getString("post_file_link");
                String browser_title = object.getString("browser_title");
                String meta_desc = object.getString("meta_desc");
                Double latitude = object.getDouble("latitude");
                Double longitude = object.getDouble("longitude");
                String address = object.getString("address");
                String state = object.getString("state");
                String banner_image = object.getString("banner_image");

                //propertyOverview
                JSONObject overview = object.getJSONObject("ProjectOverview");
                String overView_desc = overview.getString("detail_desc");
                overViewList.add(overView_desc);

                //sitePlan
                JSONObject siteplan = object.getJSONObject("siteplan");
                String sitePlan_pic = siteplan.getString("detail_desc");

                sitePlanList = sitePlan_pic;


                //floorPlan
                JSONArray floorPlan = object.getJSONArray("floorplan");
                for (int j = 0; j < floorPlan.length(); j++) {
                    JSONObject floorObject = floorPlan.getJSONObject(j);
//                    String detail_title = floorObject.getString("detail_title");
//                    String detail_name = floorObject.getString("detail_name");
                    String detail_desc = floorObject.getString("detail_desc");

                    floorPlanList.add(detail_desc);

                }
                Log.d("TAG", "floorplans:" + floorPlanList.size());
                //projectSpecification
                JSONArray specification = object.getJSONArray("ProjectSpecification_two_list");
                for (int k = 0; k < specification.length(); k++) {
                    JSONObject specificationObj = specification.getJSONObject(k);
                    String detail_title = specificationObj.getString("detail_title");
                    String detail_name = specificationObj.getString("detail_name");
                    String detail_note = specificationObj.getString("detail_note");

                //    ProjectSpecification ps = new ProjectSpecification(detail_title, detail_name, detail_note);
                 //   projectSpecificationList.add(ps);
                }
                //highlights array
                JSONArray highlights = object.getJSONArray("highlights");
                for (int m = 0; m < highlights.length(); m++) {
                    JSONObject obj = highlights.getJSONObject(m);
                    String category_name = obj.getString("name");
                    String category_image = obj.getString("amenities");
                    String description = obj.getString("image");

                    HighLightBean highLightBean = new HighLightBean(category_name, category_image, description);
                    highLightArray.add(highLightBean);


                }
                //Milestone Status
                JSONObject jsonObject1 = object.getJSONObject("milestone_status");
                int max_milestone = Integer.parseInt(jsonObject1.getString("Max"));
                int current_milestone = Integer.parseInt(jsonObject1.getString("Current"));
                String MilestoneName = jsonObject1.getString("MilestoneName");
            }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.propertyRecycler);

//        propertyBeans.add(new PropertyBean(R.drawable.apartment,R.drawable.ic_locationcard,"SRP Tower","A-205","3 BHK","SNPete,Bellary","RoofLaying","Milestone","09/13"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        propertyAdapter = new MyPropertyAdapter(myProperties, getActivity().getApplicationContext());
        recyclerView.setAdapter(propertyAdapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
