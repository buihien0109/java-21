package org.example.demo.stream.api;

import org.example.demo.stream.api.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Functional Interface -> Lambda Expression -> Stream API
 * Functional Interface: Interface chỉ có 1 phương thức trừu tượng
 * */
@SpringBootApplication
public class DemoStreamApiApplication implements CommandLineRunner {
    @Autowired
    private PersonDAO personDAO;

    public static void main(String[] args) {
        SpringApplication.run(DemoStreamApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        personDAO.printListPeople(personDAO.getAll());
//        // C1: Sử dụng class implement Greeting
//        Greeting vietnam = new Vietnam();
//        vietnam.sayHello("Huy");
//
//        // C2: Sử dụng anonymous class
//        Greeting english = new Greeting() {
//            @Override
//            public void sayHello(String name) {
//                System.out.println("Hello " + name);
//            }
//        };
//        english.sayHello("An");
//
//        // C3: Sử dụng Lambda Expression
//        Greeting french = (name) -> {
//            System.out.println("Bonjour " + name);
//        };
//        french.sayHello("Hoa");
//
//        Greeting china = (name) -> System.out.println("你好 " + name);
//        china.sayHello("Hoa");
//
//        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));
//        for (int i = 0; i < numbers.size(); i++) {
//            System.out.println(numbers.get(i));
//        }
//        for (Integer number : numbers) {
//            System.out.println(number);
//        }
//        numbers.forEach(number -> System.out.println(number));
//
//        numbers.sort((a, b) -> b - a);
//        numbers.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//        System.out.println(numbers);
//
//        List<Integer> numbers = List.of(10, 5, 3, 12, 6, 7, 5, 3);
//
////        1. Duyệt numbers
//        numbers.stream().forEach(number -> System.out.println(number));
//
////        2. Tìm các giá trị chẵn trong list
//        numbers.stream()
//                .filter(number -> number % 2 == 0)
//                .forEach(number -> System.out.println(number));
//
////        3. Tìm các giá trị > 5 trong list
//        numbers.stream()
//                .filter(number -> number > 5)
//                .forEach(number -> System.out.println(number));
//
////        4. Tìm giá trị max trong list
//        Integer max = numbers.stream().max(Comparator.comparingInt(number -> number)).orElse(null);
//        System.out.println(max);
//
////        5. Tìm giá trị min trong list (min)
////        6. Tính tổng các phần tử của mảng (reduce / reduce JS)
////        7. Lấy danh sách các phần tử không trùng nhau (distinct)
////        8. Lấy 5 phần tử đầu tiên trong mảng (limit)
////        9. Lấy phần tử từ thứ 3 -> thứ 5 trong mảng (skip + limit)
////        10. Lấy phần tử đầu tiên > 5 (findFirst / find JS)
////        11. Kiểm tra xem list có phải là list chẵn hay không (allMatch / every JS)
////        12. Kiểm tra xem list có phần tử > 10 hay không (anyMatch / some JS)
////        13. Có bao nhiêu phần tử > 5 (filter + count)
////        14. Nhân đôi các phần tủ trong list và trả về list mới (map / map JS)
////        15. Kiểm tra xem list không chứa giá trị nào = 8 hay không (noneMatch)
    }
}
