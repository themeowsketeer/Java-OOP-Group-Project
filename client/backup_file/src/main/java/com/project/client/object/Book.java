package com.project.client.object;

public class Book {
    private String Name;
    private String Author;
    private Integer yearPublished;
    private Integer edition;

    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (getName() != null ? !getName().equals(book.getName()) : book.getName() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(book.getAuthor()) : book.getAuthor() != null) return false;
        if (getYearPublished() != null ? !getYearPublished().equals(book.getYearPublished()) : book.getYearPublished() != null)
            return false;
        return getEdition() != null ? getEdition().equals(book.getEdition()) : book.getEdition() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getYearPublished() != null ? getYearPublished().hashCode() : 0);
        result = 31 * result + (getEdition() != null ? getEdition().hashCode() : 0);
        return result;
    }

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

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
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

    @Override
    public String toString() {
        return "Book{" +
                "name='" + Name + '\'' +
                ", author='" + Author + '\'' +
                ", yearPublished=" + yearPublished +
                ", edition=" + edition +
                ", quantity=" + quantity +
                '}';
    }
}
