package com.publishing.house.bookcatalog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"reviews", "authors"})
@EqualsAndHashCode(exclude = {"reviews", "authors"})
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "isbn")
    private Long isbn;
    @Column(name = "book_name")
    private String name;
    @Column(name = "book_year")
    private int year;
    @Column(name = "published")
    private Date published;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "create_date")
    private Date createDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "authorId")
    )
    @JsonIgnore
    private Set<Author> authors;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<Review> reviews;

    public void addReview(final Review review) {
        reviews.add(review);
        review.setBook(this);
    }

    public void removeReview(final Review review) {
        reviews.remove(review);
        review.setBook(null);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        final Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
