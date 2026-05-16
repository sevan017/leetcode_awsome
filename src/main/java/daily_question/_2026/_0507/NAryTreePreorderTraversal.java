package daily_question._2026._0507;

import java.util.*;

/**
 * 589. N 叉树的前序遍历
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 */
public class NAryTreePreorderTraversal {

    //    [1,null,3,2,4,null,5,6]
    public static void main(String[] args) {
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        List<Node> _children = new ArrayList<>();
        _children.add(node4);
        _children.add(node5);

        Node node1 = new Node(3, _children);
        Node node2 = new Node(2);
        Node node3 = new Node(4);


        List<Node> _children2 = new ArrayList<>();
        _children2.add(node1);
        _children2.add(node2);
        _children2.add(node3);

        Node root = new Node(1, _children2);

        Solution solution = new Solution();
        solution.preorder(root);
    }


}


class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> map = new HashMap<Node, Integer>();
        Deque<Node> stack = new ArrayDeque<Node>();
        Node node = root;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                List<Node> children = node.children;
                if (children != null && children.size() > 0) {
                    map.put(node, 0);
                    node = node.children.get(0);
                } else {
                    node = null;
                }
            }

            node = stack.peek();
            int index = map.getOrDefault(node, -1) + 1;
            List<Node> children = node.children;
            if (children != null && children.size() > index) {
                map.put(node, index);
                node = children.get(index);
            } else {
                stack.pop();
                map.remove(node);
                node = null;
            }
        }
        return res;
    }

}