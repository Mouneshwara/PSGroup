package in.psgroup.psgroup.Models;

public class TopPropertyBean {

    String title,description,laying;
    int milestone_max,milestone_min;
    int seekbarMax,seekbarMin;

    public TopPropertyBean(String title, String description, String laying, int milestone_max, int milestone_min, int seekbarMax, int seekbarMin) {
        this.title = title;
        this.description = description;
        this.laying = laying;
        this.milestone_max = milestone_max;
        this.milestone_min = milestone_min;
        this.seekbarMax = seekbarMax;
        this.seekbarMin = seekbarMin;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLaying() {
        return laying;
    }

    public void setLaying(String laying) {
        this.laying = laying;
    }

    public int getMilestone_max() {
        return milestone_max;
    }

    public void setMilestone_max(int milestone_max) {
        this.milestone_max = milestone_max;
    }

    public int getMilestone_min() {
        return milestone_min;
    }

    public void setMilestone_min(int milestone_min) {
        this.milestone_min = milestone_min;
    }

    public int getSeekbarMax() {
        return seekbarMax;
    }

    public void setSeekbarMax(int seekbarMax) {
        this.seekbarMax = seekbarMax;
    }

    public int getSeekbarMin() {
        return seekbarMin;
    }

    public void setSeekbarMin(int seekbarMin) {
        this.seekbarMin = seekbarMin;
    }
}
