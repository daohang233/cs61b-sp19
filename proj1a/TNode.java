public class TNode<T> {
    public T item;
    public TNode next;
    public TNode pre;

    /** */
    public TNode(T i, TNode p, TNode n) {
        item = i;
        pre =  p;
        next = n;
    }
}
