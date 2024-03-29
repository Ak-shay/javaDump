package CoreConcepts;

/**
 * used to examine the classes, methods, fields, interfaces at runtime 
 * and also possible to change the behavior of classes too.
 * 
 * JVM creates one class Class object for each class everytime it loads
 * Class class contains metadata about that class, eg: constuctor, fields, method etc.
 * Instance of Class class represent classes at the runtime
 * 
 */

class Bird {
	void canFly() {
		
	}
}

public class Reflection {
	public static void main(String args[]) {
		try {
			// M1
			Class birdClass = Class.forName("Bird");
			System.out.println(birdClass.toString());
		} catch(Exception ex) {
			System.out.println(ex.toString());
		}
		
		// M2
		Class birdClass = Bird.class;
		System.out.println(birdClass);
		
		//M3
		Bird b = new Bird();
		Class birdClass2 = b.getClass();
		System.out.println(birdClass2);
		
	}
}
