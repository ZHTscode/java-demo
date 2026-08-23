package solution.labuladong.array.slideWindow;

import java.util.TreeSet;

public class ContainsNearbyAlmostDuplicate {
    /* 220. 存在重复元素 III */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> window = new TreeSet<>();
        int left = 0, right = 0;
        while (right < nums.length) {
            Integer ceiling = window.ceiling(nums[right]); // 找 大于等于 nums[right] 的最小元素
            if(ceiling != null && ceiling <= nums[right] + valueDiff){
                return true;
            }
            Integer floor = window.floor(nums[right]); // 找 小于等于 nums[right] 的最大元素
            if(floor != null && floor >= nums[right] - valueDiff){
                return true;
            }
            window.add(nums[right]);
            right++;
            if (right - left == indexDiff + 1) { // 窗口大小等于最大值
                window.remove(nums[left]);
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate solution = new ContainsNearbyAlmostDuplicate();
        int[] nums = {1,2,3,4,5,6,7,8,9,1};
        System.out.println(solution.containsNearbyAlmostDuplicate(nums, 5, 5));
    }
}
