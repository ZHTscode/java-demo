package solution.labuladong.array.slideWindow;

import java.util.HashSet;

public class ContainsNearbyDuplicate {
    /* 219. 存在重复元素 II */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
            if(set.size() > k) set.remove(nums[i - k]);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsNearbyDuplicate containsNearbyDuplicate = new ContainsNearbyDuplicate();
        int[] nums = {1,2,3,4,5,6,7,8,9,1};
        int k = 5;
        System.out.println(containsNearbyDuplicate.containsNearbyDuplicate(nums, k));
    }
}
