package com.project.client.ui.viewIssuedMenu;

import java.util.Date;

public class issueInfo {
    private long borrowId;
    private Date issueAt;
    private long bookId;
    private String bookName;
    private long userId;
    private String userName;

    public issueInfo(long borrowId, Date issueAt, long bookId, String bookName, long userId, String userName) {
        this.borrowId = borrowId;
        this.issueAt = issueAt;
        this.bookId = bookId;
        this.bookName = bookName;
        this.userId = userId;
        this.userName = userName;
    }

    public issueInfo() {
    }

    public long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(long borrowId) {
        this.borrowId = borrowId;
    }

    public Date getIssueAt() {
        return issueAt;
    }

    public void setIssueAt(Date issueAt) {
        this.issueAt = issueAt;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof issueInfo issueInfo)) return false;

        if (getBorrowId() != issueInfo.getBorrowId()) return false;
        if (getBookId() != issueInfo.getBookId()) return false;
        if (getUserId() != issueInfo.getUserId()) return false;
        if (getIssueAt() != null ? !getIssueAt().equals(issueInfo.getIssueAt()) : issueInfo.getIssueAt() != null)
            return false;
        if (getBookName() != null ? !getBookName().equals(issueInfo.getBookName()) : issueInfo.getBookName() != null)
            return false;
        return getUserName() != null ? getUserName().equals(issueInfo.getUserName()) : issueInfo.getUserName() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getBorrowId() ^ (getBorrowId() >>> 32));
        result = 31 * result + (getIssueAt() != null ? getIssueAt().hashCode() : 0);
        result = 31 * result + (int) (getBookId() ^ (getBookId() >>> 32));
        result = 31 * result + (getBookName() != null ? getBookName().hashCode() : 0);
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "issueBook{" +
                "borrowId=" + borrowId +
                ", issueAt=" + issueAt +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
