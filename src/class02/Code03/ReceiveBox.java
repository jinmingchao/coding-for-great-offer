package class02.Code03;

import java.util.HashMap;
import java.util.Map;

public class ReceiveBox {

    Map<Integer, Node> headMap;

    Map<Integer, Node> tailMap;

    Integer curStreamKey = 0;

    public ReceiveBox(){
        headMap = new HashMap<>();
        tailMap = new HashMap<>();
    }



    public void put(Integer streamKey, String msg) {
        Node n = new Node(msg);
        Node tmp;

        if (headMap.containsKey(streamKey + 1)) {
             tmp = headMap.get(streamKey + 1);
             n.next = tmp;
             headMap.remove(streamKey + 1);
             headMap.put(streamKey, n);
        }

        if (tailMap.containsKey(streamKey - 1)) {
            tmp = headMap.get(streamKey - 1);
            tmp.next = n;
            headMap.remove(streamKey - 1);
            headMap.put(streamKey, n);
        }

        if (streamKey == curStreamKey) {
            StringBuilder str = new StringBuilder();
            while(n != null) {
                str.append(msg);
                str.append("-");
            }
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

