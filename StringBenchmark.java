import java.util.Locale;

public class StringBenchmark {
    private static final int WARMUP = 2;
    private static final int RUNS = 5;

    private static String[] fields(int count) {
        String[] data = new String[count];
        for (int i = 0; i < count; i++) data[i] = "FIELD" + i + "=RailwayData;";
        return data;
    }

    private static String usingPlus(String[] fields) {
        String result = "";
        for (String field : fields) result = result + field;
        return result;
    }

    private static String usingBuilder(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (String field : fields) result.append(field);
        return result.toString();
    }

    private static String usingBuffer(String[] fields) {
        StringBuffer result = new StringBuffer();
        for (String field : fields) result.append(field);
        return result.toString();
    }

    private static long timeNanos(int tickets, String[] fields, int method) {
        long start = System.nanoTime();
        long checksum = 0;
        for (int i = 0; i < tickets; i++) {
            String result;
            if (method == 0) result = usingPlus(fields);
            else if (method == 1) result = usingBuilder(fields);
            else result = usingBuffer(fields);
            checksum += result.length();
        }
        long elapsed = System.nanoTime() - start;
        // Prevent the work from being treated as unused.
        if (checksum == 0) System.out.println("Unexpected empty result");
        return elapsed;
    }

    private static void benchmark(String label, int tickets, int fieldCount) {
        String[] data = fields(fieldCount);
        String[] names = {"String +", "StringBuilder", "StringBuffer"};
        System.out.println("\n" + label + ": tickets=" + tickets + ", fields/ticket=" + fieldCount);
        for (int method = 0; method < 3; method++) {
            for (int w = 0; w < WARMUP; w++) timeNanos(tickets, data, method);
            long total = 0;
            for (int r = 0; r < RUNS; r++) total += timeNanos(tickets, data, method);
            System.out.printf(Locale.US, "%s average: %.3f ms%n",
                    names[method], total / (double) RUNS / 1_000_000.0);
        }
    }

    public static void main(String[] args) {
        benchmark("Small", 1, 5);
        benchmark("Medium", 1000, 20);
        benchmark("Large", 10000, 50);
        System.out.println("\nNote: timings depend on machine/JVM. Run several times.");
    }
}
