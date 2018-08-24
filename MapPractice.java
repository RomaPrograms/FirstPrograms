import java.util.*;

public class MapPractice {
    public static void main(String[] args) {
        Map map = new HashMap();   //map which keep it object
        Map map2 = new Hashtable();  //map whose methods are synchronized
        Map map3 = new LinkedHashMap();  //map which  don't sort our objects, and also in addition we get two extra function like "head", "tail"
        //it's obvious cause it Link = a little bit list.
        Map map4 = new TreeMap();  //100 sort map,
        map.put("1", "one");
        map.put("3", "three");
        map.put("2", "two");
        System.out.println(map.get(3));
        Set set = map.entrySet();  //comfort output
        for(Object o: set){
            System.out.println(o);
        }

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("a", "1");
        treeMap.put("b", "2");
        treeMap.put("d", "4");
        treeMap.put("c", "3");
        System.out.println(treeMap.subMap("a", "d")); //with a help of TreeMap we can output some definite elements
        System.out.println(treeMap.subMap("a", "d").lastKey());
        System.out.println(treeMap.subMap("a", "d").firstKey());
    }
}



//all of it save in special mass of "NODES", where NODES contain: hash, key, value and next - pointer to other object
