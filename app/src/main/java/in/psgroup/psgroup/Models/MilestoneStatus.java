
package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MilestoneStatus implements Parcelable
{

    @SerializedName("Max")
    @Expose
    private String max;
    @SerializedName("Current")
    @Expose
    private String current;
    @SerializedName("MilestoneName")
    @Expose
    private String milestoneName;
    public final static Creator<MilestoneStatus> CREATOR = new Creator<MilestoneStatus>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MilestoneStatus createFromParcel(Parcel in) {
            return new MilestoneStatus(in);
        }

        public MilestoneStatus[] newArray(int size) {
            return (new MilestoneStatus[size]);
        }

    }
    ;

    protected MilestoneStatus(Parcel in) {
        this.max = ((String) in.readValue((String.class.getClassLoader())));
        this.current = ((String) in.readValue((String.class.getClassLoader())));
        this.milestoneName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MilestoneStatus() {
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(max);
        dest.writeValue(current);
        dest.writeValue(milestoneName);
    }

    public int describeContents() {
        return  0;
    }

}
