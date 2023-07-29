package ru.test.webzaim;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.test.webzaim.repository.CreditRepository;
import ru.test.webzaim.service.CreditService;

@SpringBootApplication
@AllArgsConstructor
public class WebzaimApplication implements CommandLineRunner {

	private final CreditService creditService;

	public static void main(String[] args) {
		SpringApplication.run(WebzaimApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(creditService.getThroughString());
	}
}
