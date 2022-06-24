package Leetcode.medium;

/*
 * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.

To complete the ith replacement operation:

    Check if the substring sources[i] occurs at index indices[i] in the original string s.
    If it does not occur, do nothing.
    Otherwise if it does occur, replace that substring with targets[i].

For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".

All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.

    For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.

Return the resulting string after performing all replacement operations on s.

A substring is a contiguous sequence of characters in a string.

 

Example 1:

Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
Output: "eeebffff"
Explanation:
"a" occurs at index 0 in s, so we replace it with "eee".
"cd" occurs at index 2 in s, so we replace it with "ffff".

Example 2:

Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
Output: "eeecd"
Explanation:
"ab" occurs at index 0 in s, so we replace it with "eee".
"ec" does not occur at index 2 in s, so we do nothing.

 

Constraints:

    1 <= s.length <= 1000
    k == indices.length == sources.length == targets.length
    1 <= k <= 100
    0 <= indexes[i] < s.length
    1 <= sources[i].length, targets[i].length <= 50
    s consists of only lowercase English letters.
    sources[i] and targets[i] consist of only lowercase English letters.


 */
class Solution {
    
    Map<Integer, Pair<Integer, String>> orderMap;
    
    private boolean isStringMatch(String src, String target) {
        if(src.length() != target.length()) return false;
        
        for(int i=0; i<src.length(); i++){
            if(src.charAt(i) != target.charAt(i))
                return false;
        }
        return true;
        
    }
     
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int k = indices.length;
        orderMap = new TreeMap<>();
        List<String> resultArray = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder(s);
        
        for(int i = 0; i < k; i++) {
            if(((indices[i]+sources[i].length()) > s.length())) {
                continue;
            }
            String temp = s.substring(indices[i], indices[i]+sources[i].length());
            if(isStringMatch(temp, sources[i])) {
                orderMap.put(indices[i], new Pair(sources[i].length(), targets[i]));
                // sb.delete(indices[i], indices[i]+sources[i].length());
                // sb.insert(indices[i], targets[i]);
            }
        }
        Set<Integer> orderSet = orderMap.keySet();
        int index = 0;
        Integer[] keySet = orderSet.toArray(new Integer[0]);
        if(keySet.length>0) {
            resultArray.add(s.substring(index, keySet[0]));   
        }
        for(int i=0; i<keySet.length; i++) {
            index = keySet[i];
            //resultArray.add(s.substring(index, index+orderMap.get(index).getKey()));
            resultArray.add(orderMap.get(index).getValue());
            index = index+orderMap.get(index).getKey();
            if(i+1 < keySet.length) {
                if(index < keySet[i+1]) {
                    resultArray.add(s.substring(index, keySet[i+1]));
                }  
            } 
        }
        resultArray.add(s.substring(index, s.length()));
        //System.out.println(resultArray);
        sb = new StringBuilder();
        for(String st : resultArray) {
            sb.append(st);
        }
        
        String result = sb.toString();
        return result;
    }
}
