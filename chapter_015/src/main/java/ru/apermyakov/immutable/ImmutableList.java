package ru.apermyakov.immutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * Class for realize immutable list.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 23.01.2018.
 * @param <T> list param.
 */
public class ImmutableList<T> implements List<T> {

    private final List<T> list;

    public ImmutableList(List<T> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private final Iterator<T> tIterator = list.iterator();

            @Override
            public boolean hasNext() {
                return this.tIterator.hasNext();
            }

            @Override
            public T next() {
                return this.tIterator.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void forEachRemaining(Consumer<? super T> action) {
                this.tIterator.forEachRemaining(action);
            }
        };
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return this.list.toArray(a);
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        return this.list.get(index);
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ListIterator<T>() {

            private final ListIterator<T> tListIterator = list.listIterator(index);

            @Override
            public boolean hasNext() {
                return this.tListIterator.hasNext();
            }

            @Override
            public T next() {
                return this.tListIterator.next();
            }

            @Override
            public boolean hasPrevious() {
                return this.tListIterator.hasPrevious();
            }

            @Override
            public T previous() {
                return this.tListIterator.previous();
            }

            @Override
            public int nextIndex() {
                return this.tListIterator.nextIndex();
            }

            @Override
            public int previousIndex() {
                return this.tListIterator.previousIndex();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return new ImmutableList<>(list.subList(fromIndex, toIndex));
    }
}
