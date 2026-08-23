package solution.labuladong.array.diffSub;

import java.util.Arrays;

public class CorpFlightBookings {
    /* 1109. 航班预订统计 */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        Difference df = new Difference(nums);
        for(int[] booking : bookings){
            int i = booking[0] - 1;
            int j = booking[1] - 1;
            int val = booking[2];
            df.increment(i, j, val);
        }
        return df.result();
    }

    public static void main(String[] args) {
        CorpFlightBookings solution = new CorpFlightBookings();
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        int[] res = solution.corpFlightBookings(bookings, 5);
        System.out.println(Arrays.toString(res));
    }
}
