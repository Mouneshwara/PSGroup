package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ViewTicketBean implements Parcelable {
    String progress_status,date,ticket_id,status,category,sub_category ;

    public ViewTicketBean(String progress_status, String date, String ticket_id, String status, String category, String sub_category) {
        this.progress_status = progress_status;
        this.date = date;
        this.ticket_id = ticket_id;
        this.status = status;
        this.category = category;
        this.sub_category = sub_category;
    }

    protected ViewTicketBean(Parcel in) {
        progress_status = in.readString();
        date = in.readString();
        ticket_id = in.readString();
        status = in.readString();
        category = in.readString();
        sub_category = in.readString();
    }

    public static final Creator<ViewTicketBean> CREATOR = new Creator<ViewTicketBean>() {
        @Override
        public ViewTicketBean createFromParcel(Parcel in) {
            return new ViewTicketBean(in);
        }

        @Override
        public ViewTicketBean[] newArray(int size) {
            return new ViewTicketBean[size];
        }
    };

    public String getProgress_status() {
        return progress_status;
    }

    public void setProgress_status(String progress_status) {
        this.progress_status = progress_status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public static Creator<ViewTicketBean> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(progress_status);
        parcel.writeString(date);
        parcel.writeString(ticket_id);
        parcel.writeString(status);
        parcel.writeString(category);
        parcel.writeString(sub_category);
    }
}
