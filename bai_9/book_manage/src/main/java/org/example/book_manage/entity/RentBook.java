package org.example.book_manage.entity;

import jakarta.persistence.*;

@Entity
public class RentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
