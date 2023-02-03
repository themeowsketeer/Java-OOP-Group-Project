package com.project.client.object;

import java.util.Date;

/**
 * Class used to deserialize JSON string format of a Book issuing order.
 * <p>
 * Also used to put the data into issueTable tableView in UI.
 *
 * @author Minh Duy
 */

public class issueBookInfo {
    private String borrowId;
    private Date issueAt;
    private String bookId;
    private String bookName;
    private String userId;
    private String userName;

    public issueBookInfo(String borrowId, Date issueAt, String bookId, String bookName, String userId, String userName) {
        this.borrowId = borrowId;
        this.issueAt = issueAt;
        this.bookId = bookId;
        this.bookName = bookName;
        this.userId = userId;
        this.userName = userName;
    }

    public issueBookInfo() {
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public Date getIssueAt() {
        return issueAt;
    }

    public void setIssueAt(Date issueAt) {
        this.issueAt = issueAt;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
        if (!(o instanceof issueBookInfo issueBookInfo)) return false;

        if (getBorrowId() != null ? !getBorrowId().equals(issueBookInfo.getBorrowId()) : issueBookInfo.getBorrowId() != null)
            return false;
        if (getIssueAt() != null ? !getIssueAt().equals(issueBookInfo.getIssueAt()) : issueBookInfo.getIssueAt() != null)
            return false;
        if (getBookId() != null ? !getBookId().equals(issueBookInfo.getBookId()) : issueBookInfo.getBookId() != null)
            return false;
        if (getBookName() != null ? !getBookName().equals(issueBookInfo.getBookName()) : issueBookInfo.getBookName() != null)
            return false;
        if (getUserId() != null ? !getUserId().equals(issueBookInfo.getUserId()) : issueBookInfo.getUserId() != null)
            return false;
        return getUserName() != null ? getUserName().equals(issueBookInfo.getUserName()) : issueBookInfo.getUserName() == null;
    }

    @Override
    public int hashCode() {
        int result = getBorrowId() != null ? getBorrowId().hashCode() : 0;
        result = 31 * result + (getIssueAt() != null ? getIssueAt().hashCode() : 0);
        result = 31 * result + (getBookId() != null ? getBookId().hashCode() : 0);
        result = 31 * result + (getBookName() != null ? getBookName().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
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
