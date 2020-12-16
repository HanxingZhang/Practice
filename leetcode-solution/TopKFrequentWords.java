import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * TopKFrequentWords Count the frequency of each word, end add it to heap that
 * stores the best k candidates. We can use priorityQueue with our custom
 * ordering which puts the worst candidates at the top of the heap. At the end,
 * we pop off the heap up to k times. After that, just reverse the result so
 * that the best candidates are first. Time Complexity: O(Nlogk), where N is the
 * length of words. We count the frequency of each word in O(N) time, then we
 * add N words to the heap, each in O(logk) time. Finally, we pop from the heap
 * up to k times. As k≤N, this is O(Nlogk) in total. Space Complexity: O(N), the
 * space used to store our count.
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b) -> {
                // check value, if value equal, check the alpha order
                if(a.getValue() == b.getValue()) {
                    return b.getKey().compareTo(a.getKey());
                } else {
                    return a.getValue() - b.getValue();
                }
            }
        );
        
        //load the pq
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(0, pq.poll().getKey());
        }
        
        return result;
    }
}