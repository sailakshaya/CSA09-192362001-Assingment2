public class Patient {
    final String name;
    final int severity;
    final long arrival;

    public Patient(String name, int severity, long arrival) {
        if (severity < 1 || severity > 5) {
            throw new IllegalArgumentException("Severity must be from 1 to 5");
        }
        this.name = name;
        this.severity = severity;
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return name + "(S" + severity + ")";
    }
}
