package com.example.models;

public enum GenreEnum {

    FICTION("Fiction"),
    NON_FICTION("Non-Fiction"),
    MYSTERY("Mystery"),
    HORROR("Horror"),
    MANGA("Manga"),
    BIOGRAPHY("Biography"),
    AUTOBIOGRAPHY("Autobiography"),
    PSYCHOLOGY("Psychology"),
    SCIENCE_FICTION("Science Fiction");

    private final String displayName;

    GenreEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
