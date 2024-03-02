package CoreConcepts;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Functional interface - interface with 1 abstract method
 * Lambda expression is a way implementing functional interface
 * 
 */

@FunctionalInterface // not required
interface FunctionalIntEx {
	
	// only 1 abstract method
	void print(String s);
	
	// inherited from Object, no need to impl in class
	String toString();
	
	
	// not abstract
	private void m1() {
		
	}
	// not abstract
	static void m2() {
		System.out.println("abstract of interface");
	}
	// not abstract
	private static void m3() {
		
	}
	
	default void print() {
		System.out.println("default");
	}
}

/*
 * How can we implement functional interface
 */


class FunctionalIntExImpl implements FunctionalIntEx {
	public void print(String s) {
		System.out.println(s);
	}
}



public class Lambda {
	public static void main(String[] args) {
		// allowed 
		FunctionalIntEx.m2();
		
		//M1 of implementing Functional interface
		FunctionalIntEx ob3 = new FunctionalIntExImpl();
		ob3.print("basic implementation");
		ob3.print();
		
		// M2 of implementing Functional interface
		FunctionalIntEx ob = new FunctionalIntEx() {
			public void print(String s) {
				System.out.println(s);
			}
		};
		ob.print("anonymous method");
		
		// M3 of implementing Functional interface
		FunctionalIntEx ob2 = (String v) -> {
			System.out.println(v);
		};
		
		ob2.print("lambda expression");
		

		/*
		 * Types of functional interface  (pre-defined)
		 * Consumer<T> (accepts T return void) - accept
		 * Supplier<R> (accepts no value return R - get
		 * Function<T, R> (accepts T one and produce a result R) - apply
		 * Predicate<T> (type of function, accepts T but always return boolean) - test
		 * 
		 * 
		 */
		Consumer<String> con = (String s) -> {System.out.println(s);};
		con.accept("consumer functonal interface");
		
		Supplier<String> sup = () -> "Supplier functional interface";
		System.out.println(sup.get());
		
		Function<String, Integer> func = (String s) -> {
			System.out.println(s);
			return 1;
		};
		System.out.println(func.apply("Functional interface input"));
		
		Predicate<String> pred = (String s) -> {
			if (s=="predicate interface") return true;
			return false;
		};
		System.out.println(pred.test("predicate interface"));
		
		
		
		
		
				
	}

}