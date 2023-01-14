package com.project.client.object;

import java.util.Objects;

public class Book {

    private String id;
    private String Name;
    private String Author;
    private Long releasedYear;
    private Integer edition;
    private Integer quantity;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Long getreleasedYear() {
        return releasedYear;
    }

    public void setreleasedYear(Long releasedYear) {
        this.releasedYear = releasedYear;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (getId() != null ? !getId().equals(book.getId()) : book.getId() != null) return false;
        if (getName() != null ? !getName().equals(book.getName()) : book.getName() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(book.getAuthor()) : book.getAuthor() != null) return false;
        if (!Objects.equals(releasedYear, book.releasedYear)) return false;
        if (getEdition() != null ? !getEdition().equals(book.getEdition()) : book.getEdition() != null) return false;
        return getQuantity() != null ? getQuantity().equals(book.getQuantity()) : book.getQuantity() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (releasedYear != null ? releasedYear.hashCode() : 0);
        result = 31 * result + (getEdition() != null ? getEdition().hashCode() : 0);
        result = 31 * result + (getQuantity() != null ? getQuantity().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Author='" + Author + '\'' +
                ", releasedYear=" + releasedYear +
                ", edition=" + edition +
                ", quantity=" + quantity +
                '}';
    }
}
