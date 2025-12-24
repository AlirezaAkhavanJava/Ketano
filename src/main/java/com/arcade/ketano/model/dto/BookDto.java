package com.arcade.ketano.model.dto;

import com.arcade.ketano.model.entities.Author;
import com.arcade.ketano.model.entities.Interpreter;
import com.arcade.ketano.model.entities.Publisher;
import com.arcade.ketano.model.entities.UserDashBoard;
import com.arcade.ketano.model.enums.BookCategory;
import lombok.Builder;

import java.time.LocalDate;


@Builder
public record BookDto(
        BookCategory category,
        Integer pages_amount,
        Double price,
        String type,
        Double volume,
        String language,
        LocalDate publishDate,
        Double price_dollar,
        byte rating,
        boolean has_discount,
        double discount_amount,
        UserDashBoard user_dashboard,
        Interpreter interpreter,
        Author author,
        Publisher publisher
) {
}
