package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.*;

public class Codec {
    // 序列化：前序遍历
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }
    // 反序列化：递归重建
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(nodes);
    }
    private TreeNode deserializeHelper(Queue<String> nodes) {
        String val = nodes.poll();
        if ("null".equals(val)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(nodes);
        node.right = deserializeHelper(nodes);
        return node;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,3,-1,-1,4,5});
        TreeNodeUtil.printTreeShape(root);
        String data = codec.serialize(root);
        System.out.println(data);
        TreeNode node = codec.deserialize(data);
        TreeNodeUtil.printTreeShape(node);
    }
}