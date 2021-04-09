
package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbyLandmark implements Parcelable
{

    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("property")
    @Expose
    private String property;
    @SerializedName("properties")
    @Expose
    private String properties;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("distance")
    @Expose
    private String distance;
    public final static Creator<NearbyLandmark> CREATOR = new Creator<NearbyLandmark>() {


        @SuppressWarnings({
            "unchecked"
        })
        public NearbyLandmark createFromParcel(Parcel in) {
            return new NearbyLandmark(in);
        }

        public NearbyLandmark[] newArray(int size) {
            return (new NearbyLandmark[size]);
        }

    }
    ;

    protected NearbyLandmark(Parcel in) {
        this.index = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.property = ((String) in.readValue((String.class.getClassLoader())));
        this.properties = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.distance = ((String) in.readValue((String.class.getClassLoader())));
    }

    public NearbyLandmark() {
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(index);
        dest.writeValue(property);
        dest.writeValue(properties);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(distance);
    }

    public int describeContents() {
        return  0;
    }

}
