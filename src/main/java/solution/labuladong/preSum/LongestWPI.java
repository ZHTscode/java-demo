package solution.labuladong.preSum;

import java.util.HashMap;
import java.util.Map;

public class LongestWPI {
    /* 1124. 表现良好的最长时间段 */
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] preSum = new int[n+1];
        Map<Integer, Integer> valToIndex = new HashMap<>();
        int res = 0;
        for(int i=1; i<=n; i++){
            preSum[i] = preSum[i-1] + (hours[i-1]>8? 1:-1);
            if(!valToIndex.containsKey(preSum[i])){
                valToIndex.put(preSum[i], i);
            } // 只记录最左边的索引

            if(preSum[i] > 0){
                res = Math.max(res, i);
            } else{
                if(valToIndex.containsKey(preSum[i]-1)){ // 从左到右第一个小于preSum[i]的值（画图理解）
                    res = Math.max(res, i-valToIndex.get(preSum[i]-1));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestWPI solution = new LongestWPI();
        int[] hours = {9,9,6,0,6,6,9};
        System.out.println(solution.longestWPI(hours));
    }
}
