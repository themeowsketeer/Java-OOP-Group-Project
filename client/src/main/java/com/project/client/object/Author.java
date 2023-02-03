package com.project.client.object;

/**
 * Class used to access Author attribute inside JSON object for deserialization.
 *
 * @author Minh Duy
 */
public class Author {
    private long id;
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;

        return getName() != null ? getName().equals(author.getName()) : author.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    /**
     * Overloaded toString function that display authors' names only.
     * <p>
     * Default parsing only uses toString method of the class, thus simple method to get
     * author name cannot be used.
     * @return Author name. Used for mainMenu UI application to simply display Authors list of a book
     */

    @Override
    public String toString() {
//        return "Author{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
        return name;
    }
}
