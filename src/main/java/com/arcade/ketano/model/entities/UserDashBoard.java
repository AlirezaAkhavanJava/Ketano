package com.arcade.ketano.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDashBoard  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullName;
    private int age;

    @OneToMany
    private List<Book> books;
}
