package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Codec {
    /* 297. 二叉树的序列化与反序列化 */
    String SEP = ",";
    String NULL = "#";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper3(root, sb);
        return sb.toString();
    }
    public TreeNode deserialize(String data) {
        String[] split = data.split(SEP); // "a,b,"切分为["a","b",""]
        List<String> nodes = new LinkedList<>();
        for(String s : split){
            if(!s.isEmpty()){ // 跳过空字符串
                nodes.add(s);
            }
        }
        return deserializeHelper3(nodes);
    }

    // 前序写法
    private void serializeHelper(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }
    private TreeNode deserializeHelper(List<String> nodes){
        if(nodes.isEmpty()) return null;
        String val = nodes.remove(0);
        if(val.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(val)); // 字符串转整数
        root.left = deserializeHelper(nodes);
        root.right = deserializeHelper(nodes);
        return root;
    }
    // 后序写法
    private void serializeHelper2(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append(NULL).append(SEP);
            return;
        }
        serializeHelper2(root.left, sb);
        serializeHelper2(root.right, sb);
        sb.append(root.val).append(SEP);
    }
    private TreeNode deserializeHelper2(List<String> nodes){
        if(nodes.isEmpty()) return null;
        String val = nodes.remove(nodes.size() - 1); // 根节点是最后一个
        if(val.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(val)); // 字符串转整数
        root.right = deserializeHelper2(nodes); // 紧接着是右子树
        root.left = deserializeHelper2(nodes);
        return root;
    }
    // 层序写法
    private void serializeHelper3(TreeNode root, StringBuilder sb){
        // 初始化队列，将 root 加入队列
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                if (cur == null) {
                    sb.append(NULL).append(SEP);
                    continue;
                }
                sb.append(cur.val).append(SEP);
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
    }
    private TreeNode deserializeHelper3(List<String> nodes){
        String val = nodes.remove(0);
        if(val.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(val)); // 字符串转整数
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int index = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode parent = q.poll();
                // 为父节点构造左侧子节点
                String left = nodes.get(index++);
                if (!left.equals(NULL)) {
                    parent.left = new TreeNode(Integer.parseInt(left));
                    q.offer(parent.left);
                }
                // 为父节点构造右侧子节点
                String right = nodes.get(index++);
                if (!right.equals(NULL)) {
                    parent.right = new TreeNode(Integer.parseInt(right));
                    q.offer(parent.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,3,-1,-1,4,5});
        TreeNodeUtil.printTreeShape(root);
        String data = codec.serialize(root);
        System.out.println(data);
        TreeNode node = codec.deserialize(codec.serialize(root));
        TreeNodeUtil.printTreeShape(node);
    }
}
