import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// I need one array to store the previous state and one array to calculate the current state
// Optimization: if how to reduce the runtime complexity to less than N?
// All states between two repetitive states form a cycle, which would repeat itself over the time.
// Therefore, based on this observation, we could fast-forward the simulation rather than going step by step, once we encounter any repetitive state.
// Use a map to record all previous states.(I don't need the array anymore)
public class PrisonCellAfterNDay {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> map = new HashMap<>();
        while(N > 0) {
            map.put(Arrays.toString(cells), N);
            int[] current = new int[cells.length];
            for(int i = 1; i < cells.length - 1; i++) {
                current[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            }
            cells = current;
            N--;
            if(map.containsKey(Arrays.toString(cells))) {
                // last time we see this combination - current step = period
                int period = (map.get(Arrays.toString(cells)) - N);
                N = N % period;
            }
        }
        return cells;
    }
}
