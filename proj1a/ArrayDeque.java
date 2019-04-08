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
        items = (T[]) new Object[8];
        if (other.size() == 0) {
            return;
        }
        for (int i = 0; i < other.size(); i++) {
            T a = (T) other.get(i);
            addLast((T) other.get(i));
        }
    }

    /** If the usage factor is more than 50%, double the size of deque */
    private void resizeCheck() {
        if (size() >= items.length * 0.5) {
            T[] newItems = (T[]) new Object[items.length * 2];
            for (int i = 0;i < size(); i++) {
                newItems[i] = items[(start + items.length + i) % items.length];
            }
            int newEnd = size() - 1;
            deleteDeque();
            items = newItems;

            start = 0;
            end = newEnd;
            return;
//            T[] newItems = (T[]) new Object[items.length * 2];
//            System.arraycopy(items, 0, newItems, 0, (end +  items.length + 1) % items.length);
//            if (start < 0) {
//                System.arraycopy(items, items.length + start, newItems,
//                        newItems.length + start, -start);
//            }
//            deleteDeque();
//            items = newItems;
        }
        if (size() < items.length * 0.25 && size() >= 2) {
            T[] newItems = (T[]) new Object[items.length / 2];
            for (int i = 0;i < size(); i++) {
                newItems[i] = items[(start + items.length + i) % items.length];
            }
            int newEnd = size() - 1;
            deleteDeque();
            items = newItems;
            start = 0;
            end = newEnd;
            return;

//            if (start < 0) {
//                System.arraycopy(items, (items.length + start) % items.length, newItems,
//                        newItems.length + start, -start);
//                items = newItems;
//                return;
//            }
//            for (int i = 0; i < size(); i++) {
//                System.arraycopy(items, (start + i) % items.length, newItems, i, 1);
//            }
//
////            System.arraycopy(items, 0, newItems, 0, (end +  items.length + 1) % items.length);
//
//            end = size() - 1;
//            start = 0;
//            items = newItems;
        }
    }

    /** Add an item of type T to the front of the deque */
    public void addFirst(T item) {
        resizeCheck();
        start = (start - 1 + items.length) % items.length ;
        items = (T[]) items;
        items[(start + items.length) % items.length] = item;
    }

    /** Add an item of type T to the back of the deque */
    public void addLast(T item) {
        resizeCheck();
        end = (end + 1) % items.length;
        items[(end + items.length) % items.length] = (T) item;
    }

    /** Return whether the deque is empty */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Return the number of the items in the deque */
    public int size() {

        if (start > end) {
            return (end + items.length - start + 1) % items.length;
        } else {
            return (end - start + 1) % items.length;
        }
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
        start = (start + 1 + items.length) % items.length;
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
        end = (end - 1 + items.length) % items.length;
        resizeCheck();
        return res;
    }

    /** Return the item at the given index */
    public T get(int index) {
        if (index > size() || index < 0) {
            return null;
        }
        return (T) items[(index + start + items.length) % items.length];
    }

    public static void main(String[] args) {
        int size = 0;

        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addLast(0);
        ad.addFirst(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addLast(4);;
        ad.isEmpty();
        ad.removeLast();
        ad.addFirst(7);
        ad.addFirst(8);
        ad.addLast(9);
        ad.addFirst(10);
        ad.removeLast()      ;

        for (int i = 0; i < 1000; i++) {
            double rand = Math.random() * 4 + 1;
            System.out.println(rand);
            if (rand < 1) {
                ad.addLast(i);
            } else if (rand < 2.9) {
                ad.addFirst(i);
            } else if (rand < 3) {
                ad.removeFirst();
            } else {
                ad.removeLast();
            }
        }


    }
}
