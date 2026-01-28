package utils;

/**
 * 包含：数组打印、数组拷贝、最值查找、反转数组、数组判空、数组填充、求和/平均值
 * 所有方法均为静态方法，可直接通过 ArrayUtil.方法名() 调用
 */

public class ArrayUtil {
    /**
     * 1. 打印数组
     */
    // 打印int类型数组 → 格式 [1, 2, 3, 4]
    public static void printIntArray(int[] arr) {
        if (isEmpty(arr)) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb);
    }
    // 打印String类型数组 → 格式 [a, b, c]
    public static void printStringArray(String[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb);
    }

    /**
     * 2. 数组判空
     */
    // 判断int数组是否为空（null 或 长度为0）
    public static boolean isEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }

    //判断任意类型数组是否为空
    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * 3. 数组最值查找
     */
    // 获取int数组的最大值
    public static int getMax(int[] arr) {
        checkArray(arr);
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    // 获取int数组的最小值
    public static int getMin(int[] arr) {
        checkArray(arr);
        int min = arr[0];
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    /**
     * 4. 数组计算
     */
    // 计算int数组的总和
    public static int getSum(int[] arr) {
        checkArray(arr);
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    // 计算int数组的平均值，返回double类型
    public static double getAvg(int[] arr) {
        checkArray(arr);
        return (double) getSum(arr) / arr.length;
    }

    /**
     * 5. 数组反转
     */
    // 反转int数组（原地反转，无额外空间开销，最优）
    public static void reverse(int[] arr) {
        checkArray(arr);
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // 交换左右指针的值
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 6. 数组拷贝
     */
    // 拷贝一个全新的int数组，修改新数组不会影响原数组
    public static int[] copyArray(int[] arr) {
        if (isEmpty(arr)) {
            return new int[0];
        }
        int[] newArr = new int[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    /**
     * 7. 数组填充
     */
    // 将int数组的所有元素填充为指定值
    public static void fill(int[] arr, int val) {
        if (isEmpty(arr)) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = val;
        }
    }

    /**
     * 8. 私有校验方法
     */
    // 校验数组是否合法，空数组则抛出异常，避免空指针
    private static void checkArray(int[] arr) {
        if (isEmpty(arr)) {
            throw new IllegalArgumentException("数组不能为空！");
        }
    }
}
