//package class03;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class App_DistanceKNodes {
//    public static void main(String[] args) {
//        int k = 12315;
//        Node root = getMockTree();
//        Node target = getMockTree();
//        Map<Node, Node> map = new HashMap<>(); // 随便用一种遍历，构建出map <key=当前节点, value=父节点>, 根节点的父节点是null
//        System.out.println(process(map ,target, target, 0, k));
//    }
//
//    private static Node getMockTree() {
//    }
//
//    private static int process(Map<Node, Node> map ,Node from, Node cur, int distance, int k) {
//        if (cur == null || distance > k) return 0;
//        if (distance == k) {
//            return 1;
//        } else {
//            if (from == cur) { //target节点, 也就是起始点
//                //左右根都走
//                return process(map, cur, cur.left, distance + 1, k) +
//                       process(map, cur, cur.right, distance + 1, k) +
//                       process(cur, cur.left, distance + 1, k);
//            }else if (cur.parent == from ) {
//                return process(cur, cur.right, distance + 1, k) + process(cur, cur.left, distance + 1, k);
//            } else if (cur.left == from) {
//                return process(cur, cur.parent, distance + 1, k) + process(cur, cur.right, distance + 1, k);
//            } else {
//                return process(cur, cur.parent, distance + 1, k) + process(cur, cur.left, distance + 1, k);
//            }
//        }
//    }
//
//    class Node {
//        Node left;
//        Node right;
//        Node parent;
//    }
//}
