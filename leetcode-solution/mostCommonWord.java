import java.util.HashMap;
import java.util.Map;

/*Solution:
*  1. replace the punctuations with space, and put all letters in lower case
*  2. split the string into words
*  3. use map to count the appearance of each word, excluding the banned words
*  4. return the word with the highest frequency
* */
public class mostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        String modified = paragraph.replaceAll("[^a-zA-Z]", " ").toLowerCase();
        String[] words = modified.split("\\s+");
        HashMap<String, Integer> map = new HashMap<>();
        for(String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for(String word : banned) {
            map.remove(word);
        }
        String result = null;
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(result == null || entry.getValue().compareTo(map.get(result)) > 0) {
                result = entry.getKey();
            }
        }
        return result;
    }
}
