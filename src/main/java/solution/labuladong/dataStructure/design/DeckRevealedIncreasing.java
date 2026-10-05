package solution.labuladong.dataStructure.design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DeckRevealedIncreasing {
    /* 950. 按递增顺序显示卡牌 */
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        // 链表头部代表牌堆顶，尾部代表牌堆底
        List<Integer> res = new LinkedList<>();
        // 升序排列，然后从倒着遍历，就是点数递减
        Arrays.sort(deck);
        for (int i = n - 1; i >= 0; i--) {
            if (!res.isEmpty()) {
                res.add(0, res.remove(res.size() - 1));
            }
            res.add(0, deck[i]);
        }

        // 将双链表转化成 Java 的数组
        int[] arr = new int[n];
        int i = 0;
        for (int e : res) {
            arr[i++] = e;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] deck = {17,13,11,2,3,5,7};
        int[] arr = new DeckRevealedIncreasing().deckRevealedIncreasing(deck);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
