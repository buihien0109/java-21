package vn.techmaster.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void example_period() {
		LocalDate startDate = LocalDate.of(2015, 2, 20);
		LocalDate endDate = LocalDate.of(2017, 1, 15);

		Period period = Period.between(startDate, endDate);

		System.out.println("Years: " + period.getYears() + " Months: " + period.getMonths() + " Days: " + period.getDays());
	}

	@Test
	void example_duration() {
		LocalDateTime start = LocalDateTime.parse("2020-01-01T08:00:00");
		LocalDateTime end = LocalDateTime.parse("2023-01-01T12:00:00");

		long seconds = Duration.between(start, end).getSeconds();

		System.out.println("Seconds: " + seconds);
	}
}
