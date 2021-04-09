package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;


public class ProjectBean implements Parcelable {

    String sk_post_id, address, post_author, post_date, post_type, post_title, post_content, post_category, post_file_link, comment_count,
            state, location_id, start_date, end_date, starting_price, last_tran_date, post_status, project_status, digital_count, page_sort,bannerImage,end_price,city_name;
    Double latitude, longitude;
    boolean checked=false;


    public ProjectBean(String sk_post_id, String address, String post_author, String post_date, String post_type, String post_title, String post_content, String post_category, String post_file_link, String comment_count, String state, String location_id, String start_date, String end_date, String starting_price, String last_tran_date, String post_status, String project_status, String digital_count, String page_sort,String bannerImage,String end_price,String city_name ,Double latitude, Double longitude, boolean checked) {
        this.sk_post_id = sk_post_id;
        this.address = address;
        this.post_author = post_author;
        this.post_date = post_date;
        this.post_type = post_type;
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_category = post_category;
        this.post_file_link = post_file_link;
        this.comment_count = comment_count;
        this.state = state;
        this.location_id = location_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.starting_price = starting_price;
        this.last_tran_date = last_tran_date;
        this.post_status = post_status;
        this.project_status = project_status;
        this.digital_count = digital_count;
        this.page_sort = page_sort;
        this.latitude = latitude;
        this.longitude = longitude;

        this.bannerImage=bannerImage;
        this.end_price=end_price;
        this. city_name=city_name;
        this.checked = checked;

    }

    protected ProjectBean(Parcel in) {
        sk_post_id = in.readString();
        address = in.readString();
        post_author = in.readString();
        post_date = in.readString();
        post_type = in.readString();
        post_title = in.readString();
        post_content = in.readString();
        post_category = in.readString();
        post_file_link = in.readString();
        comment_count = in.readString();
        state = in.readString();
        location_id = in.readString();
        start_date = in.readString();
        end_date = in.readString();
        starting_price = in.readString();
        last_tran_date = in.readString();
        post_status = in.readString();
        bannerImage = in.readString();
        end_price= in.readString();
        city_name= in.readString();
        project_status = in.readString();
        digital_count = in.readString();
        checked = in.readByte() != 0;
        page_sort = in.readString();
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
    }

    public static final Creator<ProjectBean> CREATOR = new Creator<ProjectBean>() {
        @Override
        public ProjectBean createFromParcel(Parcel in) {
            return new ProjectBean(in);
        }

        @Override
        public ProjectBean[] newArray(int size) {
            return new ProjectBean[size];
        }
    };

    public String getSk_post_id() {
        return sk_post_id;
    }

    public void setSk_post_id(String sk_post_id) {
        this.sk_post_id = sk_post_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getLast_tran_date() {
        return last_tran_date;
    }

    public void setLast_tran_date(String last_tran_date) {
        this.last_tran_date = last_tran_date;
    }

    public String getPost_status() {
        return post_status;
    }

    public void setPost_status(String post_status) {
        this.post_status = post_status;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public String getDigital_count() {
        return digital_count;
    }

    public void setDigital_count(String digital_count) {
        this.digital_count = digital_count;
    }

    public String getPage_sort() {
        return page_sort;
    }

    public void setPage_sort(String page_sort) {
        this.page_sort = page_sort;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getEnd_price() {
        return end_price;
    }

    public void setEnd_price(String end_price) {
        this.end_price = end_price;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sk_post_id);
        dest.writeString(address);
        dest.writeString(post_author);
        dest.writeString(post_date);
        dest.writeString(post_type);
        dest.writeString(post_title);
        dest.writeString(post_content);
        dest.writeString(post_category);
        dest.writeString(post_file_link);
        dest.writeString(comment_count);
        dest.writeString(state);
        dest.writeString(location_id);
        dest.writeString(start_date);
        dest.writeString(end_date);
        dest.writeString(bannerImage);
        dest.writeString(end_price);
        dest.writeString(city_name);
        dest.writeString(starting_price);
        dest.writeString(last_tran_date);
        dest.writeString(post_status);
        dest.writeString(project_status);
        dest.writeString(digital_count);
        dest.writeString(page_sort);
        dest.writeByte((byte) (checked ? 1 : 0));

        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
    }
}