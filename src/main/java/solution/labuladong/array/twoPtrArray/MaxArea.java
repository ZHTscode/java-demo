package solution.labuladong.array.twoPtrArray;

public class MaxArea {
    /* 11. 盛最多水的容器 */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while(left < right){
            int curArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, curArea);
            if(height[left] < height[right]){
                left++;
            } else{
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea.maxArea(height));
    }
}
