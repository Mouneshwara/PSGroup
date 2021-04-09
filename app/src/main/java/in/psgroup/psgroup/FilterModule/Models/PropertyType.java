package in.psgroup.psgroup.FilterModule.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Codebele on 4/16/2019.
 */
public class PropertyType implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;

    private boolean slected=false;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSlected() {
        return slected;
    }

    public void setSlected(boolean slected) {
        this.slected = slected;
    }
}
