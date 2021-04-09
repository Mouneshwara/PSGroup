
package in.psgroup.psgroup.Models;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectDetailBean implements Serializable {

    @SerializedName("post_content")
    @Expose
    private String postContent;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("post_title")
    @Expose
    private String postTitle;
    @SerializedName("familiesbooked")
    @Expose
    private String familiesbooked;
    @SerializedName("startprice")
    @Expose
    private String startprice;
    @SerializedName("endprice")
    @Expose
    private String endprice;
    @SerializedName("sharelink")
    @Expose
    private String sharelink;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("resale")
    @Expose
    private String resale;
    @SerializedName("purchase_cost")
    @Expose
    private String purchaseCost;
    @SerializedName("apartment_no")
    @Expose
    private String apartmentNo;
    @SerializedName("apartment_type")
    @Expose
    private String apartmentType;
    @SerializedName("browser_title")
    @Expose
    private String browserTitle;
    @SerializedName("meta_keywords")
    @Expose
    private String metaKeywords;
    @SerializedName("meta_desc")
    @Expose
    private String metaDesc;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("socialmeta_desc")
    @Expose
    private String socialmetaDesc;
    @SerializedName("meta_img")
    @Expose
    private Object metaImg;
    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = null;
    @SerializedName("banner_image")
    @Expose
    private String bannerImage;
    @SerializedName("digital_count")
    @Expose
    private String digitalCount;
    @SerializedName("ProjectOverview")
    @Expose
    private ProjectOverview projectOverview;
    @SerializedName("siteplan")
    @Expose
    private Siteplan siteplan;
    @SerializedName("floorplan")
    @Expose
    private List<Floorplan> floorplan = null;
    @SerializedName("ProjectSpecification_two_list")
    @Expose
    private List<ProjectSpecificationTwoList> projectSpecificationTwoList = null;
    @SerializedName("highlights")
    @Expose
    private List<Highlight> highlights = null;
    @SerializedName("nearbyLandmarks")
    @Expose
    private List<NearbyLandmark> nearbyLandmarks = null;
    @SerializedName("testimonials")
    @Expose
    private List<Object> testimonials = null;

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getFamiliesbooked() {
        return familiesbooked;
    }

    public void setFamiliesbooked(String familiesbooked) {
        this.familiesbooked = familiesbooked;
    }

    public String getStartprice() {
        return startprice;
    }

    public void setStartprice(String startprice) {
        this.startprice = startprice;
    }

    public String getEndprice() {
        return endprice;
    }

    public void setEndprice(String endprice) {
        this.endprice = endprice;
    }

    public String getSharelink() {
        return sharelink;
    }

    public void setSharelink(String sharelink) {
        this.sharelink = sharelink;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResale() {
        return resale;
    }

    public void setResale(String resale) {
        this.resale = resale;
    }

    public String getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(String purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getBrowserTitle() {
        return browserTitle;
    }

    public void setBrowserTitle(String browserTitle) {
        this.browserTitle = browserTitle;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDesc() {
        return metaDesc;
    }

    public void setMetaDesc(String metaDesc) {
        this.metaDesc = metaDesc;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getSocialmetaDesc() {
        return socialmetaDesc;
    }

    public void setSocialmetaDesc(String socialmetaDesc) {
        this.socialmetaDesc = socialmetaDesc;
    }

    public Object getMetaImg() {
        return metaImg;
    }

    public void setMetaImg(Object metaImg) {
        this.metaImg = metaImg;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getDigitalCount() {
        return digitalCount;
    }

    public void setDigitalCount(String digitalCount) {
        this.digitalCount = digitalCount;
    }

    public ProjectOverview getProjectOverview() {
        return projectOverview;
    }

    public void setProjectOverview(ProjectOverview projectOverview) {
        this.projectOverview = projectOverview;
    }

    public Siteplan getSiteplan() {
        return siteplan;
    }

    public void setSiteplan(Siteplan siteplan) {
        this.siteplan = siteplan;
    }

    public List<Floorplan> getFloorplan() {
        return floorplan;
    }

    public void setFloorplan(List<Floorplan> floorplan) {
        this.floorplan = floorplan;
    }

    public List<ProjectSpecificationTwoList> getProjectSpecificationTwoList() {
        return projectSpecificationTwoList;
    }

    public void setProjectSpecificationTwoList(List<ProjectSpecificationTwoList> projectSpecificationTwoList) {
        this.projectSpecificationTwoList = projectSpecificationTwoList;
    }

    public List<Highlight> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<Highlight> highlights) {
        this.highlights = highlights;
    }

    public List<NearbyLandmark> getNearbyLandmarks() {
        return nearbyLandmarks;
    }

    public void setNearbyLandmarks(List<NearbyLandmark> nearbyLandmarks) {
        this.nearbyLandmarks = nearbyLandmarks;
    }

    public List<Object> getTestimonials() {
        return testimonials;
    }

    public void setTestimonials(List<Object> testimonials) {
        this.testimonials = testimonials;
    }

/*
    @SerializedName("post_content")
    @Expose
    private String postContent;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("post_title")
    @Expose
    private String postTitle;
    @SerializedName("familiesbooked")
    @Expose
    private String familiesbooked;
    @SerializedName("startprice")
    @Expose
    private String startprice;
    @SerializedName("endprice")
    @Expose
    private String endprice;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("resale")
    @Expose
    private String resale;
    @SerializedName("purchase_cost")
    @Expose
    private String purchaseCost;
    @SerializedName("apartment_no")
    @Expose
    private String apartmentNo;
    @SerializedName("apartment_type")
    @Expose
    private String apartmentType;
    @SerializedName("browser_title")
    @Expose
    private String browserTitle;
    @SerializedName("meta_keywords")
    @Expose
    private String metaKeywords;
    @SerializedName("meta_desc")
    @Expose
    private String metaDesc;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("socialmeta_desc")
    @Expose
    private String socialmetaDesc;
    @SerializedName("meta_img")
    @Expose
    private String metaImg;
    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = null;
    @SerializedName("banner_image")
    @Expose
    private String bannerImage;
    @SerializedName("digital_count")
    @Expose
    private String digitalCount;

    public String getShare_link() {
        return share_link;
    }

    public void setShare_link(String share_link) {
        this.share_link = share_link;
    }

    @SerializedName("ProjectOverview")

    @Expose
    private String share_link;
    @SerializedName("sharelink")

    @Expose
    private ProjectOverview projectOverview;
    @SerializedName("siteplan")
    @Expose
    private Siteplan siteplan;
    @SerializedName("floorplan")
    @Expose
    private List<Floorplan> floorplan = null;
    @SerializedName("ProjectSpecification_two_list")
    @Expose
    private List<ProjectSpecificationTwoList> projectSpecificationTwoList = null;
    @SerializedName("highlights")
    @Expose
    private List<Highlight> highlights = null;
    @SerializedName("nearbyLandmarks")
    @Expose
    private List<NearbyLandmark> nearbyLandmarks = null;
    @SerializedName("testimonials")
    @Expose
    private List<Testimonial> testimonials = null;
    public final static Creator<ProjectDetailBean> CREATOR = new Creator<ProjectDetailBean>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProjectDetailBean createFromParcel(Parcel in) {
            return new ProjectDetailBean(in);
        }

        public ProjectDetailBean[] newArray(int size) {
            return (new ProjectDetailBean[size]);
        }

    }
    ;

    protected ProjectDetailBean(Parcel in) {
        this.postContent = ((String) in.readValue((String.class.getClassLoader())));
        this.postId = ((String) in.readValue((String.class.getClassLoader())));
        this.postTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.familiesbooked = ((String) in.readValue((String.class.getClassLoader())));
        this.startprice = ((String) in.readValue((String.class.getClassLoader())));
        this.endprice = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.resale = ((String) in.readValue((String.class.getClassLoader())));
        this.purchaseCost = ((String) in.readValue((String.class.getClassLoader())));
        this.apartmentNo = ((String) in.readValue((String.class.getClassLoader())));
        this.apartmentType = ((String) in.readValue((String.class.getClassLoader())));
        this.browserTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.metaKeywords = ((String) in.readValue((String.class.getClassLoader())));
        this.metaDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.metaTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.socialmetaDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.metaImg = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.gallery, (in.psgroup.psgroup.Models.Gallery.class.getClassLoader()));
        this.bannerImage = ((String) in.readValue((String.class.getClassLoader())));
        this.digitalCount = ((String) in.readValue((String.class.getClassLoader())));
        this.projectOverview = ((ProjectOverview) in.readValue((ProjectOverview.class.getClassLoader())));
        this.siteplan = ((Siteplan) in.readValue((Siteplan.class.getClassLoader())));
        in.readList(this.floorplan, (in.psgroup.psgroup.Models.Floorplan.class.getClassLoader()));
        in.readList(this.projectSpecificationTwoList, (in.psgroup.psgroup.Models.ProjectSpecificationTwoList.class.getClassLoader()));
        in.readList(this.highlights, (in.psgroup.psgroup.Models.Highlight.class.getClassLoader()));
        in.readList(this.nearbyLandmarks, (in.psgroup.psgroup.Models.NearbyLandmark.class.getClassLoader()));
        in.readList(this.testimonials, (in.psgroup.psgroup.Models.Testimonial.class.getClassLoader()));
    }

    public ProjectDetailBean() {
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getFamiliesbooked() {
        return familiesbooked;
    }

    public void setFamiliesbooked(String familiesbooked) {
        this.familiesbooked = familiesbooked;
    }

    public String getStartprice() {
        return startprice;
    }

    public void setStartprice(String startprice) {
        this.startprice = startprice;
    }

    public String getEndprice() {
        return endprice;
    }

    public void setEndprice(String endprice) {
        this.endprice = endprice;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResale() {
        return resale;
    }

    public void setResale(String resale) {
        this.resale = resale;
    }

    public String getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(String purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getBrowserTitle() {
        return browserTitle;
    }

    public void setBrowserTitle(String browserTitle) {
        this.browserTitle = browserTitle;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDesc() {
        return metaDesc;
    }

    public void setMetaDesc(String metaDesc) {
        this.metaDesc = metaDesc;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getSocialmetaDesc() {
        return socialmetaDesc;
    }

    public void setSocialmetaDesc(String socialmetaDesc) {
        this.socialmetaDesc = socialmetaDesc;
    }

    public String getMetaImg() {
        return metaImg;
    }

    public void setMetaImg(String metaImg) {
        this.metaImg = metaImg;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getDigitalCount() {
        return digitalCount;
    }

    public void setDigitalCount(String digitalCount) {
        this.digitalCount = digitalCount;
    }

    public ProjectOverview getProjectOverview() {
        return projectOverview;
    }

    public void setProjectOverview(ProjectOverview projectOverview) {
        this.projectOverview = projectOverview;
    }

    public Siteplan getSiteplan() {
        return siteplan;
    }

    public void setSiteplan(Siteplan siteplan) {
        this.siteplan = siteplan;
    }

    public List<Floorplan> getFloorplan() {
        return floorplan;
    }

    public void setFloorplan(List<Floorplan> floorplan) {
        this.floorplan = floorplan;
    }

    public List<ProjectSpecificationTwoList> getProjectSpecificationTwoList() {
        return projectSpecificationTwoList;
    }

    public void setProjectSpecificationTwoList(List<ProjectSpecificationTwoList> projectSpecificationTwoList) {
        this.projectSpecificationTwoList = projectSpecificationTwoList;
    }

    public List<Highlight> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<Highlight> highlights) {
        this.highlights = highlights;
    }

    public List<NearbyLandmark> getNearbyLandmarks() {
        return nearbyLandmarks;
    }

    public void setNearbyLandmarks(List<NearbyLandmark> nearbyLandmarks) {
        this.nearbyLandmarks = nearbyLandmarks;
    }

    public List<Testimonial> getTestimonials() {
        return testimonials;
    }

    public void setTestimonials(List<Testimonial> testimonials) {
        this.testimonials = testimonials;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(postContent);
        dest.writeValue(postId);
        dest.writeValue(postTitle);
        dest.writeValue(familiesbooked);
        dest.writeValue(startprice);
        dest.writeValue(endprice);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(address);
        dest.writeValue(state);
        dest.writeValue(resale);
        dest.writeValue(purchaseCost);
        dest.writeValue(apartmentNo);
        dest.writeValue(apartmentType);
        dest.writeValue(browserTitle);
        dest.writeValue(metaKeywords);
        dest.writeValue(metaDesc);
        dest.writeValue(metaTitle);
        dest.writeValue(socialmetaDesc);
        dest.writeValue(metaImg);
        dest.writeList(gallery);
        dest.writeValue(bannerImage);
        dest.writeValue(digitalCount);
        dest.writeValue(projectOverview);
        dest.writeValue(siteplan);
        dest.writeList(floorplan);
        dest.writeList(projectSpecificationTwoList);
        dest.writeList(highlights);
        dest.writeList(nearbyLandmarks);
        dest.writeList(testimonials);
    }

    public int describeContents() {
        return  0;
    }*/

}
