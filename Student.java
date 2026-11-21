import java.util.Objects;

public class Student {
    private final String regNo;
    private final String name;

    public Student(String regNo, String name) {
        this.regNo = Objects.requireNonNull(regNo);
        this.name = Objects.requireNonNull(name);
    }

    public String getRegNo() { return regNo; }
    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return regNo.equals(student.regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }

    @Override
    public String toString() {
        return regNo + " " + name;
    }
}
