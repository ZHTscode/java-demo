package solution;

public class FindMedianSortedArrays {
    /* 4.寻两个正序数组的中位数 */
    // Time: O(m + n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] nums = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j])    nums[k++] = nums1[i++];
            else                        nums[k++] = nums2[j++];
        }
        while (i < m) { nums[k++] = nums1[i++]; }
        while (j < n) { nums[k++] = nums2[j++]; }
        int mid = (m + n) / 2;
        if ((m + n) % 2 == 0)           return (nums[mid] + nums[mid - 1]) / 2.0;
        else                            return nums[mid];
    }
    // Time: O(log(min(m, n))) 二分查找 最优
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int total = m + n;
        // 奇数，找第 (total/2 + 1) 小；偶数，找第 (total/2) 和 (total/2 + 1) 小的平均值
        if (total % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, total / 2 + 1);
        } else {
            int left = findKth(nums1, 0, nums2, 0, total / 2);
            int right = findKth(nums1, 0, nums2, 0, total / 2 + 1);
            return (left + right) / 2.0;
        }
    }
    // 找两个有序数组中第 k 小的数
    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        // 边界：nums1 用完，直接在 nums2 找
        if (i >= nums1.length)  return nums2[j - 1 + k];
        // 边界：nums2 用完，直接在 nums1 找
        if (j >= nums2.length)  return nums1[i - 1 + k];
        // 边界：k=1，返回较小的第一个元素（要找的 k 越来越小，最终 k=1 时，就是要求的第 k 小的数）
        if (k == 1)             return Math.min(nums1[i], nums2[j]);
        // 比较两个数组的第 k/2 个元素
        int mid1 = (i - 1 + k / 2 < nums1.length) ? nums1[i - 1 + k / 2] : Integer.MAX_VALUE;
        int mid2 = (j - 1 + k / 2 < nums2.length) ? nums2[j - 1 + k / 2] : Integer.MAX_VALUE;
        // 排除较小的那 k/2 个元素
        if (mid1 < mid2)    return findKth(nums1, i + k / 2, nums2, j, k - k / 2); // nums1 的前 k/2 个元素肯定不是第 k 小的数
        else                return findKth(nums1, i, nums2, j + k / 2, k - k / 2); // nums2 的前 k/2 个元素肯定不是第 k 小的数
    }

    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) { // 保证 nums1 是较短的数组，减少二分次数
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length;
        int n = nums2.length;
        int lo = 0; // 二分左边界
        int hi = m; // 二分右边界
        while (lo <= hi) {
            int i = (lo + hi) / 2; // 分割 nums1：前 i 个元素在左边
            int j = (m + n + 1) / 2 - i; // 分割 nums2：前 j 个元素在左边，保证左右两边总元素数相等（或左多1个）
            // 处理边界
            int left1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1]; // nums1 左边无元素，用最小值表示
            int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i]; // nums1 右边无元素，用最大值表示
            int left2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1]; // nums2 左边无元素，用最小值表示
            int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j]; // nums2 右边无元素，用最大值表示
            // 满足分割条件：左边所有元素 ≤ 右边所有元素
            if (left1 <= right2 && left2 <= right1) {
                // 总长度为奇数：中位数是左边最大值
                if ((m + n) % 2 == 1)   return Math.max(left1, left2);
                // 总长度为偶数：中位数是左边最大值和右边最小值的平均
                else    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
            }
            // nums1 左边太大，需要向左分割 nums1
            else if (left1 > right2) hi = i - 1;
            // nums2 左边太大，需要向右分割 nums1
            else    lo = i + 1;
        }
        return 0.0; // 题目保证输入合法，理论上不会走到这里
    }

    public static void main(String[] args) {
        FindMedianSortedArrays fmsa = new FindMedianSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(fmsa.findMedianSortedArrays3(nums1, nums2));
    }
}
