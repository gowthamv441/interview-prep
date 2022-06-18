package string;

/**

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true

Example 2:

Input: s = "rat", t = "car"
Output: false

 

Constraints:

    1 <= s.length, t.length <= 5 * 104
    s and t consist of lowercase English letters.


 */

public class anagram {
    public boolean isAnagram(String s, String t) {
        int[] s_arr = new int[26];
        int[] t_arr = new int[26];
        if(s.length() != t.length())
            return false;
        for(int i=0;i<s.length();i++) {
            s_arr[s.charAt(i)-97]++;
            t_arr[t.charAt(i)-97]++;
        }
        for(int i=0;i<26;i++) {
            if(s_arr[i]!=t_arr[i])
                return false;
        }
        return true;
    }
}
