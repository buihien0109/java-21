package org.example.demo.thymeleaf.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    int id;
    String name;
    String email;
    String phone;
    int score;
}

/*
* /students, /blogs, /admin -> normal path
* /student/1, /student/2 -> /student/{id} : path variable
* /student?name=an&email=an@gmail.com -> name=an&email=an@gmail.com : query string
* */
