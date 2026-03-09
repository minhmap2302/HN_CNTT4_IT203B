package Bai6;

public class User {
    private String Name;
    private int age;

    public User(int age, String name) {
        this.age = age;
        Name = name;
    }
    public User(){

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", Name='" + Name + '\'' +
                '}';
    }
}
