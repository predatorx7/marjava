package learn;

public class User {
    final String name;
    final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User = '" + name + "' of age " + age + ".";
    }
}
