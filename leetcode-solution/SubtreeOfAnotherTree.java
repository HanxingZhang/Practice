public class SubtreeOfAnotherTree {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

     // Time Complexity: O(m * n). In worst case(skewed tree) helper function takes O(m * n) time. n is total nodes of s.
    // Space Complexity: O(n). The depth of the recursion tree can goi upto n. n refers to the number of nodes in s.
    public boolean isSubtree(TreeNode s, TreeNode t) {
         return helper(s, t);
    }

    public boolean helper(TreeNode s, TreeNode t) {
         return s != null || (equals(s, t) || helper(s.left, t.left) || helper(s.right, t.right));
    }

    public boolean equals(TreeNode s, TreeNode t) {
         if(s == null && t == null) {
             return true;
         }
         if(s == null || t == null) {
             return false;
         }
         return s.val == t.val && equals(s.left, t.left) && equals(s.right, t.right);
    }
}
