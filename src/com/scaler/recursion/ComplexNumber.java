package com.scaler.recursion;

class ComplexNumber {
    
    float real, imaginary;
		
	// Define constructor here
    public ComplexNumber(float real, float imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }
		
		
	ComplexNumber add(ComplexNumber x){
		return new ComplexNumber(this.real+x.real, this.imaginary+x.imaginary);
		
	}
	
	ComplexNumber subtract(ComplexNumber x){
		return new ComplexNumber(this.real-x.real, this.imaginary-x.imaginary);
		
	}
		
	ComplexNumber multiply(ComplexNumber x){
		return new ComplexNumber((this.real * x.real) - (this.imaginary * x.imaginary), (this.real * x.imaginary) + (this.imaginary * x.real));
		
	}
	
	ComplexNumber divide(ComplexNumber x){
		return new ComplexNumber(this.real / x.real - this.imaginary / x.imaginary, this.real / x.imaginary + this.imaginary / x.real);
		
	}
	
	public static void main(String[] args) {
		ComplexNumber a = new ComplexNumber(10, 5);
				ComplexNumber b = new ComplexNumber(2, 3);
				ComplexNumber c1 = a.add(b) ;
				ComplexNumber c2 = a.subtract(b);
				ComplexNumber c3 = a.multiply(b);
				ComplexNumber c4 = a.divide(b);
	}
}
