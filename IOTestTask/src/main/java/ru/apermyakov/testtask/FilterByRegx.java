package ru.apermyakov.testtask;

import java.io.File;
import java.io.FileFilter;

/**
 * Class for modulate filter by regular expression.
 *
 * @author apermyakov
 * @version 1.0
 * @since 29.12.2017
 */
public class FilterByRegx implements FileFilter {

    /**
     * Field for base filter.
     */
    private final BaseFilter filter;

    /**
     * Design filter by regular expression.
     *
     * @param filter filter
     */
    public FilterByRegx(BaseFilter filter) {
        this.filter = filter;
    }

    /**
     * Method for compare file name and condition (regx).
     *
     * @param pathname file
     * @return compare or not
     */
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory() || filter.getNameByFile(pathname).matches(this.filter.getCheckedCond());
    }
}
