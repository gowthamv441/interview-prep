package Leetcode.hard;

/*
 * 

 A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.

A half-open interval [left, right) denotes all the real numbers x where left <= x < right.

Implement the RangeModule class:

    RangeModule() Initializes the object of the data structure.
    void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
    boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
    void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).

 

Example 1:

Input
["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
Output
[null, null, null, true, false, true]

Explanation
RangeModule rangeModule = new RangeModule();
rangeModule.addRange(10, 20);
rangeModule.removeRange(14, 16);
rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)

 

Constraints:

    1 <= left < right <= 109
    At most 104 calls will be made to addRange, queryRange, and removeRange.


 */


class RangeModule {
    TreeMap<Integer, Integer> map;
    
    public RangeModule() {
        map = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> floorEntry = map.floorEntry(left);
        Map.Entry<Integer, Integer> higherEntry = map.higherEntry(left);
        right = right - 1;
        
        if(floorEntry!=null && floorEntry.getValue()>=left-1){
            map.remove(floorEntry.getKey());
            left = floorEntry.getKey();
            right = Math.max(right, floorEntry.getValue());
        }
        
        while(higherEntry!=null && higherEntry.getKey()<=(right+1)){
            map.remove(higherEntry.getKey());
            
            if(higherEntry.getValue()>right) {
                right = higherEntry.getValue();
            }
            higherEntry = map.higherEntry(higherEntry.getKey());
        }
        
        map.put(left, right);
    }
    
    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> ceilingEntry = map.floorEntry(left);
        right = right - 1;
        if(ceilingEntry!= null && ceilingEntry.getValue()>= right)
            return true;
        return false;
    }
    
    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> floorEntry = map.floorEntry(left);
        Map.Entry<Integer, Integer> higherEntry = map.higherEntry(left);
        right = right - 1;

        if(floorEntry!=null && floorEntry.getValue()>=left){
            if(floorEntry.getValue()>right) {
                map.put(floorEntry.getKey(), left-1);
                map.put(right+1, floorEntry.getValue());
                return;
            } else{
                map.put(floorEntry.getKey(), left-1);
            }
        }
        
        while(higherEntry!=null && higherEntry.getKey()<= right){
            if(higherEntry.getValue()>right) {
                map.remove(higherEntry.getKey());
                map.put(right+1, higherEntry.getValue());
                return;
            } else{
                map.remove(higherEntry.getKey());
                higherEntry = map.higherEntry(higherEntry.getKey());
            }
        }
    }
}
/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */