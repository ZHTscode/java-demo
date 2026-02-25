package solution;

public class MaxWater {
    /* 11.盛最多水的容器 */
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int max = Math.min(height[start], height[end]) * (end - start);
        while(start < end){
            if(height[start] < height[end]){start++;}
            else {end--;}
            int tmp = Math.min(height[start], height[end]) * (end - start);
            max = Math.max(tmp,max);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxWater maxWater = new MaxWater();
        System.out.println(maxWater.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
