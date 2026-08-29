package solution.labuladong.array.randomSelect;

import java.util.*;

public class RandomIndex {
    /* 398. 随机数索引 */
    Map<Integer, List<Integer>> map;
    Random rand;

    public RandomIndex(int[] nums) {
        map = new HashMap<>();
        rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            // 将 nums[i] 的所有下标加入 map
            map.computeIfAbsent(nums[i], key -> new ArrayList<>()).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> idxList = map.get(target); // 获取 target 的所有下标
        int pos = rand.nextInt(idxList.size()); // 随机取一个下标
        return idxList.get(pos);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};
        RandomIndex obj = new RandomIndex(nums);
        int param_1 = obj.pick(3);
        System.out.println(param_1);
    }
}
