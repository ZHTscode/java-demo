package solution.labuladong.array.twoPtrArray;

public class Trap {
    /* 42. 接雨水 */
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int lMax = 0, rMax = 0;
        int res = 0;
        while(left < right){
            lMax = Math.max(lMax, height[left]);
            rMax = Math.max(rMax, height[right]);
            if(lMax < rMax){
                res += lMax - height[left];
                left++;
            } else{
                res += rMax - height[right];
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Trap trap = new Trap();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap.trap(height));
    }
}
