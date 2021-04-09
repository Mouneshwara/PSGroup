package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckPointHistoryBean implements Serializable {

    String Type,ReferredCustomerName,ProjectName,Points,DateofPoint;

    public CheckPointHistoryBean(String type, String referredCustomerName, String projectName, String points, String dateofPoint) {
        Type = type;
        ReferredCustomerName = referredCustomerName;
        ProjectName = projectName;
        Points = points;
        DateofPoint = dateofPoint;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getReferredCustomerName() {
        return ReferredCustomerName;
    }

    public void setReferredCustomerName(String referredCustomerName) {
        ReferredCustomerName = referredCustomerName;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }

    public String getDateofPoint() {
        return DateofPoint;
    }

    public void setDateofPoint(String dateofPoint) {
        DateofPoint = dateofPoint;
    }
}