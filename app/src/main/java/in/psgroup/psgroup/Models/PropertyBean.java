package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PropertyBean implements Parcelable {
    private String project_title,completion_time;
    private int max_milestone,current_milestone;
    private String MilestoneName;

    public PropertyBean(String project_title, String completion_time, int max_milestone, int current_milestone, String milestoneName) {
        this.project_title = project_title;
        this.completion_time = completion_time;
        this.max_milestone = max_milestone;
        this.current_milestone = current_milestone;
        MilestoneName = milestoneName;
    }

    protected PropertyBean(Parcel in) {
        project_title = in.readString();
        completion_time = in.readString();
        max_milestone = in.readInt();
        current_milestone = in.readInt();
        MilestoneName = in.readString();
    }

    public static final Creator<PropertyBean> CREATOR = new Creator<PropertyBean>() {
        @Override
        public PropertyBean createFromParcel(Parcel in) {
            return new PropertyBean(in);
        }

        @Override
        public PropertyBean[] newArray(int size) {
            return new PropertyBean[size];
        }
    };

    public String getProject_title() {
        return project_title;
    }

    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }

    public String getCompletion_time() {
        return completion_time;
    }

    public void setCompletion_time(String completion_time) {
        this.completion_time = completion_time;
    }

    public int getMax_milestone() {
        return max_milestone;
    }

    public void setMax_milestone(int max_milestone) {
        this.max_milestone = max_milestone;
    }

    public int getCurrent_milestone() {
        return current_milestone;
    }

    public void setCurrent_milestone(int current_milestone) {
        this.current_milestone = current_milestone;
    }

    public String getMilestoneName() {
        return MilestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        MilestoneName = milestoneName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(project_title);
        dest.writeString(completion_time);
        dest.writeInt(max_milestone);
        dest.writeInt(current_milestone);
        dest.writeString(MilestoneName);
    }
}
