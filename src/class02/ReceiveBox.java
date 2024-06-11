package class02;

import java.util.HashMap;
import java.util.Map;

public class ReceiveBox {

    public static void main(String[] args) {
       ReceiveBox box = new ReceiveBox();
        // MessageBox only receive 1~N
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1
        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10
        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11

    }

    Map<Integer, Node> headMap;

    Map<Integer, Node> tailMap;

    Integer curStreamKey = 1;

    public ReceiveBox(){
        headMap = new HashMap<>();
        tailMap = new HashMap<>();
    }



    public void receive(Integer streamKey, String msg) {
        Node n = new Node(msg);
        Node tmp;

        headMap.put(streamKey, n);
        tailMap.put(streamKey, n);


        if (headMap.containsKey(streamKey + 1)) {
             tmp = headMap.get(streamKey + 1);
             n.next = tmp;
             headMap.remove(streamKey + 1);
             tailMap.remove(streamKey); // 因为streamKey后面接了streamKey + 1, 所以streamKey做不了尾节点
        }

        if (tailMap.containsKey(streamKey - 1)) {
            tmp = tailMap.get(streamKey - 1);
            tmp.next = n;
            tailMap.remove(streamKey - 1);
            headMap.remove(streamKey); // 因为streamKey前面接了streamKey - 1, 所以streamKey做不了头节点
        }

        if (streamKey == curStreamKey) {
            Node c = headMap.get(curStreamKey);
            StringBuilder str = new StringBuilder();
            while (c != null) {
                str.append(c.msg);
                str.append("-");
                curStreamKey++;
                c = c.next;
            }
            tailMap.remove(curStreamKey - 1);
            str.deleteCharAt(str.length() - 1);
            System.out.println(str.toString());
        }
    }


    public class Node {

        String  msg;

        Node next;

        public Node(String msg) {
            this.msg = msg;
        }

    }


}

