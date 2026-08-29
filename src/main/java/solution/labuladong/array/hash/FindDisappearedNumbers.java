package solution.labuladong.array.hash;

import java.util.LinkedList;
import java.util.List;

public class FindDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        int[] count = new int[n + 1];
        for (int num : nums) {
            count[num]++;
        }
        List<Integer> res = new LinkedList<>();
        for (int num = 1; num <= n; num++) {
            if (count[num] == 0) {
                res.add(num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindDisappearedNumbers solution = new FindDisappearedNumbers();
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(solution.findDisappearedNumbers(nums));
    }
}
