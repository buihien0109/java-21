package vn.techmaster.demo.model;

import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@Component
public class Student {
    private int id;
    private String name;

    public Student() {
        System.out.println("Student constructor");
    }
}
