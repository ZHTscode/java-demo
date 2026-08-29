package solution.labuladong.array.binarySearch;

public class PeakIndexInMountainArray {
    /* 852. 山脉数组的峰顶索引 */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) { // mid 右侧是下降序列，峰值在左半边
                right = mid; // mid本身可能是峰值
            } else {
                left = mid + 1;
            }
        }
        return left; // left == right，任意返回一个
    }

    public static void main(String[] args) {
        PeakIndexInMountainArray solution = new PeakIndexInMountainArray();
        int[] arr = {0,1,0};
        System.out.println(solution.peakIndexInMountainArray(arr));
    }
}
