package CoreConcepts;

/*
 * t.join();
 * waits thread that is calling join to finish thread t
 * 
 * 
 * Two types of thread - user and daemon
 * Daemon - Something which  is running in ASYNC manner - setDaemon(true)
 * It is alive till any user thread is alive, gets destroyed as soon as all users thread are finished
 * Eg: GC, auto-save, logging
 */

class RunnableImpl implements Runnable {
	public void run() {
		System.out.println("Run method from thread: " + Thread.currentThread().getName());
	}
}

class ThreadExtend extends Thread {
	public void run() {
		System.out.println("Run method from thread: " + Thread.currentThread().getName());
	}
}


class SharedResource {
	boolean available = false;
	
	synchronized void addItem() {
		this.available = true;
		try {
			System.out.println("trying to add new item" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("added new item" + Thread.currentThread().getName());
			notifyAll(); // notify all waited threads to run again
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	synchronized void consumeItem () {
		while(!this.available) { // good practice to put in while loop to avoid superior wake up
			System.out.println("Thread trying to consume but item not availble" + Thread.currentThread().getName());
			try {
				wait(); // monitor lock is released and thread is in waiting till notified
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread consumed item" + Thread.currentThread().getName());
		this.available = false;		
	}
}


public class Threads {
	public static void main(String args[]) {
		// main thread
		System.out.println("Run method from thread: " + Thread.currentThread().getName());
		
		Thread t1 = new Thread(new RunnableImpl()); // thread created
		t1.start(); // starts the thread, calls the run method
		
		Thread t2 = new Thread(() -> {
			System.out.println("Run method from thread: " + Thread.currentThread().getName());
		});
		t2.start();
		
		Thread t3 = new ThreadExtend();
		t3.start();
		
		
		// shared resource
		SharedResource r = new SharedResource();
		Thread consumer1 = new Thread(() -> {
			r.consumeItem();
		});
		Thread supplier = new Thread(() -> r.addItem());
		
		consumer1.start(); // took lock on r but release it as not available
		supplier.start(); // adds item and notify thread to try again
	}
	

}
