package in.psgroup.psgroup.Models;

public class IssueBean {
    String issue_id,issue_type;

    public IssueBean(String issue_id, String issue_type) {
        this.issue_id = issue_id;
        this.issue_type = issue_type;
    }

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public String getIssue_type() {
        return issue_type;
    }

    public void setIssue_type(String issue_type) {
        this.issue_type = issue_type;
    }
}
