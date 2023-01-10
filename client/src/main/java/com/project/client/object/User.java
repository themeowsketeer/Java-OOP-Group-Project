package com.project.client.object;

public class User {
    private String Name;
    private String DoB;
    private String Address;
    private Integer civillianID;
    private Integer phoneNumber;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getCivillianID() {
        return civillianID;
    }

    public void setCivillianID(Integer civillianID) {
        this.civillianID = civillianID;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getDoB() != null ? !getDoB().equals(user.getDoB()) : user.getDoB() != null) return false;
        if (getAddress() != null ? !getAddress().equals(user.getAddress()) : user.getAddress() != null) return false;
        if (getCivillianID() != null ? !getCivillianID().equals(user.getCivillianID()) : user.getCivillianID() != null)
            return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(user.getPhoneNumber()) : user.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDoB() != null ? getDoB().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getCivillianID() != null ? getCivillianID().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }
}
