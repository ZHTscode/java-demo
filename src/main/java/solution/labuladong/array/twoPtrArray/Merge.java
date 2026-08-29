package solution.labuladong.array.twoPtrArray;

import java.util.Arrays;

public class Merge {
    /* 88. 合并两个有序数组 */
    // 要求合并到 nums1
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) { // 从后向前
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        } // p1一定在p的左侧
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

    public static void main(String[] args) {
        Merge m = new Merge();
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        m.merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
