import java.util.*;

public class QueuePractice {
    public static void main(String[] args) {
        Queue queue = new LinkedList();  //not sort
        Queue queue1 = new PriorityQueue(); //sort
        Deque<String> deque = new LinkedList<>(); //the same like "queue", but with more functions
        Deque<String> deque1 = new ArrayDeque<>(); //ArrayDeque faster than LinkedList, and use more functions, that's why better use ArrayDeque<>();
        queue.add(1);
        queue.add(3);
        queue.add(2);
        //also we can sort it like that:
        List list = (List)queue;
        Collections.sort(list);
        while(queue.size()>0)
            System.out.println(queue.poll());
    }
}
