package ru.apermyakov.maprealize;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for realize oun simple hash map.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.11.2017
 */
public class SimpleHashMap<K, V> implements Iterable<V> {

    /**
     * Field for object's array.
     */
    private Node[] array = new Node[16];

    /**
     * Field for calculate alive items in array.
     */
    private int aliveItems = 0;

    /**
     * Method for increase array if add item more then array length.
     */
    private void increaseArray() {
        Node[] oldArray = this.array;
        Node[] newArray = new Node[oldArray.length * 2];
        for (Node node : oldArray) {
            if (node != null) {
                newArray[calculateHash((K) node.getKey()) & (newArray.length - 1)] = node;
            }
        }
        this.array = newArray;
    }

    /**
	 * Method for calculate hash of key.
	 *
     * @param key key
     * @return key's hash
     */
    private int calculateHash(K key) {
		int hash = key == null ? 0 : key.hashCode();
		/*
		* HashCodes that differ only by constant multiples at each bit position 
		* have a bounded number of collisions (approximately 8 at default load factor).
		*/
		hash ^= (hash >>> 20) ^ (hash >>> 12);
		return hash ^ (hash >>> 7) ^ (hash >>> 4);
    }

    /**
	 *Method for build array's index base on key's hash.
	 *
     * @param key key
     * @return array's index
     */
    private int buildIndex(K key) {
        return calculateHash(key) & array.length - 1;
    }

    /**
	 * Method for check key in array.
	 *
     * @param key key
     * @return exist or not
     */
    private boolean checkKey(K key) {
        return array[buildIndex(key)] == null;
    }

    /**
	 * Method for insert value to array.
	 *
     * @param node insert node
     * @return true
     */
    private boolean insertValue(Node node) {
        if (aliveItems == array.length) {
            increaseArray();
        }
        array[buildIndex((K) node.getKey())] = node;
        aliveItems++;

        return true;
    }

    /**
	 * Method for initialize insert action.
	 *
     * @param node insert node
     * @return insert of not
     */
    public boolean insert(Node node) {
        return checkKey((K) node.getKey()) && insertValue(node);
    }

    /**
	 * Method for find and get value by key.
     * @param key key
     * @return found value
     * @throws NoSuchElementException Map has't such element
     */
    private V getValue(K key) throws NoSuchElementException {
        if (!checkKey(key)) {
            return (V) this.array[buildIndex(key)].getValue();
        } else {
            throw new NoSuchElementException("Map has't such element");
        }
    }

    /**
	 * Method for initialize get action.
	 *
     * @param key key
     * @return found value
     */
    public V get(K key) {
        return getValue(key);
    }

    /**
	 * Method for delete value by key out of array.
	 *
     * @param key key
     * @return true
     */
    private boolean deleteValue(K key) {
        this.array[buildIndex(key)] = null;
        aliveItems--;
        return true;
    }

    /**
	 * Method for initialize delete action.
	 *
     * @param key key
     * @return delete or not
     */
    public boolean delete(K key) {
        return !checkKey(key) && deleteValue(key);
    }
	
	/**
	 * Method for increase array by 10 times.
	 * 
	 * @return array length
	 */
	public int largeMap() {
		this.array = Arrays.copyOf(this.array, 160);
		return this.array.length;
	}

    /**
     * Private class for initialize map iterator.
     *
     * @author apermyakov
     * @version 1.0
     * @since 14.11.2017
     * @param <V>
     */
    private class MapIterator<V> implements Iterator<V>{

        /**
         * Field for iterator counter.
         */
        private int iteratorCounter = 0;

        /**
         * Field for iterator inside index.
         */
        private int iteratorIndex = 0;

        /**
         * Method for override has next.
         *
         * @return array has next alive item or not
         */
        @Override
        public boolean hasNext() {
            return iteratorCounter < aliveItems;
        }

        /**
         * Method for override next.
         *
         * @return next item if exist
         * @throws NoSuchElementException Map has't such element
         */
        @Override
        public V next() throws NoSuchElementException {
            if (hasNext()) {
                V next = null;
                for (; iteratorIndex < array.length;) {
                    if (array[iteratorIndex++] != null) {
                        next = (V) array[iteratorIndex - 1].getValue();
                        iteratorCounter++;
                        break;
                    }
                }
                return next;
            } else {
                throw new NoSuchElementException("Map has't such element");
            }
        }
    }

    /**
     * Field for initialize map iterator object.
     */
    private MapIterator mapIterator = new MapIterator();

    /**
	 * Realize of iterator.
	 *
     * @return new iterator.
     */
    @Override
    public Iterator<V> iterator() {
        return mapIterator;
    }
}
