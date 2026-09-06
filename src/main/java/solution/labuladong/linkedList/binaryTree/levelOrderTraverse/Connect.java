package solution.labuladong.linkedList.binaryTree.levelOrderTraverse;


import java.util.LinkedList;
import java.util.Queue;

public class Connect {
    /* 116. 填充每个节点的下一个右侧节点指针$$ */
    public Node connect(Node root) {
        if(root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                Node cur = q.poll();
                // 不是本层最后一个，next = 队列下一个元素
                if(i < size - 1) cur.next = q.peek();
                else cur.next = null; // 本层最右，next置null
                if(cur.left != null) q.offer(cur.left);
                if(cur.right != null) q.offer(cur.right);
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if(root == null) return null;
        Node levelStart = root; // levelStart：每一层最开头的节点
        while(levelStart.left != null){ // 只要还有下一层（不是叶子层）
            Node cur = levelStart; // cur：在当前层，从左往右跑
            while(cur != null){
                // 同一个父节点：左孩子 → 右孩子
                cur.left.next = cur.right;
                // 跨父节点：当前节点的右孩子 → 右边邻居的左孩子
                if(cur.next != null){
                    cur.right.next = cur.next.left;
                }
                cur = cur.next; // 当前层向右移动，处理同层下一个节点
            }
            levelStart = levelStart.left; // 跳到下一层的第一个节点
        }
        return root;
    }

    public static void main(String[] args) {
        // 构建完美二叉树： 1,2,3,4,5,6,7
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n2 = new Node(2, n4, n5, null);
        Node n3 = new Node(3, n6, n7, null);
        Node root = new Node(1, n2, n3, null);

        Connect connect = new Connect();
        Node res = connect.connect(root);
        System.out.println("按next指针分层输出：");
        printByNext(res);
    }
    // 层序打印，利用next指针横向输出，验证next是否正确
    public static void printByNext(Node root){
        Node level = root;
        while(level != null){
            Node cur = level;
            while(cur != null){
                System.out.print(cur.val + " ");
                cur = cur.next;
            }
            System.out.println();
            level = level.left;
        }
    }
}

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
}
