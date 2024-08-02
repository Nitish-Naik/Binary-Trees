import java.util.LinkedList;
import java.util.Queue;

/**
 * BinaryTreeB
 */
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

    // inorder traversal -> O(n)

    public static void inorder(Node root) {
        if(root == null ){
            return;
        }

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // level of traversal = O(n)
    public static void levelTraversal(Node root) {
        if(root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node currNode = queue.remove();
            if(currNode == null) {
                System.out.println();
                if(queue.isEmpty()) {
                    break;
                }
                else {
                    queue.add(null);
                }
            }
            else
            {
                System.out.println(currNode.data +" ");
                if(currNode.left != null) {
                    queue.add(currNode.left);
                }
                if(currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
        }

    }

    // Find height of tree == O(n)

    public static int height(Node root) {
        if(root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // count of nodes => O(n)

    public static int countNodes(Node root) {
        if(root == null) {
            return 0;
        }

        int leftCount = countNodes(root.left);
        System.out.println(root.data);
        int rightCount = countNodes(root.right);

        return leftCount + rightCount + 1;
    }

    // sum of nodes = O(n)
    static int sum = 0;
    public static int sum_of_nodes(Node root) {
        if(root == null) {
            return 0;
        }

        int leftSum = sum_of_nodes(root.left);
        int rightSum = sum_of_nodes(root.right);
        return leftSum + rightSum + root.data;
    }

    // Diameter of a tree -> O(n*n) == number of nodes in the longest path between 2 leaves

    // public static int diameter(Node root) {
    //     if(root == null) {
    //         return 0;
    //     }

    //     int leftDia = diameter(root.left);
    //     int rightDia = diameter(root.right);

    //     int maxDia = Math.max(leftDia+ rightDia) +1;
    //     return Math.max(maxDia, leftDia);

    // }



    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        // if (root != null) {
        //     System.out.println("Preorder traversal:");
        //     preorder(root);
        //     System.out.println("\nPostorder traversal:");
        //     postorder(root);
        //     System.out.println("\nPostorder traversal:");
        //     inorder(root);
        // } else {
        //     System.out.println("The tree is empty.");
        // }

        // System.out.println(height(root));
        // levelTraversal(root);

        // System.out.println(countNodes(root));
        // System.out.println(sum_of_nodes(root));

    }
}
