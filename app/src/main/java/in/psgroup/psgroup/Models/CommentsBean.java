package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentsBean implements Parcelable {

    String name,comment,time;

    public CommentsBean(String name, String comment, String time) {
        this.name = name;
        this.comment = comment;
        this.time = time;
    }

    protected CommentsBean(Parcel in) {
        name = in.readString();
        comment = in.readString();
        time = in.readString();
    }

    public static final Creator<CommentsBean> CREATOR = new Creator<CommentsBean>() {
        @Override
        public CommentsBean createFromParcel(Parcel in) {
            return new CommentsBean(in);
        }

        @Override
        public CommentsBean[] newArray(int size) {
            return new CommentsBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(comment);
        parcel.writeString(time);
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static Creator<CommentsBean> getCREATOR() {
        return CREATOR;
    }
}
