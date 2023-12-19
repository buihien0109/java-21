package vn.techmaster.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import vn.techmaster.demo.model.ClassRoom;
import vn.techmaster.demo.model.Student;
import vn.techmaster.demo.model.Teacher;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		// Lấy ra đối tượng Student từ Spring IoC Container
		Student student = context.getBean(Student.class);
		System.out.println(student);

		// Lấy ra đối tượng Teacher từ Spring IoC Container
		Teacher teacher = context.getBean(Teacher.class);
		System.out.println(teacher);

		// Lấy ra đối tượng ClassRoom từ Spring IoC Container
		ClassRoom classRoom = context.getBean(ClassRoom.class);
		System.out.println(classRoom);
	}

	// @Bean được sử dụng để khai báo 1 Bean
	// @Bean chỉ được sử dụng trong class được đanh dấu là @Configuration
	// Method trả về đối tượng được gọi là Bean
	@Bean
	public Teacher teacher() {
		return new Teacher(1, "Cường");
	}

	@Override
	public void run(String... args) throws Exception {
		// Book book = new Book("1", "Gone with the wind", "Cuong", 1945);
		// System.out.println(book);
		//
		// // Suwr dụng Builder Pattern
		// Book book2 = Book.builder()
		// .title("Chi Pheo")
		// .author("Nam Cao")
		// .id("2")
		// .year(1943)
		// .build();
		// System.out.println(book2);
		//
		// // Suwr dụng Builder Pattern
		// Book book3 = Book.builder()
		// .author("Nam Cao")
		// .title("Chi Pheo")
		// .build();
		// System.out.println(book3);
	}
}
