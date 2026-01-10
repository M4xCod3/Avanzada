import java.util.LinkedList;

public class HashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;
    private int m;
    private LinkedList<Node>[] st;

    private static class Node {
        private Object key;
        private Object val;

        public Node(Object key, Object val) {
            this.key = key;
            this.val = val;
        }
    }

    public HashST() {
        this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashST(int m) {
        this.m = m;
        st = (LinkedList<Node>[]) new LinkedList[m];
        for (int i = 0; i < m; i++) {
            st[i] = new LinkedList<>();
        }
    }

    private void resize(int chains) {
        HashST<Key, Value> temp = new HashST<>(chains);
        for (int i = 0; i < m; i++) {
            for (Node node : st[i]) {
                temp.put((Key) node.key, (Value) node.val);
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    @SuppressWarnings("unchecked")
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        for (Node node : st[i]) {
            if (key.equals(node.key)) {
                return (Value) node.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            return;
        }

        if (n >= 10 * m) resize(2 * m);

        int i = hash(key);
        for (Node node : st[i]) {
            if (key.equals(node.key)) {
                node.val = val;
                return;
            }
        }
        st[i].add(new Node(key, val));
        n++;
    }

    public Iterable<Key> keys() {
        LinkedList<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < m; i++) {
            for (Node node : st[i]) {
                queue.add((Key)node.key);
            }
        }
        return queue;
    }
}
