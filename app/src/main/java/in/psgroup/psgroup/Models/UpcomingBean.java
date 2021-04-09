package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class UpcomingBean implements Parcelable {
    String appartment_img,appartment_name,plot_no,bhk,landmark,date,status;

    public UpcomingBean(String appartment_img, String appartment_name, String plot_no, String bhk, String landmark, String date, String status) {
        this.appartment_img = appartment_img;
        this.appartment_name = appartment_name;
        this.plot_no = plot_no;
        this.bhk = bhk;
        this.landmark = landmark;
        this.date = date;
        this.status = status;
    }

    protected UpcomingBean(Parcel in) {
        appartment_img = in.readString();
        appartment_name = in.readString();
        plot_no = in.readString();
        bhk = in.readString();
        landmark = in.readString();
        date = in.readString();
        status = in.readString();
    }

    public static final Creator<UpcomingBean> CREATOR = new Creator<UpcomingBean>() {
        @Override
        public UpcomingBean createFromParcel(Parcel in) {
            return new UpcomingBean(in);
        }

        @Override
        public UpcomingBean[] newArray(int size) {
            return new UpcomingBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appartment_img);
        parcel.writeString(appartment_name);
        parcel.writeString(plot_no);
        parcel.writeString(bhk);
        parcel.writeString(landmark);
        parcel.writeString(date);
        parcel.writeString(status);
    }

    public String getAppartment_img() {
        return appartment_img;
    }

    public void setAppartment_img(String appartment_img) {
        this.appartment_img = appartment_img;
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

    public static Creator<UpcomingBean> getCREATOR() {
        return CREATOR;
    }
}
