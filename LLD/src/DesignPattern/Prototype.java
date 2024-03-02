package DesignPattern;

/**
 * Creational Design Pattern
 * Used when we have to make a copy/clone from existing object.
 * And might have to make certain tweaks from original object
 */

/**
 * Prototype design pattern says that cloning of object should not be responsibility of Client 
 * Rather Original has to take care 
 * Object Class comes with .clone() method, class implementing this method must implement Cloneable interface
 */


class RandomClass {
	int x;
}

class Student implements Cloneable{
	int rollNo;
	private String name;
	int age;
	private RandomClass rc;
	
	Student () {};
	
	Student(int rollNo, String name, int age) {
		this.age = age;
		this.name = name;
		this.rollNo = rollNo;
		this.rc = new RandomClass();
	}
	
	// this will create a shallow copy (copy of reference) of Student
//	public Object clone() throws CloneNotSupportedException {
//		return super.clone();
//	}
//	
	
	// Deep copy 
	public Object clone() throws CloneNotSupportedException {
		Student cloned = (Student) super.clone();
		// create deep copy
		cloned.rc = new RandomClass();
		// initialize its fields if present OR implement cloned method in that class too
		cloned.rc.x = this.rc.x;
		
		return cloned;
		
	}
}

public class Prototype {
	
	public static void main(String args[]) {
		Student s = new Student(12, "Ram", 24);
		
		/**
		 * Now we want to clone this object s
		 * One could be initalize object and keep on copying fields from original object
		 */
		
		Student s2 = new Student();
		s2.age = s.age;
		s2.rollNo = s.rollNo;
		s2.name = s.name; // what if some field is private? And we dont have getter also
		// What if there are 100s of fields, this would not scale
		// name of fields might not be known
		
		Student clonedStudent = (Student) s.clone();
		
	}
}
