/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private static final int LEFT_TO_RIGHT = 0;
    private static final int RIGHT_TO_LEFT = 1;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>(); //지그재그 리스트를 담을 참조변수 result

        //예외 처리
        if (root == null) {
            return result;
        }

        //bfs를 처리할 큐
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root); //root 노드 삽입 (왼쪽)
        int direction = LEFT_TO_RIGHT; //방향 설정

        while (!queue.isEmpty()) {  //bfs 알고리즘
            int levelSize = queue.size(); //트리의 레벨별 사이즈
            List<Integer> currentLevel = new ArrayList<>(); //현재 레벨의 값

            //레벨 별 처리
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll(); //큐에서 노드 가져오기

                //방향을 확인 후 값을 리스트에 넣어주기
                if (direction == LEFT_TO_RIGHT) { //정방향이면 리스트에 그냥 넣고
                    currentLevel.add(node.val);
                } else {    //역방향이면 리스트의 앞에다가 삽입
                    currentLevel.add(0, node.val);
                }

                //큐에 다음 레벨 노드 추가
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(currentLevel); //리스트에 레벨별 리스트 값 넣기
            direction = (direction == LEFT_TO_RIGHT) ? RIGHT_TO_LEFT : LEFT_TO_RIGHT; //방향 반전

        }

        return result;
    }
}