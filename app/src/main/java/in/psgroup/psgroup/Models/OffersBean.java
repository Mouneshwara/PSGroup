package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class OffersBean implements Parcelable {

    String sk_offer_id, offer_image, offer_heading, offer_desc, start_date, end_date, offer_percentage, property_id, offer_type, city, offer_status;

    public OffersBean(String sk_offer_id, String offer_image, String offer_heading, String offer_desc, String start_date, String end_date, String offer_percentage, String property_id, String offer_type, String city, String offer_status) {
        this.sk_offer_id = sk_offer_id;
        this.offer_image = offer_image;
        this.offer_heading = offer_heading;
        this.offer_desc = offer_desc;
        this.start_date = start_date;
        this.end_date = end_date;
        this.offer_percentage = offer_percentage;
        this.property_id = property_id;
        this.offer_type = offer_type;
        this.city = city;
        this.offer_status = offer_status;
    }

    protected OffersBean(Parcel in) {
        sk_offer_id = in.readString();
        offer_image = in.readString();
        offer_heading = in.readString();
        offer_desc = in.readString();
        start_date = in.readString();
        end_date = in.readString();
        offer_percentage = in.readString();
        property_id = in.readString();
        offer_type = in.readString();
        city = in.readString();
        offer_status = in.readString();
    }

    public static final Creator<OffersBean> CREATOR = new Creator<OffersBean>() {
        @Override
        public OffersBean createFromParcel(Parcel in) {
            return new OffersBean(in);
        }

        @Override
        public OffersBean[] newArray(int size) {
            return new OffersBean[size];
        }
    };

    public String getSk_offer_id() {
        return sk_offer_id;
    }

    public void setSk_offer_id(String sk_offer_id) {
        this.sk_offer_id = sk_offer_id;
    }

    public String getOffer_image() {
        return offer_image;
    }

    public void setOffer_image(String offer_image) {
        this.offer_image = offer_image;
    }

    public String getOffer_heading() {
        return offer_heading;
    }

    public void setOffer_heading(String offer_heading) {
        this.offer_heading = offer_heading;
    }

    public String getOffer_desc() {
        return offer_desc;
    }

    public void setOffer_desc(String offer_desc) {
        this.offer_desc = offer_desc;
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

    public String getOffer_percentage() {
        return offer_percentage;
    }

    public void setOffer_percentage(String offer_percentage) {
        this.offer_percentage = offer_percentage;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getOffer_type() {
        return offer_type;
    }

    public void setOffer_type(String offer_type) {
        this.offer_type = offer_type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOffer_status() {
        return offer_status;
    }

    public void setOffer_status(String offer_status) {
        this.offer_status = offer_status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sk_offer_id);
        dest.writeString(offer_image);
        dest.writeString(offer_heading);
        dest.writeString(offer_desc);
        dest.writeString(start_date);
        dest.writeString(end_date);
        dest.writeString(offer_percentage);
        dest.writeString(property_id);
        dest.writeString(offer_type);
        dest.writeString(city);
        dest.writeString(offer_status);
    }
}