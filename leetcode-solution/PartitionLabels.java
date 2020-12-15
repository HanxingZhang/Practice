import java.util.ArrayList;
import java.util.List;

class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        // First, create a array with size 26 to record the last appearence of each characters.
        for(int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        
        int left = 0, right = 0;
        // Then create 2 pointers, left and right
        // left is to record start index of the each part
        // right is to record end index of the each part
        // How to update left and right=>
        // for each char, we can find the last apperence of this characters by using the last array. We can update the right correspondingly.
        // Whenever we find a char which is the last appereance of this char itself. We can update the left pointer and we start to find the next part
        List<Integer> results = new ArrayList<>();
        for(int i = 0; i < S.length(); i++) {
            right = Math.max(right, last[S.charAt(i) -'a']);
            if(i == right) {
                results.add(right - left + 1);
                left = i + 1;
            }
        }
        return results;
    }
}