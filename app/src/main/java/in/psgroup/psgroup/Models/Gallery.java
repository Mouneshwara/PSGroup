
package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gallery implements Parcelable
{

    @SerializedName("image")
    @Expose
    private String image;
    public final static Creator<Gallery> CREATOR = new Creator<Gallery>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Gallery createFromParcel(Parcel in) {
            return new Gallery(in);
        }

        public Gallery[] newArray(int size) {
            return (new Gallery[size]);
        }

    }
    ;

    protected Gallery(Parcel in) {
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Gallery() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(image);
    }

    public int describeContents() {
        return  0;
    }

}
