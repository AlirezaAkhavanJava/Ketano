package com.arcade.ketano.model.entities;

import com.arcade.ketano.model.enums.BookCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BookCategory category;

    @Column(nullable = false)
    private Integer pages_amount;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double volume;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private LocalDate publishDate;

    private Double price_dollar;

    @Max(5)
    @Min(1)
    private byte rating;

    @Column(nullable = false)
    private boolean has_discount;

    private double discount_amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dashboard_id", nullable = true)
    private UserDashBoard user_dashboard;

    @ManyToOne
    private Interpreter interpreter;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Publisher publisher;

}
