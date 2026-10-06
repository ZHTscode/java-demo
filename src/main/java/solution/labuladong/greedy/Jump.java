package solution.labuladong.greedy;

import java.util.ArrayList;
import java.util.List;

public class Jump {
    /* 45. 跳跃游戏 II */
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        int n = nums.length;
        // jumps 步可以跳到索引区间 [i, end]
        int end = 0, jumps = 0;
        // 在 [i, end] 出发，最远可以跳到 farthest
        int farthest = 0;
        for (int i = 0; i < n - 1; i++) {
            // 计算从索引 i 可以跳到的最远索引
            farthest = Math.max(nums[i] + i, farthest);
            if (i == end) {
                jumps++; // 跳到 [end+1, farthest] 区间
                end = farthest; // 更新下一步起跳区间
                // 已经可以到达终点
                if (farthest >= n - 1) return jumps;
            }
        }
        return -1;
    }
    public List<Integer> jumpPro(int[] nums) {
        int n = nums.length;
        List<Integer> path = new ArrayList<>();
        if(n <= 1) {
            path.add(0);
            return path;
        }
        int end = 0;
        int farthest = 0;
        int bestIdx = 0; // 跳到 farthest 的起跳点
        for (int i = 0; i < n - 1; i++) {
            if(i + nums[i] > farthest){
                farthest = i + nums[i];
                bestIdx = i; // 跳到 farthest 的起跳点
            }
            // 区间走到边界，执行一次跳跃
            if(i == end) {
                end = farthest;
                path.add(bestIdx); // 存入起跳点
                if(farthest >= n-1) break;
            }
        }
        path.add(n-1); // 终点单独补上
        return path;
    }

    public static void main(String[] args) {
        Jump jump = new Jump();
        int[] nums = {2,3,1,1,4};
        List<Integer> path = jump.jumpPro(nums);
        int result = jump.jump(nums);
        System.out.println(path);
        System.out.println(result);
    }

}