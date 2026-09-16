package huawei_od._0913;

import java.util.*;

// 注意事项
// nextInt / next / nextDouble：读之前自动跳过空白，互相连着用不用处理换行。
//nextLine：不跳过前面任何字符，读到换行为止。
//只要 nextInt 后面跟 nextLine，就需要手动吞换行。
// https://blog.csdn.net/qq_45776114/article/details/165457342
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 获取输入
        int capacity = sc.nextInt();
        int n = sc.nextInt();
        // 一句话总结：nextInt() 只拿数字，
        // 回车还留在输入流；sc.nextLine() 把这个回车吃掉，防止下一次读行拿到空字符串。
        sc.nextLine();
        List<String> operations = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            operations.add(sc.nextLine());
//            System.out.println(operations.get(i));
        }
        List<Integer> result = solve(capacity, operations);
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(result.get(i));
        }
    }

    static class Node {
        String name;
        int count;

        Node(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    static List<Integer> solve(int capacity, List<String> operations) {
        List<Integer> result = new ArrayList<>();

        // 双向链表, 头部表示最近使用
        LinkedList<Node> apps = new LinkedList<>();
        // 根据 app name迅速找到节点
        HashMap<String, Node> mp = new HashMap<>();

        for (String operation : operations) {
            // 按空格拆分
            String[] tmp = operation.split("\\s+");
            String op = tmp[0];
            String name = tmp[1];

            // OPEN操作
            if (op.equals("OPEN")) {
                Node node = mp.get(name);
                // 如果 app不存在
                if (node == null) {
                    result.add(-1);
                    continue;
                }
                // 打开次数 + 1
                node.count++;
                // 移动到最前面
                apps.remove(node);
                apps.addFirst(node);
                // 返回当前打开次数
                result.add(node.count);
            } else if (op.equals("INSTALL")) {
                Node node = mp.get(name);
                // 如果app存在
                if (node != null) {
                    // 打开次数不变，移动到最前面
                    apps.remove(node);
                    apps.addFirst(node);
                    continue;
                }
                // 判断容量为0， 无法安装
                if (capacity == 0) {
                    continue;
                }
                // 列表已满，卸载最久未使用的app
                if (apps.size() == capacity) {
                    Node last = apps.removeLast();
                    mp.remove(last.name);
                }

                // 创建新的app
                Node newNode = new Node(name, 0);
                apps.addFirst(newNode);

                // 加入HashMap
                mp.put(name, newNode);

            }
            // UNINSTALL 操作
            else if (op.equals("UNINSTALL")) {
                Node node = mp.get(name);

                // app不存在
                if (node == null) {
                    result.add(-1);
                    continue;
                }
                // 返回打开次数
                result.add(node.count);
                // 删除操作
                mp.remove(name);
                apps.remove(node);
            }
        }
        return result;
    }
}
