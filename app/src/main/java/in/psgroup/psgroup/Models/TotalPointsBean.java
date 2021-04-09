package in.psgroup.psgroup.Models;

/**
 * Created by Codebele on 27-Feb-19.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalPointsBean implements Parcelable
{
    String totalRemainingPoints,totalReferralPoints,totalRedeemPointsRequested,totalRedeemPointsProcessed,totalExpiredPoints,lastName,firstName,customerCode;

    public TotalPointsBean(String totalRemainingPoints, String totalReferralPoints, String totalRedeemPointsRequested, String totalRedeemPointsProcessed, String totalExpiredPoints, String lastName, String firstName, String customerCode) {
        this.totalRemainingPoints = totalRemainingPoints;
        this.totalReferralPoints = totalReferralPoints;
        this.totalRedeemPointsRequested = totalRedeemPointsRequested;
        this.totalRedeemPointsProcessed = totalRedeemPointsProcessed;
        this.totalExpiredPoints = totalExpiredPoints;
        this.lastName = lastName;
        this.firstName = firstName;
        this.customerCode = customerCode;
    }

    protected TotalPointsBean(Parcel in) {
        totalRemainingPoints = in.readString();
        totalReferralPoints = in.readString();
        totalRedeemPointsRequested = in.readString();
        totalRedeemPointsProcessed = in.readString();
        totalExpiredPoints = in.readString();
        lastName = in.readString();
        firstName = in.readString();
        customerCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(totalRemainingPoints);
        dest.writeString(totalReferralPoints);
        dest.writeString(totalRedeemPointsRequested);
        dest.writeString(totalRedeemPointsProcessed);
        dest.writeString(totalExpiredPoints);
        dest.writeString(lastName);
        dest.writeString(firstName);
        dest.writeString(customerCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TotalPointsBean> CREATOR = new Creator<TotalPointsBean>() {
        @Override
        public TotalPointsBean createFromParcel(Parcel in) {
            return new TotalPointsBean(in);
        }

        @Override
        public TotalPointsBean[] newArray(int size) {
            return new TotalPointsBean[size];
        }
    };

    public String getTotalRemainingPoints() {
        return totalRemainingPoints;
    }

    public void setTotalRemainingPoints(String totalRemainingPoints) {
        this.totalRemainingPoints = totalRemainingPoints;
    }

    public String getTotalReferralPoints() {
        return totalReferralPoints;
    }

    public void setTotalReferralPoints(String totalReferralPoints) {
        this.totalReferralPoints = totalReferralPoints;
    }

    public String getTotalRedeemPointsRequested() {
        return totalRedeemPointsRequested;
    }

    public void setTotalRedeemPointsRequested(String totalRedeemPointsRequested) {
        this.totalRedeemPointsRequested = totalRedeemPointsRequested;
    }

    public String getTotalRedeemPointsProcessed() {
        return totalRedeemPointsProcessed;
    }

    public void setTotalRedeemPointsProcessed(String totalRedeemPointsProcessed) {
        this.totalRedeemPointsProcessed = totalRedeemPointsProcessed;
    }

    public String getTotalExpiredPoints() {
        return totalExpiredPoints;
    }

    public void setTotalExpiredPoints(String totalExpiredPoints) {
        this.totalExpiredPoints = totalExpiredPoints;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
