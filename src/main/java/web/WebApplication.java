package web;

import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	public static String getHTML(String pageTitle, String bodyContent) {
		return "<!DOCTYPE html>\n<html xmlns:th=\"http://www.thymeleaf.org\" lang=\"en\">\n<head>\n" +
				"    <meta charset=\"UTF-8\">\n    <title>" + pageTitle +"</title>\n</head>\n<body>\n" +
				"<p>\n    " + bodyContent + "\n</p>\n" + getHomeButton() +
				"\n</body>\n</html>";
	}

	private static String getHomeButton() {
		return "<button onclick=\"location.href='index';\">Home Page</button>";
	}

	private static String getHomeLink() {
		return "<a href=\"index\">home page</a>";
	}

	@GetMapping("/result")
	public String sumResult(@RequestParam(value = "a", defaultValue = "0") int a,
							@RequestParam(value = "b", defaultValue = "0") @NumberFormat(style = NumberFormat.Style.NUMBER) @Validated int b) {
		System.out.println("a=" + a + ", b=" + b);
		return WebApplication.getHTML("SumResult", "sum=" + (a + b));
	}
}
