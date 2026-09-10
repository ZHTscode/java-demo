package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import java.util.LinkedList;
import java.util.List;

public class IsValidSerialization {
    /* 331. 验证二叉树的前序序列化 */
    // 空闲空位始终非负，最终占满为零
    public boolean isValidSerialization(String preorder) {
        // 一条指向根节点的虚拟边
        int edge = 1;
        for (String node : preorder.split(",")) {
            // 任何时候，边数都不能小于 0
            if (node.equals("#")) { // 空节点消耗一条空闲边
                edge -= 1;
                if (edge < 0) return false;
            } else { // 非空节点消耗一条空闲边，增加两条空闲边
                edge -= 1;
                if (edge < 0) return false;
                edge += 2;
            }
        }
        // 最后不应该存在空闲边
        return edge == 0;
    }

    public boolean isValidSerialization2(String preorder) {
        List<String> nodes = new LinkedList<>();
        for (String s : preorder.split(",")) {
            nodes.add(s);
        }
        // 解析整棵树，并且解析完之后，所有节点必须全部用完（列表空）
        return deserialize(nodes) && nodes.isEmpty();
    }

    // 从nodes头部解析一棵子树，成功，返回true
    boolean deserialize(List<String> nodes) {
        // 没有节点可以读了，没法构造子树，失败
        if (nodes.isEmpty()) return false;

        String first = nodes.remove(0); // 取出当前根，消耗掉这个节点

        // 如果是空节点：这棵子树就是空树，解析成功，直接返回true，不再递归左右
        if (first.equals("#")) return true;

        // 如果是数字节点：根成功，还需要解析左子树 && 右子树，两个都成功才算这棵子树完整
        return deserialize(nodes) && deserialize(nodes);
    }

    public static void main(String[] args) {
        IsValidSerialization solution = new IsValidSerialization();
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(solution.isValidSerialization2(preorder));
        System.out.println(solution.isValidSerialization(preorder));
    }
}
