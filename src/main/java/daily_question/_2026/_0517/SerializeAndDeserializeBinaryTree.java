package daily_question._2026._0517;

public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        String val = "1,2,3,#,#,4,5";
        Codec.deserialize(val);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Codec {
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            f(root, sb);
            return sb.toString();
        }

        // 先序
        static void f(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append("#,");
                // return;
            } else {
                sb.append(node.val + ",");
                f(node.left, sb);
                f(node.right, sb);
            }
        }


        public static TreeNode deserialize(String data) {
            String[] vals = data.split(",");
//            cnt = 0;
            return g(vals, 0);
        }

        // cnt 当前遍历位置
//        public static int cnt;

        static TreeNode g(String[] vals, int cnt) {
            String cur = vals[cnt++];
            if (cur.equals("#")) {
                return null;
            } else {
                TreeNode head = new TreeNode(Integer.valueOf(cur));
                head.left = g(vals, cnt);
                head.right = g(vals, cnt);
                return head;
            }
        }
    }
}
