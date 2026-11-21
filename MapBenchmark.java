import java.util.*;

public class MapBenchmark {
    private static volatile int sink;

    private static void run(int n) {
        Map<String, Integer> hash = new HashMap<>();
        Map<String, Integer> tree = new TreeMap<>();
        Map<String, Integer> linked = new LinkedHashMap<>();

        for (int i = n - 1; i >= 0; i--) {
            String key = String.format(Locale.ROOT, "MED%06d", i);
            hash.put(key, i);
            tree.put(key, i);
            linked.put(key, i);
        }

        String lookupKey = String.format(Locale.ROOT, "MED%06d", n / 2);
        int repetitions = 100000;

        System.out.println("\nMedicine count = " + n);
        measure("HashMap 100,000 lookups", hash, lookupKey, repetitions);
        measure("TreeMap 100,000 lookups", tree, lookupKey, repetitions);
        measure("LinkedHashMap 100,000 lookups", linked, lookupKey, repetitions);

        long start = System.nanoTime();
        List<String> sortedReport = new ArrayList<>(hash.keySet());
        Collections.sort(sortedReport);
        long sortedTime = System.nanoTime() - start;

        start = System.nanoTime();
        int treeCount = 0;
        for (String ignored : tree.keySet()) treeCount++;
        long treeReportTime = System.nanoTime() - start;

        start = System.nanoTime();
        int insertionCount = 0;
        for (String ignored : linked.keySet()) insertionCount++;
        long linkedReportTime = System.nanoTime() - start;

        System.out.printf("HashMap + sort code-order report: %.3f ms%n", sortedTime / 1e6);
        System.out.printf("TreeMap code-order report: %.3f ms%n", treeReportTime / 1e6);
        System.out.printf("LinkedHashMap insertion-order report: %.3f ms%n", linkedReportTime / 1e6);
        System.out.println("Sorted report starts with: " + sortedReport.get(0));
        sink = treeCount + insertionCount;
    }

    private static void measure(String label, Map<String, Integer> map,
                                String key, int repetitions) {
        long sum = 0;
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) sum += map.get(key);
        long elapsed = System.nanoTime() - start;
        sink = (int) sum;
        System.out.printf("%s: %.3f ms%n", label, elapsed / 1e6);
    }

    public static void main(String[] args) {
        run(100);
        run(1000);
        run(10000);
    }
}
