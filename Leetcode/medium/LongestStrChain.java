package Leetcode.medium;
/**
 * You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

    For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".

A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

 

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.

 

Constraints:

    1 <= words.length <= 1000
    1 <= words[i].length <= 16
    words[i] only consists of lowercase English letters.


 */

 /**
  * Algorithm

    Initialize a map where key is the word and value is the length of the longest word sequence possible with the key as the end word.

    Sort the word list in increasing order of the word length.

    Initialize longestWordSequenceLength to 1. This variable holds the length of the longest word sequence possible.

    Iterate over the sorted list.

    For each word initialize presentLength to 1.

    Iterate over the entire length of each word.
        Delete the character at ithi^{th}ith position from the current word and assign the new word to the variable predecessor.
        Check if predecessor is present in the list or not.
        If the predecessor is present, then assign its mapped value to previousLength. Update the presentLength if previousLength + 1 is greater than the presentLength.

    After terminating the inner for loop, assign presentLength to the current word in the map dp.

    Update the longestWordSequenceLength if the longest word sequence formed with the current word as the end word is longer than the previously considered word sequence.

    After terminating the outer for loop, return longestWordSequenceLength.

  */
class Solution {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();

        // Sorting the list in terms of the word length.
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int longestWordSequenceLength = 1;

        for (String word : words) {
            int presentLength = 1;
            // Find all possible predecessors for the current word by removing one letter at a time.
            for (int i = 0; i < word.length(); i++) {
                StringBuilder temp = new StringBuilder(word);
                temp.deleteCharAt(i);
                String predecessor = temp.toString();
                int previousLength = dp.getOrDefault(predecessor, 0);
                presentLength = Math.max(presentLength, previousLength + 1);
            }
            dp.put(word, presentLength);
            longestWordSequenceLength = Math.max(longestWordSequenceLength, presentLength);
        }
        return longestWordSequenceLength;
    }
}

/*
 * Let NNN be the number of words in the list and LLL be the maximum possible length of a word.

    Time complexity: O(N⋅(log⁡N+L2))O(N \cdot (\log N + L ^ 2))O(N⋅(logN+L2)).

    Sorting a list of size NNN takes O(Nlog⁡N)O(N \log N)O(NlogN) time. Next, we use two for loops in which the outer loop runs for O(N)O(N)O(N) iterations and the inner loop runs for O(L2)O(L ^ 2)O(L2) iterations in the worst case scenario. The first LLL is for the inner loop and the second LLL is for creating each predecessor. Thus the overall time complexity is O(Nlog⁡N+(N⋅L2))O(N \log N + (N \cdot L ^ 2))O(NlogN+(N⋅L2)) which equals O(N⋅(log⁡N+L2))O(N \cdot (\log N + L ^ 2))O(N⋅(logN+L2)).

    Space complexity: O(N)O(N)O(N).

    We use a map to store the length of the longest sequence formed with each of the NNN words as the end word.

 */



