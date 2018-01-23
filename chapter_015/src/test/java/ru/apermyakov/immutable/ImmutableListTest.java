package ru.apermyakov.immutable;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class for test immutable list.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 23.01.2018.
 */
public class ImmutableListTest {

    private List<String> list;

    @Before
    public void initial() {
        List<String> checkList = new ArrayList<>();
        checkList.add("Petr");
        checkList.add("Alexander");
        checkList.add("Alexander");
        this.list = new ImmutableList<>(checkList);
    }

    @Test
    public void whenGetSizeThenTake2() {
        assertThat(this.list.size(), is(3));
    }

    @Test
    public void whenIsEmptyThenFalse() {
        assertThat(this.list.isEmpty(), is(false));
    }

    @Test
    public void whenContainPetrThenTrue() {
        assertThat(this.list.contains("Petr"), is(true));
    }

    @Test
    public void whenIteratorNextThenTakePetr() {
        assertThat(this.list.iterator().hasNext(), is(true));
        assertThat(this.list.iterator().next(), is("Petr"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenIteratorRemoveTakeException() {
        this.list.iterator().remove();
    }

    @Test
    public void whenGetFirstItemThenTakePetr() {
        assertThat(this.list.get(0), is("Petr"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenAddSomeItemTakeException() {
        this.list.add("check");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenRemoveSomeItemTakeException() {
        this.list.remove("check");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenAddSomeCollectionTakeException() {
        this.list.addAll(new ArrayList<>());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenRemoveSomeCollectionTakeException() {
        this.list.removeAll(new ArrayList<>());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenClearListThenTakeException() {
        this.list.clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenSetSomeItemThenTakeException() {
        this.list.set(1, "Ivan");
    }


    @Test(expected = UnsupportedOperationException.class)
    public void whenAddToIndexSomeItemThenTakeException() {
        this.list.add(1, "Ivan");
    }

    @Test
    public void whenIndexOfPetrThenTakeZero() {
        assertThat(this.list.indexOf("Petr"), is(0));
    }

    @Test
    public void whenLastIndexOfAlexanderThenTakeTwo() {
        assertThat(this.list.lastIndexOf("Alexander"), is(2));
    }

    @Test
    public void whenListIteratorActionThenTakePetr() {
        assertThat(this.list.listIterator().hasNext(), is(true));
        assertThat(this.list.listIterator().next(), is("Petr"));
        assertThat(this.list.listIterator(1).hasPrevious(), is(true));
        assertThat(this.list.listIterator(1).previous(), is("Petr"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenListIteratorRemoveThenTakeException() {
        this.list.listIterator().remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenListIteratorSetThenTakeException() {
        this.list.listIterator().set("Ivan");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenListIteratorAddThenTakeException() {
        this.list.listIterator().add("Ivan");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenSubListAndAddThenTakeException() {
        List<String> result = this.list.subList(1, 2);
        result.add("Vasilii");
    }
}