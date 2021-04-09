package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class PastvisitBean implements Parcelable {
   String image,appartment_name,plot_no,bhk,landmark,date,status,comments;

    public PastvisitBean(String image, String appartment_name, String plot_no, String bhk, String landmark, String date, String status, String comments) {
        this.image = image;
        this.appartment_name = appartment_name;
        this.plot_no = plot_no;
        this.bhk = bhk;
        this.landmark = landmark;
        this.date = date;
        this.status = status;
        this.comments = comments;
    }

    protected PastvisitBean(Parcel in) {
        image = in.readString();
        appartment_name = in.readString();
        plot_no = in.readString();
        bhk = in.readString();
        landmark = in.readString();
        date = in.readString();
        status = in.readString();
        comments = in.readString();
    }

    public static final Creator<PastvisitBean> CREATOR = new Creator<PastvisitBean>() {
        @Override
        public PastvisitBean createFromParcel(Parcel in) {
            return new PastvisitBean(in);
        }

        @Override
        public PastvisitBean[] newArray(int size) {
            return new PastvisitBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(appartment_name);
        parcel.writeString(plot_no);
        parcel.writeString(bhk);
        parcel.writeString(landmark);
        parcel.writeString(date);
        parcel.writeString(status);
        parcel.writeString(comments);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAppartment_name() {
        return appartment_name;
    }

    public void setAppartment_name(String appartment_name) {
        this.appartment_name = appartment_name;
    }

    public String getPlot_no() {
        return plot_no;
    }

    public void setPlot_no(String plot_no) {
        this.plot_no = plot_no;
    }

    public String getBhk() {
        return bhk;
    }

    public void setBhk(String bhk) {
        this.bhk = bhk;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public static Creator<PastvisitBean> getCREATOR() {
        return CREATOR;
    }
}
