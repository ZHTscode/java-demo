package solution;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    /* 22.括生成 */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(0, 0, n, res, path);
        return res;
    }
    // 核心：递归 + 剪枝 + 回溯
    /*
                    ""
                   /  \
                 "("  ❌：不能先加 ')'
                /  \
            "(("   "()"
            /      /  \
        "(()"   "()("  ❌：right=1, left=1 → 不能加 ')'
         /        \
     "(())"     "()()"
    */
    private void dfs(int left, int right, int n, List<String> res, StringBuilder path){
        // 递归终止条件
        if(left == n && right == n){
            res.add(path.toString()); // 添加当前路径到结果中
            return;
//            System.out.println(path.toString()); // 打印当前路径
        } else {
            if(left < n){ // 添加左括号的条件
                path.append("("); // 添加左括号到路径中
                dfs(left + 1, right, n, res, path); // 递归添加左括号
    //            System.out.println(path.toString()); // 打印当前路径
                // 递归返回时，需要恢复到之前的状态，以便尝试其他可能的路径
                path.deleteCharAt(path.length() - 1); // 回溯：删除添加的左括号
            }
            if(right < left){ // 添加右括号的条件
                path.append(")"); // 添加右括号到路径中
                dfs(left, right + 1, n, res, path); // 递归添加右括号
    //            System.out.println(path.toString()); // 打印当前路径
                path.deleteCharAt(path.length() - 1); // 回溯：删除添加的右括号
            }
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis gp = new GenerateParenthesis();
        System.out.println(gp.generateParenthesis(3));
    }
}
