package Leetcode.medium;

/*

Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

    For example, "ace" is a subsequence of "abcde".

 

Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".

Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2

 

Constraints:

    1 <= s.length <= 5 * 104
    1 <= words.length <= 5000
    1 <= words[i].length <= 50
    s and words[i] consist of only lowercase English letters.


 */


/*

Next Letter Pointer Algorithm ::

go through S once, and while I'm doing that, I move through all words accordingly. That is, I keep track of how much of each word I've already seen, and with each letter of S, I advance the words waiting for that letter. To quickly find the words waiting for a certain letter, I store each word (and its progress) in a list of words waiting for that letter. Then for each of the lucky words whose current letter just occurred in S, I update their progress and store them in the list for their next letter.

Let's go through the given example:

S = "abcde"
words = ["a", "bb", "acd", "ace"]

I store that "a", "acd" and "ace" are waiting for an 'a' and "bb" is waiting for a 'b' (using parentheses to show how far I am in each word):

'a':  ["(a)", "(a)cd", "(a)ce"]
'b':  ["(b)b"]

Then I go through S. First I see 'a', so I take the list of words waiting for 'a' and store them as waiting under their next letter:

'b':  ["(b)b"]
'c':  ["a(c)d", "a(c)e"]
None: ["a"]

You see "a" is already waiting for nothing anymore, while "acd" and "ace" are now waiting for 'c'. Next I see 'b' and update accordingly:

'b':  ["b(b)"]
'c':  ["a(c)d", "a(c)e"]
None: ["a"]

Then 'c':

'b':  ["b(b)"]
'd':  ["ac(d)"]
'e':  ["ac(e)"]
None: ["a"]

Then 'd':

'b':  ["b(b)"]
'e':  ["ac(e)"]
None: ["a", "acd"]

Then 'e':

'b':  ["b(b)"]
None: ["a", "acd", "ace"]

And now I just return how many words aren't waiting for anything anymore.

*/

class Node {
    String word;
    int index;
    
    Node(String word, int index) {
        this.word = word;
        this.index = index;
    }
}


class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        ArrayList<Node>[] buckets = new ArrayList[26];
        
        for(int i=0; i<26; i++) {
            buckets[i] = new ArrayList<Node>();
        }
        
        for(String word: words) {
            buckets[word.charAt(0)-'a'].add(new Node(word, 0));
        }
        
        List<String> ans = new ArrayList<String>();
        for(char c: s.toCharArray()) {
            ArrayList<Node> oldBucket = buckets[c-'a'];
            buckets[c-'a'] = new ArrayList<Node>();
            
            for(Node node: oldBucket) {
                node.index++;
                if(node.index == node.word.length()) {
                    ans.add(node.word);   
                }
                else {
                    buckets[node.word.charAt(node.index)-'a'].add(node);
                }
            }
            oldBucket.clear();
        }
        return ans.size();
    }
    
}

