package br.com.restwith.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.restwith.converter.NumberConverter;
import br.com.restwith.exception.ResourceNotFoundException;
import br.com.restwith.math.SimpleMath;

@RestController
public class MathController {
	
	private SimpleMath math = new SimpleMath();

	@GetMapping(value = "/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		NumberConverter.extractedIsNumerico(numberOne, numberTwo);
		Double sum = math.sum(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
		return sum;
	}
	
	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		NumberConverter.extractedIsNumerico(numberOne, numberTwo);
		Double resp = math.subTraction(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
		return resp;
	}
	
	@RequestMapping(value = "/mutiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mutiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		NumberConverter.extractedIsNumerico(numberOne, numberTwo);
		Double resp = math.multiplication(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
		return resp;
	}
	
	@RequestMapping(value = "/divisior/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double divisior(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		NumberConverter.extractedIsNumerico(numberOne, numberTwo);
		Double resp = math.division(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));
		return resp;
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		NumberConverter.extractedIsNumerico(numberOne, numberTwo);
		Double resp = math.mean( NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo) );
		return resp;
	}

	@RequestMapping(value = "/squareroot/{number}", method = RequestMethod.GET)
	public Double squareRoot(@PathVariable("number") String number)
			throws Exception {
		NumberConverter.extractedIsNumerico(number);
		Double resp = math.squareRoot(NumberConverter.convertToDouble(number));
		return resp;
	}
	

}
