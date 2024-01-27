package org.example.movie.app.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertDirectorRequest {
    String name;
    String description;
    Date birthday;
}
