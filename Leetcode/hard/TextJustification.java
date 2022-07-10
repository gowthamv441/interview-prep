package Leetcode.hard;

/*

Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.

 

Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.

Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]

 

Constraints:

    1 <= words.length <= 300
    1 <= words[i].length <= 20
    words[i] consists of only English letters and symbols.
    1 <= maxWidth <= 100
    words[i].length <= maxWidth


 */

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        int totalWordsProcessed = 0;
        while (totalWordsProcessed < words.length) {
            int count = findWordCountLimitPerLine(words, totalWordsProcessed, maxWidth);
            res.add(packWords(words, totalWordsProcessed, count, maxWidth));
            totalWordsProcessed += count;
        }
        return res;
    }
    
    private int findWordCountLimitPerLine(String[] words, int startIdx, int maxWidth) {
        int width = 0;
        int count = 0;
        for (int i=startIdx; i<words.length; i++) {
            // space
            if (i!=startIdx)
                width++;

            width += words[i].length();
            
            if (width<=maxWidth) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
    
    private String packWords(String[] words, int start, int len, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        
        if (start+len < words.length) {
            sb.append(packWordsNonLastLine(words, start, len, maxWidth));
        } else {
            sb.append(packWordsLastLine(words, start, len));
        }
        
        // padding per line if needed
        for (int i=sb.length(); i<maxWidth; i++) {
            sb.append(" ");
        }
        
        return sb.toString();
    }
    
    private String packWordsNonLastLine(String[] words, int start, int len, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        
        int onlyWordsLength = 0;
        for (int i=start; i<start+len; i++) {
            onlyWordsLength += words[i].length();
        }
        
        int totalSpaces = maxWidth-onlyWordsLength;
        int equalSpaceCount = len>1 ? totalSpaces/(len-1) : 0;
        int extraSpaceCount = len>1 ? totalSpaces%(len-1) : 0;

        for (int i=start; i<start+len; i++) {
            if (i!=start) {
                for (int j=0; j<equalSpaceCount; j++) {
                    sb.append(" ");
                }
                if (extraSpaceCount>0) {
                    sb.append(" ");
                    extraSpaceCount--;
                }
            }

            sb.append(words[i]);
        }
        
        return sb.toString();
    }
    
    private String packWordsLastLine(String[] words, int start, int len) {
        StringBuilder sb = new StringBuilder();
        
        for (int i=start; i<start+len; i++) {
            if (i!=start) {
                sb.append(" ");
            }

            sb.append(words[i]);
        }
        
        return sb.toString();
    }
}