package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ScheduleBean implements Parcelable{
    String day,date,time;
    boolean visiblity;

    protected ScheduleBean(Parcel in) {
        day = in.readString();
        date = in.readString();
        time = in.readString();
    }

    public static final Creator<ScheduleBean> CREATOR = new Creator<ScheduleBean>() {
        @Override
        public ScheduleBean createFromParcel(Parcel in) {
            return new ScheduleBean(in);
        }

        @Override
        public ScheduleBean[] newArray(int size) {
            return new ScheduleBean[size];
        }
    };

    public boolean isVisiblity() {
        return visiblity;
    }

    public void setVisiblity(boolean visiblity) {
        this.visiblity = visiblity;
    }

    public ScheduleBean(String day, String date, String time, boolean visiblity) {
        this.day = day;
        this.date = date;
        this.time = time;
        this.visiblity=visiblity;

    }



    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(day);
        dest.writeString(date);
        dest.writeString(time);
    }
}
