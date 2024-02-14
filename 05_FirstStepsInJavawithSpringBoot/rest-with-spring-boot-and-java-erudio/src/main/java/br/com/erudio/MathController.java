package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exceptions.UnsupportedMathOperationException;

@RestController	//Passa o "localhost:8080//
public class MathController {

	private static final String template = "Hello, %s!"; //%s = Váriavel do parametro do link: "?name=Lucas ficando "http://localhost:8080/greeting?name=Lucas"//
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)                         //Passa o local: "localhost:8080/greeting"//
	public Double sum (
			@PathVariable(value = "numberOne")String numberOne,      //passa o parâmetro que leva a variavel "?name=%s"//
			@PathVariable(value = "numberTwo")String numberTwo
	) throws Exception{
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) { //Se não for numerico ele retorna a excessão//
			throw new UnsupportedMathOperationException("Please set a numeric Value");
	}
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}

	private Double convertToDouble(String strNumber) { //"str", serve para one e two(stringnumber)//
		if (strNumber == null) return 0D;
		//BR 10,25 US 10;25
		String number = strNumber.replaceAll(",", ".");
			if (isNumeric(number))return Double.parseDouble(number);
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");//Está verificando se é negativo ou positivo, de 0 a 9 e se tem "." de 0 a 9.//
		
	}

}
