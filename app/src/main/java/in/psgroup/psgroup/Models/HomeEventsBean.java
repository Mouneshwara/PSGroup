package in.psgroup.psgroup.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeEventsBean implements Serializable {

    @SerializedName("sk_event_id")
    @Expose
    private String skEventId;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("propery_name")
    @Expose
    private String properyName;
    @SerializedName("event_title")
    @Expose
    private String eventTitle;
    @SerializedName("event_short_note")
    @Expose
    private String eventShortNote;
    @SerializedName("event_desc")
    @Expose
    private String eventDesc;
    @SerializedName("event_image")
    @Expose
    private String eventImage;
    @SerializedName("event_location")
    @Expose
    private String eventLocation;
    @SerializedName("event_date")
    @Expose
    private String eventDate;
    @SerializedName("event_status")
    @Expose
    private String eventStatus;

    public String getSkEventId() {
        return skEventId;
    }

    public void setSkEventId(String skEventId) {
        this.skEventId = skEventId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getProperyName() {
        return properyName;
    }

    public void setProperyName(String properyName) {
        this.properyName = properyName;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventShortNote() {
        return eventShortNote;
    }

    public void setEventShortNote(String eventShortNote) {
        this.eventShortNote = eventShortNote;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

}
