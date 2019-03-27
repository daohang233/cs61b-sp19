import java.util.Objects;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int start;
    private int end;

    ArrayDeque() {
        start = 0;
        end = -1;
        items = (T[]) new Objects[8];
    }

    public ArrayDeque(ArrayDeque other) {
        for(int i = 0;i < other.size();i++) {
            addLast((T) other.get(i));
        }
    }

    /** If the usage factor is more than 50%, double the size of deque */
    public void resizeCheck() {
        if(size > items.length * 0.5) {
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
        if(size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /** Return the number of the items in the deque */
    public int size() {
        return end - start + 1;
    }

    /** Print the items in the deque from first to last */
    public void printDeque() {
        for(int i = 0;i < size();i++) {
            System.out.print(items[(items.length + start + i) % items.length]);
        }
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
}
