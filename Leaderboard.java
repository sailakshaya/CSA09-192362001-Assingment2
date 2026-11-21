import java.util.*;

public class Leaderboard<T> {
    private final List<T> items = new ArrayList<>();
    private final Comparator<? super T> order;

    public Leaderboard(Comparator<? super T> order) {
        this.order = Objects.requireNonNull(order);
    }

    public void add(T item) {
        items.add(Objects.requireNonNull(item));
    }

    public List<T> ranking() {
        List<T> result = new ArrayList<>(items);
        result.sort(order);
        return result;
    }
}
