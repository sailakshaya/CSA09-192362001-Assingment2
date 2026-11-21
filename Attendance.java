import java.util.*;

public class Attendance {
    public static void main(String[] args) {
        Set<Student> present = new HashSet<>();
        present.add(new Student("192311001", "Arun"));
        present.add(new Student("192311002", "Divya"));
        present.add(new Student("192311001", "Arun"));
        System.out.println("Test 1 - repeated scan: " + present.size());

        Set<Student> unique = new HashSet<>();
        unique.add(new Student("192311003", "Kavi"));
        unique.add(new Student("192311004", "Maya"));
        unique.add(new Student("192311005", "Ravi"));
        System.out.println("Test 2 - unique IDs: " + unique.size());

        Set<Student> sameIdDifferentName = new HashSet<>();
        sameIdDifferentName.add(new Student("192311006", "Anu"));
        sameIdDifferentName.add(new Student("192311006", "Anusha"));
        System.out.println("Test 3 - same ID, different names: " + sameIdDifferentName.size());

        System.out.println("Expected sizes: 2, 3, 1");
    }
}
