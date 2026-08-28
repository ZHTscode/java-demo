package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    /* 71. 简化路径 */
    public String simplifyPath(String path) {
        String[] parts = path.split("/");
        Deque<String> stk = new ArrayDeque<>();
        // 借助栈计算最终的文件夹路径
        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (!stk.isEmpty()) stk.pop();
                continue;
            }
            stk.push(part);
        }
        // 栈中存储的文件夹组成路径
        StringBuilder res = new StringBuilder();
        while (!stk.isEmpty()) {
            res.insert(0, "/" + stk.pop());
        }
        return (res.isEmpty()) ? "/" : res.toString();
    }

    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();
        System.out.println(sp.simplifyPath("/home/../foo/../.."));
    }
}
