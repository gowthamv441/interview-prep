/*
You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.

The MKAverage can be calculated using these steps:

    If the number of the elements in the stream is less than m you should consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to a separate container.
    Remove the smallest k elements and the largest k elements from the container.
    Calculate the average value for the rest of the elements rounded down to the nearest integer.

Implement the MKAverage class:

    MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
    void addElement(int num) Inserts a new element num into the stream.
    int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.

 

Example 1:

Input
["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
Output
[null, null, null, -1, null, 3, null, null, null, 5]

Explanation
MKAverage obj = new MKAverage(3, 1); 
obj.addElement(3);        // current elements are [3]
obj.addElement(1);        // current elements are [3,1]
obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
obj.addElement(10);       // current elements are [3,1,10]
obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
                          // After removing smallest and largest 1 element the container will be [3].
                          // The average of [3] equals 3/1 = 3, return 3
obj.addElement(5);        // current elements are [3,1,10,5]
obj.addElement(5);        // current elements are [3,1,10,5,5]
obj.addElement(5);        // current elements are [3,1,10,5,5,5]
obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
                          // After removing smallest and largest 1 element the container will be [5].
                          // The average of [5] equals 5/1 = 5, return 5

 
*/

public class MKAverage {

    int m, k;
    Deque<Integer> queue = new ArrayDeque();
    SortedList l1 = new SortedList();
    SortedList l2 = new SortedList();
    SortedList l3 = new SortedList();

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {
        queue.addLast(num);
        // add in the proper place
        if (l1.isEmpty() || num <= l1.getLast()) {
            l1.add(num);
        } else if (l2.isEmpty() || num <= l2.getLast()) {
            l2.add(num);
        } else {
            l3.add(num);
        }
        if (queue.size() > m) {
            int removedElement = queue.removeFirst();
            // remove in the proper place
            if (l1.contains(removedElement)) {
                l1.remove(removedElement);
            } else if (l2.contains(removedElement)) {
                l2.remove(removedElement);
            } else {
                l3.remove(removedElement);
            }
        }
		// adjust size of l1, l2, l3
        if (l1.size > k) {
            l2.add(l1.removeLast());
        } else if (l1.size < k && !l2.isEmpty()) {
            l1.add(l2.removeFirst());
        }
        if (l2.size > m - k - k) {
            l3.add(l2.removeLast());
        } else if (l2.size < m - k - k && !l3.isEmpty()) {
            l2.add(l3.removeFirst());
        }
    }

    public int calculateMKAverage() {
        if (l1.size + l2.size + l3.size < m) {
            return -1;
        }
        return (int)Math.floor((double)(l2.sum) / (l2.size));
    }

    static class SortedList {
        long sum;
        int size;
        TreeMap<Integer, Integer> tm = new TreeMap<>();

        public void add(int n) {
            tm.put(n, tm.getOrDefault(n, 0) + 1);
            sum += n;
            size++;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean contains(int n) {
            return tm.containsKey(n);
        }

        public void remove(int n) {
            if (!tm.containsKey(n)) {
                return;
            }
            int count = tm.get(n);
            sum -= n;
            size--;
            if (count == 1) {
                tm.remove(n);
            } else {
                tm.put(n, count - 1);
            }
        }

        public int removeLast() {
            Map.Entry<Integer, Integer> lastEntry = tm.lastEntry();
            if (lastEntry.getValue() == 1) {
                tm.remove(lastEntry.getKey());
            } else {
                tm.put(lastEntry.getKey(), lastEntry.getValue() - 1);
            }
            sum -= lastEntry.getKey();
            size--;
            return lastEntry.getKey();
        }

        public int removeFirst() {
            Map.Entry<Integer, Integer> firstEntry = tm.firstEntry();
            if (firstEntry.getValue() == 1) {
                tm.remove(firstEntry.getKey());
            } else {
                tm.put(firstEntry.getKey(), firstEntry.getValue() - 1);
            }
            sum -= firstEntry.getKey();
            size--;
            return firstEntry.getKey();
        }

        public int getLast() {
            return tm.lastKey();
        }

        public int getFirst() {
            return tm.firstKey();
        }
    }
	
}


/*

My Approach:

- create 1 double ended queue (to always keeps m elements), 1 treeMap (to element freq in queue), 1 sorted list (obtained using treeMap)
- if queue size is greater than M 
    - get front element O(1)
    - delete the front element from the map along with freq, if freq 0 remove, else reduce freq O(logM)
    - pust new element to queue's back O(1)
    - traverse trees keyset and update the sorted List O(M)

- for calc average
    - use left and right pointer to traverse over the sorted list O(k)
    - calc avg between left and right O(m-2k)


*/
