package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class BankBean implements Parcelable {
    String bank_name,bank_logo,asbank_banner,bank_display_printing,bank_short_desc,bank_desc,bank_city,benifits,bank_status,tran_status,documents,bank_id;

    public BankBean(String bank_name, String bank_logo, String asbank_banner, String bank_display_printing, String bank_short_desc, String bank_desc, String bank_city, String benifits, String bank_status, String tran_status, String documents, String bank_id) {
        this.bank_name = bank_name;
        this.bank_logo = bank_logo;
        this.asbank_banner = asbank_banner;
        this.bank_display_printing = bank_display_printing;
        this.bank_short_desc = bank_short_desc;
        this.bank_desc = bank_desc;
        this.bank_city = bank_city;
        this.benifits = benifits;
        this.bank_status = bank_status;
        this.tran_status = tran_status;
        this.documents = documents;
        this.bank_id = bank_id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_logo() {
        return bank_logo;
    }

    public void setBank_logo(String bank_logo) {
        this.bank_logo = bank_logo;
    }

    public String getAsbank_banner() {
        return asbank_banner;
    }

    public void setAsbank_banner(String asbank_banner) {
        this.asbank_banner = asbank_banner;
    }

    public String getBank_display_printing() {
        return bank_display_printing;
    }

    public void setBank_display_printing(String bank_display_printing) {
        this.bank_display_printing = bank_display_printing;
    }

    public String getBank_short_desc() {
        return bank_short_desc;
    }

    public void setBank_short_desc(String bank_short_desc) {
        this.bank_short_desc = bank_short_desc;
    }

    public String getBank_desc() {
        return bank_desc;
    }

    public void setBank_desc(String bank_desc) {
        this.bank_desc = bank_desc;
    }

    public String getBank_city() {
        return bank_city;
    }

    public void setBank_city(String bank_city) {
        this.bank_city = bank_city;
    }

    public String getBenifits() {
        return benifits;
    }

    public void setBenifits(String benifits) {
        this.benifits = benifits;
    }

    public String getBank_status() {
        return bank_status;
    }

    public void setBank_status(String bank_status) {
        this.bank_status = bank_status;
    }

    public String getTran_status() {
        return tran_status;
    }

    public void setTran_status(String tran_status) {
        this.tran_status = tran_status;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public static Creator<BankBean> getCREATOR() {
        return CREATOR;
    }

    protected BankBean(Parcel in) {
        bank_name = in.readString();
        bank_logo = in.readString();
        asbank_banner = in.readString();
        bank_display_printing = in.readString();
        bank_short_desc = in.readString();
        bank_desc = in.readString();
        bank_city = in.readString();
        benifits = in.readString();
        bank_status = in.readString();
        tran_status = in.readString();
        documents = in.readString();
        bank_id = in.readString();
    }

    public static final Creator<BankBean> CREATOR = new Creator<BankBean>() {
        @Override
        public BankBean createFromParcel(Parcel in) {
            return new BankBean(in);
        }

        @Override
        public BankBean[] newArray(int size) {
            return new BankBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bank_name);
        dest.writeString(bank_logo);
        dest.writeString(asbank_banner);
        dest.writeString(bank_display_printing);
        dest.writeString(bank_short_desc);
        dest.writeString(bank_desc);
        dest.writeString(bank_city);
        dest.writeString(benifits);
        dest.writeString(bank_status);
        dest.writeString(tran_status);
        dest.writeString(documents);
        dest.writeString(bank_id);
    }
}
