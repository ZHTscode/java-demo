package solution.labuladong.array.hash;

import java.util.LinkedList;
import java.util.List;

public class FindDuplicates {
    /* 442. 数组中重复的数据 */
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> res = new LinkedList<>();
        // 用数组模拟哈希集合
        int[] seen = new int[n + 1];
        for (int num : nums) {
            if (seen[num] == 0) {
                // 添加到哈希集合
                seen[num] = 1;
            } else {
                // 找到重复元素
                res.add(num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindDuplicates solution = new FindDuplicates();
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(solution.findDuplicates(nums));
    }
}
