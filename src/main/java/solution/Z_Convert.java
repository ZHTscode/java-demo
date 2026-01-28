package solution;

public class Z_Convert {
    public String convert(String s, int numRows) {
        // 边界条件：行数为1或行数大于等于字符串长度，直接返回原字符串
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        // 初始化每行的字符容器
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int currentRow = 0; // 当前所在行
        int direction = -1; // 移动方向：-1表示向上，1表示向下（初始为-1，第一次遍历会触发方向反转）
        // 遍历每个字符，分配到对应行
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            // 到达顶部或底部边界，反转方向
            if (currentRow == 0 || currentRow == numRows - 1) {
                direction = -direction;
            }
            // 移动到下一行
            currentRow += direction;
        }
        // 拼接所有行的字符
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Z_Convert zConvert = new Z_Convert();
        // 测试用例1：经典示例
        String s1 = "PAYPALISHIRING";
        int numRows1 = 3;
        String res1 = zConvert.convert(s1, numRows1);
        System.out.println("输入字符串：" + s1);
        System.out.println("行数：" + numRows1);
        System.out.println("Z字形变换结果：" + res1); // 预期输出：PAHNAPLSIIGYIR
        System.out.println("------------------------");
        // 测试用例2：行数为1的边界情况
        String s2 = "ABCDEFG";
        int numRows2 = 1;
        String res2 = zConvert.convert(s2, numRows2);
        System.out.println("输入字符串：" + s2);
        System.out.println("行数：" + numRows2);
        System.out.println("Z字形变换结果：" + res2); // 预期输出：ABCDEFG
        System.out.println("------------------------");
        // 测试用例3：行数大于字符串长度
        String s3 = "XYZ";
        int numRows3 = 5;
        String res3 = zConvert.convert(s3, numRows3);
        System.out.println("输入字符串：" + s3);
        System.out.println("行数：" + numRows3);
        System.out.println("Z字形变换结果：" + res3); // 预期输出：XYZ
    }
}
