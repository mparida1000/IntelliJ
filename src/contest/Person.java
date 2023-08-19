package contest;

public class Person {
    String name;
    int age;

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.age = 10;
        p1.name = "ABC";
        Person p2 = new Person();
        p2.age = 20;
        p2.name = "DEF";
        fun(p1,p2);
        System.out.println(p1.age+p1.name);
        System.out.println(p2.age+p2.name);

    }

    static void fun(Person p1, Person p2){
        Person temp = p1;
        p1=p2;
        p2=temp;
    }
}
