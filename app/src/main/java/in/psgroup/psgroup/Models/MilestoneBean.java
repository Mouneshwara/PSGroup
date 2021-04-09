package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class MilestoneBean implements Parcelable{
     String  milestone_id,property_id,milestone_imgages,mile_stone_name,milestone_date,milestone_desc,milestone_percentage,milestone_status;

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public MilestoneBean(String milestone_id, String property_id, String milestone_imgages, String mile_stone_name, String milestone_date, String milestone_desc, String milestone_percentage, String milestone_status) {
        this.milestone_id = milestone_id;
        this.property_id=property_id;

        this.milestone_imgages = milestone_imgages;
        this.mile_stone_name = mile_stone_name;
        this.milestone_date = milestone_date;
        this.milestone_desc = milestone_desc;
        this.milestone_percentage = milestone_percentage;
        this.milestone_status = milestone_status;
    }

    protected MilestoneBean(Parcel in) {
        milestone_id = in.readString();
        property_id =in.readString();
        milestone_imgages = in.readString();
        mile_stone_name = in.readString();
        milestone_date = in.readString();
        milestone_desc = in.readString();
        milestone_percentage = in.readString();
        milestone_status = in.readString();
    }

    public static final Creator<MilestoneBean> CREATOR = new Creator<MilestoneBean>() {
        @Override
        public MilestoneBean createFromParcel(Parcel in) {
            return new MilestoneBean(in);
        }

        @Override
        public MilestoneBean[] newArray(int size) {
            return new MilestoneBean[size];
        }
    };

    public String getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(String milestone_id) {
        this.milestone_id = milestone_id;
    }

    public String getMilestone_imgages() {
        return milestone_imgages;
    }

    public void setMilestone_imgages(String milestone_imgages) {
        this.milestone_imgages = milestone_imgages;
    }

    public String getMile_stone_name() {
        return mile_stone_name;
    }

    public void setMile_stone_name(String mile_stone_name) {
        this.mile_stone_name = mile_stone_name;
    }

    public String getMilestone_date() {
        return milestone_date;
    }

    public void setMilestone_date(String milestone_date) {
        this.milestone_date = milestone_date;
    }

    public String getMilestone_desc() {
        return milestone_desc;
    }

    public void setMilestone_desc(String milestone_desc) {
        this.milestone_desc = milestone_desc;
    }

    public String getMilestone_percentage() {
        return milestone_percentage;
    }

    public void setMilestone_percentage(String milestone_percentage) {
        this.milestone_percentage = milestone_percentage;
    }

    public String getMilestone_status() {
        return milestone_status;
    }

    public void setMilestone_status(String milestone_status) {
        this.milestone_status = milestone_status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(milestone_id);
        dest.writeString(property_id);
        dest.writeString(milestone_imgages);
        dest.writeString(mile_stone_name);
        dest.writeString(milestone_date);
        dest.writeString(milestone_desc);
        dest.writeString(milestone_percentage);
        dest.writeString(milestone_status);
    }
}
