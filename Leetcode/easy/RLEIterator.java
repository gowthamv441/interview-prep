package Leetcode.easy;
/*
 * We can use run-length encoding (i.e., RLE) to encode a sequence of integers. In a run-length encoded array of even length encoding (0-indexed), for all even i, encoding[i] tells us the number of times that the non-negative integer value encoding[i + 1] is repeated in the sequence.

    For example, the sequence arr = [8,8,8,5,5] can be encoded to be encoding = [3,8,2,5]. encoding = [3,8,0,9,2,5] and encoding = [2,8,1,8,2,5] are also valid RLE of arr.

Given a run-length encoded array, design an iterator that iterates through it.

Implement the RLEIterator class:

    RLEIterator(int[] encoded) Initializes the object with the encoded array encoded.
    int next(int n) Exhausts the next n elements and returns the last element exhausted in this way. If there is no element left to exhaust, return -1 instead.

 

Example 1:

Input
["RLEIterator", "next", "next", "next", "next"]
[[[3, 8, 0, 9, 2, 5]], [2], [1], [1], [2]]
Output
[null, 8, 8, 5, -1]

Explanation
RLEIterator rLEIterator = new RLEIterator([3, 8, 0, 9, 2, 5]); // This maps to the sequence [8,8,8,5,5].
rLEIterator.next(2); // exhausts 2 terms of the sequence, returning 8. The remaining sequence is now [8, 5, 5].
rLEIterator.next(1); // exhausts 1 term of the sequence, returning 8. The remaining sequence is now [5, 5].
rLEIterator.next(1); // exhausts 1 term of the sequence, returning 5. The remaining sequence is now [5].
rLEIterator.next(2); // exhausts 2 terms, returning -1. This is because the first term exhausted was 5,
but the second term did not exist. Since the last term exhausted does not exist, we return -1.

 */

 /*
  * APPROACH 1, TIME COMPLEXITY = O(N)
  */

  class RLEIterator {

    int[] encoding;
    int lastVisitedIndex;
    
    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
        lastVisitedIndex = 0;
    }
    
    public int next(int n) {
        int index = lastVisitedIndex;
        if(index < 0)
            return -1;
        while (index< encoding.length) {
            if(encoding[index]-n >= 0) {
                encoding[index] = encoding[index] - n;
                lastVisitedIndex = index;
                return encoding[index+1];
            }
            else {
                n = n - encoding[index];
                encoding[index] = 0;
                index = index + 2;
            }
        }
        lastVisitedIndex = -1;
        return -1;
        
    }
}

/**
val = 3 8 0 9 2 5
index = 0 1 2 3 4 5

n  = 2;
encoding.length = 6

3 - 2 >= 0  => 1 8 0 9 2 5 , 8

n = 1;
1 - 1 > =0 => 0 8 0 9 2 5, 8

n = 1;

0 - 1 < 0 

n = 1 - 0 = 1;

0 - 1 < 0

n = 1 - 0 = 1;

2 - 1 > = 0 => 0 8 0 9 1 5, 5

n = 2

1 - 2 < 0
n = 2 - 1 = 1 => 0 8 0 9 0 5

6

Time Complexity : Q*N (Quadratic)

Time Complexity : N (Linear)

**/

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(encoding);
 * int param_1 = obj.next(n);
 */

  /*
  * APPROACH 1, TIME COMPLEXITY = O(LogN) using TreeMAp
  */

  class RLEIterator {
    private TreeMap<Long, Integer> indexToVal;
    private long nextIndex;
    private long total;

    public RLEIterator(int[] encoding) {
        this.indexToVal = new TreeMap<>();
        this.nextIndex = -1;
        
        if(encoding.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        int n = encoding.length;
        int prev = -1;
        long index = 0;
        for(int i = 0; i < n; i+=2) {
            int count = encoding[i];
            int val = encoding[i + 1];
            
            if(count == 0) {
                continue;
            }
            
            if(val != prev) {
                indexToVal.put(index, val);
            }
            
            index +=count;
            prev = val;
        }
        
        this.total = index;
    }
    
    public int next(int n) {
        this.nextIndex += n;
        
        if(nextIndex >= total) {
            return -1;
        }
        
        Map.Entry<Long, Integer> floorEntry = indexToVal.floorEntry(nextIndex);
        
        if(floorEntry != null) {
            return floorEntry.getValue();
        } else {
            return -1;
        }
    }
}