package in.psgroup.psgroup.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OtpBean implements Serializable {

    @SerializedName("sk_customer_id")
    @Expose
    private String skCustomerId;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("login_status")
    @Expose
    private Object loginStatus;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("property_name")
    @Expose
    private Object propertyName;
    @SerializedName("unit_number")
    @Expose
    private Object unitNumber;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("ctype")
    @Expose
    private Object ctype;
    @SerializedName("loyaltycode")
    @Expose
    private Object loyaltycode;
    @SerializedName("customertype")
    @Expose
    private Object customertype;
    @SerializedName("sfdcId")
    @Expose
    private String sfdcId;
    @SerializedName("NRI_PowerOfAttorneyHolderName")
    @Expose
    private String nRIPowerOfAttorneyHolderName;
    @SerializedName("NRI_PassportNumber")
    @Expose
    private String nRIPassportNumber;
    @SerializedName("NRI_LocalContactState")
    @Expose
    private String nRILocalContactState;
    @SerializedName("NRI_LocalContactPin")
    @Expose
    private String nRILocalContactPin;
    @SerializedName("NRI_LocalContactName")
    @Expose
    private String nRILocalContactName;
    @SerializedName("NRI_LocalContactMobile")
    @Expose
    private String nRILocalContactMobile;
    @SerializedName("NRI_LocalContactDistrict")
    @Expose
    private String nRILocalContactDistrict;
    @SerializedName("NRI_LocalContactCountry")
    @Expose
    private String nRILocalContactCountry;
    @SerializedName("NRI_LocalContactCorrespondenceAddress")
    @Expose
    private String nRILocalContactCorrespondenceAddress;
    @SerializedName("NRI_LocalContactCity")
    @Expose
    private String nRILocalContactCity;
    @SerializedName("NRI_LocalContactAddress")
    @Expose
    private String nRILocalContactAddress;
    @SerializedName("C_State")
    @Expose
    private String cState;
    @SerializedName("C_ResidentialStatus")
    @Expose
    private String cResidentialStatus;
    @SerializedName("C_PostOffice")
    @Expose
    private String cPostOffice;
    @SerializedName("C_PoliceStation")
    @Expose
    private String cPoliceStation;
    @SerializedName("C_Pin")
    @Expose
    private String cPin;
    @SerializedName("C_PermanentAddress")
    @Expose
    private String cPermanentAddress;
    @SerializedName("C_PanNumber")
    @Expose
    private String cPanNumber;
    @SerializedName("C_Occupation")
    @Expose
    private String cOccupation;
    @SerializedName("C_GSTIN")
    @Expose
    private String cGSTIN;
    @SerializedName("C_FatherOrHusbandName")
    @Expose
    private String cFatherOrHusbandName;
    @SerializedName("C_District")
    @Expose
    private String cDistrict;
    @SerializedName("C_DateOfBirth")
    @Expose
    private String cDateOfBirth;
    @SerializedName("C_DateOfAnniversary")
    @Expose
    private String cDateOfAnniversary;
    @SerializedName("C_Country")
    @Expose
    private String cCountry;
    @SerializedName("C_City")
    @Expose
    private String cCity;
    @SerializedName("C_AdhaarNumber")
    @Expose
    private String cAdhaarNumber;
    @SerializedName("C_AddressProofDocumentNumber")
    @Expose
    private String cAddressProofDocumentNumber;
    @SerializedName("C_AddressProofDocumentName")
    @Expose
    private String cAddressProofDocumentName;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("BuyerType")
    @Expose
    private String buyerType;

    public String getSkCustomerId() {
        return skCustomerId;
    }

    public void setSkCustomerId(String skCustomerId) {
        this.skCustomerId = skCustomerId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Object loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(Object propertyName) {
        this.propertyName = propertyName;
    }

    public Object getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Object unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getCtype() {
        return ctype;
    }

    public void setCtype(Object ctype) {
        this.ctype = ctype;
    }

    public Object getLoyaltycode() {
        return loyaltycode;
    }

    public void setLoyaltycode(Object loyaltycode) {
        this.loyaltycode = loyaltycode;
    }

    public Object getCustomertype() {
        return customertype;
    }

    public void setCustomertype(Object customertype) {
        this.customertype = customertype;
    }

    public String getSfdcId() {
        return sfdcId;
    }

    public void setSfdcId(String sfdcId) {
        this.sfdcId = sfdcId;
    }

    public String getNRIPowerOfAttorneyHolderName() {
        return nRIPowerOfAttorneyHolderName;
    }

    public void setNRIPowerOfAttorneyHolderName(String nRIPowerOfAttorneyHolderName) {
        this.nRIPowerOfAttorneyHolderName = nRIPowerOfAttorneyHolderName;
    }

    public String getNRIPassportNumber() {
        return nRIPassportNumber;
    }

    public void setNRIPassportNumber(String nRIPassportNumber) {
        this.nRIPassportNumber = nRIPassportNumber;
    }

    public String getNRILocalContactState() {
        return nRILocalContactState;
    }

    public void setNRILocalContactState(String nRILocalContactState) {
        this.nRILocalContactState = nRILocalContactState;
    }

    public String getNRILocalContactPin() {
        return nRILocalContactPin;
    }

    public void setNRILocalContactPin(String nRILocalContactPin) {
        this.nRILocalContactPin = nRILocalContactPin;
    }

    public String getNRILocalContactName() {
        return nRILocalContactName;
    }

    public void setNRILocalContactName(String nRILocalContactName) {
        this.nRILocalContactName = nRILocalContactName;
    }

    public String getNRILocalContactMobile() {
        return nRILocalContactMobile;
    }

    public void setNRILocalContactMobile(String nRILocalContactMobile) {
        this.nRILocalContactMobile = nRILocalContactMobile;
    }

    public String getNRILocalContactDistrict() {
        return nRILocalContactDistrict;
    }

    public void setNRILocalContactDistrict(String nRILocalContactDistrict) {
        this.nRILocalContactDistrict = nRILocalContactDistrict;
    }

    public String getNRILocalContactCountry() {
        return nRILocalContactCountry;
    }

    public void setNRILocalContactCountry(String nRILocalContactCountry) {
        this.nRILocalContactCountry = nRILocalContactCountry;
    }

    public String getNRILocalContactCorrespondenceAddress() {
        return nRILocalContactCorrespondenceAddress;
    }

    public void setNRILocalContactCorrespondenceAddress(String nRILocalContactCorrespondenceAddress) {
        this.nRILocalContactCorrespondenceAddress = nRILocalContactCorrespondenceAddress;
    }

    public String getNRILocalContactCity() {
        return nRILocalContactCity;
    }

    public void setNRILocalContactCity(String nRILocalContactCity) {
        this.nRILocalContactCity = nRILocalContactCity;
    }

    public String getNRILocalContactAddress() {
        return nRILocalContactAddress;
    }

    public void setNRILocalContactAddress(String nRILocalContactAddress) {
        this.nRILocalContactAddress = nRILocalContactAddress;
    }

    public String getCState() {
        return cState;
    }

    public void setCState(String cState) {
        this.cState = cState;
    }

    public String getCResidentialStatus() {
        return cResidentialStatus;
    }

    public void setCResidentialStatus(String cResidentialStatus) {
        this.cResidentialStatus = cResidentialStatus;
    }

    public String getCPostOffice() {
        return cPostOffice;
    }

    public void setCPostOffice(String cPostOffice) {
        this.cPostOffice = cPostOffice;
    }

    public String getCPoliceStation() {
        return cPoliceStation;
    }

    public void setCPoliceStation(String cPoliceStation) {
        this.cPoliceStation = cPoliceStation;
    }

    public String getCPin() {
        return cPin;
    }

    public void setCPin(String cPin) {
        this.cPin = cPin;
    }

    public String getCPermanentAddress() {
        return cPermanentAddress;
    }

    public void setCPermanentAddress(String cPermanentAddress) {
        this.cPermanentAddress = cPermanentAddress;
    }

    public String getCPanNumber() {
        return cPanNumber;
    }

    public void setCPanNumber(String cPanNumber) {
        this.cPanNumber = cPanNumber;
    }

    public String getCOccupation() {
        return cOccupation;
    }

    public void setCOccupation(String cOccupation) {
        this.cOccupation = cOccupation;
    }

    public String getCGSTIN() {
        return cGSTIN;
    }

    public void setCGSTIN(String cGSTIN) {
        this.cGSTIN = cGSTIN;
    }

    public String getCFatherOrHusbandName() {
        return cFatherOrHusbandName;
    }

    public void setCFatherOrHusbandName(String cFatherOrHusbandName) {
        this.cFatherOrHusbandName = cFatherOrHusbandName;
    }

    public String getCDistrict() {
        return cDistrict;
    }

    public void setCDistrict(String cDistrict) {
        this.cDistrict = cDistrict;
    }

    public String getCDateOfBirth() {
        return cDateOfBirth;
    }

    public void setCDateOfBirth(String cDateOfBirth) {
        this.cDateOfBirth = cDateOfBirth;
    }

    public String getCDateOfAnniversary() {
        return cDateOfAnniversary;
    }

    public void setCDateOfAnniversary(String cDateOfAnniversary) {
        this.cDateOfAnniversary = cDateOfAnniversary;
    }

    public String getCCountry() {
        return cCountry;
    }

    public void setCCountry(String cCountry) {
        this.cCountry = cCountry;
    }

    public String getCCity() {
        return cCity;
    }

    public void setCCity(String cCity) {
        this.cCity = cCity;
    }

    public String getCAdhaarNumber() {
        return cAdhaarNumber;
    }

    public void setCAdhaarNumber(String cAdhaarNumber) {
        this.cAdhaarNumber = cAdhaarNumber;
    }

    public String getCAddressProofDocumentNumber() {
        return cAddressProofDocumentNumber;
    }

    public void setCAddressProofDocumentNumber(String cAddressProofDocumentNumber) {
        this.cAddressProofDocumentNumber = cAddressProofDocumentNumber;
    }

    public String getCAddressProofDocumentName() {
        return cAddressProofDocumentName;
    }

    public void setCAddressProofDocumentName(String cAddressProofDocumentName) {
        this.cAddressProofDocumentName = cAddressProofDocumentName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }
}
