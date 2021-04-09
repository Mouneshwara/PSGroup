package in.psgroup.psgroup.Models;

/**
 * Created by Codebele on 27-Feb-19.
 */
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReferralHistoryBean implements Parcelable
{

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("referralDate")
    @Expose
    private String referralDate;
    @SerializedName("projectName")
    @Expose
    private String projectName;
    @SerializedName("PostalCode")
    @Expose
    private String postalCode;
    @SerializedName("pointsEarned")
    @Expose
    private String pointsEarned;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("customerCode")
    @Expose
    private String customerCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("Address")
    @Expose
    private String address;
    public final static Parcelable.Creator<ReferralHistoryBean> CREATOR = new Creator<ReferralHistoryBean>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReferralHistoryBean createFromParcel(Parcel in) {
            return new ReferralHistoryBean(in);
        }

        public ReferralHistoryBean[] newArray(int size) {
            return (new ReferralHistoryBean[size]);
        }

    }
            ;

    protected ReferralHistoryBean(Parcel in) {
        this.street = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.referralDate = ((String) in.readValue((String.class.getClassLoader())));
        this.projectName = ((String) in.readValue((String.class.getClassLoader())));
        this.postalCode = ((String) in.readValue((String.class.getClassLoader())));
        this.pointsEarned = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.customerCode = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ReferralHistoryBean() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(String referralDate) {
        this.referralDate = referralDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(String pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(street);
        dest.writeValue(status);
        dest.writeValue(state);
        dest.writeValue(referralDate);
        dest.writeValue(projectName);
        dest.writeValue(postalCode);
        dest.writeValue(pointsEarned);
        dest.writeValue(mobile);
        dest.writeValue(lastName);
        dest.writeValue(firstName);
        dest.writeValue(email);
        dest.writeValue(customerCode);
        dest.writeValue(country);
        dest.writeValue(city);
        dest.writeValue(address);
    }

    public int describeContents() {
        return 0;
    }

}