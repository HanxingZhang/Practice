import java.util.Stack;

public class FlattenBinaryTreeToLinkedList {

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

      public void flatten(TreeNode root) {
          if(root == null) {
              return;
          }

          flatten(root.left);
          flatten(root.right);
          TreeNode left = root.left;
          TreeNode right = root.right;
          root.left = null;
          root.right = left;

          TreeNode node = root;
          while(node.right != null) {
              node = node.right;
          }
          node.right = right;
      }

      public void flatten2(TreeNode root) {
          if(root == null) {
              return;
          }
          Stack<TreeNode> stack = new Stack<TreeNode>();
          stack.push(root);

          while(!stack.isEmpty()) {
              TreeNode node = stack.pop();
              if(node.right != null) {
                  stack.add(node.right);
              }
              if(node.left != null) {
                  stack.add(node.left);
              }
              node.left = null;
              if(stack.isEmpty()) {
                  node.right = null;
              } else {
                  node.right = stack.peek();
              }
          }
      }

    private TreeNode lastNode = null;
    public void flatten3(TreeNode root) {
        if(root == null) {
            return;
        }

        if(lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }
        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}
