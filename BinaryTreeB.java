import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeB {

    // Node class
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Binary tree class
    static class BinaryTree {
        static int index = -1;

        public static Node buildTree(int nodes[]) {
            index++;
            if (index >= nodes.length || nodes[index] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[index]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }

        // Reset the index to build a new tree
        public static void resetIndex() {
            index = -1;
        }
    }

    // Preorder traversal == O(n)
    // root -> left -> right
    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Postorder traversal == O(n)
    // left -> right -> root
    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // Inorder traversal -> O(n)
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Level traversal = O(n)
    public static void levelTraversal(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node currNode = queue.remove();
            if (currNode == null) {
                System.out.println();
                if (queue.isEmpty()) {
                    break;
                } else {
                    queue.add(null);
                }
            } else {
                System.out.print(currNode.data + " ");
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
        }
    }

    // Find height of tree == O(n)
    public static int height(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Count of nodes => O(n)
    public static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }

        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        return leftCount + rightCount + 1;
    }

    // Sum of nodes = O(n)
    public static int sum_of_nodes(Node root) {
        if (root == null) {
            return 0;
        }

        int leftSum = sum_of_nodes(root.left);
        int rightSum = sum_of_nodes(root.right);
        return leftSum + rightSum + root.data;
    }

    // Diameter of a tree - number of nodes in the longest path between 2 leaves -> O(n*n)
    // Approach - 1
    public static int diameter1(Node root) {
        if (root == null) {
            return 0;
        }

        int leftDiam = diameter1(root.left);
        int rightDiam = diameter1(root.right);
        int leftHt = height(root.left);
        int rightHt = height(root.right);

        int selfDiam = leftHt + rightHt + 1;

        return Math.max(selfDiam, Math.max(leftDiam, rightDiam));
    }

    // Approach - 2
    static class Info {
        int diam;
        int ht;

        public Info(int diam, int ht) {
            this.diam = diam;
            this.ht = ht;
        }
    }

    public static Info diameter2(Node root) { // O(n)
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = diameter2(root.left);
        Info rightInfo = diameter2(root.right);
        int diam = Math.max(Math.max(leftInfo.diam, rightInfo.diam), leftInfo.ht + rightInfo.ht + 1);
        int ht = Math.max(leftInfo.ht, rightInfo.ht) + 1;

        return new Info(diam, ht);
    }

    // Check if subtree
    public static boolean isIdentical(Node node, Node subroot) {
        if (node == null && subroot == null) {
            return true;
        } else if (node == null || subroot == null) {
            return false;
        }

        if (node.data != subroot.data) {
            return false;
        }

        return isIdentical(node.left, subroot.left) && isIdentical(node.right, subroot.right);
    }

    public static boolean isSubtree(Node root, Node subroot) {
        if (root == null) {
            return false;
        }
        if (isIdentical(root, subroot)) {
            return true;
        }

        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        int subnodes[] = {2, 4, -1, -1, 5, -1, -1};

        BinaryTree.resetIndex();
        Node root = BinaryTree.buildTree(nodes);

        BinaryTree.resetIndex();
        Node subroot = BinaryTree.buildTree(subnodes);

        // Uncomment these lines to test traversal methods
        // if (root != null) {
        //     System.out.println("Preorder traversal:");
        //     preorder(root);
        //     System.out.println("\nPostorder traversal:");
        //     postorder(root);
        //     System.out.println("\nInorder traversal:");
        //     inorder(root);
        // } else {
        //     System.out.println("The tree is empty.");
        // }

        // Uncomment these lines to test other methods
        // System.out.println("Height of tree: " + height(root));
        // System.out.println("Level order traversal:");
        // levelTraversal(root);
        // System.out.println("Count of nodes: " + countNodes(root));
        // System.out.println("Sum of nodes: " + sum_of_nodes(root));
        // System.out.println("Diameter of tree (approach 1): " + diameter1(root));
        // System.out.println("Diameter of tree (approach 2): " + diameter2(root).diam);

        System.out.println("Is subtree: " + isSubtree(root, subroot));
    }
}
