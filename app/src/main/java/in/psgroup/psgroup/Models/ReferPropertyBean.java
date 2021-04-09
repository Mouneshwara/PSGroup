package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ReferPropertyBean implements Parcelable {

    String image,name,bhk,location,/*points_to_earned,hira_no,website,*/ammount;
    boolean checked=false;

    public ReferPropertyBean(String image, String name, String bhk, String location, /*String points_to_earned, String hira_no, String website,*/ String ammount, boolean checked) {
        this.image = image;
        this.name = name;
        this.bhk = bhk;
        this.location = location;
        /*this.points_to_earned = points_to_earned;
        this.hira_no = hira_no;
        this.website = website;*/
        this.ammount = ammount;
        this.checked = checked;
    }

    protected ReferPropertyBean(Parcel in) {
        image = in.readString();
        name = in.readString();
        bhk = in.readString();
        location = in.readString();
       /* points_to_earned = in.readString();
        hira_no = in.readString();
        website = in.readString();*/
        ammount = in.readString();
        checked = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(bhk);
        dest.writeString(location);
       /* dest.writeString(points_to_earned);
        dest.writeString(hira_no);
        dest.writeString(website);*/
        dest.writeString(ammount);
        dest.writeByte((byte) (checked ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReferPropertyBean> CREATOR = new Creator<ReferPropertyBean>() {
        @Override
        public ReferPropertyBean createFromParcel(Parcel in) {
            return new ReferPropertyBean(in);
        }

        @Override
        public ReferPropertyBean[] newArray(int size) {
            return new ReferPropertyBean[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBhk() {
        return bhk;
    }

    public void setBhk(String bhk) {
        this.bhk = bhk;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

   /* public String getPoints_to_earned() {
        return points_to_earned;
    }

    public void setPoints_to_earned(String points_to_earned) {
        this.points_to_earned = points_to_earned;
    }*/

   /* public String getHira_no() {
        return hira_no;
    }

    public void setHira_no(String hira_no) {
        this.hira_no = hira_no;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }*/

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
