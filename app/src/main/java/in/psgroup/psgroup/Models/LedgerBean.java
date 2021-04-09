package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class LedgerBean implements Parcelable {

    String date,amount,balance,demand,receipt,check_no,drawn_on,amount_tax,tax,eft_no;

    public LedgerBean(String date, String amount, String balance, String demand, String receipt, String check_no, String drawn_on, String amount_tax, String tax, String eft_no) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
        this.demand = demand;
        this.receipt = receipt;
        this.check_no = check_no;
        this.drawn_on = drawn_on;
        this.amount_tax = amount_tax;
        this.tax = tax;
        this.eft_no = eft_no;
    }

    protected LedgerBean(Parcel in) {
        date = in.readString();
        amount = in.readString();
        balance = in.readString();
        demand = in.readString();
        receipt = in.readString();
        check_no = in.readString();
        drawn_on = in.readString();
        amount_tax = in.readString();
        tax = in.readString();
        eft_no = in.readString();
    }

    public static final Creator<LedgerBean> CREATOR = new Creator<LedgerBean>() {
        @Override
        public LedgerBean createFromParcel(Parcel in) {
            return new LedgerBean(in);
        }

        @Override
        public LedgerBean[] newArray(int size) {
            return new LedgerBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(amount);
        dest.writeString(balance);
        dest.writeString(demand);
        dest.writeString(receipt);
        dest.writeString(check_no);
        dest.writeString(drawn_on);
        dest.writeString(amount_tax);
        dest.writeString(tax);
        dest.writeString(eft_no);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getCheck_no() {
        return check_no;
    }

    public void setCheck_no(String check_no) {
        this.check_no = check_no;
    }

    public String getDrawn_on() {
        return drawn_on;
    }

    public void setDrawn_on(String drawn_on) {
        this.drawn_on = drawn_on;
    }

    public String getAmount_tax() {
        return amount_tax;
    }

    public void setAmount_tax(String amount_tax) {
        this.amount_tax = amount_tax;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getEft_no() {
        return eft_no;
    }

    public void setEft_no(String eft_no) {
        this.eft_no = eft_no;
    }

    public static Creator<LedgerBean> getCREATOR() {
        return CREATOR;
    }
}
