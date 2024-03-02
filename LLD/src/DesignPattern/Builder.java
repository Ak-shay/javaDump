package DesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder pattern is used when we want create complex object step by step
 */


abstract class AbsStudent1 {
	String name;
	int rollNo;
	int age;
	List<String> subjects;
	
}

/**
 * Actual object we want to create
 */

class Student1 extends AbsStudent1 {
	Student1(StudentBuilder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.rollNo = builder.rollNo;
		this.subjects = builder.subjects;
	}
}

abstract class StudentBuilder extends AbsStudent1{
	
	public StudentBuilder setRollNo(int rollNo) {
		this.rollNo = rollNo;
		return this;
	}
	
	public StudentBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public StudentBuilder setAge(int age) {
		this.age = age;
		return this;
	}
	
	public abstract StudentBuilder setSubjects();
	
	public Student1 build() {
		return new Student1(this);
	}
}

abstract class EngieeringStudentBuilder extends StudentBuilder {
	
	public StudentBuilder setSubjects() {
		this.subjects = new ArrayList<String>();
		this.subjects.add("science");
		return this;
		
	}
}

abstract class MBAStudentBuilder extends StudentBuilder {
	
	public StudentBuilder setSubjects() {
		this.subjects = new ArrayList<String>();
		this.subjects.add("commerce");
		return this;
		
	}
}

class Director {
	StudentBuilder builder;
	
	Director(StudentBuilder builder) {
		this.builder = builder;
	}
	
	public Student1 createEngineeringStudent() {
		return this.builder.setAge(12).setName("Ram").setRollNo(1).setSubjects().build();
	}
	
}

public class Builder {
	public static void main(String args[]) {
		
	}

}
