package in.psgroup.psgroup.NotificationsModule.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NotificationsBean implements Serializable {


    @SerializedName("sk_notification_id")
    @Expose
    private String skNotificationId;
    @SerializedName("notification_date")
    @Expose
    private String notificationDate;
    @SerializedName("notification_time")
    @Expose
    private String notificationTime;
    @SerializedName("notification_type")
    @Expose
    private String notificationType;
    @SerializedName("notification_short_desc")
    @Expose
    private String notificationShortDesc;
    @SerializedName("notification_desc")
    @Expose
    private String notificationDesc;
    @SerializedName("notification_id")
    @Expose
    private String notificationId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("read_status")
    @Expose
    private String readStatus;
    @SerializedName("duration")
    @Expose
    private String duration;

    public String getSkNotificationId() {
        return skNotificationId;
    }

    public void setSkNotificationId(String skNotificationId) {
        this.skNotificationId = skNotificationId;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationShortDesc() {
        return notificationShortDesc;
    }

    public void setNotificationShortDesc(String notificationShortDesc) {
        this.notificationShortDesc = notificationShortDesc;
    }

    public String getNotificationDesc() {
        return notificationDesc;
    }

    public void setNotificationDesc(String notificationDesc) {
        this.notificationDesc = notificationDesc;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
