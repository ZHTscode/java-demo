package solution.labuladong.preSum;

import java.util.ArrayList;
import java.util.List;

public class ProductOfNumbers {
    /* 1352. 最后 K 个数的乘积 */
    List<Integer> preProduct = new ArrayList<>();
    // preProduct.get(i)：下标0到下标i所有元素的乘积

    public ProductOfNumbers() {
        preProduct.add(1);
    }

    public void add(int num) {
        if (num == 0) { // 如果当前数是0，则清空前缀积列表，重新开始
            preProduct.clear();
            preProduct.add(1);
        } else {
            preProduct.add(preProduct.get(preProduct.size() - 1) * num); // 计算当前前缀积并添加到列表中
        }
    }

    public int getProduct(int k) {
        if (k >= preProduct.size()) {
            return 0;
        }
        return preProduct.get(preProduct.size() - 1) / preProduct.get(preProduct.size() - 1 - k);
    }
}
