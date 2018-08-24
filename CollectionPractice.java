import java.lang.reflect.Array;
import java.util.*;

class Book{
    private Set nameAutor = new HashSet();

    public Book(){};

    public Book(Set nameAutor) {
        this.nameAutor = nameAutor;
    }

    public Set getNameAutor() {
        return nameAutor;
    }

    public void setNameAutor(Set nameAutor) {
        this.nameAutor = nameAutor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "nameAutor=" + nameAutor +
                '}';
    }
}

public class CollectionPractice {
    public static void main(String[] args) {
        Collection collection = new ArrayList(); //When we speak about collections we speak about all of that.
        Collections collections;  //Collection it's conteiners for keeping data in different types.
        Array array;
  //also we have some collections which extend from collections like

//////////////////////////////////////////////////////////////////////////
        List collection1 = new ArrayList();  //With list we work only with a help of getter and setter
        collection1.add("1");
        collection1.add("2");
        collection1.add("3");
        collection1.set(2, "5");
        for(int i = 0; i<collection.size(); ++i)
            System.out.println(collection1.get(i));
///////////////////////////////////////////////////////////////////////////
       Queue collection2 = new PriorityQueue();
        collection2.offer("1");
        collection2.offer("2");
        collection2.offer("3");
        for(int i = 0; i<collection2.size(); ++i)
            System.out.println(collection2.poll());
        System.out.println("\n" + collection2.size());
/////////////////////////////////////////////////////////////////////////////
        Set collection3 = new HashSet();
        collection3.add("1");
        collection3.add("2");
        collection3.add("3");
        collection3.add("4");
        collection3.add("5");
        collection3.add("6");
        collection3.add("1");
        collection3.add("2");
        collection3.add("2");
        for(Object o:collection3)
            System.out.println(o);
        System.out.println(collection3.size());
 //////////////////////////////////////////////////////////////////////////////////

        Map collection4 = new HashMap();
        Book book = new Book();
        book.getNameAutor().add("Karnegi");
        book.getNameAutor().add("JackLondon");
        book.getNameAutor().add("Freddi");
        book.getNameAutor().add("Dunclin");
        collection4.put("1", "Dan Brown");
        collection4.put("2", book);
        collection4.put("3", "Jack London");
        //System.out.println(collection.get("2"));
        //Set set = collection.entrySet();
        Set set = collection4.keySet();
        for(Object o:set)
            System.out.println(o);


///////////////////////////////////////////////////////////////////////////////

        /*HashSet - whether sort or not
          LinkedHashSet - don't sort objects
          TreeSet - sort our object 100%

          So, collections can be uporiadoch and not uporiadoch.*/

    }
}

