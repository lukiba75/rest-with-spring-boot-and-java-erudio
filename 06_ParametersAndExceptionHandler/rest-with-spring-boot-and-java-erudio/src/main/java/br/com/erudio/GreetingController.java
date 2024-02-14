package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController	//Passa o "localhost:8080//
public class GreetingController {

	private static final String template = "Hello, %s!"; //%s = Váriavel do parametro do link: "?name=Lucas ficando "http://localhost:8080/greeting?name=Lucas"//
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting") //Passa o local: "localhost:8080/greeting"//
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "Favor, informar seu nome" )//passa o parâmetro que leva a variavel "?name=%s"
	String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
