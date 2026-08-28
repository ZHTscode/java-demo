package solution.labuladong.array.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class LengthLongestPath {
    /* 388. 文件的最长绝对路径 */
    public int lengthLongestPath(String input) {
        // 这个栈存储之前的父路径。实际上这里只用存父路径的长度就够了，这个优化留给你吧
        Deque<String> stack = new LinkedList<>();
        int maxLen = 0;
        for (String part : input.split("\n")) { // 穷举
            int level = part.lastIndexOf("\t") + 1; // 当前目录的层级
            // 让栈中只保留当前目录的父路径，即栈的大小等于当前目录的层级
            while (level < stack.size()) {
                stack.removeLast();
            }
            stack.addLast(part.substring(level)); // 将当前目录加入栈中
            // 如果是文件，就计算路径长度
            if (part.contains(".")) {
                int sum = stack.stream().mapToInt(String::length).sum(); // 计算当前路径的长度
                // 加上父路径的分隔符
                sum += stack.size() - 1; // 加上父路径的分隔符
                maxLen = Math.max(maxLen, sum);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LengthLongestPath solution = new LengthLongestPath();
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        int result = solution.lengthLongestPath(input);
        System.out.println(result); // 输出 32
    }
}
