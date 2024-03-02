package CoreConcepts;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A fixed queue shared between a consumer and producer
 * consume don't consume if queue in empty
 * producer don't produce to if queue is full
 */

class QueueResource {
	private Queue<Integer> q;
	int size;
	int count;
	
	QueueResource(int s) {
		this.q = new LinkedList<Integer>();
		this.size = s;
		this.count = 0;
	}

	
	synchronized void produce() {
		while(this.q.size() == this.size) {
			System.out.println("Tried producing but queue is full " + Thread.currentThread().getName());
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
			System.out.println("Produced: " + count + " by thread: " + Thread.currentThread().getName());
			this.q.add(count++);
			notify();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	synchronized void consume() {
		while (this.q.isEmpty()) {
			System.out.println("Tried consuming but no data found " + Thread.currentThread().getName());
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
//			Thread.sleep(1500);
			System.out.println("Consumed: " + this.q.remove() + " by thread: " + Thread.currentThread().getName());
			notify();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

public class ProducerConsumerProblem {
	
	public static void main(String args[]) {
		QueueResource r = new QueueResource(5);
		Thread pro = new Thread(() -> {
			while(true) r.produce();
		});
		Thread con1 = new Thread(() -> {
			while(true) r.consume();
		});
		
		con1.start();
		pro.start();
		
	}
	
	

}
