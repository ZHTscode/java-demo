package solution;

import java.util.Arrays;

public class FindKthLargest {
    // 库函数
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    // 堆排序
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
    public void buildMaxHeap(int[] a, int heapSize){
        for(int i=heapSize/2-1;i>=0;i--){
            maxHeapify(a, i, heapSize);
        }
    }
    public void maxHeapify(int[] a, int i, int heapSize){
        int l = i*2+1, r=i*2+2, max = i;
        if(l<heapSize && a[l] > a[max]){
            max = l;
        }
        if (r < heapSize && a[r] > a[max]) {
            max = r;
        }
        if(max!=i){
            swap(a,max,i);
            maxHeapify(a, max,heapSize);
        }
    }
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        FindKthLargest f = new FindKthLargest();
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(f.findKthLargest(nums, k));
    }
}
