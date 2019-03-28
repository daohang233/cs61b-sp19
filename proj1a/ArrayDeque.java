import java.util.Objects;

public class ArrayDeque<T> {
    private T[] items;
    private int start;
    private int end;

    public ArrayDeque() {
        start = 0;
        end = -1;
        items = (T[]) new Object[8];
    }

    public ArrayDeque(ArrayDeque other) {
        start = 0;
        end = -1;
        items = (T[]) new Objects[8];
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    /** If the usage factor is more than 50%, double the size of deque */
    private void resizeCheck() {
        if (size() > items.length * 0.5) {
            T[] newItems = (T[]) new Object[items.length * 2];
            System.arraycopy(items, 0, newItems, 0, end + start);
            if (start < 0) {
                System.arraycopy(items, items.length + start, newItems,
                        newItems.length + start, -start);
            }
            items = newItems;
        }
    }

    /** Add an item of type T to the front of the deque */
    public void addFirst(T item) {
        resizeCheck();
        start--;
        items = (T[]) items;
        int tmp = (start + items.length) % items.length;
        items[(start + items.length) % items.length] = item;
    }

    /** Add an item of type T to the back of the deque */
    public void addLast(T item) {
        resizeCheck();
        end++;
        items[(end + items.length) % items.length] = item;
    }

    /** Return whether the deque is empty */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Return the number of the items in the deque */
    public int size() {
        return end - start + 1;
    }

    /** Print the items in the deque from first to last */
    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(items[(items.length + start + i) % items.length] + " ");
        }
        System.out.println();
    }

    /** Remove and return the first item */
    public T removeFirst() {
        T res = (T) items[(items.length + start) % items.length];
        items[(items.length + start) % items.length] = null;
        start++;
        return res;
    }

    /** Remove and return the last item */
    public T removeLast() {
        T res = (T) items[(end + items.length) % items.length];
        items[(end + items.length) % items.length] = null;
        end--;
        return res;
    }

    /** Return the item at the given index */
    public T get(int index) {
        if (index > size()) {
            return null;
        }
        return (T) items[(index + start + items.length) % items.length];
    }

    public static void main(String[] args) {

        /*ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.addFirst(5);

        ad.addFirst(6);

        ad.addLast(7);

        ad.addFirst(4);

        ad.addLast(8);

        ad.addFirst(3);

        ad.addLast(1);

        ad.addLast(0);

        ad.addLast(9);

        ad.addFirst(2);

        ad.addFirst(1);

        System.out.println(ad.get(0));

        System.out.println(ad.get(7));

        ad.removeLast();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();

        ad.removeFirst();*/

        ArrayDeque<Integer> copy = new ArrayDeque<>();

        copy.addFirst(5);

        copy.addLast(6);

    }
}
