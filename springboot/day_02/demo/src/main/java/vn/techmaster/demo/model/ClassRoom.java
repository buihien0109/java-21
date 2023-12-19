package vn.techmaster.demo.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* Inject Bean như thế nào?
* C1: Đánh dấu field (Sử dụng annotation @Autowired) - Field-based Injection
* C2: Đánh dấu constructor
* C3: Đánh dấu setter
* */
@Component
@Getter
@Setter
@ToString
public class ClassRoom {
    private final Student student;
    private final Teacher teacher;

//    public ClassRoom() {
//        System.out.println("ClassRoom constructor");
//    }

    public ClassRoom(Student student, Teacher teacher) {
        System.out.println("ClassRoom constructor");
        this.student = student;
        this.teacher = teacher;
    }
}
