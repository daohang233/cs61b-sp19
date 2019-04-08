public class LinkedListDeque<T> {
    private class TNode<T> {
        private T item;
        private TNode next;
        private TNode pre;

        /** */
        TNode(T i, TNode p, TNode n) {
            item = i;
            pre =  p;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(0, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new TNode(0, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        if (other.size() == 0) {
            return;
        }

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
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
        return size() == 0;
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
        sentinel.next.item = null;
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
        sentinel.pre.item = null;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        size--;
        return res;
    }

    /** Return the item at the given index (iteration) */
    public T get(int index) {
        int cnt = -1;
        TNode ptr = sentinel.next;
        while (ptr != sentinel) {
            cnt++;
            if (cnt == index) {
                return (T) ptr.item;
            }
            ptr = ptr.next;
        }
        return null;
    }

    /** Return the item at the given index (recursion) */
    public T getRecursive(int index) {
        return (T) helpRecursive(sentinel.next, index);
    }

    /** Recursive get item of given index */
    private T helpRecursive(TNode ptr, int index) {
        if (index == 0) {
            return (T) ptr.item;
        } else if (index < 0) {
            return null;
        }
        return (T) helpRecursive(ptr.next, index - 1);
    }
}
