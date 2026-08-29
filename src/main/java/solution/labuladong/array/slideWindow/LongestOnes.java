package solution.labuladong.array.slideWindow;

public class LongestOnes {
    /* 1004. 最大连续1的个数 III */
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        int windowOneCount = 0;
        int res = 0;
        while(right < nums.length){
            if(nums[right] == 1) windowOneCount++;
            right++;
            while(right - left - windowOneCount > k){ // 窗口内0的个数大于k时，收缩左边界
                if(nums[left] == 1) windowOneCount--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestOnes longestOnes = new LongestOnes();
        int[] nums = {1,1,1,0,0,0,1,1,1};
        int k = 2;
        System.out.println(longestOnes.longestOnes(nums, k));
    }
}
