package ru.apermyakov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test convert list to array and bask.
 *
 * @author apermyakov
 * @version 1.0
 * @since 23.10.2017
 */
public class ConvertListTest {

    /**
     * Test when convert check array to list.
     */
    @Test
    public void whenConvertArrayToListThenAssert() {
        int[][] check = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ConvertList convert = new ConvertList();
        List<Integer> result = convert.toList(check);
        List<Integer> expect = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            expect.add(i);
        }
        assertThat(result, is(expect));
    }

    /**
     * Test when convert check array with null to list.
     */
    @Test
    public void whenConvertArrayWithNullToListThenAssert() {
        int[][] check = new int[][] {{1, 2, 3}, null, {7, 8, 9}};
        ConvertList convert = new ConvertList();
        List<Integer> result = convert.toList(check);
        List<Integer> expect = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            expect.add(i);
        }
        expect.add(null);
        for (int i = 7; i <= 9; i++) {
            expect.add(i);
        }
        assertThat(result, is(expect));
    }

    /**
     * Test when convert check list to array with 3 rows.
     */
    @Test
    public void whenConvertCheckListToArrayWith3RowsThenAssert() {
        List<Integer> check = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            check.add(i);
        }
        int[][] expect = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        ConvertList convert = new ConvertList();
        int[][] result = convert.toArray(check, 3);
        assertThat(result, is(expect));
    }

    /**
     * Test when convert check list with null to array with 3 rows.
     */
    @Test
    public void whenConvertCheckListWithNullToArrayWith3RowsThenAssert() {
        List<Integer> check = new ArrayList<>();
        check.add(1);
        check.add(2);
        check.add(null);
        check.add(3);
        check.add(4);
        int[][] expect = new int[][]{{1, 2, 0}, {3, 4, 0}, {0, 0, 0}};
        ConvertList convert = new ConvertList();
        int[][] result = convert.toArray(check, 3);
        assertThat(result, is(expect));
    }

    /**
     * Test when convert check list to array with 4 rows.
     */
    @Test
    public void whenConvertCheckListToArrayWith4RowsTHenAssert() {
        List<Integer> check = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            check.add(i);
        }
        int[][] expect = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 0, 0}, {0, 0, 0, 0}};
        ConvertList convert = new ConvertList();
        int[][] result = convert.toArray(check, 4);
        assertThat(result, is(expect));
    }

    /**
     * Test when convert check list to one list.
     */
    @Test
    public void whenConvertCheckListOfArraysThenTakeOneList() {
        List<int[]> check = new ArrayList<>();
        check.add(new int[]{25, 35, 45});
        check.add(new int[]{6, 7, 8, 9, 10});
        List<Integer> expect = new ArrayList<>();
        expect.add(25);
        expect.add(35);
        expect.add(45);
        for (int i = 6; i < 11; i++) {
            expect.add(i);
        }
        ConvertList convert = new ConvertList();
        List<Integer> result = convert.convert(check);
        assertThat(result, is(expect));
    }

    /**
     * Test when convert check list with null to one list.
     */
    @Test
    public void whenConvertCheckListWithNullOfArraysThenTakeOneList() {
        List<int[]> check = new ArrayList<>();
        check.add(new int[]{25, 35, 45});
        check.add(null);
        check.add(new int[]{6, 7, 8, 9, 10});
        List<Integer> expect = new ArrayList<>();
        expect.add(25);
        expect.add(35);
        expect.add(45);
        expect.add(null);
        for (int i = 6; i < 11; i++) {
            expect.add(i);
        }
        ConvertList convert = new ConvertList();
        List<Integer> result = convert.convert(check);
        assertThat(result, is(expect));
    }

}
