package DesignPattern;

class EagerSingleton  {
	private static EagerSingleton instance = new EagerSingleton();
	
	private EagerSingleton() {};
	
	public static EagerSingleton getInstance() {
		return EagerSingleton.instance;
	}
}

class LazySingleton {
	private static LazySingleton instance = null;
	
	private LazySingleton() {};
	
	public static LazySingleton getInstance() {
		if (instance == null) {
			// what if 2 thread come here -> 2 object will be created in memory
			instance = new LazySingleton();
		}
		return instance;
	}
}

// Thread safe but expensive, what if 1000s thread come to getinstance
class SyncSingleton {
	private static SyncSingleton instance = null;
	
	private SyncSingleton() {};
	
	synchronized public static SyncSingleton getInstance() {
		if (instance == null) {
			instance = new SyncSingleton();
		}
		return instance;
	}
}

class Singleton {
	// volatile independent of cores, it deals with visibility -> threads tend to cache value 
	private static volatile Singleton instance = null; // volatile -> happens before link, read and write from main memory
	
	private Singleton() {};
	
	public static Singleton getInstance() {
		if (instance == null) {
			// t1, t2 here but only one will go due to sync 
			synchronized (Singleton.class) {
				// t1 first go in,found null -> creates
				// t2 came found non null -> came out
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	public static void main(String args[]) {
		System.out.println("hello");
	}
}



