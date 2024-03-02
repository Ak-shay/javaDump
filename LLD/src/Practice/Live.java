package Practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class Test1 {
	public static void m() {
		System.out.println("test1 static");
	}
}

class Test2 extends Test1 {
	
	// hides (compile time) the Test1 m, otherwise Test2.m() would point to Test1.m()
	public static void m() {
		System.out.println("test2 static");
	}
}

class Student {
	String name;
	int age;
	
	Student(String name, int age) {
		this.age = age;
		this.name = name;
	}
	
	int getAge() {
		return this.age;
	}
	
	String getName() {
		return this.name;
	}
}

public class Live {
	public static void main(String args[]) {
		System.out.println("hello");
		
		String s1 = "11";
		String s3 = "11";
		String s2 = new String("11");
		String s4 = new String("11");
		System.out.println(s1==s3);
		System.out.println(s3.equals(s2));
		System.out.println(s2.equals(s3));
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
		System.out.println(s2.equals(s1));
		System.out.println(s4==s2);
		System.out.println(s4.equals(s2));
		System.out.println(s2.equals(s4));
		
		
		Test1.m();
		Test2.m();
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		q.remove();
		q.peek();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // ascending order by default, Collections.reverseOrder() -> makes it max heap
		pq.add(1);
		pq.peek();
		pq.remove();
		
		Iterator<Integer> iterator = pq.iterator();
		 
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
		
		Stack<Integer> s = new Stack<Integer>();
		s.add(1);
		s.peek();
		s.pop();
		
		Deque<Integer> dq = new ArrayDeque<Integer>();
		dq.add(1); // similar to add last
		dq.pop(); // removeFirst
		
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.remove(0); // index or object's first occ
		
		Collections.sort(arr, Collections.reverseOrder()); // ascending order by default, Collections.reverseOrder() -> makes it descending
		
		List<Student> stu = new ArrayList<>();
		stu.add(new Student("ram", 3));
		stu.add(new Student("syam", 2));
		stu.add(new Student(null, 5));
		// sort based on age
		Comparator<Student> comp1 = Comparator.comparing(Student::getName, Comparator.nullsLast(String::compareTo)); // ascending order of age with null at last
		Collections.sort(stu, comp1.reversed()); // Descending order with nulls at first
		
		for(Student st: stu) {
			System.out.println(st.getName() + "  " + st.getAge());
		}
		
//		Math.max(a, b)
		
	}
}