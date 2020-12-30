import java.util.*;

public class WebsiteVisitPattern {

    //Define Object class
    class Data {
        private String username;
        private int timeStamp;
        private String website;

        public Data(String username, int timeStamp, String website) {
            this.username = username;
            this.timeStamp = timeStamp;
            this.website = website;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        //Load the data
        List<Data> dataLog = new ArrayList<>();
        for(int i = 0; i < username.length; i++) {
            dataLog.add(new Data(username[i], timestamp[i], website[i]));
        }
        //Sort by timeStamp
        Collections.sort(dataLog, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.timeStamp - o2.timeStamp;
            }
        });
        //count userPath: username - path
        Map<String, List<String>> userPaths = new HashMap<>();
        for(Data d : dataLog) {
            userPaths.putIfAbsent(d.username, new ArrayList<>());
            userPaths.get(d.username).add(d.website);
        }
        //calculate the frequency
        Map<List<String>, Integer> freqs = new HashMap<>();
        for(List<String> list : userPaths.values()) {
            calculateFreq(0, freqs, list, new ArrayList<>(), new HashSet<>());
        }

        int max = 0;
        List<String> result = new ArrayList<>();

        for(List<String> key : freqs.keySet()) {
            int freq = freqs.get(key);
            if(freq > max) {
                max = freq;
                result = key;
            } else if (freq == max && isLiteralSmall(key, result)) {
                result = key;
            }
        }
        return result;
    }

    private void calculateFreq(int index,
                               Map<List<String>, Integer> freqs,
                               List<String> websites,
                               List<String> currentList,
                               Set<List<String>> visited) {
        if(currentList.size() == 3) {
            if(visited.contains(currentList)) {
                return;
            }
            List<String> copy = new ArrayList<String>(currentList);
            visited.add(copy);
            if(!freqs.containsKey(copy)) {
                freqs.put(copy, 0);
            }
            freqs.put(copy, freqs.get(copy) + 1);
            return;
        }

        for(int i = index; i < websites.size(); i++) {
            currentList.add(websites.get(i));
            calculateFreq(i + 1, freqs, websites, currentList, visited);
            currentList.remove(currentList.size() - 1);
        }
    }

    private boolean isLiteralSmall(List<String> key, List<String> result) {
        for(int i = 0; i < key.size(); i++) {
            if(!key.get(i).equals(result.get(i))) {
                return key.get(i).compareTo(result.get(i)) < 0;
            }
        }
        return false;
    }

}
