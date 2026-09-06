package solution.labuladong.linkedList.divideConquer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DiffWaysToCompute {
    /* 241. 为运算表达式设计优先级 */
    private Map<String, List<Integer>> memo = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        if (memo.containsKey(input)){
            return memo.get(input); // 避免重复计算
        }
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-' || c == '*' || c == '+') { // 遇到运算符，计算左右两部分结果

                // 分：以运算符为中心，分割成两个字符串，分别递归计算
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));

                // 治：通过子问题的结果，合成原问题的结果
                for (int a : left){
                    for (int b : right){
                        if (c == '+') res.add(a + b);
                        else if (c == '-') res.add(a - b);
                        else if (c == '*') res.add(a * b);
                    }
                }
            }
        }
        // base case：res 为空，说明算式是一个数字，没有运算符
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        memo.put(input, res); // 将结果添加进备忘录
        return res;
    }

    public static void main(String[] args) {
        DiffWaysToCompute solution = new DiffWaysToCompute();
        List<Integer> res = solution.diffWaysToCompute("2*3-4*5");
        System.out.println(res);
    }
}