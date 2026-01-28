package basic;

public class TreeNode {
    // 类的成员变量
    public int val;
    public TreeNode left;
    public TreeNode right;
    //无参构造
    public TreeNode() {}
    //有参构造
    public TreeNode(int val) {
        this.val = val;
    }
    //有参构造
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}