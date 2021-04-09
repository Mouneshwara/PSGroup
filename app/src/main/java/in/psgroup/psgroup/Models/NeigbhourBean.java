package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class NeigbhourBean implements Parcelable {
    String event_title,event_short_note,event_image,event_location,event_date;

    public NeigbhourBean(String event_title, String event_short_note, String event_image, String event_location, String event_date) {
        this.event_title = event_title;
        this.event_short_note = event_short_note;
        this.event_image = event_image;
        this.event_location = event_location;
        this.event_date = event_date;
    }

    protected NeigbhourBean(Parcel in) {
        event_title = in.readString();
        event_short_note = in.readString();
        event_image = in.readString();
        event_location = in.readString();
        event_date = in.readString();
    }

    public static final Creator<NeigbhourBean> CREATOR = new Creator<NeigbhourBean>() {
        @Override
        public NeigbhourBean createFromParcel(Parcel in) {
            return new NeigbhourBean(in);
        }

        @Override
        public NeigbhourBean[] newArray(int size) {
            return new NeigbhourBean[size];
        }
    };

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_short_note() {
        return event_short_note;
    }

    public void setEvent_short_note(String event_short_note) {
        this.event_short_note = event_short_note;
    }

    public String getEvent_image() {
        return event_image;
    }

    public void setEvent_image(String event_image) {
        this.event_image = event_image;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(event_title);
        dest.writeString(event_short_note);
        dest.writeString(event_image);
        dest.writeString(event_location);
        dest.writeString(event_date);
    }
}
