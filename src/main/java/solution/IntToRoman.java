package solution;

public class IntToRoman {
    public String intToRoman(int num) {
        // 定义罗马数字的数值-字符映射，按数值从大到小排列
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        // 用StringBuilder拼接结果，效率高于String
        StringBuilder roman = new StringBuilder();
        // 遍历所有数值-字符对
        for (int i = 0; i < values.length && num > 0; i++) {
            // 当当前数值小于等于剩余数字时，重复拼接对应字符并减去数值
            while (num >= values[i]) {
                roman.append(symbols[i]);
                num -= values[i];
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) {
        IntToRoman intToRoman = new IntToRoman();
        System.out.println(intToRoman.intToRoman(444));
    }
}
