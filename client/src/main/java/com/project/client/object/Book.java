package com.project.client.object;

import java.util.Date;
import java.util.Set;

/**
 * Class used to deserialize values of attributes in Book instance
 * from JSON string format.
 * <p>
 * Class also used to push corresponding
 * value of each attribute to the table in any Book tableView instance for UI.
 *
 * @author Minh Duy
 */

public class Book {

    private String id;
    private String name;
    private Set<Author> authors;
    private long releasedYear;
    private int edition;
    private Date placedAt;
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Long getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(Long releasedYear) {
        this.releasedYear = releasedYear;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public Book() {
    }

    public Book(String id, String name, Set<Author> authors, Long releasedYear, int edition, int quantity, Date placedAt) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.releasedYear = releasedYear;
        this.edition = edition;
        this.quantity = quantity;
        this.placedAt = placedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (getEdition() != book.getEdition()) return false;
        if (getQuantity() != book.getQuantity()) return false;
        if (getId() != null ? !getId().equals(book.getId()) : book.getId() != null) return false;
        if (getName() != null ? !getName().equals(book.getName()) : book.getName() != null) return false;
        if (getAuthors() != null ? !getAuthors().equals(book.getAuthors()) : book.getAuthors() != null) return false;
        return getReleasedYear() != null ? getReleasedYear().equals(book.getReleasedYear()) : book.getReleasedYear() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAuthors() != null ? getAuthors().hashCode() : 0);
        result = 31 * result + (getReleasedYear() != null ? getReleasedYear().hashCode() : 0);
        result = 31 * result + getEdition();
        result = 31 * result + getQuantity();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", releasedYear=" + releasedYear +
                ", edition=" + edition +
                ", quantity=" + quantity +
                ", placedAt=" + placedAt +
                '}';
    }
}
