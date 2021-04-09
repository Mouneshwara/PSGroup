
package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectOverview implements Parcelable
{

    @SerializedName("sk_details_id")
    @Expose
    private String skDetailsId;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("detail_type")
    @Expose
    private String detailType;
    @SerializedName("detail_title")
    @Expose
    private Object detailTitle;
    @SerializedName("detail_name")
    @Expose
    private Object detailName;
    @SerializedName("detail_desc")
    @Expose
    private String detailDesc;
    @SerializedName("detail_date")
    @Expose
    private String detailDate;
    @SerializedName("last_tran_date")
    @Expose
    private String lastTranDate;
    @SerializedName("detail_status")
    @Expose
    private String detailStatus;
    public final static Creator<ProjectOverview> CREATOR = new Creator<ProjectOverview>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProjectOverview createFromParcel(Parcel in) {
            return new ProjectOverview(in);
        }

        public ProjectOverview[] newArray(int size) {
            return (new ProjectOverview[size]);
        }

    }
    ;

    protected ProjectOverview(Parcel in) {
        this.skDetailsId = ((String) in.readValue((String.class.getClassLoader())));
        this.postId = ((String) in.readValue((String.class.getClassLoader())));
        this.detailType = ((String) in.readValue((String.class.getClassLoader())));
        this.detailTitle = ((Object) in.readValue((Object.class.getClassLoader())));
        this.detailName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.detailDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.detailDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastTranDate = ((String) in.readValue((String.class.getClassLoader())));
        this.detailStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProjectOverview() {
    }

    public String getSkDetailsId() {
        return skDetailsId;
    }

    public void setSkDetailsId(String skDetailsId) {
        this.skDetailsId = skDetailsId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public Object getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(Object detailTitle) {
        this.detailTitle = detailTitle;
    }

    public Object getDetailName() {
        return detailName;
    }

    public void setDetailName(Object detailName) {
        this.detailName = detailName;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getDetailDate() {
        return detailDate;
    }

    public void setDetailDate(String detailDate) {
        this.detailDate = detailDate;
    }

    public String getLastTranDate() {
        return lastTranDate;
    }

    public void setLastTranDate(String lastTranDate) {
        this.lastTranDate = lastTranDate;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(skDetailsId);
        dest.writeValue(postId);
        dest.writeValue(detailType);
        dest.writeValue(detailTitle);
        dest.writeValue(detailName);
        dest.writeValue(detailDesc);
        dest.writeValue(detailDate);
        dest.writeValue(lastTranDate);
        dest.writeValue(detailStatus);
    }

    public int describeContents() {
        return  0;
    }

}
