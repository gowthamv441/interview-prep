package Leetcode.easy;

/**

Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.

Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).

 

Example 1:

Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
Output: 2

Example 2:

Input: board = [["."]]
Output: 0

 

Constraints:

    m == board.length
    n == board[i].length
    1 <= m, n <= 200
    board[i][j] is either '.' or 'X'.

 

Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?

 */

class Solution {
    
    private void moveRight(char[][] board, int r, int c, int border) {
        while(c<border) {
            if(board[r][c] == 'X') {
                board[r][c] = '.';
                c++;
            } else {
                break;
            }
        }
    }
    
    private void moveDown(char[][] board, int r, int c, int border) {
        while(r<border) {
            if(board[r][c] == 'X') {
                board[r][c] = '.';
                r++;
            } else {
                break;
            }
        }
    }
    
    private boolean hasRight(int i, int j, int R, int C) {
        return j+1<C;
    }
    
    private boolean hasDown(int i, int j, int R, int C) {
        return i+1<R;
    }
    
    private boolean isBattleShipPresent(char[][] board, int i, int j) {
        return board[i][j] == 'X';
    }
    
    public int countBattleships(char[][] board) {
        int R = board.length;
        int C = board[0].length;
        
        int count =0;
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(board[i][j] == 'X')   {
                    if(hasRight(i,j,R,C) && isBattleShipPresent(board,i, j+1)) {
                        moveRight(board, i, j, C);
                        count+=1;
                    } 
                    else if(hasDown(i,j,R,C) && isBattleShipPresent(board, i+1,j)) {
                        moveDown(board, i, j, R);
                        count+=1;
                    }
                    else {
                        board[i][j] = '.';
                        count +=1;
                    }
                }
            }
        }
        return count;
    }
}
