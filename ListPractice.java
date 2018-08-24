import java.util.*;

public class ListPractice {
    public static void main(String[] args) {
        List list = new ArrayList();
        List<String> list2 = new LinkedList<>();
        List list1 = new Vector();
        List<String> list3 = new Stack<>();
        List<String> list4 = Collections.synchronizedList(new ArrayList<>());
        list.add(1);
        list.add(4);
        list.add(3);
        list.add(2);
        for(int i = 0; i<list.size();++i){
            System.out.println(list.get(i));
        }
        Collections.sort(list);
        for(int i=0; i<list.size(); ++i){
            System.out.println(list.get(i));
        }
    }
}


//Vector is equal to ArrayList, but all Vector functions are synchronize.
//From only beginning when we create List created 10 empty ячеек, but size continue to stay 0.
//