import java.util.*;

public class Day2_BST_Queries {

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
        if (root == null) return new Node(val);

        if (val < root.data) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);

        return root;
    }

    // ==================================================
    // Sum of Nodes in Range
    // ==================================================
    public static int sumInRange(Node root, int low, int high){
        if(root == null) return 0;

        int sum = 0;

        if(root.data >= low && root.data <= high){
            sum += root.data;
        }
        if(root.data > low){
            sum += sumInRange(root.left, low, high);
        }
        if(root.data < high){
            sum += sumInRange(root.right, low, high);
        }

        return sum;
    }

    // ==================================================
    // Closest Element in BST
    // ==================================================
    static int minDiff;
    static int answer;

    static void maxDiff(Node root, int k){
        if(root == null) return;

        if(minDiff > Math.abs(k - root.data)){
            minDiff = Math.abs(k - root.data);
            answer = root.data;
        }

        if(k < root.data){
            maxDiff(root.left, k);
        } else {
            maxDiff(root.right, k);
        }
    }

    static int closestElement(Node root, int target){
        minDiff = Integer.MAX_VALUE;
        answer = -1;
        maxDiff(root, target);
        return answer;
    }

    // ==================================================
    // Kth Smallest (Inorder Method)
    // ==================================================
    public static int kThSmallest(Node root, int k){
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(k-1);
    }

    static void inorder(Node root, ArrayList<Integer> list){
        if(root == null) return;
        inorder(root.left, list);
        list.add(root.data);
        inorder(root.right, list);
    }

    // ==================================================
    // Count Pairs from Two BST
    // ==================================================
    public static int countPairs(Node root1, Node root2, int x){

        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();

        int count = 0;

        while(true){

            while(root1 != null){
                st1.push(root1);
                root1 = root1.left;
            }

            while(root2 != null){
                st2.push(root2);
                root2 = root2.right;
            }

            if(st1.isEmpty() || st2.isEmpty()) break;

            Node top1 = st1.peek();
            Node top2 = st2.peek();

            if(top1.data + top2.data == x){
                count++;
                st1.pop();
                st2.pop();
                root1 = top1.right;
                root2 = top2.left;
            }
            else if(top1.data + top2.data < x){
                st1.pop();
                root1 = top1.right;
            }
            else{
                st2.pop();
                root2 = top2.left;
            }
        }
        return count;
    }
}
