package org.example.movie.app.model.enums;

import lombok.Getter;

@Getter
public enum MovieType {
    PHIM_LE("Phim lẻ"),
    PHIM_BO("Phim bộ"),
    PHIM_CHIEU_RAP("Phim chiếu rạp");

    private final String value;

    MovieType(String value) {
        this.value = value;
    }
}
