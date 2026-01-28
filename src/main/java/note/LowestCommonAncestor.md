|       递归层级        | 当前执行节点 |              执行操作描述               |         调用的子递归方法         | 子递归返回值 | 当前节点最终返回值 |              执行状态说明              |
| :-------------------: | :----------: | :-------------------------------------: | :------------------------------: | :----------: | :----------------: | :------------------------------------: |
|           1           |    root=3    |      不触发终止条件，先执行左递归       |  `lowestCommonAncestor(5,6,7)`   |      5       |         5          |         主线递归，最后返回结果         |
|           2           |    root=5    |      不触发终止条件，先执行左递归       |  `lowestCommonAncestor(6,6,7)`   |      6       |         5          |   ✅【核心关键层】命中答案的核心节点    |
|           3           |    root=6    |     root=6 是目标节点，触发终止条件     |                无                |      无      |         6          |           递归触底，直接返回           |
| ↩️回溯至 2 层 (root=5) |    root=5    |   左递归完成，拿到 left=6，执行右递归   |  `lowestCommonAncestor(2,6,7)`   |      7       |         5          |        等待右递归结果做最终判断        |
|           3           |    root=2    |      不触发终止条件，先执行左递归       |  `lowestCommonAncestor(7,6,7)`   |      7       |         7          |              继续向下递归              |
|           4           |    root=7    |     root=7 是目标节点，触发终止条件     |                无                |      无      |         7          |           递归触底，直接返回           |
| ↩️回溯至 3 层 (root=2) |    root=2    |   左递归完成，拿到 left=7，执行右递归   |  `lowestCommonAncestor(4,6,7)`   |     null     |         7          |           继续执行右子树递归           |
|           4           |    root=4    |      不触发终止条件，先执行左递归       | `lowestCommonAncestor(null,6,7)` |     null     |        null        |          叶子节点的左子树为空          |
| ↩️回溯至 4 层 (root=4) |    root=4    |       左递归返回 null，执行右递归       | `lowestCommonAncestor(null,6,7)` |     null     |        null        |          叶子节点的右子树为空          |
| ↩️回溯至 4 层 (root=4) |    root=4    | left=null，right=null → 按规则返回 null |                无                |      无      |        null        |           无目标节点，返回空           |
| ↩️回溯至 3 层 (root=2) |    root=2    |  left=7，right=null → 按规则返回 left   |                无                |      无      |         7          |        只在左子树找到目标节点 7        |
| ↩️回溯至 2 层 (root=5) |    root=5    |   ✔️`left=6 且 right=7`，左右均不为空    |                无                |      无      |         5          | 满足核心条件，返回当前节点【答案确定】 |
| ↩️回溯至 1 层 (root=3) |    root=3    |   左递归完成，拿到 left=5，执行右递归   |  `lowestCommonAncestor(1,6,7)`   |     null     |         5          |         验证右子树，无目标节点         |
|           2           |    root=1    |      不触发终止条件，先执行左递归       |  `lowestCommonAncestor(0,6,7)`   |     null     |        null        |             遍历右子树分支             |
|           3           |    root=0    |     左右递归均返回 null → 返回 null     |                无                |      无      |        null        |               无目标节点               |
| ↩️回溯至 2 层 (root=1) |    root=1    |       左递归返回 null，执行右递归       |  `lowestCommonAncestor(8,6,7)`   |     null     |        null        |             遍历右子树分支             |
|           3           |    root=8    |     左右递归均返回 null → 返回 null     |                无                |      无      |        null        |               无目标节点               |
| ↩️回溯至 2 层 (root=1) |    root=1    |    left=null，right=null → 返回 null    |                无                |      无      |        null        |          右子树无任何目标节点          |
| ↩️回溯至 1 层 (root=3) |    root=3    |  left=5，right=null → 按规则返回 left   |                无                |      无      |         5          |              最终返回结果              |

------