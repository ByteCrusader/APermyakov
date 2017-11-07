package ru.apermyakov.simpleset;

import ru.apermyakov.generic.Item;
import ru.apermyakov.generic.SimpleLinkedArray;

import java.util.ListIterator;

/**
 * Class for realize simple set base by linked items.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.11.2017
 * @param <T>
 */
public class SimpleLinkedSet<T> extends SimpleLinkedArray<T> implements SimpleSet<T> {

    /**
     * Method for comparing insert item with array's items.
     *
     * @param object insert item
     * @return has duplicate or not
     */
    private boolean checkDuplicate(T object) {
        boolean duplicate;
        int index = 0;
        Item<T> checkItem = getFirst();
        do {
            duplicate = object == null ? checkItem.getObject() == null : object.equals(checkItem.getObject());
            checkItem = checkItem.getNext();
            index++;
        } while (index < getSize() && !duplicate);
        return duplicate;
    }

    /**
     * Method for override add item.
     *
     * @param insert insert item
     */
    @Override
    public void add(T insert) {
        if (getSize() == 0 || !checkDuplicate(insert)) {
            addItem(insert);
        }
    }
}
