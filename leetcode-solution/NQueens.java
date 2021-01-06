import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // records the column index we placed in each row
        List<Integer> cols = new ArrayList<Integer>();
        backTrack(result, cols, n);
        return result;
    }

    private void backTrack(List<List<String>> result, List<Integer> cols, int n) {
        if(cols.size() == n) {
            result.add(drawChessboard(cols));
            return;
        }

        for(int colIndex = 0; colIndex < n; colIndex++) {
            if(!isValid(cols, colIndex)) {
                continue;
            }
            cols.add(colIndex);
            backTrack(result, cols, n);
            cols.remove(cols .size() - 1);
        }
    }

    private List<String> drawChessboard(List<Integer> cols) {
        List<String> chessboard = new ArrayList<>();
        for(int i = 0; i < cols.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < cols.size(); j++) {
                sb.append(j == cols.get(i) ? 'Q': '.');
            }
            chessboard.add(sb.toString());
        }
        return chessboard;
    }

    private boolean isValid(List<Integer> cols, int column) {
        int rows = cols.size();
        for(int row = 0; row < rows; row++) {
            //对称性
            if(cols.get(row) == column) {
                return false;
            }
            //斜线1：cols里面的值 + 其对应的行数 == column + column对应的行数
            if(row + cols.get(row) == rows + column) {
                return false;
            }
            //斜线2： cols里面的值 - 其对应的行数 == column - column对应的行数。
            if(row - cols.get(row) == rows - column) {
                return false;
            }
        }
        return true;
    }
}
