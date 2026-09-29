package solution.labuladong.dataStructure.graph;

public class EquationsPossible {
    /* 990. 等式方程的可满足性 */
    public boolean equationsPossible(String[] equations) {
        // 26 个英文字母
        UF uf = new UF(26);
        // 先让相等的字母形成连通分量
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                uf.union(x - 'a', y - 'a');
            }
        }
        // 检查不等关系是否打破相等关系的连通性
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                // 如果相等关系成立，就是逻辑冲突
                if (uf.connected(x - 'a', y - 'a'))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] equations = {"a==b","b!=a"};
        EquationsPossible ep = new EquationsPossible();
        System.out.println(ep.equationsPossible(equations));
    }
}
