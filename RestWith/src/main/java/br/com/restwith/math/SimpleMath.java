package br.com.restwith.math;

public class SimpleMath {
	
	public Double sum(Double firstNumber, Double segundNumber) {
		return firstNumber+segundNumber;
	}
	
	public Double subTraction(Double firstNumber, Double segundNumber) {
		return firstNumber-segundNumber;
	}
	
	public Double multiplication(Double firstNumber, Double segundNumber) {
		return firstNumber*segundNumber;
	}
	
	public Double division(Double firstNumber, Double segundNumber) {
		return firstNumber/segundNumber;
	}
	
	public Double mean(Double firstNumber, Double segundNumber) {
		return (firstNumber+segundNumber)/2;
	}
	
	public Double squareRoot(Double number) {
		return (Double) Math.sqrt(number);
	}

}
