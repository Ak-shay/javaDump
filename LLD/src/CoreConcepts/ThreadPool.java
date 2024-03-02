package CoreConcepts;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Thread Pool
 * It is a collection of threads aka workers, which are available to perform submitted tasks
 * Once completed, worker thread get back to thread pool and wait for a new task to assign
 * thread can be re-used
 * Thread creation takes time as certain memory needs to allocated (or some work has to be done)
 * but with thread pool, threads are already created and we can re-use them.
 * Thread creation is saved
 * Increase performance - more threads mean more context switching, 
 * using pool we have predefined num of threads
 * 
 * Tasks are put in queue, as soon as a thread is free, It gets assigned
 * 
 * java.util.Concurrent have a framework
 */

/**
 * ThreadPoolExecutor(corePoolSize, maxSize, keepAliveTime, TimeUnit unit, workQueue, threadFactory, rejectedExecutionHandler)
 * helps to create customized thread pool
 * task queue size
 * first min Size thread will be spawned then later task will be put in queue
 * if queue is full then 1 more thread will be spawned if maxThread size is not reached
 * and that task will be taken care by the new thread
 * why not create thread first before filling queue? because idea of min threads is it is sufficient to perform, 
 * once all threads are created they will come back to pool, average thread wont be min in this case 
 * 
 * Lets say new task come and queue is full and the max thread has reach
 * This task will be rejected.
 * 
 * if any thread gets back, it will check queue and run
 * 
 * corePoolSize/min threads: num of threads initially created and keep in pool even if they are idle
 * 
 * allowCoreThreadTimeOut: property of ThreadPoolExecuter, default is false. If true, 
 * idle threads will be terminated if crosses keepAliveTime time sitting idle (applies to all threads in pool) if false extra threads will stay
 * 
 * unit: sec, hour, min, ms.. unit for keepAliveTime
 * 
 * blockingQueue: used hold task before they got picked up by worker threads
 * - boundedQueue - with fixed size (ArrayBoundedQueue)
 * - unboundedQueue - no fixed size (LinkedBlockingQueue)
 * 
 * threadFactory: factory for creating new thread, ThreadPoolExecuter uses this to create new thread.
 * provides interface to:
 * give custom thread name
 * set thread priority (cannot rely on it, doesnt gaurantee)
 * set daemon flag
 * 
 * rejectedExecutionHandler: handler for the task that cannot be picked up by thread pool (when queue is full and maxThreads are created)
 * generally use for logging and debugging
 * 4 types of handler exists already
 * 
 */

/**
 * Lifecycle of thread pool executer
 * running: accepting new tasks
 * Running -> shutdown (dont accept new task but finish existing running tasks and queue) -> terminated (all threads get eliminated)
 * Running -> stop (dont accept new task and forcefully stops existing running tasks) -> terminated
 * 
 */

/**
 * How to choose coreThradPoolSize? min or max
 * it depends on various factor:
 * CPU cores(more threads more context switching)
 * JVM memory (each thread gets certain memories from JVM, lets say JVM was 2Gi -> limited resource so you cannot create as many thread 
 * CPU intense task: more processing time (less thread should be there)
 * I/O intense task: thread is idle during I/O call (better to have more threads)
 * Concurrency requirement (high or low)
 * memory required to process a request, eg: 1 request takes 10MB space, 100 such thread will take 1Gi so consider heap mem
 * Throughput, how fast you want to process the request
 * 
 * approx formula (doesnt consider mem yet): max threads = num of CPU cores * (1 + request waiting time/request processing time)
 * high CPU intensive means processing time is high
 * high I/O intensive means waiting time is high
 * 
 * 
 * JVM mem (2Gi)
 * 1 Gi Heap
 * code segment: 128MB
 * per thread space: 5MB (thread stack space) * num of threads
 * overhead: 256 MB
 * 
 * 2-1-0.128-0.256 ~ 500MB
 * thread = 100
 * 
 * mem required to process a request: 10MB (uses heap)
 * heap used = 100% (risky, use 60-70% ) -> 60-70 threads
 */


public class ThreadPool {
	public static void main(String args[]) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2));
		executor.allowCoreThreadTimeOut(true);
		for (int i = 1; i<=10; i++) {
			final int taskId = i;
			executor.submit(() -> {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("task " + taskId + " process by " + Thread.currentThread().getName());
			}); // accepts runnable interface
		}
		executor.shutdown();
	}

}
