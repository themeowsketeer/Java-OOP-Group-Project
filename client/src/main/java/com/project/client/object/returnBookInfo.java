package com.project.client.object;

import java.util.Date;

/**
 * Class used to deserialize JSON string format of a returning Book order.
 * <p>
 * Also used to put the data into returnedTable tableView in UI.
 *
 * @author Minh Duy
 */

public class returnBookInfo {
    private String borrowId;
    private Date issueAt;
    private String bookId;
    private String bookName;
    private String userId;
    private String userName;
    private Date returnAt;

    public returnBookInfo(String borrowId, Date issueAt, String bookId, String bookName, String userId, String userName, Date returnAt) {
        this.borrowId = borrowId;
        this.issueAt = issueAt;
        this.bookId = bookId;
        this.bookName = bookName;
        this.userId = userId;
        this.userName = userName;
        this.returnAt = returnAt;
    }

    public returnBookInfo() {
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

    public Date getReturnAt() {
        return returnAt;
    }

    public void setReturnAt(Date returnAt) {
        this.returnAt = returnAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof returnBookInfo that)) return false;

        if (getBorrowId() != null ? !getBorrowId().equals(that.getBorrowId()) : that.getBorrowId() != null)
            return false;
        if (getIssueAt() != null ? !getIssueAt().equals(that.getIssueAt()) : that.getIssueAt() != null) return false;
        if (getBookId() != null ? !getBookId().equals(that.getBookId()) : that.getBookId() != null) return false;
        if (getBookName() != null ? !getBookName().equals(that.getBookName()) : that.getBookName() != null)
            return false;
        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        if (getUserName() != null ? !getUserName().equals(that.getUserName()) : that.getUserName() != null)
            return false;
        return getReturnAt() != null ? getReturnAt().equals(that.getReturnAt()) : that.getReturnAt() == null;
    }

    @Override
    public int hashCode() {
        int result = getBorrowId() != null ? getBorrowId().hashCode() : 0;
        result = 31 * result + (getIssueAt() != null ? getIssueAt().hashCode() : 0);
        result = 31 * result + (getBookId() != null ? getBookId().hashCode() : 0);
        result = 31 * result + (getBookName() != null ? getBookName().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getReturnAt() != null ? getReturnAt().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "returnBookInfo{" +
                "borrowId='" + borrowId + '\'' +
                ", issueAt=" + issueAt +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", returnAt=" + returnAt +
                '}';
    }
}
