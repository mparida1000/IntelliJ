package leetcode.graph;

public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        return 0;
    }

    public static void main(String[] args) {
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };

        SnakesAndLadders game = new SnakesAndLadders();
        int result = game.snakesAndLadders(board);
        System.out.println(result);
    }
}
