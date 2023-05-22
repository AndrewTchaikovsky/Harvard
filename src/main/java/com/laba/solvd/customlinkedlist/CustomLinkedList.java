package com.laba.solvd.customlinkedlist;

import com.laba.solvd.Main;
import com.laba.solvd.interfaces.IGetName;
import com.laba.solvd.interfaces.IPrintInfo;
import org.apache.log4j.Logger;

import java.util.*;

public class CustomLinkedList<T> implements List<T>, IGetName, IPrintInfo {
    public static Logger logger = Logger.getLogger(CustomLinkedList.class);
    private Node<T> head;
    private int size;

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public String getName() {
        return "CustomLinkedList";
    }

    @Override
    public void printInfo() {
        logger.info("The " + getName() + " has " + size + " elements.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size ==0;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(current.data, o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            private Node<T> previous = null;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                previous = current;
                current = current.next;
                return data;
            }

            @Override
            public void remove() {
                if (previous == null) {
                    throw new IllegalStateException();
                }
                previous.next = current;
                previous = null;
                size--;
            }
        };
    }


    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public boolean add(T t) {
        Node<T> newNode = new Node<>(t);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }
        if (Objects.equals(head.data, o)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (Objects.equals(current.next.data, o)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T oldData = current.data;
        current.data = element;
        return oldData;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            Node<T> newNode = new Node<>(element);
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node<T> newNode = new Node<>(element);
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            T removedData = head.data;
            head = head.next;
            size--;
            return removedData;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            T removedData = current.next.data;
            current.next = current.next.next;
            size--;
            return removedData;
        }
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("This method is not supported in this custom implementation.");
    }

}
