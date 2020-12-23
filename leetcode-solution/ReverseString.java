
public class ReverseString {
    // 344 Reverse String
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while(left < right) {
            char temp = s[left];
            s[left] = s [right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    // 541. Reverse String II
    public String reverseStr(String s, int k) {
        int remains = s.length();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        /*
        * remains >= 2k => reverse first k, move to index + 2k
        * k <= remains < 2k => reverse first k, move index to the end of the string;
        * remains < k => reverse all, move index to the end of the string;
        */
        while(remains > 0) {
            if(remains >= 2 * k) {
                String first = s.substring(index, index + k);
                index += k;
                String second = s.substring(index, index + k);
                index += k;
                StringBuilder firstSb = new StringBuilder(first).reverse();
                StringBuilder secondSb = new StringBuilder(second);
                sb.append(firstSb).append(secondSb);
                remains -= 2 * k;
            } else if(remains >= k) {
                String first = s.substring(index, index + k);
                index += k;
                String second = s.substring(index);
                StringBuilder firstSb = new StringBuilder(first).reverse();
                StringBuilder secondSb = new StringBuilder(second);
                sb.append(firstSb).append(secondSb);
                remains = 0;
            } else {
                String first = s.substring(index);
                StringBuilder firstSb = new StringBuilder(first).reverse();
                sb.append(firstSb);
                remains = 0;
            }
        }
        return sb.toString();
    }

    //557. Reverse Words in a String III
    public String reverseWords(String s) {
        String words[] = s.split(" ");
        StringBuilder res=new StringBuilder();
        for (String word: words)
            res.append(new StringBuilder(word).reverse().toString() + " ");
        return res.toString().trim();
    }
}
