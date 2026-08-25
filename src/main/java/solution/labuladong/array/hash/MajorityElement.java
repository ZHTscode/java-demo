package solution.labuladong.array.hash;

public class MajorityElement {
    /* 169.多数元素 */
    // 不同元素互相抵消，剩下的就是多数元素
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        for(int num : nums){
            if(count == 0) candidate = num; // 如果计数器为0，则将当前元素设为候选众数
            if(num == candidate) count++; // 如果当前元素等于候选众数，则计数器加1
            else count--; // 如果当前元素不等于候选众数，则计数器减1
        }
        return candidate;
    }

    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();
        int[] nums = {3,2,3};
        System.out.println(solution.majorityElement(nums));
    }
}
