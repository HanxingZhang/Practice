import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// 1. Find the circles. Any edge on a circle is not a critical connections.
// 2. How to find all the circles by O(n) time?
//    I. DFS forward search: For each node, traversal at most once. 
//       And then record the steps from head to current node when a move happen.
//    II. DFS backtrack: count the minimum steps back to the current nodes
// If the child node is not head and its steps is equals to the current steps we moved, that means this edge is a critial edge.
// Time complexity: O(n), Space complexity: O(n)

public class CriticalConnectionsInANetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, HashSet<Integer>> graph = initGraph(connections);
        int[] steps = new int[n];
        Arrays.fill(steps, -1);
        List<List<Integer>> results = new ArrayList<>();
        dfs(0, -1, 0, steps, graph, results);
        return results;
    }
    
    private Map<Integer, HashSet<Integer>> initGraph(List<List<Integer>> connections) {
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        for(List<Integer> connection: connections) {
            graph.putIfAbsent(connection.get(0), new HashSet<Integer>());
            graph.get(connection.get(0)).add(connection.get(1));
            graph.putIfAbsent(connection.get(1), new HashSet<Integer>());
            graph.get(connection.get(1)).add(connection.get(0));
        }
        return graph;
    }
    
    private int dfs(int child, int father, int step, int[] steps, Map<Integer, HashSet<Integer>> graph, List<List<Integer>> results) {
        int curStep = step + 1;
        steps[child] = curStep;
        for(int connection: graph.get(child)) {
            if(connection == father) {
                continue;
            } else if (steps[connection] == -1) {
                steps[child] = Math.min(steps[child], dfs(connection, child, curStep, steps, graph, results));
            } else {
                steps[child] = Math.min(steps[child], steps[connection]);
            }
        }
        
        if(child != 0 && steps[child] == curStep) {
            List<Integer> critial = new ArrayList<>();
            if(father > child) {
                int tmp = child;
                child = father;
                father = tmp;
            }
            critial.add(father);
            critial.add(child);
            results.add(critial);
        }
        return steps[child];
    }
}