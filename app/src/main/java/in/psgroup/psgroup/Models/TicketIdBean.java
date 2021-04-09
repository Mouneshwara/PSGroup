package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class TicketIdBean implements Parcelable {
    String name,comment,date;

    public TicketIdBean(String name, String comment, String date) {
        this.name = name;
        this.comment = comment;
        this.date = date;
    }

    protected TicketIdBean(Parcel in) {
        name = in.readString();
        comment = in.readString();
        date = in.readString();
    }

    public static final Creator<TicketIdBean> CREATOR = new Creator<TicketIdBean>() {
        @Override
        public TicketIdBean createFromParcel(Parcel in) {
            return new TicketIdBean(in);
        }

        @Override
        public TicketIdBean[] newArray(int size) {
            return new TicketIdBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(comment);
        parcel.writeString(date);
    }
}
