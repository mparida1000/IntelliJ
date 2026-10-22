package Top150;

import com.scaler.LL.Solution;

public class NQueens {
    private int count = 0;
    public int totalNQueens(int n) {

        // Boolean arrays for columns and diagonals
        boolean[] columns = new boolean[n];
        boolean[] diagonal1 = new boolean[2 * n - 1]; // '/' diagonal
        boolean[] diagonal2 = new boolean[2 * n - 1]; // '\' diagonal

        // Start placing queens row by row
        backtrack(0, n, columns, diagonal1, diagonal2);
        return count;
    }
    private void backtrack(int row, int n, boolean[] columns, boolean[] diagonal1, boolean[] diagonal2) {
        // Base case: all queens are placed
        if (row == n) {
            count++;
            return;
        }

        // Try placing a queen in each column of the current row
        for (int col = 0; col < n; col++) {
            int d1 = row + col;          // Index for '/' diagonal
            int d2 = row - col + n - 1;  // Index for '\' diagonal

            // Check if column and diagonals are safe
            if (columns[col] || diagonal1[d1] || diagonal2[d2]) {
                continue; // Not safe, skip
            }

            // Mark current column and diagonals as occupied
            columns[col] = true;
            diagonal1[d1] = true;
            diagonal2[d2] = true;

            // Recurse to the next row
            backtrack(row + 1, n, columns, diagonal1, diagonal2);

            // Backtrack: Unmark the current column and diagonals
            columns[col] = false;
            diagonal1[d1] = false;
            diagonal2[d2] = false;
        }
    }

    public static void main(String[] args) {
        NQueens solution = new NQueens();

        // Example 1
        int n1 = 4;
        System.out.println("Number of solutions for n = " + n1 + ": " + solution.totalNQueens(n1));

        // Example 2
        solution = new NQueens();
        int n2 = 2;
        System.out.println("Number of solutions for n = " + n2 + ": " + solution.totalNQueens(n2));
    }
}
