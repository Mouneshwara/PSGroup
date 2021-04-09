package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HelpBean implements  Parcelable {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;

    protected HelpBean(Parcel in) {
        categoryId = in.readString();
        categoryName = in.readString();
    }

    public static final Creator<HelpBean> CREATOR = new Creator<HelpBean>() {
        @Override
        public HelpBean createFromParcel(Parcel in) {
            return new HelpBean(in);
        }

        @Override
        public HelpBean[] newArray(int size) {
            return new HelpBean[size];
        }
    };

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryId);
        dest.writeString(categoryName);
    }
}
