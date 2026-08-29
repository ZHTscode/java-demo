package solution.labuladong.array.hash;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {
    /* 128. 最长连续序列 */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums){
            numSet.add(num);
        }
        int res = 0;
        for(int num : numSet){
            if(numSet.contains(num-1)){
                continue;
            }
            int curNum = num;
            int curLen = 1;
            while(numSet.contains(curNum+1)){
                curNum = curNum + 1;
                curLen = curLen + 1;
            }
            res = Math.max(res, curLen);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int res = longestConsecutive.longestConsecutive(nums);
        System.out.println(res);
    }
}
