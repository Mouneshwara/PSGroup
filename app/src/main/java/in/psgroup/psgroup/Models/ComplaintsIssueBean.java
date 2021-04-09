package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ComplaintsIssueBean implements Parcelable{
    String sk_support_id,sk_category_id,support_title,support_heading,support_desc;

    public ComplaintsIssueBean(String sk_support_id, String sk_category_id, String support_title, String support_heading, String support_desc) {
        this.sk_support_id = sk_support_id;
        this.sk_category_id = sk_category_id;
        this.support_title = support_title;
        this.support_heading = support_heading;
        this.support_desc = support_desc;
    }

    protected ComplaintsIssueBean(Parcel in) {
        sk_support_id = in.readString();
        sk_category_id = in.readString();
        support_title = in.readString();
        support_heading = in.readString();
        support_desc = in.readString();
    }

    public static final Creator<ComplaintsIssueBean> CREATOR = new Creator<ComplaintsIssueBean>() {
        @Override
        public ComplaintsIssueBean createFromParcel(Parcel in) {
            return new ComplaintsIssueBean(in);
        }

        @Override
        public ComplaintsIssueBean[] newArray(int size) {
            return new ComplaintsIssueBean[size];
        }
    };

    public String getSk_support_id() {
        return sk_support_id;
    }

    public void setSk_support_id(String sk_support_id) {
        this.sk_support_id = sk_support_id;
    }

    public String getSk_category_id() {
        return sk_category_id;
    }

    public void setSk_category_id(String sk_category_id) {
        this.sk_category_id = sk_category_id;
    }

    public String getSupport_title() {
        return support_title;
    }

    public void setSupport_title(String support_title) {
        this.support_title = support_title;
    }

    public String getSupport_heading() {
        return support_heading;
    }

    public void setSupport_heading(String support_heading) {
        this.support_heading = support_heading;
    }

    public String getSupport_desc() {
        return support_desc;
    }

    public void setSupport_desc(String support_desc) {
        this.support_desc = support_desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sk_support_id);
        dest.writeString(sk_category_id);
        dest.writeString(support_title);
        dest.writeString(support_heading);
        dest.writeString(support_desc);
    }
}
