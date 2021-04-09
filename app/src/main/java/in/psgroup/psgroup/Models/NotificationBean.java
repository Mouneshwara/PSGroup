package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class NotificationBean implements Parcelable {

    String notificatio_timestamp,notification_type,notification_short_desc,notification_desc,notification_status,notification_time,notification_date;

    public NotificationBean(String notificatio_timestamp, String notification_type, String notification_short_desc, String notification_desc, String notification_status, String notification_time, String notification_date) {
        this.notificatio_timestamp = notificatio_timestamp;
        this.notification_type = notification_type;
        this.notification_short_desc = notification_short_desc;
        this.notification_desc = notification_desc;
        this.notification_status = notification_status;
        this.notification_time = notification_time;
        this.notification_date = notification_date;
    }

    public String getNotificatio_timestamp() {
        return notificatio_timestamp;
    }

    public void setNotificatio_timestamp(String notificatio_timestamp) {
        this.notificatio_timestamp = notificatio_timestamp;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public String getNotification_short_desc() {
        return notification_short_desc;
    }

    public void setNotification_short_desc(String notification_short_desc) {
        this.notification_short_desc = notification_short_desc;
    }

    public String getNotification_desc() {
        return notification_desc;
    }

    public void setNotification_desc(String notification_desc) {
        this.notification_desc = notification_desc;
    }

    public String getNotification_status() {
        return notification_status;
    }

    public void setNotification_status(String notification_status) {
        this.notification_status = notification_status;
    }

    public String getNotification_time() {
        return notification_time;
    }

    public void setNotification_time(String notification_time) {
        this.notification_time = notification_time;
    }

    public String getNotification_date() {
        return notification_date;
    }

    public void setNotification_date(String notification_date) {
        this.notification_date = notification_date;
    }

    public static Creator<NotificationBean> getCREATOR() {
        return CREATOR;
    }

    protected NotificationBean(Parcel in) {
        notificatio_timestamp = in.readString();
        notification_type = in.readString();
        notification_short_desc = in.readString();
        notification_desc = in.readString();
        notification_status = in.readString();
        notification_time = in.readString();
        notification_date = in.readString();
    }

    public static final Creator<NotificationBean> CREATOR = new Creator<NotificationBean>() {
        @Override
        public NotificationBean createFromParcel(Parcel in) {
            return new NotificationBean(in);
        }

        @Override
        public NotificationBean[] newArray(int size) {
            return new NotificationBean[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(notificatio_timestamp);
        dest.writeString(notification_type);
        dest.writeString(notification_short_desc);
        dest.writeString(notification_desc);
        dest.writeString(notification_status);
        dest.writeString(notification_time);
        dest.writeString(notification_date);
    }
}
