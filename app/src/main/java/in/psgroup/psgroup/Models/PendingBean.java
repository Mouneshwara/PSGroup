package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class PendingBean implements Parcelable  {

    String appartment,bhk,invoice_btn,amount_due,pending_days,interest_charged,milestone;

    public PendingBean(String appartment, String bhk, String invoice_btn, String amount_due, String pending_days, String interest_charged, String milestone) {
        this.appartment = appartment;
        this.bhk = bhk;
        this.invoice_btn = invoice_btn;
        this.amount_due = amount_due;
        this.pending_days = pending_days;
        this.interest_charged = interest_charged;
        this.milestone = milestone;
    }

    protected PendingBean(Parcel in) {
        appartment = in.readString();
        bhk = in.readString();
        invoice_btn = in.readString();
        amount_due = in.readString();
        pending_days = in.readString();
        interest_charged = in.readString();
        milestone = in.readString();
    }

    public static final Creator<PendingBean> CREATOR = new Creator<PendingBean>() {
        @Override
        public PendingBean createFromParcel(Parcel in) {
            return new PendingBean(in);
        }

        @Override
        public PendingBean[] newArray(int size) {
            return new PendingBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appartment);
        parcel.writeString(bhk);
        parcel.writeString(invoice_btn);
        parcel.writeString(amount_due);
        parcel.writeString(pending_days);
        parcel.writeString(interest_charged);
        parcel.writeString(milestone);
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

    public String getInvoice_btn() {
        return invoice_btn;
    }

    public void setInvoice_btn(String invoice_btn) {
        this.invoice_btn = invoice_btn;
    }

    public String getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(String amount_due) {
        this.amount_due = amount_due;
    }

    public String getPending_days() {
        return pending_days;
    }

    public void setPending_days(String pending_days) {
        this.pending_days = pending_days;
    }

    public String getInterest_charged() {
        return interest_charged;
    }

    public void setInterest_charged(String interest_charged) {
        this.interest_charged = interest_charged;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public static Creator<PendingBean> getCREATOR() {
        return CREATOR;
    }
}
