package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LaunchBean implements Parcelable {
    String launch_name,launch_description,image;

    public LaunchBean(String launch_name, String launch_description, String image) {
        this.launch_name = launch_name;
        this.launch_description = launch_description;
        this.image = image;
    }

    protected LaunchBean(Parcel in) {
        launch_name = in.readString();
        launch_description = in.readString();
        image = in.readString();
    }

    public static final Creator<LaunchBean> CREATOR = new Creator<LaunchBean>() {
        @Override
        public LaunchBean createFromParcel(Parcel in) {
            return new LaunchBean(in);
        }

        @Override
        public LaunchBean[] newArray(int size) {
            return new LaunchBean[size];
        }
    };

    public String getLaunch_name() {
        return launch_name;
    }

    public void setLaunch_name(String launch_name) {
        this.launch_name = launch_name;
    }

    public String getLaunch_description() {
        return launch_description;
    }

    public void setLaunch_description(String launch_description) {
        this.launch_description = launch_description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(launch_name);
        dest.writeString(launch_description);
        dest.writeString(image);
    }
}
