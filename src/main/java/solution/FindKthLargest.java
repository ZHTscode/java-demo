package solution;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindKthLargest {
    /* 215. 数组中的第K个最大元素 */
    // 解法一：排序
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    // 解法二：堆（最优，手搓堆）
    public int findKthLargest2(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for(int i=nums.length-1; i>= nums.length-k+1; i--){
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums,0,heapSize);
        }
        return nums[0];
    }
    /* 将任意数组转换成最大堆
       自下而上，对每个非叶子节点，执行堆化操作
       叶子节点不需要调整，从第一个非叶子节点开始 */
    public void buildMaxHeap(int[] a, int heapSize){
        for(int i=heapSize/2-1;i>=0;i--){
            maxHeapify(a, i, heapSize);
        }
    }
    /* 最大堆：一棵完全二叉树，满足 每个节点的值 ≥ 其子节点的值
       堆化：将一个子树调整成最大堆
       层序数组：左子节点 2*i+1  右子节点 2*i+2  父节点 (i-1)/2
       找到 i、左子、右子 中的最大值
       如果最大值不是 i → 交换
       递归调整被交换的子树
     */
    public void maxHeapify(int[] a, int i, int heapSize){
        int l = i * 2 + 1;      // 左子节点索引
        int r = i * 2 + 2;      // 右子节点索引
        int max = i;            // 假设当前节点最大
        // 找最大值
        if (l < heapSize && a[l] > a[max]) max = l;
        if (r < heapSize && a[r] > a[max]) max = r;
        // 如果需要交换
        if (max != i) {
            swap(a, max, i); // 交换
            maxHeapify(a, max, heapSize); // 递归调整子树
        }
    }
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    // 解法三：库函数实现堆
    public int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length == 0) // 边界检查
            throw new IllegalArgumentException("Invalid input");
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 最小堆
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k)     minHeap.poll();
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        FindKthLargest f = new FindKthLargest();
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(f.findKthLargest3(nums, k));
    }
}
