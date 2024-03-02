package DesignPattern;
/**
 * Create object based on some conditions
 */

abstract class Shape {
	int sides;
}

class Triangle extends Shape {
	Triangle() {
		this.sides = 3;
	}
}

class Rectangle extends Shape {
	Rectangle() {
		this.sides = 4;
	}
}

class Circle extends Shape {
	Circle() {
		this.sides = 0;
	}
}

class ShapeFactory {
	Shape createShapeObject(String shape) {
		if (shape.equals("CIRCLE")) return new Circle();
		else if (shape.equals("TRIANGLE")) return new Triangle();
		return null;
	}
}

public class Factory {

}
