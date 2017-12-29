package ru.apermyakov.testtask;

import java.io.File;
import java.io.FileFilter;

/**
 * Class for modulate filter by name.
 *
 * @author apermyakov
 * @version 1.0
 * @since 29.12.2017
 */
public class FilterByName implements FileFilter {

    /**
     * Field for base filter.
     */
    private final BaseFilter filter;

    /**
     * Design filter by name.
     *
     * @param filter base filter
     */
    public FilterByName(BaseFilter filter) {
        this.filter = filter;
    }

    /**
     * Method for compare file name and condition (name).
     *
     * @param pathname file
     * @return compare or not
     */
    @Override
    public boolean accept(File pathname) {
        return this.filter.accept(pathname);
    }
}
