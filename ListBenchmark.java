import java.util.*;

public class ListBenchmark {
    private static volatile long sink;

    private static long timeAddEnd(List<Integer> list, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(i);
        return System.nanoTime() - start;
    }

    private static long timeFrontInsert(List<Integer> list, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(0, i);
        return System.nanoTime() - start;
    }

    private static long timeMiddleInsert(List<Integer> list, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(list.size() / 2, i);
        return System.nanoTime() - start;
    }

    private static long timeGet(List<Integer> list, int repetitions) {
        long sum = 0;
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) sum += list.get(list.size() / 2);
        long elapsed = System.nanoTime() - start;
        sink = sum;
        return elapsed;
    }

    private static long timeIterate(List<Integer> list) {
        long sum = 0;
        long start = System.nanoTime();
        for (int value : list) sum += value;
        long elapsed = System.nanoTime() - start;
        sink = sum;
        return elapsed;
    }

    private static void run(int n) {
        System.out.println("\nSIZE = " + n);
        List<Integer> array = new ArrayList<>();
        List<Integer> linked = new LinkedList<>();

        System.out.printf("ArrayList add-end: %.3f ms%n", timeAddEnd(array, n) / 1e6);
        System.out.printf("LinkedList add-end: %.3f ms%n", timeAddEnd(linked, n) / 1e6);

        System.out.printf("ArrayList front-insert: %.3f ms%n",
                timeFrontInsert(new ArrayList<>(), n) / 1e6);
        System.out.printf("LinkedList front-insert: %.3f ms%n",
                timeFrontInsert(new LinkedList<>(), n) / 1e6);

        System.out.printf("ArrayList middle-insert: %.3f ms%n",
                timeMiddleInsert(new ArrayList<>(), n) / 1e6);
        System.out.printf("LinkedList middle-insert: %.3f ms%n",
                timeMiddleInsert(new LinkedList<>(), n) / 1e6);

        System.out.printf("ArrayList get-middle (10000): %.3f ms%n",
                timeGet(array, 10000) / 1e6);
        System.out.printf("LinkedList get-middle (10000): %.3f ms%n",
                timeGet(linked, 10000) / 1e6);

        System.out.printf("ArrayList iteration: %.3f ms%n", timeIterate(array) / 1e6);
        System.out.printf("LinkedList iteration: %.3f ms%n", timeIterate(linked) / 1e6);

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) deque.addLast(i);
        for (int i = 0; i < n / 2; i++) deque.removeFirst();
        for (int i = 0; i < n / 2; i++) deque.addFirst(i);
        System.out.printf("ArrayDeque end operations: %.3f ms%n",
                (System.nanoTime() - start) / 1e6);
    }

    public static void main(String[] args) {
        run(1000);
        run(10000);
        run(100000);
        System.out.println("\nArrayDeque has no indexed get/insert operation.");
    }
}
