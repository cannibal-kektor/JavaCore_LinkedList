package kektor.innowise;

import java.util.NoSuchElementException;
import java.util.Optional;

public class LinkedList<E> {

    int size = 0;
    Node<E> first;
    Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public int size() {
        return size;
    }

    public void addFirst(E element) {
        var node = new Node<>(element, null, first);
        if (first != null) {
            first.prev = node;
        } else {
            last = node;
        }
        first = node;
        size++;
    }

    public void addLast(E element) {
        var node = new Node<>(element, last, null);
        if (last == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            var nodeAtIndex = getNodeByIndex(index);
            var prev = nodeAtIndex.prev;
            var node = new Node<>(element, prev, nodeAtIndex);
            nodeAtIndex.prev = node;
            prev.next = node;
            size++;
        }
    }


    public E getFirst() {
        return Optional.ofNullable(first).orElseThrow().element;
    }

    public E getLast() {
        return Optional.ofNullable(last).orElseThrow().element;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        return getNodeByIndex(index).element;
    }

    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        var next = first.next;
        E element = first.element;
        first = next;
        if (first == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }


    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        var prev = last.prev;
        final E element = last.element;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }


    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            var node = getNodeByIndex(index);
            E element = node.element;
            var prev = node.prev;
            var next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
            return element;
        }
    }

    private Node<E> getNodeByIndex(int index) {
        Node<E> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }


}
