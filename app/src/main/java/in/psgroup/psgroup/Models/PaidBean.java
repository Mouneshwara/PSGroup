package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class PaidBean implements Parcelable  {


    String milestone_date,appartment,bhk,btn_ledger,milestone,paid_on,paid_time;

    public PaidBean(String milestone_date, String appartment, String bhk, String btn_ledger, String milestone, String paid_on, String paid_time) {
        this.milestone_date = milestone_date;
        this.appartment = appartment;
        this.bhk = bhk;
        this.btn_ledger = btn_ledger;
        this.milestone = milestone;
        this.paid_on = paid_on;
        this.paid_time = paid_time;
    }

    public String getMilestone_date() {
        return milestone_date;
    }

    public void setMilestone_date(String milestone_date) {
        this.milestone_date = milestone_date;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    public String getBhk() {
        return bhk;
    }

    public void setBhk(String bhk) {
        this.bhk = bhk;
    }

    public String getBtn_ledger() {
        return btn_ledger;
    }

    public void setBtn_ledger(String btn_ledger) {
        this.btn_ledger = btn_ledger;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getPaid_on() {
        return paid_on;
    }

    public void setPaid_on(String paid_on) {
        this.paid_on = paid_on;
    }

    public String getPaid_time() {
        return paid_time;
    }

    public void setPaid_time(String paid_time) {
        this.paid_time = paid_time;
    }

    public static Creator<PaidBean> getCREATOR() {
        return CREATOR;
    }

    protected PaidBean(Parcel in) {
        milestone_date = in.readString();
        appartment = in.readString();
        bhk = in.readString();
        btn_ledger = in.readString();
        milestone = in.readString();
        paid_on = in.readString();
        paid_time = in.readString();
    }

    public static final Creator<PaidBean> CREATOR = new Creator<PaidBean>() {
        @Override
        public PaidBean createFromParcel(Parcel in) {
            return new PaidBean(in);
        }

        @Override
        public PaidBean[] newArray(int size) {
            return new PaidBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(milestone_date);
        parcel.writeString(appartment);
        parcel.writeString(bhk);
        parcel.writeString(btn_ledger);
        parcel.writeString(milestone);
        parcel.writeString(paid_on);
        parcel.writeString(paid_time);
    }
}
