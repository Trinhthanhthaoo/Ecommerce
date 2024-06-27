package com.vku.Model;

public class History {
    private String id;
    private String userId;  // Ensure this matches your JSON key
    private String details;
    private String updateDate;

    // Getters
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getDetails() {
        return details;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    // Setters (if needed)
    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "History{" + "id=" + id + ", userId=" + userId + ", details=" + details + ", updateDate=" + updateDate + '}';
    }
}
