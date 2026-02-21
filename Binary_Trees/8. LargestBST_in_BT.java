import java.util.*;

public class Day3_LargestBST_in_BT {

    // ==================================================
    // Node Structure
    // ==================================================
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    // ==================================================
    // Info Class for Largest BST
    // ==================================================
    static class Info {
        boolean isBST;
        int size;
        int min;
        int max;

        Info(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    static int maxBST = 0;

    // ==================================================
    // Largest BST in Binary Tree
    // ==================================================
    public static Info largestBST(Node root) {

        if(root == null){
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info left = largestBST(root.left);
        Info right = largestBST(root.right);

        int size = left.size + right.size + 1;

        if(root.data <= left.max || root.data >= right.min){
            return new Info(false, size, 0, 0);
        }

        if(left.isBST && right.isBST){
            maxBST = Math.max(maxBST, size);
            return new Info(true, size,
                    Math.min(root.data, left.min),
                    Math.max(root.data, right.max));
        }

        return new Info(false, size, 0, 0);
    }

    // ==================================================
    // Main Method
    // ==================================================
    public static void main(String[] args) {

        Node root = new Node(50);
        root.left = new Node(30);
        root.right = new Node(60);
        root.left.left = new Node(5);
        root.left.right = new Node(20);
        root.right.left = new Node(45);
        root.right.right = new Node(70);
        root.right.right.left = new Node(65);
        root.right.right.right = new Node(80);

        largestBST(root);

        System.out.println("Largest BST size = " + maxBST);
    }
}
