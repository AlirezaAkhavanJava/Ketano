package com.arcade.ketano.model.enums;


import lombok.Getter;

/**
 * Enum representing book categories commonly used in Persian/Iranian bookstores or libraries.
 * The enum constants are translated to English based on the Persian labels visible in the provided screenshot.
 * This enum can be used in Spring Boot entities (e.g., Book entity) with @Enumerated(EnumType.STRING)
 * to persist category names in the database.
 */

@Getter
public enum BookCategory {

    /**
     * Fiction / Novels
     */
    FICTION("Fiction"),

    /**
     * Non-Fiction
     */
    NON_FICTION("Non-Fiction"),

    /**
     * Literature and Art
     */
    LITERATURE_AND_ART("Literature and Art"),

    /**
     * Children and Young Adult
     */
    CHILDREN_AND_YOUNG_ADULT("Children and Young Adult"),

    /**
     * History and Biography
     */
    HISTORY_AND_BIOGRAPHY("History and Biography"),

    /**
     * Philosophy and Psychology
     */
    PHILOSOPHY_AND_PSYCHOLOGY("Philosophy and Psychology"),

    /**
     * Religion
     */
    RELIGION("Religion"),

    /**
     * Social Sciences
     */
    SOCIAL_SCIENCES("Social Sciences"),

    /**
     * Sciences and Technology
     */
    SCIENCES_AND_TECHNOLOGY("Sciences and Technology"),

    /**
     * Cookbooks / Cooking and Nutrition
     */
    COOKING_AND_NUTRITION("Cooking and Nutrition"),

    /**
     * Self-Improvement / Success and Motivation
     */
    SELF_IMPROVEMENT("Self-Improvement"),

    /**
     * Health and Sports
     */
    HEALTH_AND_SPORTS("Health and Sports"),

    /**
     * Computer and Internet
     */
    COMPUTER_AND_INTERNET("Computer and Internet"),

    /**
     * Management and Entrepreneurship
     */
    MANAGEMENT_AND_ENTREPRENEURSHIP("Management and Entrepreneurship"),

    /**
     * Politics and Law
     */
    POLITICS_AND_LAW("Politics and Law"),

    /**
     * Travel and Tourism
     */
    TRAVEL_AND_TOURISM("Travel and Tourism"),

    /**
     * Poetry
     */
    POETRY("Poetry"),

    /**
     * Short Stories
     */
    SHORT_STORIES("Short Stories"),

    /**
     * Psychology
     */
    PSYCHOLOGY("Psychology"),

    /**
     * Educational Books
     */
    EDUCATIONAL("Educational"),

    /**
     * Reference and Dictionary
     */
    REFERENCE_AND_DICTIONARY("Reference and Dictionary"),

    /**
     * Art and Photography
     */
    ART_AND_PHOTOGRAPHY("Art and Photography"),

    /**
     * Foreign Languages
     */
    FOREIGN_LANGUAGES("Foreign Languages");


    private final String displayName;

    BookCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}