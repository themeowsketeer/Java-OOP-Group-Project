package com.project.client.object;

/**
 * Class used to distinguish accessibility between User and Librarian.
 * <p>
 * By default, id 1 is labelled Librarian, id 2 is labelled User.
 *
 * @author Minh Duy
 */

public class Role {
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;

        if (getId() != null ? !getId().equals(role.getId()) : role.getId() != null) return false;
        return getName() != null ? getName().equals(role.getName()) : role.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    /**
     * Overloaded toString function that display role name only.
     * <p>
     * Default parsing only uses toString method of the class, thus simple method to get
     * role name cannot be used.
     *
     * @return Role name. Used for mainUserMenu UI application to simply display User's Role
     */

    @Override
    public String toString() {
//        return "Role{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
        return getName();
    }
}
