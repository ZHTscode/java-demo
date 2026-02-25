package solution;

public class SearchRange {
    /* 34. 在排序数组中查找元素的第一个和最后一个位置
       核心：二分查找，找到target后，向左向右扩散，找到target的左边界和右边界 */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        res[0] = binarySearch(nums, target, true);
        res[1] = binarySearch(nums, target, false);
        return res;
    }
    //leftOrRight：true，找左边界；false，找右边界
    public int binarySearch(int[] nums, int target, boolean leftOrRight) {
        int res = -1;
        int left = 0, right = nums.length - 1, mid;
        while(left <= right) {
            mid = left + (right - left) / 2;
            if(target < nums[mid])          right = mid - 1; // target在左半边
            else if(target > nums[mid])     left = mid + 1; // target在右半边
            else { // 此时 target == nums[mid]
                res = mid;
                //处理target == nums[mid]
                if(leftOrRight)             right = mid - 1; // 找左边界，继续在左半边找
                else                        left = mid + 1;  // 找右边界，继续在右半边找
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SearchRange sr = new SearchRange();
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        int[] res = sr.searchRange(nums, target);
        for(int i : res)
            System.out.print(i + " ");
    }
}

