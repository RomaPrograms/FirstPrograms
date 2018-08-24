import java.util.*;

//if we use Comparable<Person>

class Person implements Comparable<Person>{
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}

public class CollectionSort {
    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        set.add(new Person(4));
        set.add(new Person(6));
        set.add(new Person(5));
        set.add(new Person(2));
        for(Person o: set)
            System.out.println(o.getAge());
    }
}

//if we use Comparable<Person>

/*class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}

class ComparePerson implements Comparator<Person>{
    @Override
    public int compare(Person o1, Person o2){
        return o1.getAge() - o2.getAge();
    }
}

public class CollectionSort {
    public static void main(String[] args) {
        ComparePerson comparePerson = new ComparePerson();
        Set set = new TreeSet(comparePerson);
        set.add(new Person(4));
        set.add(new Person(6));
        set.add(new Person(5));
        set.add(new Person(2));
        for(Object o: set)
            System.out.println(o);
    }
}*/

//here the most convenient way for comparing some methods in java.