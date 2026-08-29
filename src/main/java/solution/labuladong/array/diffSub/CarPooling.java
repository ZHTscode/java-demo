package solution.labuladong.array.diffSub;

public class CarPooling {
    /* 1094. 拼车 */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001];
        Difference df = new Difference(nums);
        for(int[] trip : trips){
            int val = trip[0]; // 乘客数量
            int i = trip[1]; // trip[1]站乘客上车
            int j = trip[2] - 1; // trip[2]站乘客已经下车
            df.increment(i, j, val);
        }
        int[] res = df.result();
        for(int i : res){
            if(i > capacity) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CarPooling solution = new CarPooling();
        int[][] trips = {{2,1,5},{3,3,7}};
        int capacity = 4;
        boolean res = solution.carPooling(trips, capacity);
        System.out.println(res);
    }
}
