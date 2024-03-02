package DesignPattern;

import java.util.*;

/**
 * Implement Notify Me feature for a product
 * Weather Station - As temp changes update multiple screens
 */

interface ObservorInf {
	void update(Object obj);
}

abstract class AbsObservable {
	List<Observor> observors;
	int data;
	
	public abstract void add(Observor ob);
	public abstract void remove(Observor ob);
	public abstract void notifyOb();
	public abstract int getData();
	public abstract void setData(int data);
}

class Observable extends AbsObservable {
	
	Observable(int data) {
		this.observors = new ArrayList<Observor>();
		this.data = data;
	}
	
	public void add(Observor ob) {
		this.observors.add(ob);
	}
	
	public void remove(Observor ob) {
		this.observors.remove(ob);
		System.out.println("removed " + ob.name);
	}
	public void notifyOb() {
		for (Observor ob: this.observors) {
			ob.update(this);
		}
	}
	public int getData() {
		return this.data;
	}
	
	public void setData(int data) {
		// add business logic here, when to notify users?
		this.data = data;
		this.notifyOb(); // update users
	}
	
}



public class Observor implements ObservorInf{
	String name;
	
	Observor(String name) {
		this.name = name;
	}
	
	public void update(Object obj) {
		// there could be many types observable for a observor
		if (obj instanceof Observable) {
			System.out.println("Updating " + this.name);
		}
		
	}
	
	public static void main(String args[]) {
		Observor ob1 = new Observor("ob1");
		Observor ob2 = new Observor("ob2");
		Observor ob3 = new Observor("ob3");
		
		Observable obj = new Observable(10);
		obj.add(ob1);
		obj.add(ob2);
		obj.add(ob3);
		obj.setData(12);
		
	}

}
