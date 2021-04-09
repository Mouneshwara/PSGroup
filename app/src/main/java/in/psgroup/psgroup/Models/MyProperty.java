package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

public class MyProperty implements Parcelable {

    private String project_title,post_content,post_id,post_title,post_file_link,address,state,browser_title,meta_desc,banner_image,resale,apartment_no,apartment_type,meta_keywords,socialmeta_desc,meta_img;
    private String purchase_cost;
    private ArrayList<ProjectSpecification> ProjectSpecification_two_list=new ArrayList<ProjectSpecification>();
    private  ProjectOverviewBean ProjectOverview;
    private ArrayList<FloorPlans> floorplan = new ArrayList<FloorPlans>();
   // private ArrayList<String> siteplan = new ArrayList<String>();
   // private ArrayList<String> milestone_status = new ArrayList<String>();
    private double latitude,longitude;

    public MyProperty(String project_title, String post_content, String post_id, String post_title, String post_file_link, String address, String state, String browser_title, String meta_desc, String banner_image, String resale, String apartment_no, String apartment_type, String meta_keywords, String socialmeta_desc, String meta_img,String highlights, String purchase_cost, ArrayList<ProjectSpecification> projectSpecification_two_list,ProjectOverviewBean projectOverview, ArrayList<FloorPlans> floorplan, ArrayList<String> siteplan, double latitude, double longitude) {
        this.project_title = project_title;
        this.post_content = post_content;
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_file_link = post_file_link;
        this.address = address;
        this.state = state;
        this.browser_title = browser_title;
        this.meta_desc = meta_desc;
        this.banner_image = banner_image;
        this.resale = resale;
        this.apartment_no = apartment_no;
        this.apartment_type = apartment_type;
        this.meta_keywords = meta_keywords;
        this.socialmeta_desc = socialmeta_desc;
        this.meta_img = meta_img;
        this.purchase_cost = purchase_cost;

        ProjectSpecification_two_list = projectSpecification_two_list;
        this.ProjectOverview = ProjectOverview;
        this.floorplan = floorplan;
      //  this.siteplan = siteplan;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    protected MyProperty(Parcel in) {


        project_title = in.readString();
        post_content = in.readString();
        post_id = in.readString();
        post_title = in.readString();
        post_file_link = in.readString();
        address = in.readString();
        state = in.readString();
        browser_title = in.readString();
        meta_desc = in.readString();
        banner_image = in.readString();
        resale = in.readString();
        apartment_no = in.readString();
        apartment_type = in.readString();
        meta_keywords = in.readString();
        socialmeta_desc = in.readString();

        meta_img = in.readString();

        purchase_cost = in.readString();
        ProjectSpecification_two_list = in.createTypedArrayList(ProjectSpecification.CREATOR);
       // ProjectOverview = in.createStringArrayList();
        floorplan = in.createTypedArrayList(FloorPlans.CREATOR);

        //  milestone_status=in.createStringArrayList();
        latitude = in.readDouble();
        longitude = in.readDouble();
       // ProjectOverview= in.readValue(ProjectOverview);
    }

    public static final Creator<MyProperty> CREATOR = new Creator<MyProperty>() {
        @Override
        public MyProperty createFromParcel(Parcel in) {
            return new MyProperty(in);
        }

        @Override
        public MyProperty[] newArray(int size) {
            return new MyProperty[size];
        }
    };

    public String getProject_title() {
        return project_title;
    }

    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_file_link() {
        return post_file_link;
    }

    public void setPost_file_link(String post_file_link) {
        this.post_file_link = post_file_link;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBrowser_title() {
        return browser_title;
    }

    public void setBrowser_title(String browser_title) {
        this.browser_title = browser_title;
    }

    public String getMeta_desc() {
        return meta_desc;
    }

    public void setMeta_desc(String meta_desc) {
        this.meta_desc = meta_desc;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getResale() {
        return resale;
    }

    public void setResale(String resale) {
        this.resale = resale;
    }

    public String getApartment_no() {
        return apartment_no;
    }

    public void setApartment_no(String apartment_no) {
        this.apartment_no = apartment_no;
    }

    public String getApartment_type() {
        return apartment_type;
    }

    public void setApartment_type(String apartment_type) {
        this.apartment_type = apartment_type;
    }

    public String getMeta_keywords() {
        return meta_keywords;
    }

    public void setMeta_keywords(String meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    public String getSocialmeta_desc() {
        return socialmeta_desc;
    }

    public void setSocialmeta_desc(String socialmeta_desc) {
        this.socialmeta_desc = socialmeta_desc;
    }

    public String getMeta_img() {
        return meta_img;
    }

    public void setMeta_img(String meta_img) {
        this.meta_img = meta_img;
    }

    public String getPurchase_cost() {
        return purchase_cost;
    }

    public void setPurchase_cost(String purchase_cost) {
        this.purchase_cost = purchase_cost;
    }

    public ArrayList<ProjectSpecification> getProjectSpecification_two_list() {
        return ProjectSpecification_two_list;
    }

    public void setProjectSpecification_two_list(ArrayList<ProjectSpecification> projectSpecification_two_list) {
        ProjectSpecification_two_list = projectSpecification_two_list;
    }

    public ProjectOverviewBean getProjectOverview() {
        return ProjectOverview;
    }

    public void setProjectOverview(ProjectOverviewBean projectOverview) {
        ProjectOverview = projectOverview;
    }

    public ArrayList<FloorPlans> getFloorplan() {
        return floorplan;
    }

    public void setFloorplan(ArrayList<FloorPlans> floorplan) {
        this.floorplan = floorplan;
    }

   /* public ArrayList<String> getSiteplan() {
        return siteplan;
    }

    public void setSiteplan(ArrayList<String> siteplan) {
        this.siteplan = siteplan;
    }*/

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


/*
    public ArrayList<String> getMilestone_status() {
        return milestone_status;
    }

    public void setMilestone_status(ArrayList<String> milestone_status) {
        this.milestone_status = milestone_status;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(project_title);
        dest.writeString(post_content);
        dest.writeString(post_id);
        dest.writeString(post_title);
        dest.writeString(post_file_link);
        dest.writeString(address);
        dest.writeString(state);
        dest.writeString(browser_title);
        dest.writeString(meta_desc);
        dest.writeString(banner_image);
        dest.writeString(resale);
        dest.writeString(apartment_no);
        dest.writeString(apartment_type);
        dest.writeString(meta_keywords);
        dest.writeString(socialmeta_desc);
        dest.writeString(meta_img);
        dest.writeString(purchase_cost);
        dest.writeTypedList(ProjectSpecification_two_list);
       // dest.writeValue(ProjectOverview);
        dest.writeTypedList(floorplan);
     //   dest.writeStringList(siteplan);
    //    dest.writeStringList(milestone_status);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);

    }
}
