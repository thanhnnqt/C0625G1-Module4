package org.example.bai_tap_1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String tittle;
    private String content;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "User_Id")
    private AppUser appUser;
}
