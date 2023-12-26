package org.example.demo.stream.api.dao;

import org.example.demo.stream.api.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonDAO {
    void printListPeople(List<Person> persons);

    List<Person> getAll(); //Liệt kê danh sách tất cả

    List<Person> sortPeopleByFullName(); //Liệt kê danh sách sắp xếp theo tên full name từ A-Z

    List<Person> sortPeopleByFullNameReversed(); //Liệt kê danh sách sắp xếp theo tên full name từ Z-A

    List<String> getSortedJobs(); //Lấy danh sách tất cả nghề nghiệp đã được sắp xếp từ A-Z

    List<String> getSortedCities(); //Lấy danh sách tất cả thành phố đã được sắp xếp từ A-Z

    List<String> femaleNames(); // Lấy danh sách tất cả tên của những người có giới tính nữ

    Person highestEarner(); // Tìm người có mức lương cao nhất

    List<Person> bornAfter1990(); // Lọc ra những người có năm sinh sau năm 1990

    double averageSalary(); // Tính lương trung bình của mọi người

    double averageAge(); // Tính tuổi trung bình của mọi người

    List<Person> nameContains(String keyword); // Lọc ra các person trong tên có chứa keyword

    List<Person> sortedByAgeForMale(); // Sắp xếp tuổi của những người có giới tính Male giảm dần

    Person longestName(); // Tìm người có tên dài nhất

    List<Person> aboveAverageSalary(); // Lấy danh sách những người có mức lương lớn hơn mức lương trung bình

    Map<String, List<Person>> groupPeopleByCity(); //Gom tất cả những người trong cùng một thành phố lại

    /* - Hanoi
          - Nguyen Văn X  |
          - Nguyên Văn Y  | -> List<Person>
          - Bui Thi Z     |
        - New York
          - John Lenon
          - Iron Man
          - John Biden
        - Tokyo
          - Ajino Moto
          - Murakami
          - Kawazaki
    */
    Map<String, Integer> groupJobByCount();  //Nhóm các nghề nghiệp và đếm số người làm mỗi nghề
  /*
  Pharmacist  - 2
  Data Coordiator - 3
  Sales Representative - 5
  */

    List<Person> inSalaryRange(int start, int end); // Liệt kê danh sách những người có mức lương trong khoảng được chỉ định start -> end

    List<Person> startsWith(String prefix); // Liệt kê danh sách những người có tên bắt đầu bằng 1 prefix nào đó

    List<Person> sortByBirthYearDescending(); // Sắp xếp danh sách theo năm sinh giảm dần

    List<Person> top5HighestPaid(); // Lấy danh sách 5 người có mức lương cao nhất

    List<Person> inAgeRange(int start, int end); // Liệt kê danh sách những người có tuổi trong khoảng được chỉ định start -> end
}
