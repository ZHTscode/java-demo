package test;

import solution.CanFinish;

public class TestCanFinish {
    public static void main(String[] args) {
        CanFinish canFinish = new CanFinish();

        // 测试用例1：无环情况，可以完成所有课程
        // 课程0 -> 课程1，即要学课程1必须先学课程0
        System.out.println("测试用例1：");
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println("课程数：" + numCourses1);
        System.out.println("先修关系：" + formatPrerequisites(prerequisites1));
        System.out.println("能否完成：" + canFinish.canFinish(numCourses1, prerequisites1)); // 应输出true
        System.out.println();

        // 测试用例2：有环情况，无法完成所有课程
        // 课程0 -> 课程1 -> 课程2 -> 课程0（形成环）
        System.out.println("测试用例2：");
        int numCourses2 = 3;
        int[][] prerequisites2 = {{1, 0}, {2, 1}, {0, 2}};
        System.out.println("课程数：" + numCourses2);
        System.out.println("先修关系：" + formatPrerequisites(prerequisites2));
        System.out.println("能否完成：" + canFinish.canFinish(numCourses2, prerequisites2)); // 应输出false
        System.out.println();

        // 测试用例3：无依赖关系，可以完成所有课程
        System.out.println("测试用例3：");
        int numCourses3 = 3;
        int[][] prerequisites3 = {};
        System.out.println("课程数：" + numCourses3);
        System.out.println("先修关系：" + formatPrerequisites(prerequisites3));
        System.out.println("能否完成：" + canFinish.canFinish(numCourses3, prerequisites3)); // 应输出true
        System.out.println();

        // 测试用例4：复杂的依赖关系，无环
        // 0->1, 0->2, 1->3, 2->3
        System.out.println("测试用例4：");
        int numCourses4 = 4;
        int[][] prerequisites4 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("课程数：" + numCourses4);
        System.out.println("先修关系：" + formatPrerequisites(prerequisites4));
        System.out.println("能否完成：" + canFinish.canFinish(numCourses4, prerequisites4)); // 应输出true
        System.out.println();

        // 测试用例5：自环情况，无法完成
        System.out.println("测试用例5：");
        int numCourses5 = 2;
        int[][] prerequisites5 = {{1, 1}}; // 课程1依赖于自己
        System.out.println("课程数：" + numCourses5);
        System.out.println("先修关系：" + formatPrerequisites(prerequisites5));
        System.out.println("能否完成：" + canFinish.canFinish(numCourses5, prerequisites5)); // 应输出false
        System.out.println();

        // 测试用例6：复杂一点的有环情况
        System.out.println("测试用例6：");
        int numCourses6 = 4;
        int[][] prerequisites6 = {{0, 1}, {1, 2}, {2, 3}, {3, 0}}; // 形成大环
        System.out.println("课程数：" + numCourses6);
        System.out.println("先修关系：" + formatPrerequisites(prerequisites6));
        System.out.println("能否完成：" + canFinish.canFinish(numCourses6, prerequisites6)); // 应输出false
        System.out.println();
    }

    private static String formatPrerequisites(int[][] prerequisites) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < prerequisites.length; i++) {
            sb.append("[").append(prerequisites[i][0]).append(",").append(prerequisites[i][1]).append("]");
            if (i < prerequisites.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}