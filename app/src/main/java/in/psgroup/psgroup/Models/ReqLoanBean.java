package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ReqLoanBean implements Parcelable {

    int image,checkbox,loc;
    String name,number,type,location;

    public ReqLoanBean(int image, int checkbox, int loc, String name, String number, String type, String location) {
        this.image = image;
        this.checkbox = checkbox;
        this.loc = loc;
        this.name = name;
        this.number = number;
        this.type = type;
        this.location = location;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(int checkbox) {
        this.checkbox = checkbox;
    }

    public int getLoc() {
        return loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static Creator<ReqLoanBean> getCREATOR() {
        return CREATOR;
    }

    protected ReqLoanBean(Parcel in) {
    }

    public static final Creator<ReqLoanBean> CREATOR = new Creator<ReqLoanBean>() {
        @Override
        public ReqLoanBean createFromParcel(Parcel in) {
            return new ReqLoanBean(in);
        }

        @Override
        public ReqLoanBean[] newArray(int size) {
            return new ReqLoanBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
