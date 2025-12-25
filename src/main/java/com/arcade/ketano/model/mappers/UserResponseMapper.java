package com.arcade.ketano.model.mappers;

import com.arcade.ketano.model.dto.BookDto;
import com.arcade.ketano.model.dto.UserDashDto;
import com.arcade.ketano.model.dto.UserSearchResponseDto;
import com.arcade.ketano.model.entities.Book;
import com.arcade.ketano.model.entities.User;
import com.arcade.ketano.model.entities.UserDashBoard;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResponseMapper {

    public UserSearchResponseDto toUserSearchResponse(User user) {
        if (user == null) {
            return null;
        }

        UserDashBoard dashboard = user.getDashboard();
        List<BookDto> bookDtos = Collections.emptyList();

        if (dashboard != null && dashboard.getBooks() != null) {
            bookDtos = dashboard.getBooks().stream()
                    .map(this::toBookDto)
                    .collect(Collectors.toList());
        }

        UserDashDto dashDto = UserDashDto.builder()
                .full_name(user.getName())
                .id(user.getId())
                .books(bookDtos)
                .build();

        return UserSearchResponseDto.builder()
                .name(user.getName())
                .dashDto(dashDto)
                .build();
    }

    private BookDto toBookDto(Book book) {
        if (book == null) {
            return null;
        }

        return BookDto.builder()
                .category(book.getCategory())
                .pages_amount(book.getPages_amount())
                .price(book.getPrice())
                .type(book.getType())
                .volume(book.getVolume())
                .language(book.getLanguage())
                .publishDate(book.getPublishDate())
                .price_dollar(book.getPrice_dollar())
                .rating(book.getRating())
                .has_discount(book.isHas_discount())
                .discount_amount(book.getDiscount_amount())
                .user_dashboard(book.getUser_dashboard())
                .interpreter(book.getInterpreter())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .build();
    }
}