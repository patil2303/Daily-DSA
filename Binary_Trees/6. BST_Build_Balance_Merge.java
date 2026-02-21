import java.util.*;

public class Day1_BST_Build_Balance_Merge {

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
    // Insert into BST
    // ==================================================
    public static Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }

        if (val < root.data) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        return root;
    }

    // ==================================================
    // Create Balanced BST from Sorted Array
    // ==================================================
    public static Node createBST(ArrayList<Integer> arr, int st, int end) {
        if (st > end) {
            return null;
        }

        int mid = (st + end) / 2;
        Node root = new Node(arr.get(mid));

        root.left = createBST(arr, st, mid - 1);
        root.right = createBST(arr, mid + 1, end);

        return root;
    }

    // ==================================================
    // Inorder Traversal
    // ==================================================
    public static void getInorder(Node root, ArrayList<Integer> inorder) {
        if (root == null) return;

        getInorder(root.left, inorder);
        inorder.add(root.data);
        getInorder(root.right, inorder);
    }

    // ==================================================
    // Preorder Traversal
    // ==================================================
    public static void preOrder(Node root) {
        if (root == null) return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // ==================================================
    // Balance BST
    // ==================================================
    public static Node balanceBST(Node root) {
        ArrayList<Integer> inOrder = new ArrayList<>();
        getInorder(root, inOrder);
        return createBST(inOrder, 0, inOrder.size() - 1);
    }

    // ==================================================
    // Merge Two BST
    // ==================================================
    public static Node mergeBST(Node root1, Node root2) {

        ArrayList<Integer> arr1 = new ArrayList<>();
        getInorder(root1, arr1);

        ArrayList<Integer> arr2 = new ArrayList<>();
        getInorder(root2, arr2);

        ArrayList<Integer> finalList = new ArrayList<>();

        int i = 0, j = 0;
        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) <= arr2.get(j)) {
                finalList.add(arr1.get(i++));
            } else {
                finalList.add(arr2.get(j++));
            }
        }

        while (i < arr1.size()) finalList.add(arr1.get(i++));
        while (j < arr2.size()) finalList.add(arr2.get(j++));

        return createBST(finalList, 0, finalList.size() - 1);
    }

    // ==================================================
    // Main Method
    // ==================================================
    public static void main(String[] args) {

        Node root1 = null;
        Node root2 = null;

        int tree1[] = {5,3,7,2,4,6,8};
        for(int val : tree1){
            root1 = insert(root1, val);
        }

        int tree2[] = {10,6,15,8,10,11,18};
        for(int val : tree2){
            root2 = insert(root2, val);
        }

        Node merged = mergeBST(root1, root2);

        System.out.print("Merged BST (Preorder): ");
        preOrder(merged);
    }
}
