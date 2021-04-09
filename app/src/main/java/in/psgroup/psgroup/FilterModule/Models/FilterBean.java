package in.psgroup.psgroup.FilterModule.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FilterBean implements Serializable {
    @SerializedName("location")
    @Expose
    private List<Location> location = null;
    @SerializedName("property_type")
    @Expose
    private List<PropertyType> propertyType = null;
    @SerializedName("property_status")
    @Expose
    private List<PropertyStatus> propertyStatus = null;
    @SerializedName("unit_type")
    @Expose
    private List<String> unitType = null;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("price_interval")
    @Expose
    private List<PriceInterval> priceInterval = null;

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public List<PropertyType> getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(List<PropertyType> propertyType) {
        this.propertyType = propertyType;
    }

    public List<PropertyStatus> getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(List<PropertyStatus> propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public List<String> getUnitType() {
        return unitType;
    }

    public void setUnitType(List<String> unitType) {
        this.unitType = unitType;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<PriceInterval> getPriceInterval() {
        return priceInterval;
    }

    public void setPriceInterval(List<PriceInterval> priceInterval) {
        this.priceInterval = priceInterval;
    }

}
