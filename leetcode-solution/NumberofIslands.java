import java.util.LinkedList;
import java.util.Queue;

/**
 * NumberofIslands Linear scan the 2d grid map. If a node contains a '1', then
 * it is a root node that triggers a Breadth First Search. Put it into a queue
 * and set its value as '0' to mark as visited node. Iteratively search the
 * neighbors (four direction) of enqueued nodes until the queue become empty
 * Time complexity : O(M×N) where MM is the number of rows and NN is the number
 * of columns. Space complexity : O(min(M,N)) because in worst case where the
 * grid is filled with lands, the size of queue can grow up to min(M,N).
 */
public class NumberofIslands {
    private int rows;
    private int cols;

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        
        rows = grid.length;
        cols = grid[0].length;
        
        int count = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(i * cols + j);
                    
                    while(!queue.isEmpty()) {
                        int index = queue.poll();
                        int row = index / cols;
                        int col = index % cols;
                        if(row - 1 >= 0 && grid[row - 1][col] == '1') {
                            queue.add((row - 1) * cols + col);
                            grid[row - 1][col] = '0';
                        }
                        if(row + 1 < rows && grid[row + 1][col] == '1') {
                            queue.add((row + 1) * cols + col);
                            grid[row + 1][col] = '0';
                        }
                        if(col - 1 >= 0 && grid[row][col - 1] == '1') {
                            queue.add(row * cols + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if(col + 1 < cols && grid[row][col + 1] == '1') {
                            queue.add(row * cols + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        
        return count;
    }
}