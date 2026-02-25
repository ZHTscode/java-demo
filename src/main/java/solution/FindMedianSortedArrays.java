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

    public static void main(String[] args) {
        FindMedianSortedArrays fmsa = new FindMedianSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(fmsa.findMedianSortedArrays2(nums1, nums2));
    }
}
