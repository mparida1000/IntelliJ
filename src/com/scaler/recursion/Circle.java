package com.scaler.recursion;

class Circle {
    public final float PI = 3.14f;
    public float radius;
    
    
    // Define constructor here
    public Circle(float radius){
        this.radius = radius;
    }

    float perimeter(){
		return (float)2*PI*radius;
		
	}
	
	float area(){
		return (float)PI*radius*radius;
		
	}
	
	public static void main(String[] args) {
		 Circle a = new Circle(3);  // Radius = 3
					System.out.println(a.perimeter()); // 18.84
				    System.out.println(a.area()); // 28.26
	}
}

