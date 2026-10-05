package solution.labuladong.dataStructure.design;

public class CountStudents {
    /* 1700.无法吃午餐的学生数量 */
    public int countStudents(int[] students, int[] sandwiches) {
        // studentCount[0 or 1] 分别代表吃 0 和吃 1的学生数量
        int[] studentCount = new int[2];
        for (int type : students) {
            studentCount[type]++;
        }
        // 遍历三明治栈，若栈顶的三明治无法被取走，则剩下的人都吃不上了
        for (int type : sandwiches) {
            if (studentCount[type] == 0) {
                // 两种喜好加起来就是剩下的学生数量
                return studentCount[0] + studentCount[1];
            }
            studentCount[type]--;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] students = {1,1,0,0};
        int[] sandwiches = {0,1,0,1};
        int res = new CountStudents().countStudents(students, sandwiches);
        System.out.println(res);
    }
}
