package ru.apermyakov.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LoopCheckerTest {

    @Test
    public void i() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.add("Fourth");
        array.findByObject("Fourth").setNext(array.findByObject("First"));
        LoopChecker<String> checker = new LoopChecker<>(array);
        assertThat(checker.hasCycle(), is(true));
        assertThat(checker.hasCycle(array.getFirst()), is(true));
    }

    @Test
    public void y() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.add("Fourth");
        array.findByObject("First").setPrevious(array.findByObject("Fourth"));
        LoopChecker<String> checker = new LoopChecker<>(array);
        assertThat(checker.hasCycle(), is(true));
        assertThat(checker.hasCycle(array.getLast()), is(true));
    }

    @Test
    public void k() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.add("Fourth");
        array.findByObject("Third").setNext(array.findByObject("Second"));
        LoopChecker<String> checker = new LoopChecker<>(array);
        assertThat(checker.hasCycle(), is(true));
        assertThat(checker.hasCycle(array.getFirst()), is(false));
        assertThat(checker.hasCycle(array.findByObject("Second")), is(true));
        assertThat(checker.hasCycle(array.findByObject("Third")), is(true));
        assertThat(checker.hasCycle(array.getLast()), is(false));

    }

    @Test
    public void b() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.add("Fourth");
        array.findByObject("Second").setPrevious(array.findByObject("Third"));
        LoopChecker<String> checker = new LoopChecker<>(array);
        assertThat(checker.hasCycle(), is(true));
        assertThat(checker.hasCycle(array.getFirst()), is(false));
        assertThat(checker.hasCycle(array.findByObject("Second")), is(true));
        assertThat(checker.hasCycle(array.findByObject("Third")), is(true));
        assertThat(checker.hasCycle(array.getLast()), is(false));
    }

    @Test
    public void q() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("First");
        array.add("Second");
        array.add("Third");
        array.add("Fourth");
        LoopChecker<String> checker = new LoopChecker<>(array);
        assertThat(checker.hasCycle(array.findByObject("Third")), is(false));
        assertThat(checker.hasCycle(), is(false));
    }

    @Test
    public void eq() {
        SimpleLinkedArray<String> array = new SimpleLinkedArray<>();
        array.add("First");
        LoopChecker<String> checker = new LoopChecker<>(array);
        assertThat(checker.hasCycle(), is(false));
    }
}