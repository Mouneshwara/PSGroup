package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class QueryBean implements Parcelable{
    String sk_category_id,category_name,support_type,category_status;
    boolean extendable;

    public QueryBean(String sk_category_id, String category_name, String support_type, String category_status) {
        this.sk_category_id = sk_category_id;
        this.category_name = category_name;
        this.support_type = support_type;
        this.category_status = category_status;
        this.extendable=false;
    }

    protected QueryBean(Parcel in) {
        sk_category_id = in.readString();
        category_name = in.readString();
        support_type = in.readString();
        category_status = in.readString();
    }

    public boolean isExtendable() {
        return extendable;
    }

    public void setExtendable(boolean extendable) {
        this.extendable = extendable;
    }

    public static final Creator<QueryBean> CREATOR = new Creator<QueryBean>() {
        @Override
        public QueryBean createFromParcel(Parcel in) {
            return new QueryBean(in);
        }

        @Override
        public QueryBean[] newArray(int size) {
            return new QueryBean[size];
        }
    };

    public String getSk_category_id() {
        return sk_category_id;
    }

    public void setSk_category_id(String sk_category_id) {
        this.sk_category_id = sk_category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getSupport_type() {
        return support_type;
    }

    public void setSupport_type(String support_type) {
        this.support_type = support_type;
    }

    public String getCategory_status() {
        return category_status;
    }

    public void setCategory_status(String category_status) {
        this.category_status = category_status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sk_category_id);
        dest.writeString(category_name);
        dest.writeString(support_type);
        dest.writeString(category_status);
    }
}
