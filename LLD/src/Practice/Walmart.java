package Practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//You are given two stream of data as shown below : 
//stream 1 - apple-123 ball-456 cat-234 dog-123 elephant-543 apple-321 (dup)
//stream 2 - apple-123 cat-234 dog-122 eagle-345
//
//The first part is the key and the second part is the value.
//
//A pair of records from the stream is said to be matching when both the key and value match.
//A pair of records from the stream is said to be mismatching when the keys match but the values do not match.
//
//A record is called duplicate if the same key appears twice in same stream.
//
//
//You have to consume both streams and generate a report as below : 
//
//
//matching count - number of records matched.
//mismatch count - number of records mismatched. (both keys but mismatch value)
//duplicate count - number of records appearing as duplicates.
//
//input format - each line of the form stream number followed by '-' followed by key followed by '-' followed by value or it is 'gr' which is to generate report.


/*
 * 
sample i/p -
1-apple-123
2-dog-122
1-ball-456
2-apple-123
gr
1-cat-234
1-dog-123
gr
1-cat-234
1-elephant-543
2-eagle-345
gr

sample o/p - 
matching count - 1
mismatching count - 0
duplicate count - 0

matching count - 1
mismatching count - 1
duplicate count - 0

matching count - 1
mismatching count - 1
duplicate count - 1
 */


/**
 * gr -> report 
 * 
 * map1 
 * apple -> 123
 * ball -> 456
 * cat -> 234
 * dog -> 123
 * 
 * map2
 * dog -> 122
 * apple -> 123
 * 
 * set<> procesKeys ->  dog and apple (take care of both match and miss)
 * 
 * match +1
 * 
 * gr
 * 
 * 
 * mismatch = 1
 * match = 1
 * 
 * 
 * dup -> just check the keys
 * 
 * mis -> had a mismatch
 * 
 * match -> 
 * 
 */

public class Walmart {

	
	
	public static void main(String args[]) {
		
		int match = 0;
		int misMatch = 0;
		int duplicate = 0;
		
		Map<String, String> map1 = new HashMap<>(); // map of stream 1
		Map<String, String> map2 = new HashMap<>(); // map of stream 2
		
		Set<String> processedKeys = new HashSet<String>(); // process
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			String s = sc.nextLine();  // 1-apple-123
						
			if (s.equals("gr")) {
				// report
				
				for (String key1: map2.keySet()) {
					if (!processedKeys.contains(key1) && map1.containsKey(key1)) {
						// match or miss
						if (map1.get(key1).equals(map2.get(key1))) {
							match++;
						}
						else misMatch++;
						processedKeys.add(key1);
					}
				}
				System.out.println(match + " " + misMatch + " " + duplicate);
			}
			else {
				// "1" "apple" "123"
				String[] splits = s.split("-"); // 3 strings
				char streamNum = s.charAt(0); // 1 or 2
				String key = splits[1]; // apple
				String val = splits[2]; // 123

				if (streamNum == '1') {
					if (map1.containsKey(key)) {
						// duplicate
						duplicate++;
					}
					else map1.put(key, val);
				}
				
				if (streamNum == '2') {
					if (map2.containsKey(key)) {
						// duplicate
						duplicate++;
					}
					else map2.put(key, val);
				}
			}
		}
	}
	
}
