package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ProjectWiseBean implements Parcelable {

    String name;
    ArrayList<AllReferenceBean> referenceList = new ArrayList<>();

    public ProjectWiseBean(String name, ArrayList<AllReferenceBean> referenceList) {
        this.name = name;
        this.referenceList = referenceList;
    }

    protected ProjectWiseBean(Parcel in) {
        name = in.readString();
        referenceList = in.createTypedArrayList(AllReferenceBean.CREATOR);
    }

    public static final Creator<ProjectWiseBean> CREATOR = new Creator<ProjectWiseBean>() {
        @Override
        public ProjectWiseBean createFromParcel(Parcel in) {
            return new ProjectWiseBean(in);
        }

        @Override
        public ProjectWiseBean[] newArray(int size) {
            return new ProjectWiseBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<AllReferenceBean> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(ArrayList<AllReferenceBean> referenceList) {
        this.referenceList = referenceList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(referenceList);
    }
}
