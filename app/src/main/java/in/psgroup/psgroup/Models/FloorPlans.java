package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class FloorPlans implements Parcelable{
    String detail_title,detail_name,detail_desc,sk_details_id,post_id,detail_type,detail_date,last_tran_date,detail_status;


    public FloorPlans(String detail_title, String detail_name, String detail_desc) {
        this.detail_title = detail_title;
        this.detail_name = detail_name;
        this.detail_desc = detail_desc;
    }

    protected FloorPlans(Parcel in) {
        detail_title = in.readString();
        detail_name = in.readString();
        detail_desc = in.readString();
    }

    public static final Creator<FloorPlans> CREATOR = new Creator<FloorPlans>() {
        @Override
        public FloorPlans createFromParcel(Parcel in) {
            return new FloorPlans(in);
        }

        @Override
        public FloorPlans[] newArray(int size) {
            return new FloorPlans[size];
        }
    };

    public String getDetail_title() {
        return detail_title;
    }

    public void setDetail_title(String detail_title) {
        this.detail_title = detail_title;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public void setDetail_name(String detail_name) {
        this.detail_name = detail_name;
    }

    public String getDetail_desc() {
        return detail_desc;
    }

    public void setDetail_desc(String detail_desc) {
        this.detail_desc = detail_desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(detail_title);
        dest.writeString(detail_name);
        dest.writeString(detail_desc);
    }
}
