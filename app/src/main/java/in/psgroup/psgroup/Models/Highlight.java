
package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Highlight implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amenities")
    @Expose
    private String amenities;
    @SerializedName("image")
    @Expose
    private String image;
    public final static Creator<Highlight> CREATOR = new Creator<Highlight>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Highlight createFromParcel(Parcel in) {
            return new Highlight(in);
        }

        public Highlight[] newArray(int size) {
            return (new Highlight[size]);
        }

    }
    ;

    protected Highlight(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.amenities = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Highlight() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(amenities);
        dest.writeValue(image);
    }

    public int describeContents() {
        return  0;
    }

}
