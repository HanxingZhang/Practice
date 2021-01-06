public class NextRightPoint {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if(root == null) {
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }

    public void connectTwoNode(Node node1, Node node2) {
        if(node1 == null || node2 == null) {
            return;
        }
        // connect the passed in two nodes
        node1.next = node2;
        // connect two child nodes who have the same parent node
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // connect two child nodes across the parent node
        connectTwoNode(node1.right, node2.left);
    }

//    public Node connect(Node root) {
//        if(root == null) return null;
//        Node start = root;
//        while(start.left != null) {
//            Node node = start;
//            while(node != null) {
//                node.left.next = node.right;
//                if(node.next != null) {
//                    node.right.next = node.next.left;
//                }
//                node = node.next;
//            }
//            start = start.left;
//        }
//        return root;
//    }
}
