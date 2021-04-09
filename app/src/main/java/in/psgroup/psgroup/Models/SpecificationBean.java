package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class SpecificationBean implements Parcelable{

    int specImage;
    String specName;

    public SpecificationBean(int specImage, String specName) {
        this.specImage = specImage;
        this.specName = specName;
    }

    public int getSpecImage() {
        return specImage;
    }

    public void setSpecImage(int specImage) {
        this.specImage = specImage;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public static Creator<SpecificationBean> getCREATOR() {
        return CREATOR;
    }

    protected SpecificationBean(Parcel in) {
    }

    public static final Creator<SpecificationBean> CREATOR = new Creator<SpecificationBean>() {
        @Override
        public SpecificationBean createFromParcel(Parcel in) {
            return new SpecificationBean(in);
        }

        @Override
        public SpecificationBean[] newArray(int size) {
            return new SpecificationBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static SpecificationBean get(int pos) {
        return null;
    }
}
