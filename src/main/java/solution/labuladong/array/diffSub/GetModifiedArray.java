package solution.labuladong.array.diffSub;

import java.util.Arrays;

public class GetModifiedArray {
    /* 370. 区间加法 */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] nums = new int[length];
        Difference df = new Difference(nums);
        for(int[] update : updates){
            int i = update[0];
            int j = update[1];
            int val = update[2];
            df.increment(i, j, val);
        }
        return df.result();
    }

    public static void main(String[] args) {
        GetModifiedArray solution = new GetModifiedArray();
        int[] nums = {1,2,3,4,5};
        int[][] updates = {{1,3,2},{2,4,3},{0,2,-2}};
        int[] res = solution.getModifiedArray(nums.length, updates);
        System.out.println(Arrays.toString(res));
    }
}
