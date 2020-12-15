import java.util.Arrays;
import java.util.Comparator;

// split each log with 2 part -> [identifier, content]
// define customerize comparator to sort the logs
// Case 1, if both logs are letter, compare content first. If contents are in the same order, compare the identifier
// Case 2, if one of the logs is digit, letter logs come first
// Case 3, if both logs are digits, do nothing
// Let N be the number of logs in the list and M be the maximum length of a single log.
// Time: O(M * N * logN) = Arrays.sort NlogN * each compare is M
// Space: O(M * logN): For each invocation of the compare() function, we would need up to O(M) space to hold the parsed logs
// In addition, since the implementation of Arrays.sort() is based on quicksort algorithm whose space complexity is log n, assuming that // the space for each element is O(1)). Since each log could be of O(M) space, we would need O(M⋅logN) space to hold the intermediate 
// values for sorting.

public class ReorderDataInLogFiles{
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] array1 = s1.split(" ", 2);
                String[] array2 = s2.split(" ", 2);
                String identifier1 = array1[0];
                String identifier2 = array2[0];
                String content1 = array1[1];
                String content2 = array2[1];
                
                boolean isDigit1 = Character.isDigit(array1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(array2[1].charAt(0));
                
                if(!isDigit1 && !isDigit2) {
                    return content1.equals(content2) ? identifier1.compareTo(identifier2) : content1.compareTo(content2);
                } else if (!isDigit1 && isDigit2) {
                    return -1;                    
                } else if (isDigit1 && !isDigit2) {
                    return 1;
                }
                return 0;
            }
        };
        
        Arrays.sort(logs, stringComparator);
        return logs;
    }
}