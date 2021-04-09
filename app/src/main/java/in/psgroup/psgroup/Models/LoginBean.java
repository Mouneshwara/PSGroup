package in.psgroup.psgroup.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginBean implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sfdcId")
    @Expose
    private String sfdcId;
    @SerializedName("OTP")
    @Expose
    private String oTP;
    @SerializedName("BuyerType")
    @Expose
    private String buyerType;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSfdcId() {
        return sfdcId;
    }

    public void setSfdcId(String sfdcId) {
        this.sfdcId = sfdcId;
    }

    public String getOTP() {
        return oTP;
    }

    public void setOTP(String oTP) {
        this.oTP = oTP;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

}
