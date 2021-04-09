package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class BlogBean implements Parcelable {
    String Index_id, BlogUrl, post_title, post_content, sk_post_id, post_author, post_date, post_type, post_category, post_file_link, comment_count, state, location_id, address, start_date, end_date, starting_price, end_price, latitude, longitude, project_status, last_tran_date;

    public BlogBean(String index_id, String blogUrl, String post_title, String post_content, String sk_post_id, String post_author, String post_date, String post_type, String post_category, String post_file_link, String comment_count, String state, String location_id, String address, String start_date, String end_date, String starting_price, String end_price, String latitude, String longitude, String project_status, String last_tran_date) {
        Index_id = index_id;
        BlogUrl = blogUrl;
        this.post_title = post_title;
        this.post_content = post_content;
        this.sk_post_id = sk_post_id;
        this.post_author = post_author;
        this.post_date = post_date;
        this.post_type = post_type;
        this.post_category = post_category;
        this.post_file_link = post_file_link;
        this.comment_count = comment_count;
        this.state = state;
        this.location_id = location_id;
        this.address = address;
        this.start_date = start_date;
        this.end_date = end_date;
        this.starting_price = starting_price;
        this.end_price = end_price;
        this.latitude = latitude;
        this.longitude = longitude;
        this.project_status = project_status;
        this.last_tran_date = last_tran_date;
    }

    protected BlogBean(Parcel in) {
        Index_id = in.readString();
        BlogUrl = in.readString();
        post_title = in.readString();
        post_content = in.readString();
        sk_post_id = in.readString();
        post_author = in.readString();
        post_date = in.readString();
        post_type = in.readString();
        post_category = in.readString();
        post_file_link = in.readString();
        comment_count = in.readString();
        state = in.readString();
        location_id = in.readString();
        address = in.readString();
        start_date = in.readString();
        end_date = in.readString();
        starting_price = in.readString();
        end_price = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        project_status = in.readString();
        last_tran_date = in.readString();
    }

    public String getIndex_id() {
        return Index_id;
    }

    public void setIndex_id(String index_id) {
        Index_id = index_id;
    }

    public String getBlogUrl() {
        return BlogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        BlogUrl = blogUrl;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getSk_post_id() {
        return sk_post_id;
    }

    public void setSk_post_id(String sk_post_id) {
        this.sk_post_id = sk_post_id;
    }

    public String getPost_author() {
        return post_author;
    }

    public void setPost_author(String post_author) {
        this.post_author = post_author;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getPost_category() {
        return post_category;
    }

    public void setPost_category(String post_category) {
        this.post_category = post_category;
    }

    public String getPost_file_link() {
        return post_file_link;
    }

    public void setPost_file_link(String post_file_link) {
        this.post_file_link = post_file_link;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(String starting_price) {
        this.starting_price = starting_price;
    }

    public String getEnd_price() {
        return end_price;
    }

    public void setEnd_price(String end_price) {
        this.end_price = end_price;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public String getLast_tran_date() {
        return last_tran_date;
    }

    public void setLast_tran_date(String last_tran_date) {
        this.last_tran_date = last_tran_date;
    }

    public static Creator<BlogBean> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Index_id);
        dest.writeString(BlogUrl);
        dest.writeString(post_title);
        dest.writeString(post_content);
        dest.writeString(sk_post_id);
        dest.writeString(post_author);
        dest.writeString(post_date);
        dest.writeString(post_type);
        dest.writeString(post_category);
        dest.writeString(post_file_link);
        dest.writeString(comment_count);
        dest.writeString(state);
        dest.writeString(location_id);
        dest.writeString(address);
        dest.writeString(start_date);
        dest.writeString(end_date);
        dest.writeString(starting_price);
        dest.writeString(end_price);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(project_status);
        dest.writeString(last_tran_date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BlogBean> CREATOR = new Creator<BlogBean>() {
        @Override
        public BlogBean createFromParcel(Parcel in) {
            return new BlogBean(in);
        }

        @Override
        public BlogBean[] newArray(int size) {
            return new BlogBean[size];
        }
    };
}
