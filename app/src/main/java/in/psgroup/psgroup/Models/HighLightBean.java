package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class HighLightBean implements Parcelable{
    String category_name,category_image,description;

    public HighLightBean(String category_name, String category_image, String description) {
        this.category_name = category_name;
        this.category_image = category_image;
        this.description = description;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Creator<HighLightBean> getCREATOR() {
        return CREATOR;
    }

    protected HighLightBean(Parcel in) {
        category_name = in.readString();
        category_image = in.readString();
        description = in.readString();
    }

    public static final Creator<HighLightBean> CREATOR = new Creator<HighLightBean>() {
        @Override
        public HighLightBean createFromParcel(Parcel in) {
            return new HighLightBean(in);
        }

        @Override
        public HighLightBean[] newArray(int size) {
            return new HighLightBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category_name);
        dest.writeString(category_image);
        dest.writeString(description);
    }
}
