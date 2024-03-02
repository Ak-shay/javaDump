package DesignPattern;

/**
 * Wrapping object to add more feature 
 * IS-A HAS-A relationship
 */

interface PizzaInf {
	int getCost();
}

class VegPizza implements PizzaInf {
	public int getCost() {
		return 100;
	}
}

class MargPizza implements PizzaInf {
	public int getCost() {
		return 80;
	}
}

abstract class PizzaDecorator implements PizzaInf{
	PizzaInf basePizza;
	PizzaDecorator (PizzaInf basePizza) {
		this.basePizza = basePizza;
	}
}

class ExtraCheese extends PizzaDecorator {
	ExtraCheese(PizzaInf basePizza) {
		super(basePizza);
	}

	public int getCost() {
		return this.basePizza.getCost() + 20;
	}
}

class ExtraVeg extends PizzaDecorator {
	ExtraVeg(PizzaInf basePizza) {
		super(basePizza);
	}
	public int getCost() {
		return this.basePizza.getCost() + 30;
	}
}


public class Decorator {
	public static void main(String args[]) {
		PizzaInf vegPizzaWithCheese = new ExtraCheese(new VegPizza());
		System.out.println(vegPizzaWithCheese.getCost());
		
	}
}
