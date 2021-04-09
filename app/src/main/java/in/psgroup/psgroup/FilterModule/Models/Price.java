package in.psgroup.psgroup.FilterModule.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Price implements Serializable {

    @SerializedName("minvalue")
    @Expose
    private Integer minvalue;
    @SerializedName("minrange")
    @Expose
    private String minrange;
    @SerializedName("maxvalue")
    @Expose
    private Integer maxvalue;
    @SerializedName("maxrange")
    @Expose
    private String maxrange;

    public Integer getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(Integer minvalue) {
        this.minvalue = minvalue;
    }

    public String getMinrange() {
        return minrange;
    }

    public void setMinrange(String minrange) {
        this.minrange = minrange;
    }

    public Integer getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(Integer maxvalue) {
        this.maxvalue = maxvalue;
    }

    public String getMaxrange() {
        return maxrange;
    }

    public void setMaxrange(String maxrange) {
        this.maxrange = maxrange;
    }
}
