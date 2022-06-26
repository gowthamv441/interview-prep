package Leetcode.medium;

/*
 * 

 There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.

 

Example 1:

Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12
Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.

Example 2:

Input: cardPoints = [2,2,2], k = 2
Output: 4
Explanation: Regardless of which two cards you take, your score will always be 4.

Example 3:

Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55
Explanation: You have to take all the cards. Your score is the sum of points of all cards.

 

Constraints:

    1 <= cardPoints.length <= 105
    1 <= cardPoints[i] <= 104
    1 <= k <= cardPoints.length


 */

 /**


Algorithm ::

Dynamic Programming - Space Optimized

Intuition

In approach 1 we used two extra storage spaces (two arrays of size k) to store the total score that can be obtained by taking i cards from the respective end of the array.

Instead of pre-computing the arrays, we can calculate the total score while iterating over the array and store the total score in two variables (in place of the two arrays).

Algorithm

    Initialize two variables, namely frontScore and rearScore to store the score obtained by selecting the first i cards and the last k - i cards in the array.

    frontScore is initialized to the sum of the first k cards in the array, and rearScore is initialized to 0.

    Initialize maxScore to frontScore.

    Iterate backwards from i = k - 1 -> 0. At each iteration, we calculate the score by selecting i cards from the beginning of the array and k - i cards from the end (currentScore). If this score is greater than maxScore, we update it.

  */

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        
        int maxPoints = 0;
        int n = cardPoints.length;
        
        int frontScore = 0;
        for(int i=0; i<k ;i++) 
            frontScore+=cardPoints[i];
        
        maxPoints = frontScore;
        
        int rearScore = 0;
        for(int i = k-1; i>=0; i--) {
            rearScore += cardPoints[n-(k-i)];
            frontScore -=cardPoints[i];
            int currScore = frontScore + rearScore;
            maxPoints = Math.max(maxPoints, currScore);
        }
        
        return maxPoints;
    }
}
