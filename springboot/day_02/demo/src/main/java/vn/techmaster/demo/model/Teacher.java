package vn.techmaster.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Teacher {
    private int id;
    private String name;

    public Teacher() {
        System.out.println("Teacher constructor");
    }

    public Teacher(int id, String name) {
        System.out.println("Teacher constructor");
        this.id = id;
        this.name = name;
    }
}
