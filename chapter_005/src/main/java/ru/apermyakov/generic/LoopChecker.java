package ru.apermyakov.generic;

public class LoopChecker<T> {

    private SimpleLinkedArray<T> container;

    public LoopChecker(SimpleLinkedArray<T> container) {
        this.container = container;
    }

    private boolean checkItem(Item<T> item, boolean increment) {
        int links = 0;
        int numberOfSteps = 0;

        Item<T> counter = increment ? container.getFirst() : container.getLast();
        Item<T> step = increment ? counter.getNext() : counter.getPrevious();

            for (; counter != null && numberOfSteps < container.getSize() * 2; counter = step) {
                if (links < 2) {
                    if (step != null && step.equals(item)) {
                        links++;
                    }
                } else {
                    break;
                }
                numberOfSteps++;
                step = increment ? step.getNext() : step.getPrevious();
            }
        return !(links < 2);
    }

    private boolean checkAll(boolean increment) {
        int links = 0;
        Item<T> step = increment ? container.getFirst() : container.getLast();
        while (links < container.getSize()) {
            step = increment ? step.getNext() : step.getPrevious();
            links++;
        }
        return step == null;
    }

    public boolean hasCycle(Item<T> item) {
        return checkItem(item, true) || checkItem(item, false);
    }

    public boolean hasCycle() {
        return !checkAll(true) || !checkAll(false);
    }
}
