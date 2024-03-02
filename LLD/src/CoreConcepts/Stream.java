package CoreConcepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Think stream like a pipeline through which our collection elements pass
 * through while passing various operations like filtering, sorting etc can
 * applied useful when bulk processing as it can do parallel processing (parallel stream)
 * 
 * step1: create/open stream of collection step2: add intermediate operations
 * like filter, sorted, map, distinct etc It transform one stream to another so
 * that more operations can be applied these are lazy operations -> gets
 * executed only when terminal operation is invoked step3: termination
 * operation: collect, reduce, count etc.. , it will close the stream, no more
 * operations can be added original collection is affected, you'll get a new
 */


/*
 * How to create Stream?
 * 
 * from collection: refName.stream()
 * from array: Arrays.stream(refName);
 * Stream.of(1,2,3)
 * Stream.builder().add(1).add(1).build()
 * Stream.iterate(startPoint, (Integer n)> n+1000).limit(5)  
 * 
 */

/**
 * filter
 * map((String s) -> s.toLowerCase())
 * flatMap -> takes collection, expected return is stream of that collection. Operated on collection of collection to get a merged collection
 * distinct()
 * sorted() -> sorts the ele (ascending), we cna also provide a comparator
 * peek() -> takes datatype  but dont do anything, maybe use for printing '
 * limit() -> truncate the data
 * skip() -> skip first n ele from the stream
 * mapTo* -> convert from any data type to *(int long..), takes function interface
 */

/**
 * Order of intermediate operations?
 * it will pick an element from the stream, pass till the last possible operations 
 * (stop if sorted kind of operation is given as it needs whole stream)
 * 
 */

/*
 * Terminal operators
 * forEach -> perform action on each ele but do not return , like peek but as a terminal operator
 * toArray() -> return primitive (Object[]) array, toArray((int size) -> new Integer[size])
 * reduce((Integer i, Integer j) -> i+j) -> performs associative aggregate operations on stream
 * collect(Collectors.*) -> collect as a collection, Collectors.toMap() ...
 * min(Comparator) -> reutrn max or min ele
 * max(Comparator) -> same as min default -> max with comparator do anthing
 * anyMatch(Predicate) -> if any ele match return true
 * allMatch
 * noneMatch
 * findFirst() -> first ele
 * findAny -> random ele from stream
 * 
 * once stream is closed by terminal operation it cannot be re-used (dont use ref, as stream will perform and close it)
 * 
 */

/**
 * parallelStream (uses fork join pool technique)
 * use concurrency, takes advantage of multi cores
 * .stream -> .parallelStream, perform same operations
 */
public class Stream {
	public static void main(String args[]) {
		List<Integer> salary = new ArrayList<Integer>();
		salary.add(1000);
		salary.add(2000);
		salary.add(3000);
		salary.add(4000);
		salary.add(5000);

		// get count of salary >= 3000

		long cnt = salary.stream().filter((Integer s) -> {
			return s >= 3000;
		}).count();
		System.out.println(cnt);
		System.out.println("hello");
		
		//  create a stream from primitive array
		// Array.stream(refName)
		
		List<List<Integer>> l = Arrays.asList(
			Arrays.asList(1,2,3),
			Arrays.asList(4,5,6),
			Arrays.asList(7,8,9)
		);
		List<Integer> finalList = l.stream().flatMap((List<Integer> i) -> i.stream()).collect(Collectors.toList());
		
		
	}

}
