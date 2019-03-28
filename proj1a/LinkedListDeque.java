public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(0, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        size = other.size();
        sentinel = new TNode(0, sentinel, sentinel);
        for (int i = 0; i < size; i++) {
            addFirst((T) other.get(i));
        }
    }

    /** Add an item of type T to the front of the deque */
    public void addFirst(T item) {
        TNode ptr = new TNode(item, sentinel, sentinel.next);
        sentinel.next = ptr;
        ptr.next.pre = ptr;
        size++;
    }

    /** Add an item of type T to the back of the deque */
    public void addLast(T item) {
        TNode ptr = new TNode(item, sentinel.pre, sentinel);
        sentinel.pre = ptr;
        ptr.pre.next = ptr;
        size++;
    }

    /** Return whether the deque is empty */
    public boolean isEmpty() {
        return size() > 0;
    }

    /** Return the number of items in the deque */
    public int size() {
        return size;
    }

    /** Print all the items in the deque from the first to last */
    public void printDeque() {
        TNode ptr = sentinel.next;
        while (ptr.next != sentinel.next) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    /** Remove and return the first item */
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        T res = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        size--;
        return res;
    }

    /** Remove and return the last item */
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        T res = (T) sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        size--;
        return res;
    }

    /** Return the item at the given index (iteration) */
    public T get(int index) {
        int cnt = 0;
        TNode ptr = sentinel;
        while (ptr.next != null) {
            ptr = ptr.next;
            cnt++;
            if (cnt == index) {
                return (T) ptr.item;
            }
        }
        return null;
    }

    /** Return the item at the given index (recursion) */
    public T getRecursive(int index) {
        if (index == 0) {
            //TODO fuck java
        }
        return null;
    }
     public static void main(String[] args) {

        LinkedListDeque<Integer> dq = new LinkedListDeque<>();

        dq.addFirst(666);

        dq.removeLast();

        //dq.addLast(888);

        //dq.addLast(999);

        dq.addFirst(555);
        dq.removeLast();
        System.out.println(dq.size());


        dq.printDeque();

    }
}
