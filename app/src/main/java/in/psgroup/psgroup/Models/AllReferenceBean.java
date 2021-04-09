package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class AllReferenceBean implements Parcelable  {
    String reffered_to,date,points,reffered_property,status;

    public AllReferenceBean(String reffered_to, String date, String points, String reffered_property, String status) {
        this.reffered_to = reffered_to;
        this.date = date;
        this.points = points;
        this.reffered_property = reffered_property;
        this.status = status;
    }

    protected AllReferenceBean(Parcel in) {
        reffered_to = in.readString();
        date = in.readString();
        points = in.readString();
        reffered_property = in.readString();
        status = in.readString();
    }

    public static final Creator<AllReferenceBean> CREATOR = new Creator<AllReferenceBean>() {
        @Override
        public AllReferenceBean createFromParcel(Parcel in) {
            return new AllReferenceBean(in);
        }

        @Override
        public AllReferenceBean[] newArray(int size) {
            return new AllReferenceBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reffered_to);
        parcel.writeString(date);
        parcel.writeString(points);
        parcel.writeString(reffered_property);
        parcel.writeString(status);
    }

    public String getReffered_to() {
        return reffered_to;
    }

    public void setReffered_to(String reffered_to) {
        this.reffered_to = reffered_to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getReffered_property() {
        return reffered_property;
    }

    public void setReffered_property(String reffered_property) {
        this.reffered_property = reffered_property;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Creator<AllReferenceBean> getCREATOR() {
        return CREATOR;
    }
}