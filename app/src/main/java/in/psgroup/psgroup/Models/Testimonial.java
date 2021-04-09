
package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Testimonial implements Parcelable
{

    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("quote")
    @Expose
    private String quote;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    public final static Creator<Testimonial> CREATOR = new Creator<Testimonial>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Testimonial createFromParcel(Parcel in) {
            return new Testimonial(in);
        }

        public Testimonial[] newArray(int size) {
            return (new Testimonial[size]);
        }

    }
    ;

    protected Testimonial(Parcel in) {
        this.index = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.quote = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Testimonial() {
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(index);
        dest.writeValue(quote);
        dest.writeValue(name);
        dest.writeValue(image);
    }

    public int describeContents() {
        return  0;
    }

}
