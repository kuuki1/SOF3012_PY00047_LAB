package dto;

import java.util.Date;

public class VideoShareInfoDTO {
    private String title;
    private long shareCount;
    private Date firstShareDate;
    private Date lastShareDate;

    public VideoShareInfoDTO(String title, long shareCount, Date firstShareDate, Date lastShareDate) {
        this.title = title;
        this.shareCount = shareCount;
        this.firstShareDate = firstShareDate;
        this.lastShareDate = lastShareDate;
    }

    // Getters và setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getShareCount() {
        return shareCount;
    }

    public void setShareCount(long shareCount) {
        this.shareCount = shareCount;
    }

    public Date getFirstShareDate() {
        return firstShareDate;
    }

    public void setFirstShareDate(Date firstShareDate) {
        this.firstShareDate = firstShareDate;
    }

    public Date getLastShareDate() {
        return lastShareDate;
    }

    public void setLastShareDate(Date lastShareDate) {
        this.lastShareDate = lastShareDate;
    }
}
