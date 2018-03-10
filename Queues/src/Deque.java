import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by saurabh on 3/3/17.
 */
public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first;
    private Node last;

    public Deque() {
    }

    public static void main(String[] args) {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();

        Node newNode = new Node();
        newNode.item = item;

        if (!isEmpty()) {
            newNode.next = first;
            first.previous = newNode;
        }

        first = newNode;

        if (isEmpty()) last = first;

        size++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();

        Node newNode = new Node();
        newNode.item = item;

        if (!isEmpty()) {
            newNode.previous = last;
            last.next = newNode;
        }

        last = newNode;

        if (isEmpty()) first = last;

        size++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        Node oldFirst = first;

        first = oldFirst.next;
        if (size != 1)
            first.previous = null;
        else
            last = null;

        size--;

        if (size == 1)
            last = first;

        return oldFirst.item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Node oldLast = last;

        last = oldLast.previous;
        if (size != 1)
            last.next = null;
        else
            first = null;

        size--;

        if (size == 1)
            first = last;

        return oldLast.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current;
        private int i;

        public DequeIterator() {
            this.current = first;
            this.i = size;
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;

            i--;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }


}
