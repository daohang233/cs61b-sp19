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
        if (other.size() == 0) {
            return;
        }
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    /** If the usage factor is more than 50%, double the size of deque */
    private void resizeCheck() {
        if (size() > items.length * 0.5) {
            T[] newItems = (T[]) new Object[items.length * 2];
            System.arraycopy(items, 0, newItems, 0, (end +  items.length + 1) % items.length);
            if (start < 0) {
                System.arraycopy(items, items.length + start, newItems,
                        newItems.length + start, -start);
            }
            deleteDeque();
            items = newItems;
        }
        if (size() < items.length * 0.25 && size() > 8) {
            T[] newItems = (T[]) new Object[items.length / 2];
            for (int i = 0; i < size(); i++) {
                System.arraycopy(items, start, newItems, i, 1);
            }
//            System.arraycopy(items, 0, newItems, 0, (end +  items.length + 1) % items.length);
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
//        System.out.println("here is the index " + (end + items.length) % items.length);
        items[(end + items.length) % items.length] = item;
    }

    /** Return whether the deque is empty */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Return the number of the items in the deque */
    public int size() {
        return (end - start + items.length + 1) % items.length;
    }

    /** Print the items in the deque from first to last */
    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(items[(items.length + start + i) % items.length] + " ");
        }
        System.out.println();
    }

    /** Delete the deque */
    private void deleteDeque() {
        for (int i = 0; i < size(); i++) {
            items[(items.length + start + i) % items.length] = null;
        }
    }

    /** Remove and return the first item */
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        T res = (T) items[(items.length + start) % items.length];
        items[(items.length + start) % items.length] = null;
        start++;
        resizeCheck();
        return res;
    }

    /** Remove and return the last item */
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        T res = (T) items[(end + items.length) % items.length];
        items[(end + items.length) % items.length] = null;
        end--;
        resizeCheck();
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


        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.removeLast();
        ad.removeFirst();
        for(int i = 0; i < 5; i++) {
            ad.addLast(i);
        }

        for (int i = 0;i < 5;i ++) {
            ad.removeFirst();
        }

        for(int i = 0; i < 5; i++) {
            ad.addLast(i);
        }
        System.out.println(ad.isEmpty());

        ArrayDeque<Integer> copy = new ArrayDeque<Integer>(ad);

        copy.addFirst(5);

        copy.addLast(6);
        System.out.println(copy.get(3));

    }
}
