/*
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

 

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
*/

/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start != b.start? a.start - b.start: a.end - b.end);
        
        for(List<Interval> intervals: schedule) {
            for(Interval interval: intervals) {
                pq.offer(interval);
            }
        }
        
        if(pq.isEmpty()) return res;
        
        Interval pre = pq.poll();
        
        while(!pq.isEmpty()) {
            Interval cur = pq.poll();
            if(pre.end < cur.start) {
                res.add(new Interval(pre.end, cur.start));
            } else {
                cur.start = Math.min(pre.start, cur.start);
                cur.end = Math.max(pre.end, cur.end);
            }
            pre = cur;
        }
        
        return res;
    }
}