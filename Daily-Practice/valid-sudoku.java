class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    char currentNum = board[i][j];
                    board[i][j] = '.';
                    if (!isSafe(board, i, j, currentNum)) {
                        return false; 
                    }
                    board[i][j] = currentNum;
                }
            }
        }
        return true;
    }

    private boolean isSafe(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        int rowStart = row - row % 3;
        int colStart = col - col % 3;

        for (int r = rowStart; r < rowStart + 3; r++) {
            for (int c = colStart; c < colStart + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
}