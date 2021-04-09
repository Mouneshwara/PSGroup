package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoBean implements Parcelable {
    String videolink,videodescription,videode_image;

    protected VideoBean(Parcel in) {
        videolink = in.readString();
        videodescription = in.readString();
        videode_image = in.readString();
    }

    public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel in) {
            return new VideoBean(in);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    public String getVideodescription() {
        return videodescription;
    }

    public void setVideodescription(String videodescription) {
        this.videodescription = videodescription;
    }

    public String getVideode_image() {
        return videode_image;
    }

    public void setVideode_image(String videode_image) {
        this.videode_image = videode_image;
    }

    public VideoBean(String videolink, String videodescription, String videode_image) {
        this.videolink = videolink;
        this.videodescription = videodescription;
        this.videode_image = videode_image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videolink);
        dest.writeString(videodescription);
        dest.writeString(videode_image);
    }
}
