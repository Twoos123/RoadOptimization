import java.util.*;

public class LabyrinthSolver {
    private static int ROW;
    private static int COL;
    private static int[][] grid;

    // structure to store coordinates of the cell

    // Below arrays details all 4 possible movements from a cell
    private static final int[] row = { -1, 0, 0, 1 };
    private static final int[] col = { 0, -1, 1, 0 };

    // Function to check if it is possible to go to position (row, col)
    // from current position. The function returns false if (row, col)
    // is not a valid position or has value 0 or it is already visited
    private static boolean isValidMove(int x, int y, boolean[][] visited) {
        return (x > 0 && x < ROW && y > 0 && y < COL && grid[x][y] == 1 && !visited[x][y]);
    }

    // Find Shortest Possible Route in a Matrix mat from source cell (i, j)
    // to destination cell (x, y)
    private static List<Cell> BFS(Cell src, Cell dest, int[][] grid) {
        // check source and destination cell
        // of the matrix have value 1
        List<Cell> empty = new ArrayList<>();
        if (grid[src.x][src.y] == 0 || grid[dest.x][dest.y] == 0) {
            return empty;
        }

        boolean[][] visited = new boolean[ROW][COL];

        // Mark the source cell as visited
        visited[src.x][src.y] = true;

        // Create a queue for BFS
        Queue<Cell> q = new LinkedList<>();

        // distance of source cell is 0
        q.add(src);

        // Parent array to store predecessor of each cell
        // This array is used to trace the path from destination
        // back to source
        Cell[] parent = new Cell[ROW * COL];

        // Stores length of longest path from source to destination
        int[] dist = new int[ROW * COL];

        // destination is found
        boolean destFound = false;

        // BFS loop
        while (!q.isEmpty()) {
            Cell curr = q.poll();

            // If this point is destination
            if (curr.x == dest.x && curr.y == dest.y) {
                destFound = true;
                break;
            }

            // checking all 4 possible movements from curr
            for (int i = 0; i < 4; i++) {
                int x = curr.x + row[i];
                int y = curr.y + col[i];

                // if next point is a valid and not visited yet
                if (isValidMove(x, y, visited)) {
                    visited[x][y] = true;
                    parent[x * COL + y] = curr;
                    dist[x * COL + y] = dist[curr.x * COL + curr.y] + 1;
                    q.add(new Cell(x, y));
                }
            }
        }
    
        // If destination is not found or destination is blocked
        if (!destFound) {
            return empty;
        }
    
        // vector to store the path
        List<Cell> path = new ArrayList<>();
    
        // Trace back the path from destination to source
        for (Cell curr = dest; curr != null; curr = parent[curr.x * COL + curr.y]) {
            path.add(curr);
        }
    
        // reverse the path to get from source to destination
        Collections.reverse(path);
    
        return path;
    }
    
    public static List<Cell> solve(int[][] inputGrid, int startX, int startY, int endX, int endY) {
        grid = inputGrid;
        ROW = grid.length;
        COL = grid[0].length;
    
        return BFS(new Cell(startX, startY), new Cell(endX, endY), grid);
    }
}    