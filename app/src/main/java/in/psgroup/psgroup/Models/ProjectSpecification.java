package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProjectSpecification implements Parcelable{
    String sk_details_id,post_id,detail_note,detail_type,detail_title,detail_name,detail_desc,detail_date,last_tran_date,detail_status;

    public ProjectSpecification(String sk_details_id, String post_id, String detail_note, String detail_type, String detail_title, String detail_name, String detail_desc, String detail_date, String last_tran_date, String detail_status) {
        this.sk_details_id = sk_details_id;
        this.post_id = post_id;
        this.detail_note = detail_note;
        this.detail_type = detail_type;
        this.detail_title = detail_title;
        this.detail_name = detail_name;
        this.detail_desc = detail_desc;
        this.detail_date = detail_date;
        this.last_tran_date = last_tran_date;
        this.detail_status = detail_status;
    }

    protected ProjectSpecification(Parcel in) {
        sk_details_id = in.readString();
        post_id = in.readString();
        detail_note = in.readString();
        detail_type = in.readString();
        detail_title = in.readString();
        detail_name = in.readString();
        detail_desc = in.readString();
        detail_date = in.readString();
        last_tran_date = in.readString();
        detail_status = in.readString();
    }

    public static final Creator<ProjectSpecification> CREATOR = new Creator<ProjectSpecification>() {
        @Override
        public ProjectSpecification createFromParcel(Parcel in) {
            return new ProjectSpecification(in);
        }

        @Override
        public ProjectSpecification[] newArray(int size) {
            return new ProjectSpecification[size];
        }
    };


    public String getSk_details_id() {
        return sk_details_id;
    }

    public void setSk_details_id(String sk_details_id) {
        this.sk_details_id = sk_details_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getDetail_note() {
        return detail_note;
    }

    public void setDetail_note(String detail_note) {
        this.detail_note = detail_note;
    }

    public String getDetail_type() {
        return detail_type;
    }

    public void setDetail_type(String detail_type) {
        this.detail_type = detail_type;
    }

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

    public String getDetail_date() {
        return detail_date;
    }

    public void setDetail_date(String detail_date) {
        this.detail_date = detail_date;
    }

    public String getLast_tran_date() {
        return last_tran_date;
    }

    public void setLast_tran_date(String last_tran_date) {
        this.last_tran_date = last_tran_date;
    }

    public String getDetail_status() {
        return detail_status;
    }

    public void setDetail_status(String detail_status) {
        this.detail_status = detail_status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sk_details_id);
        dest.writeString(post_id);
        dest.writeString(detail_note);
        dest.writeString(detail_type);
        dest.writeString(detail_title);
        dest.writeString(detail_name);
        dest.writeString(detail_desc);
        dest.writeString(detail_date);
        dest.writeString(last_tran_date);
        dest.writeString(detail_status);
    }
}
