package solution.labuladong.linkedList.twoPtrList;

public class FindDuplicate {
    /* 287. 寻找重复数 */
    public int findDuplicate(int[] nums) {
        // 以下是单链表环检测算法，把数组当作隐式链表
        int fast, slow;
        fast = slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow) break;
        }
        // 重新指向头结点（索引 0）
        slow = 0;
        // 快慢指针同步前进，相交点就是环入口，即重复数字
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    public static void main(String[] args) {
        FindDuplicate solution = new FindDuplicate();
        int[] nums = {1, 3, 4, 2, 2};
        int duplicate = solution.findDuplicate(nums);
        System.out.println(duplicate); // 输出 2
    }
}
